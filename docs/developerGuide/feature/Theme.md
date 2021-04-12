### [Feature] Themes

The customization of themes is a feature within the application, which allows users to select their preferred theme
as well as store their theme preferences for subsequent use.

#### Implementation

The theme selection is primarily enabled by the `Theme` enumeration.
The selection of themes can be triggered via three alternative mechanisms:

1. Upon application start up.

2. Via the specific theme selection from within the menu bar.

   ![](images/themeMenuScreenshot.png)

3. Via the singular `theme` command which toggles between themes.

   ![](images/themeCliScreenshot.png)

The `setTheme` method within `MainWindow` is directly responsible for modifying the look-and-feel of the user interface,
achieved by replacing the stylesheets associated with the requested theme. This method is indirectly exposed to the user
in the GUI as menu options, in the form of the respective `setTheme[THEME_NAME]` event handlers.
This is also the process by which the theme is initialized upon application start up.

The implementation of the theme change command is a little trickier, since the command itself does not directly
interface with the UI. The process by which message passing of the theme change is described below:

1. The user executes the `theme` command, which first retrieves the currently applied theme via the `Model`
   interface. More specifically, this theme is retrieved directly from the application's `GuiSettings` object, which
   is exposed via the `Model#getGuiSettings` method.
   
2. The `theme` command returns a `CommandResult` object that stores the desired `Theme` enumeration. This object is
   passed via the `Logic` interface back into `MainWindow#executeCommand`. which in turn processes the `Theme`
   via the `setTheme` method as specified above. Both steps 1 and 2 are illustrated below:

   ![](images/ThemeDiagramLogicExecute.png)

3. The `Theme` encapsulated in the `CommandResult` is then passed into `setTheme`.
   A replacement `GuiSettings` object is created by `Logic` using the updated theme,
   and stored for subsequent retrieval (in step 1). This is shown below:
   
   ![](images/ThemeDiagramSetTheme.png)

Notably, since `GuiSettings` is a common resource shared between the `Logic` and `Model` objects, this allows
`ToggleThemeCommand` and `MainWindow` to access `GuiSettings` from different contexts without
breaking the interface abstraction barrier.

The following sequence diagram summarizes the `theme` command execution:

![](images/ThemeDiagram.png)

#### Design considerations:

##### Saving of user preferences

When interfacing with the application, users typically decide on a single preferred theme and solely use
that theme throughout the bulk of application usage. To facilitate ease of use, the theme selected by the user is
automatically saved as part of the application GUI preferences, which will be loaded upon subsequent application
start up. This responsibility is offloaded to `GuiSettings` and its respective storage handler.

##### Different methods of changing theme

In a typical GUI, the activation of specific themes via the menu bar is desirable, since users do not
need to repeatedly call the same command to switch between themes. This is especially so for GUIs with a
relatively larger number of themes. The second method via the command line is primarily to fulfill the
typing accessibility requirement.

This however does have the associated disadvantage of requiring a corresponding `setTheme[THEME_NAME]` command
for every theme made available through the menu bar. For situations where the number of themes is exceedingly large,
a possible design decision is to filter selected themes to be added to the GUI, while hiding the rest within the
`theme` toggle command.

##### Extensibility of themes

The `theme` functionality is designed to be modular, so that new themes can be easily added.
To add a new theme, adhere to the following steps:

1. Create a new `[THEME_NAME]Theme.css` file under the `src/main/resources/view/` directory,
   and populate it with the desired styling. If more styling rules are desired, feel free to separate them into
   multiple CSS files in the same directory.
   
2. Add a new `Theme` enumeration value, and
   return the list of CSS files associated with the enumeration under the `Theme#getStyleSheets` method.
   
3. To enable selection of the new theme using `theme` command, add a new entry for the theme in
   `ToggleThemeCommand#execute` found within the `logic.commands` package.
   
4. To enable selection of theme via menu bar, in `MainWindow` from the `ui` package,
   define a new `setTheme[THEME_NAME]` FXML event
   handler calling `MainWindow#setTheme` method with this new enumeration. Additionally, in the `MainWindow` FXML itself,
   define a new `MenuItem` with the text `[THEME_NAME]` and register the aforementioned event handler.

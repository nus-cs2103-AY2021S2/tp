package seedu.us.among.ui;

import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.us.among.commons.core.GuiSettings;
import seedu.us.among.commons.core.LogsCenter;
import seedu.us.among.commons.util.StringUtil;
import seedu.us.among.logic.Logic;
import seedu.us.among.logic.commands.CommandResult;
import seedu.us.among.logic.commands.exceptions.CommandException;
import seedu.us.among.logic.endpoint.exceptions.AbortRequestException;
import seedu.us.among.logic.endpoint.exceptions.RequestException;
import seedu.us.among.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";
    private static final String WELCOME_MESSAGE = "Welcome to imPoster!\n"
            + "Not sure where to begin?\n"
            + "Simply type in \"help\" to get more information.\n";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private EndpointListPanel endpointListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    private String applicationTheme;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane endpointListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        endpointListPanel = new EndpointListPanel(logic.getFilteredEndpointList());
        endpointListPanelPlaceholder.getChildren().add(endpointListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());
        resultDisplay.setFeedbackToUser(WELCOME_MESSAGE); // welcome message

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getEndpointListFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand, resultDisplay, primaryStage);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
        String theme = guiSettings.getApplicationTheme();
        this.applicationTheme = theme;
        updateTheme(theme);
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY(), this.applicationTheme);
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> primaryStage.hide());
        } else {
            primaryStage.hide();
        }
    }

    public EndpointListPanel getEndpointListPanel() {
        return endpointListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException,
            RequestException, AbortRequestException {
        resultDisplay.setFeedbackToUser("");
        resultDisplay.getLoadingSpinnerPlaceholder().setVisible(true);
        try {
            CommandResult commandResult = logic.execute(commandText);
            resultDisplay.getLoadingSpinnerPlaceholder().setVisible(false);
            // to-do special: if you want to see the commands output comment this back in.
            logger.info("Result: " + commandResult.getFeedbackToUser());
            if (commandResult.isApiResponse()) {
                resultDisplay.setApiFeedbackToUser(commandResult.getFeedbackToUser(), commandResult.getEndpoint());
            } else {
                resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            }

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.getToggleTheme() != null) {
                updateTheme(commandResult.getToggleTheme());
            }

            return commandResult;
        } catch (AbortRequestException e) {
            //stop loading spinner (if any)
            resultDisplay.getLoadingSpinnerPlaceholder().setVisible(false);
            logger.info("Execution failed: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        } catch (CommandException | ParseException | RequestException e) {
            //stop loading spinner (if any)
            resultDisplay.getLoadingSpinnerPlaceholder().setVisible(false);

            //play error message
            resultDisplay.getErrorGifTimeline().play();

            logger.info("Execution failed: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        } finally {
            System.out.println(Thread.activeCount());
        }
    }

    /**
     * Updates theme for the application.
     *
     * @param theme theme to update with
     */
    public void updateTheme(String theme) {
        for (ThemeType e : ThemeType.values()) {
            if (!e.name().equalsIgnoreCase(theme)) {
                getRoot().getScene().getStylesheets().remove(getThemeFilePath(e.name()));
            } else {
                getRoot().getScene().getStylesheets().add(getThemeFilePath(theme));
                this.applicationTheme = theme;
            }
        }
    }

    /**
     * Gets the file path for the theme.
     *
     * @param theme theme to get file path for
     */
    public String getThemeFilePath(String theme) {
        return "view/" + StringUtil.toTitleCase(theme) + "Theme.css";
    }
}

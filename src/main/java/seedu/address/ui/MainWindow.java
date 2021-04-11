package seedu.address.ui;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_UI_PROJECT_NOT_DISPLAYED;
import static seedu.address.commons.core.Messages.MESSAGE_WELCOME;
import static seedu.address.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.uicommands.UiCommand;
import seedu.address.logic.uicommands.exceptions.UiCommandException;
import seedu.address.model.project.Project;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {
    public static final String CONTACT_LIST_PANEL_ID = "contactListPanel";
    public static final String PROJECT_PANEL_ID = "projectDisplayPanel";
    public static final String TODAY_PANEL_ID = "todayPanel";
    public static final String HELP_PANEL_ID = "helpPanel";

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private ContactListPanel contactListPanel;
    private SidePanel sidePanel;
    private ResultDisplay resultDisplay;
    private HelpPanel helpPanel;
    private TodayPanel todayPanel;
    private ProjectDisplayPanel projectDisplayPanel;

    @FXML
    private StackPane commandBoxPlaceholder;
    @FXML
    private MenuItem helpMenuItem;
    @FXML
    private StackPane sidePanelPlaceholder;
    @FXML
    private StackPane resultDisplayPlaceholder;
    @FXML
    private StackPane statusbarPlaceholder;
    @FXML
    private StackPane infoDisplayPlaceholder;

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

        helpPanel = new HelpPanel(this);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     *
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
        sidePanel = new SidePanel(logic.getFilteredProjectsList(), this);
        sidePanelPlaceholder.getChildren().add(sidePanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getColabFolderFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
        setFeedbackToUser(MESSAGE_WELCOME);

        projectDisplayPanel = new ProjectDisplayPanel();
        projectDisplayPanel.setMainWindow(this);
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
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());

            if (commandResult.hasUiCommand()) {
                executeUiCommand(commandResult.getUiCommand());
            }

            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            logic.commitState(commandResult);

            return commandResult;
        } catch (CommandException | UiCommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            setFeedbackToUser(e.getMessage());
            throw new CommandException(e.getMessage(), e);
        }
    }

    private void executeUiCommand(UiCommand uiCommand) throws UiCommandException {
        uiCommand.execute(this);
    }

    /**
     * Displays a message in the {@code resultDisplay}.
     *
     * @param message The message to display.
     */
    public void setFeedbackToUser(String message) {
        resultDisplay.setFeedbackToUser(message);
    }

    // Methods that change the UI

    /**
     * Closes the application.
     */
    public void closeApplication() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        primaryStage.hide();
    }

    /**
     * Displays the help panel.
     */
    public void displayHelp() {
        sidePanel.clearButtonStyles();
        sidePanel.clearSelection();
        if (!infoDisplayPlaceholder.getChildren().contains(helpPanel.getRoot())) {
            infoDisplayPlaceholder.getChildren().clear();
            infoDisplayPlaceholder.getChildren().add(helpPanel.getRoot());
            helpPanel.getRoot().setId(HELP_PANEL_ID);
        }
    }

    /**
     * Displays a project
     *
     * @param project Project to display.
     */
    public void displayProject(Project project) {
        requireNonNull(project);

        if (!infoDisplayPlaceholder.getChildren().contains(projectDisplayPanel.getRoot())) {
            infoDisplayPlaceholder.getChildren().clear();
            infoDisplayPlaceholder.getChildren().add(projectDisplayPanel.getRoot());
            projectDisplayPanel.getRoot().setId(PROJECT_PANEL_ID);
        }

        projectDisplayPanel.displayProject(project);
    }

    /**
     * Shows contacts tab.
     */
    public void displayContacts() {
        sidePanel.clearButtonStyles();
        sidePanel.addContactButtonStyle();
        contactListPanel = new ContactListPanel(logic.getFilteredContactList());
        contactListPanel.getRoot().setId(CONTACT_LIST_PANEL_ID);
        infoDisplayPlaceholder.getChildren().clear();
        infoDisplayPlaceholder.getChildren().add(contactListPanel.getRoot());
        sidePanel.clearSelection();
    }

    /**
     * Resets the contact list to show all contacts.
     */
    public void resetContactsList() {
        logic.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
    }

    /**
     * Shows today tab.
     */
    public void displayToday() {
        sidePanel.clearButtonStyles();
        sidePanel.addTodayButtonStyle();
        todayPanel = new TodayPanel(logic.getColabFolder(), LocalDate.now());
        infoDisplayPlaceholder.getChildren().clear();
        infoDisplayPlaceholder.getChildren().add(todayPanel.getRoot());
        todayPanel.getRoot().setId(TODAY_PANEL_ID);
        sidePanel.clearSelection();
    }

    /**
     * Shows overview tab.
     */
    public void displayOverviewTab() throws UiCommandException {
        if (!infoDisplayPlaceholder.getChildren().contains(projectDisplayPanel.getRoot())) {
            throw new UiCommandException(MESSAGE_UI_PROJECT_NOT_DISPLAYED);
        }

        projectDisplayPanel.showOverviewTab();
    }

    /**
     * Shows todos tab.
     */
    public void displayTodosTab() throws UiCommandException {
        if (!infoDisplayPlaceholder.getChildren().contains(projectDisplayPanel.getRoot())) {
            throw new UiCommandException(MESSAGE_UI_PROJECT_NOT_DISPLAYED);
        }

        projectDisplayPanel.showTodosTab();
    }

    /**
     * Selects a project in the {@code ListView} at a specific index.
     *
     * @param index Index to select.
     */
    public void selectProject(Index index) {
        sidePanel.selectProject(index);
    }

    /**
     * Clears side panel button styles.
     */
    public void clearButtonStyles() {
        sidePanel.clearButtonStyles();
    }

    // UI Handlers when button is clicked

    @FXML
    private void handleExit() {
        closeApplication();
    }

    @FXML
    private void handleHelp() {
        resultDisplay.setFeedbackToUser(SHOWING_HELP_MESSAGE);
        displayHelp();
    }
}

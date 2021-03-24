package seedu.address.ui;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_UI_PROJECT_NOT_DISPLAYED;

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

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private SidePanel sidePanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
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

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        projectDisplayPanel = new ProjectDisplayPanel();
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
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.hasUiCommand()) {
                executeUiCommand(commandResult.getUiCommand());
            }

            return commandResult;
        } catch (UiCommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw new CommandException(e.getMessage(), e);
        }
    }

    private void executeUiCommand(UiCommand uiCommand) throws UiCommandException {
        uiCommand.execute(this);
    }

    // Methods that change the UI

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    public void openHelpPanel() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    /**
     * Closes the application.
     */
    public void closeApplication() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
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
        }

        projectDisplayPanel.displayProject(project);
    }

    /**
     * Shows contacts tab.
     */
    public void displayContacts() {
        if (!infoDisplayPlaceholder.getChildren().contains(personListPanel.getRoot())) {
            infoDisplayPlaceholder.getChildren().clear();
            infoDisplayPlaceholder.getChildren().add(personListPanel.getRoot());
        }

        sidePanel.clearSelection();
    }

    /**
     * Shows today tab.
     */
    public void displayToday() {
        todayPanel = new TodayPanel(logic.getProjectsFolder(), LocalDate.now());
        infoDisplayPlaceholder.getChildren().clear();
        infoDisplayPlaceholder.getChildren().add(todayPanel.getRoot());
        sidePanel.clearSelection();
    }

    /**
     * Shows overview tab.
     */
    public void displayOverviewTab() throws UiCommandException {
        try {
            projectDisplayPanel.showOverviewTab();
        } catch (NullPointerException e) {
            throw new UiCommandException(MESSAGE_UI_PROJECT_NOT_DISPLAYED, e);
        }
    }

    /**
     * Shows todos tab.
     */
    public void displayTodosTab() throws UiCommandException {
        try {
            projectDisplayPanel.showTodosTab();
        } catch (NullPointerException e) {
            throw new UiCommandException(MESSAGE_UI_PROJECT_NOT_DISPLAYED, e);
        }
    }

    /**
     * Selects a project in the {@code ListView} at a specific index.
     *
     * @param index Index to select.
     */
    public void selectProject(Index index) {
        sidePanel.selectProject(index);
    }

    // UI Handlers when button is clicked

    @FXML
    private void handleExit() {
        closeApplication();
    }

    @FXML
    private void handleHelp() {
        openHelpPanel();
    }
}

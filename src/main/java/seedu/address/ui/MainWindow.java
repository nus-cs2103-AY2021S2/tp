package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;
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
import seedu.address.logic.Logic;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.medical.Appointment;
import seedu.address.model.medical.MedicalRecord;
import seedu.address.model.person.Patient;

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
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private List<EditorWindow> editorWindows;
    private ViewPatientBox viewPatientBox;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private StackPane viewPatientBoxPlaceholder;

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
        editorWindows = new ArrayList<>();
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
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeTextCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        ViewPatientBox viewPatientBox = new ViewPatientBox();
        viewPatientBoxPlaceholder.getChildren().add(viewPatientBox.getRoot());
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

    /**
     * Displays the patientViewBox which contains information about patient
     */
    @FXML
    public void handlePatientViewBox(Patient p) {
        ViewPatientBox viewPatientBox = new ViewPatientBox(p);
        viewPatientBoxPlaceholder.getChildren().clear();
        viewPatientBoxPlaceholder.getChildren().add(viewPatientBox.getRoot());
    }

    /**
     * Updates the patientViewBox to show a list of appointments
     */
    @FXML
    public void handleAppointmentsList(List<Appointment> appointments) {
        ViewPatientBox viewPatientBox = new ViewPatientBox();
        String allAppointments = String.format("You have %d upcoming appointments: \n\n", appointments.size());
        for (Appointment appt : appointments) {
            allAppointments += appt + "\n";
        }
        viewPatientBox.setText(allAppointments);
        viewPatientBoxPlaceholder.getChildren().clear();
        viewPatientBoxPlaceholder.getChildren().add(viewPatientBox.getRoot());
    }

    /**
     * Updates the patientViewBox to show a display a message
     */
    @FXML
    public void handleDisplayMessage(String message) {
        ViewPatientBox viewPatientBox = new ViewPatientBox();
        viewPatientBox.setText(message);
        viewPatientBoxPlaceholder.getChildren().clear();
        viewPatientBoxPlaceholder.getChildren().add(viewPatientBox.getRoot());
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleEdit(Patient patient, MedicalRecord medicalRecord) {
        EditorWindow editorWindow = new EditorWindow(this::executeCommand, patient, medicalRecord);
        editorWindows.add(editorWindow);
        if (!editorWindow.isShowing()) {
            editorWindow.show();
        } else {
            editorWindow.focus();
        }
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeTextCommand(String commandText) throws CommandException, ParseException {
        CommandResult commandResult;
        try {
            commandResult = logic.execute(commandText);
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
        processResult(commandResult);
        return commandResult;
    }

    private CommandResult executeCommand(Command command) throws CommandException {
        CommandResult commandResult;
        try {
            commandResult = logic.execute(command);
        } catch (CommandException e) {
            logger.info("Invalid command: " + command.toString());
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
        processResult(commandResult);
        return commandResult;
    }

    private void processResult(CommandResult commandResult) {
        logger.info("Result: " + commandResult.getFeedbackToUser());
        resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
        if (commandResult.isShowHelp()) {
            handleHelp();
        }

        if (commandResult.isShowEdit()) {
            handleEdit(commandResult.getPatient(), commandResult.getMedicalRecord());
        }

        if (commandResult.isShowViewBox()) {
            handlePatientViewBox(commandResult.getPatient());
        }

        if (commandResult.showAppointments()) {
            handleAppointmentsList(commandResult.getAppointments());
        }

        if (commandResult.isDisplayMessage()) {
            handleDisplayMessage(commandResult.getDisplayMessage());
        }

        if (commandResult.isExit()) {
            handleExit();
        }
    }
}

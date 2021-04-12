package seedu.address.ui;

import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
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
import seedu.address.ui.budgetpanel.BudgetListPanel;
import seedu.address.ui.reminderpanel.ReminderListPanel;
import seedu.address.ui.schedulepanel.ScheduleListPanel;
import seedu.address.ui.timetablepanel.TimeTableWindow;

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
    private ResultBarFooter resultDisplay;
    private HelpWindow helpWindow;
    private TimeTableWindow timetableWindow;
    private TutorListPanel tutorListPanel;
    private CalendarView calendarView;
    private AppointmentListPanel appointmentListPanel;
    private GradeListPanel gradeListPanel;
    private ReminderListPanel reminderListPanel;
    private FiltersPanel filtersPanel;
    private ScheduleListPanel scheduleListPanel;
    private BudgetListPanel budgetListPanel;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane filtersPanelPlaceholder;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private StackPane calendarViewPane;

    @FXML
    private StackPane appointmentListPanelPlaceholder;

    @FXML
    private StackPane gradeListPanelPlaceholder;

    @FXML
    private StackPane scheduleListPanelPlaceholder;

    @FXML
    private StackPane reminderListPanelPlaceholder;

    @FXML
    private StackPane budgetPanelPlaceholder;

    @FXML
    private TabPane tabPanePlaceHolder;

    @FXML
    private TabPane tabSidePanePlaceHolder;

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
        timetableWindow = new TimeTableWindow(logic.getFilteredEventList());

        tutorListPanel = new TutorListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(tutorListPanel.getRoot());

        appointmentListPanel = new AppointmentListPanel(logic.getFilteredAppointmentList());
        appointmentListPanelPlaceholder.getChildren().add(appointmentListPanel.getRoot());

        budgetListPanel = new BudgetListPanel(logic.getBudgetList());
        budgetPanelPlaceholder.getChildren().add(budgetListPanel.getRoot());

        scheduleListPanel = new ScheduleListPanel(logic.getFilteredScheduleList());
        scheduleListPanelPlaceholder.getChildren().add(scheduleListPanel.getRoot());

        /* Grade List */
        gradeListPanel = new GradeListPanel(logic.getFilteredGradeList());
        gradeListPanelPlaceholder.getChildren().add(gradeListPanel.getRoot());

        reminderListPanel = new ReminderListPanel(logic.getFilteredReminderList());
        reminderListPanelPlaceholder.getChildren().add(reminderListPanel.getRoot());

        resultDisplay = new ResultBarFooter();
        statusbarPlaceholder.getChildren().add(resultDisplay.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        calendarView = new CalendarView(this::executeCommand);
        calendarViewPane.getChildren().add(calendarView.getRoot());

        filtersPanel = new FiltersPanel();
        filtersPanelPlaceholder.getChildren().add(filtersPanel.getRoot());
        filtersPanel.fillInnerParts(logic.getPersonFilterStringList(),
                logic.getAppointmentFilterStringList());

        setTabsWidth(tabPanePlaceHolder);
        setTabsWidth(tabSidePanePlaceHolder);
    }

    //@@author Mantas Visockis-reused
    //Reused from https://stackoverflow.com/questions/31051756/javafx-tab-fit-full-size-of-header
    //with minor modifications
    /**
     * Sets the width programmatically according to the window's size and number of tabs.
     * @param tabPane TabPane to be adjusted
     */
    private void setTabsWidth(TabPane tabPane) {
        assert tabPane != null;
        tabPane.widthProperty().addListener((observable, oldValue, newWidth) -> {
            int numTabs = tabPane.getTabs().size();
            tabPane.setTabMinWidth(newWidth.doubleValue() / numTabs - (20));
            tabPane.setTabMaxWidth(newWidth.doubleValue() / numTabs - (20));
        });
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
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleTimetable() {
        LocalDate queryDate = logic.getTimeTableDate();
        if (!timetableWindow.isShowing()) {
            timetableWindow.show(queryDate);
        } else {
            timetableWindow.reconstruct(queryDate);
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
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.getTabName().isPresent()) {
                Command.TabName tabName = commandResult.getTabName().get();
                switch (tabName) {
                case APPOINTMENT:
                    tabPanePlaceHolder.getSelectionModel().select(0);
                    break;
                case SCHEDULE:
                    tabPanePlaceHolder.getSelectionModel().select(1);
                    break;
                case BUDGET:
                    tabSidePanePlaceHolder.getSelectionModel().select(2);
                    break;
                case GRADE:
                    tabSidePanePlaceHolder.getSelectionModel().select(1);
                    break;
                case REMINDER:
                    tabSidePanePlaceHolder.getSelectionModel().select(0);
                    break;
                default:
                    break;
                }
            }

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isShowTimetable()) {
                handleTimetable();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}

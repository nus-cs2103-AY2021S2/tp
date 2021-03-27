package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.ui.CommandBox.CommandExecutor;

public class MainPanel extends UiPart<Region> {

    private static final String FXML = "MainPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());
    private final CommandExecutor commandExecutor;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private TutorListPanel tutorListPanel;
    private CalendarView calendarView;
    private AppointmentListPanel appointmentListPanel;

    @FXML
    private StackPane personListPanelPlaceholder;
    @FXML
    private StackPane calendarViewPane;
    @FXML
    private StackPane appointmentListPanelPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainPanel(Logic logic, CommandExecutor commandExecutor) {
        super(FXML);
        this.logic = logic;
        this.commandExecutor = commandExecutor;
        fillInnerParts();
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        tutorListPanel = new TutorListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(tutorListPanel.getRoot());

        appointmentListPanel = new AppointmentListPanel(logic.getFilteredAppointmentList());
        appointmentListPanelPlaceholder.getChildren().add(appointmentListPanel.getRoot());

        calendarView = new CalendarView(commandExecutor);
        calendarViewPane.getChildren().add(calendarView.getRoot());
    }

    @FXML
    private void setCalendarNavigation(String direction) throws CommandException {
        if (direction.equals("next")) {
            calendarView.handleToNext();
        } else if (direction.equals("prev")) {
            calendarView.handleToPrev();
        } else {
            throw new CommandException("MESSAGE_UNKNOWN_COMMAND");
        }
    }

    public TutorListPanel getPersonListPanel() {
        return tutorListPanel;
    }
}

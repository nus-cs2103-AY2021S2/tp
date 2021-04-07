package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.TutorTrackerParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyTutorBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.budget.Budget;
import seedu.address.model.event.Event;
import seedu.address.model.grade.Grade;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.tutor.Tutor;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final TutorTrackerParser tutorTrackerParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        tutorTrackerParser = new TutorTrackerParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = tutorTrackerParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveTutorBook(model.getTutorBook());
            storage.saveAppointmentBook(model.getAppointmentBook());
            storage.saveBudgetBook(model.getBudgetBook());
            storage.saveGradeBook(model.getGradeBook());
            storage.saveScheduleTracker(model.getScheduleTracker());
            storage.saveReminderTracker(model.getReminderTracker());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyTutorBook getTutorBook() {
        return model.getTutorBook();
    }

    @Override
    public ObservableList<Tutor> getFilteredPersonList() {
        return model.getFilteredTutorList();
    }

    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return model.getFilteredAppointmentList();
    }

    @Override
    public ObservableList<Event> getFilteredEventList() {
        return model.getFilteredEventList();
    }

    @Override
    public ObservableList<Grade> getFilteredGradeList() {
        return model.getFilteredGradeList();
    }

    @Override
    public ObservableList<Schedule> getFilteredScheduleList() {
        return model.getFilteredScheduleList();
    }

    @Override
    public ObservableList<String> getPersonFilterStringList() {
        return model.getTutorFilterStringList();
    }

    @Override
    public ObservableList<String> getAppointmentFilterStringList() {
        return model.getAppointmentFilterStringList();
    }

    @Override
    public ObservableList<Reminder> getFilteredReminderList() {
        return model.getFilteredReminderList();
    }

    @Override
    public ObservableList<Budget> getBudgetList() {
        return model.getBudgetList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getTutorBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public LocalDate getTimeTableDate() {
        return model.getTimeTableDate();
    }
}

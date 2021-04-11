package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.MeetBuddyParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.ReadOnlyMeetingBook;
import seedu.address.model.note.Note;
import seedu.address.model.note.ReadOnlyNoteBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.ReadOnlyAddressBook;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final MeetBuddyParser meetBuddyParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        meetBuddyParser = new MeetBuddyParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = meetBuddyParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getAddressBook());
            storage.saveMeetingBook(model.getMeetingBook());
            //storage.saveNoteBook(model.getNoteBook());
            storage.saveConnection(model.getPersonMeetingConnection());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ReadOnlyMeetingBook getMeetingBook() {
        return model.getMeetingBook();
    }

    @Override
    public ObservableList<Meeting> getFilteredMeetingList() {
        return model.getFilteredMeetingList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    //======================================Notebook methods ============================================
    @Override
    public ReadOnlyNoteBook getNoteBook() {
        return model.getNoteBook();
    }

    @Override
    public ObservableList<Note> getFilteredNoteList() {
        return model.getFilteredNoteList();
    }

    @Override
    public Path getNoteBookFilePath() {
        return model.getNoteBookFilePath();
    }


    //======================================Timetable UI methods ============================================

    @Override
    public ObservableList<Meeting> getAllMeetingList() {
        return model.getUnmodifiableMeetingList();
    }
    @Override
    public ObservableValue<LocalDate> getTimeTableStartDate() {
        return model.getReadOnlyTimetableStartDate();
    }
}


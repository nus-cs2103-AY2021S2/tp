package seedu.iscam.logic;

import static seedu.iscam.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.iscam.commons.core.GuiSettings;
import seedu.iscam.commons.core.LogsCenter;
import seedu.iscam.logic.commands.Command;
import seedu.iscam.logic.commands.CommandResult;
import seedu.iscam.logic.commands.exceptions.CommandException;
import seedu.iscam.logic.parser.BookParser;
import seedu.iscam.logic.parser.MeetingBookParser;
import seedu.iscam.logic.parser.clientcommands.ClientBookParser;
import seedu.iscam.logic.parser.exceptions.ParseException;
import seedu.iscam.logic.parser.exceptions.ParseFormatException;
import seedu.iscam.logic.parser.exceptions.ParseIndexException;
import seedu.iscam.model.Model;
import seedu.iscam.model.client.Client;
import seedu.iscam.model.meeting.Meeting;
import seedu.iscam.model.util.clientbook.ObservableClient;
import seedu.iscam.model.util.clientbook.ReadOnlyClientBook;
import seedu.iscam.model.util.meetingbook.ObservableMeeting;
import seedu.iscam.model.util.meetingbook.ReadOnlyMeetingBook;
import seedu.iscam.storage.Storage;


/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;

    private final List<BookParser> bookParsers;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;

        this.bookParsers = new ArrayList<>();
        bookParsers.add(new ClientBookParser());
        bookParsers.add(new MeetingBookParser());
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        Command command = null;
        CommandResult commandResult = null;

        // Execute the first command that is successfully parsed
        for (BookParser parser : bookParsers) {
            try {
                command = parser.parseCommand(commandText);
            } catch (ParseFormatException | ParseIndexException e) {
                throw e;
            } catch (ParseException e) {
                continue;
            }

            commandResult = command.execute(model);
            break;
        }

        if (command == null) {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }

        try {
            storage.saveClientBook(model.getClientBook());
            storage.saveMeetingBook(model.getMeetingBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);

        }

        return commandResult;
    }

    @Override
    public ReadOnlyClientBook getClientBook() {
        return model.getClientBook();
    }

    @Override
    public ReadOnlyMeetingBook getMeetingBook() {
        return model.getMeetingBook();
    }

    @Override
    public ObservableList<Client> getFilteredClientList() {
        return model.getFilteredClientList();
    }

    @Override
    public ObservableList<Meeting> getFilteredMeetingList() {
        return model.getFilteredMeetingList();
    }

    @Override
    public ObservableClient getDetailedClient() {
        return model.getDetailedClient();
    }

    @Override
    public Path getClientBookFilePath() {
        return model.getClientBookFilePath();
    }

    @Override
    public ObservableMeeting getDetailedMeeting() {
        return model.getDetailedMeeting();
    }

    @Override
    public Path getMeetingBookFilePath() {
        return model.getMeetingBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}

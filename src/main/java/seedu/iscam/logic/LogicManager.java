package seedu.iscam.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import seedu.iscam.commons.core.GuiSettings;
import seedu.iscam.commons.core.LogsCenter;
import seedu.iscam.logic.commands.Command;
import seedu.iscam.logic.commands.CommandResult;
import seedu.iscam.logic.commands.exceptions.CommandException;
import seedu.iscam.logic.parser.ClientBookParser;
import seedu.iscam.logic.parser.exceptions.ParseException;
import seedu.iscam.model.Model;
import seedu.iscam.model.ObservableClient;
import seedu.iscam.model.ReadOnlyClientBook;
import seedu.iscam.model.client.Client;
import seedu.iscam.model.meeting.Meeting;
import seedu.iscam.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ClientBookParser clientBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        clientBookParser = new ClientBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = clientBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveClientBook(model.getClientBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyClientBook getAddressBook() {
        return model.getClientBook();
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
    public ObservableValue<Boolean> getIsClientMode() {
        return model.getIsClientMode();
    }

    @Override
    public ObservableClient getDetailedClient() {
        return model.getDetailedClient();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getClientBookFilePath();
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

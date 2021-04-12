package seedu.ta.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.ta.commons.core.GuiSettings;
import seedu.ta.commons.core.LogsCenter;
import seedu.ta.logic.commands.Command;
import seedu.ta.logic.commands.CommandResult;
import seedu.ta.logic.commands.exceptions.CommandException;
import seedu.ta.logic.parser.TeachingAssistantParser;
import seedu.ta.logic.parser.exceptions.ParseException;
import seedu.ta.model.Model;
import seedu.ta.model.ReadOnlyTeachingAssistant;
import seedu.ta.model.contact.Contact;
import seedu.ta.model.entry.Entry;
import seedu.ta.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {

    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final TeachingAssistantParser teachingAssistantParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        teachingAssistantParser = new TeachingAssistantParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = teachingAssistantParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveTeachingAssistant(model.getTeachingAssistant());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyTeachingAssistant getTeachingAssistant() {
        return model.getTeachingAssistant();
    }

    @Override
    public ObservableList<Contact> getFilteredContactList() {
        return model.getFilteredContactList();
    }

    @Override
    public ObservableList<Entry> getFilteredEntryList() {
        return model.getFilteredEntryList();
    }

    @Override
    public Path getTeachingAssistantFilePath() {
        return model.getTeachingAssistantFilePath();
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

package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ColabParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyColabFolder;
import seedu.address.model.contact.Contact;
import seedu.address.model.project.Project;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ColabParser colabParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        colabParser = new ColabParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = colabParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveColabFolder(model.getColabFolder());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyColabFolder getColabFolder() {
        return model.getColabFolder();
    }

    @Override
    public ObservableList<Contact> getFilteredContactList() {
        return model.getFilteredContactList();
    }

    @Override
    public Path getColabFolderFilePath() {
        return model.getColabFolderFilePath();
    }

    @Override
    public ObservableList<Project> getFilteredProjectsList() {
        return model.getFilteredProjectList();
    }

    @Override
    public void updateFilteredContactList(Predicate<Contact> predicate) {
        model.updateFilteredContactList(predicate);
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
    public void commitState(CommandResult commandResult) {
        if (!commandResult.isIgnoreHistory()) {
            model.commitState(commandResult);
        }
    }
}

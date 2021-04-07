package seedu.taskify.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.taskify.commons.core.GuiSettings;
import seedu.taskify.commons.core.LogsCenter;
import seedu.taskify.logic.commands.Command;
import seedu.taskify.logic.commands.CommandResult;
import seedu.taskify.logic.commands.exceptions.CommandException;
import seedu.taskify.logic.parser.TaskifyParser;
import seedu.taskify.logic.parser.exceptions.ParseException;
import seedu.taskify.model.Model;
import seedu.taskify.model.ReadOnlyTaskify;
import seedu.taskify.model.task.Task;
import seedu.taskify.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final TaskifyParser taskifyParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        taskifyParser = new TaskifyParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = taskifyParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getAddressBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyTaskify getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return model.getFilteredTaskList();
    }

    @Override
    public ObservableList<Task> getExpiredFilteredTaskList() {
        return model.getExpiredFilteredTaskList();
    }

    @Override
    public ObservableList<Task> getCompletedFilteredTaskList() {
        return model.getCompletedFilteredTaskList();
    }

    @Override
    public ObservableList<Task> getTodaysFilteredTaskList() {
        return model.getTodaysFilteredTaskList();
    }

    @Override
    public ObservableList<Task> getUncompletedFilteredTaskList() {
        return model.getUncompletedFilteredTaskList();
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
}

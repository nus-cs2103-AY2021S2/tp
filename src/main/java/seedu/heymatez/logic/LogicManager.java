package seedu.heymatez.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.heymatez.commons.core.GuiSettings;
import seedu.heymatez.commons.core.LogsCenter;
import seedu.heymatez.logic.commands.Command;
import seedu.heymatez.logic.commands.CommandResult;
import seedu.heymatez.logic.commands.exceptions.CommandException;
import seedu.heymatez.logic.parser.HeyMatezParser;
import seedu.heymatez.logic.parser.exceptions.ParseException;
import seedu.heymatez.model.Model;
import seedu.heymatez.model.ReadOnlyHeyMatez;
import seedu.heymatez.model.person.Person;
import seedu.heymatez.model.task.Task;
import seedu.heymatez.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final HeyMatezParser heyMatezParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        heyMatezParser = new HeyMatezParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = heyMatezParser.parseCommand(commandText);
        commandResult = command.execute(model);
        try {
            storage.saveHeyMatez(model.getHeyMatez());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyHeyMatez getHeyMatez() {
        return model.getHeyMatez();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return model.getFilteredTaskList();
    }

    @Override
    public Path getHeyMatezFilePath() {
        return model.getHeyMatezFilePath();
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

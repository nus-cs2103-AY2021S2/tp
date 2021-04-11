package seedu.flashback.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.flashback.commons.core.GuiSettings;
import seedu.flashback.commons.core.LogsCenter;
import seedu.flashback.logic.commands.Command;
import seedu.flashback.logic.commands.CommandResult;
import seedu.flashback.logic.commands.exceptions.CommandException;
import seedu.flashback.logic.parser.FlashBackParser;
import seedu.flashback.logic.parser.exceptions.ParseException;
import seedu.flashback.model.Model;
import seedu.flashback.model.ReadOnlyFlashBack;
import seedu.flashback.model.flashcard.Flashcard;
import seedu.flashback.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final FlashBackParser flashBackParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        flashBackParser = new FlashBackParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        CommandResult commandResult;
        flashBackParser.setModel(model);
        Command command = flashBackParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveFlashBack(model.getFlashBack());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public CommandResult execute(Command command) throws CommandException {
        CommandResult commandResult = command.execute(model);

        try {
            storage.saveFlashBack(model.getFlashBack());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyFlashBack getFlashBack() {
        return model.getFlashBack();
    }

    @Override
    public ObservableList<Flashcard> getFilteredFlashcardList() {
        return model.getFilteredFlashcardList();
    }

    @Override
    public Path getFlashBackFilePath() {
        return model.getFlashBackFilePath();
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

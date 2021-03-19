package fooddiary.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import fooddiary.commons.core.GuiSettings;
import fooddiary.commons.core.LogsCenter;
import fooddiary.logic.commands.Command;
import fooddiary.logic.commands.CommandResult;
import fooddiary.logic.commands.exceptions.CommandException;
import fooddiary.logic.parser.FoodDiaryParser;
import fooddiary.logic.parser.exceptions.ParseException;
import fooddiary.model.Model;
import fooddiary.model.ReadOnlyFoodDiary;
import fooddiary.model.entry.Entry;
import fooddiary.storage.Storage;
import javafx.collections.ObservableList;


/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final FoodDiaryParser foodDiaryParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        foodDiaryParser = new FoodDiaryParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = foodDiaryParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveFoodDiary(model.getFoodDiary());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyFoodDiary getFoodDiary() {
        return model.getFoodDiary();
    }

    @Override
    public ObservableList<Entry> getFilteredEntryList() {
        return model.getFilteredEntryList();
    }

    @Override
    public Path getFoodDiaryFilePath() {
        return model.getFoodDiaryFilePath();
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

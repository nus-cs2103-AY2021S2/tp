package seedu.weeblingo.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.weeblingo.commons.core.GuiSettings;
import seedu.weeblingo.commons.core.LogsCenter;
import seedu.weeblingo.logic.commands.Command;
import seedu.weeblingo.logic.commands.CommandResult;
import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.logic.parser.WeeblingoParser;
import seedu.weeblingo.logic.parser.exceptions.ParseException;
import seedu.weeblingo.model.Mode;
import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.ReadOnlyFlashcardBook;
import seedu.weeblingo.model.flashcard.Flashcard;
import seedu.weeblingo.model.score.Score;
import seedu.weeblingo.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";

    // Indicates that flashcards are given as a list and not individual questions
    public static final int LIST_INDEX = -1;

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final WeeblingoParser weeblingoParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        weeblingoParser = new WeeblingoParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {

        //logging, safe to ignore
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        //Parse user input from String to a Command
        Command command = weeblingoParser.parseCommand(commandText);
        //Executes the Command and stores the result
        commandResult = command.execute(model);

        try {
            // Whenever a command is successfully executed, we will store all the content of FlashcardBook.
            storage.saveFlashcardBook(model.getFlashcardBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyFlashcardBook getFlashcardBook() {
        return model.getFlashcardBook();
    }

    @Override
    public ObservableList<Flashcard> getFilteredFlashcardList() {
        return model.getFilteredFlashcardList();
    }

    @Override
    public ObservableList<Score> getFilteredScoreHistoryList() {
        return model.getFilteredScoreHistory();
    }


    // Gets current index of quiz question if a quiz session has started. Otherwise return LIST_INDEX.
    @Override
    public int getCurrentIndex() {
        if (getCurrentMode() == Mode.MODE_QUIZ_SESSION || getCurrentMode() == Mode.MODE_CHECK_SUCCESS) {
            int currentIndex = model.getCurrentIndex();
            assert currentIndex != LIST_INDEX;
            return currentIndex;
        } else {
            return LIST_INDEX;
        }
    };

    @Override
    public Path getFlashcardBookFilePath() {
        return model.getFlashcardBookFilePath();
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
    public Model getModel() {
        return this.model;
    }

    @Override
    public int getCurrentMode() {
        return this.model.getCurrentMode();
    }

    @Override
    public boolean isPanelVisible() {
        if (getCurrentMode() == Mode.MODE_MENU) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isAnswerVisible() {
        int mode = getCurrentMode();
        if (mode == Mode.MODE_CHECK_SUCCESS || mode == Mode.MODE_LEARN || mode == Mode.MODE_QUIZ_SESSION_ENDED) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isShowingHistory() {
        return getCurrentMode() == Mode.MODE_HISTORY;
    }
}

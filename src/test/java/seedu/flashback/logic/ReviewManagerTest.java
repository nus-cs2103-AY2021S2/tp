package seedu.flashback.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashback.testutil.TypicalFlashcards.getTypicalFlashBack;
import static seedu.flashback.testutil.TypicalFlashcards.getTypicalFlashcardsShuffle;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.flashback.commons.core.GuiSettings;
import seedu.flashback.logic.commands.Command;
import seedu.flashback.logic.commands.CommandResult;
import seedu.flashback.logic.commands.exceptions.CommandException;
import seedu.flashback.logic.parser.exceptions.ParseException;
import seedu.flashback.model.Model;
import seedu.flashback.model.ModelManager;
import seedu.flashback.model.ReadOnlyFlashBack;
import seedu.flashback.model.UserPrefs;
import seedu.flashback.model.flashcard.Flashcard;

public class ReviewManagerTest {
    private Model model;
    private ReviewManager manager;
    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFlashBack(), new UserPrefs());
        manager = new ReviewManager(new LogicStub(model));
    }
    @Test
    public void execute_getFirstFlashcard_success() {
        Flashcard expectedFlashcard = getTypicalFlashcardsShuffle().get(0);
        assertEquals(expectedFlashcard, manager.getCurrentFlashcard());
    }
    @Test
    public void execute_getSecondFlashcard_success() {
        Flashcard expectedFlashcard = getTypicalFlashcardsShuffle().get(1);
        manager.incrementCurrentIndex();
        assertEquals(expectedFlashcard, manager.getCurrentFlashcard());
    }
    @Test
    public void execute_getPreviousFlashcardFromFirstCard_throwsIndexOutOfBoundException() {
        manager.decrementCurrentIndex();
        assertThrows(IndexOutOfBoundsException.class, () -> manager.getCurrentFlashcard());
    }
    @Test
    public void execute_hasPreviousFlashcardFromFirstCard_fail() {
        assertFalse(manager.hasPreviousFlashcard());
    }
    @Test
    public void execute_hasPreviousFlashcardFromSecondCard_success() {
        manager.incrementCurrentIndex();
        assertTrue(manager.hasPreviousFlashcard());
    }
    @Test
    public void execute_hasNextFlashcardFromFirstCard_success() {
        assertTrue(manager.hasNextFlashcard());
    }
    @Test
    public void execute_hasNextFlashcardFromLastCard_fail() {
        for (int i = 0; i < 7; i++) {
            manager.incrementCurrentIndex();
        }
        assertEquals(7, manager.getFlashcardDeckSize());
        assertFalse(manager.hasNextFlashcard());
    }
    @Test
    public void execute_getCurrentIndex_success() {
        assertEquals(0, manager.getCurrentIndex());
        manager.incrementCurrentIndex();
        assertEquals(1, manager.getCurrentIndex());
        manager.decrementCurrentIndex();
        assertEquals(0, manager.getCurrentIndex());
    }
    /**
     * A default stub that have all of the methods return null,
     * except for {@code getFilteredFlashcardList()} method that returns the current filtered flashcard list
     * from the model.
     */
    private class LogicStub implements Logic {
        private Model model;
        LogicStub(Model model) {
            this.model = model;
        }
        @Override
        public CommandResult execute(String commandText) throws CommandException, ParseException {
            return null;
        }

        @Override
        public CommandResult execute(Command command) throws CommandException {
            return null;
        }

        @Override
        public ReadOnlyFlashBack getFlashBack() {
            return null;
        }

        @Override
        public ObservableList<Flashcard> getFilteredFlashcardList() {
            return model.getFilteredFlashcardList();
        }

        @Override
        public Path getFlashBackFilePath() {
            return null;
        }

        @Override
        public GuiSettings getGuiSettings() {
            return null;
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {

        }
    }
}

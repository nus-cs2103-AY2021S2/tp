package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showFlashcardAtIndex;
import static seedu.address.testutil.TypicalFlashcards.getTypicalFlashBack;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_FLASHCARD;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.flashcard.Flashcard;

public class UndoCommandTest {

    @Test
    public void execute_undoAddCommandTest_success() {
        Model model = new ModelManager(getTypicalFlashBack(), new UserPrefs());
        Flashcard firstCard = model.getFilteredFlashcardList().get(0);
        ObservableList<Flashcard> originalList = model.getFilteredFlashcardList();
        Object[] originalArray = originalList.toArray();
        model.deleteFlashcard(firstCard);
        model.commitFlashBack();
        UndoCommand command = new UndoCommand();
        try {
            CommandResult result = command.execute(model);
            assertArrayEquals(model.getFilteredFlashcardList().toArray(), originalList.toArray());
        } catch (CommandException ex) {
            throw new AssertionError("Execution of command should not fail.", ex);
        }

    }

    @Test
    public void execute_undoEditCommandTest_success() {
        Model model = new ModelManager(getTypicalFlashBack(), new UserPrefs());
        Flashcard firstCard = model.getFilteredFlashcardList().get(0);
        ObservableList<Flashcard> originalList = model.getFilteredFlashcardList();
        Object[] originalArray = originalList.toArray();
        model.commitFlashBack();
        UndoCommand command = new UndoCommand();
        try {
            CommandResult result = command.execute(model);
            assertArrayEquals(model.getFilteredFlashcardList().toArray(), originalList.toArray());
        } catch (CommandException ex) {
            throw new AssertionError("Execution of command should not fail.", ex);
        }

    }
}

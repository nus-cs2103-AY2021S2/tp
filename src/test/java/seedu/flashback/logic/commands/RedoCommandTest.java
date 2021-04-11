package seedu.flashback.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.flashback.logic.commands.CommandTestUtil.*;
import static seedu.flashback.testutil.TypicalFlashcards.getTypicalFlashBack;
import static seedu.flashback.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.flashback.logic.commands.exceptions.CommandException;
import seedu.flashback.model.FlashBack;
import seedu.flashback.model.Model;
import seedu.flashback.model.ModelManager;
import seedu.flashback.model.UserPrefs;
import seedu.flashback.model.flashcard.Flashcard;
import seedu.flashback.testutil.FlashcardBuilder;

public class RedoCommandTest {
    private Model model;

    @BeforeEach
    public void setup() {
        model = new ModelManager(getTypicalFlashBack(), new UserPrefs());
    }

    @Test
    public void execute_redoUndoDeleteCommandTest_success() {
        Model expectedModel = new ModelManager(model.getFlashBack(), new UserPrefs());
        Flashcard firstCard = model.getFilteredFlashcardList().get(0);

        model.deleteFlashcard(firstCard);
        model.commitFlashBack();
        model.undoFlashBack();

        expectedModel.deleteFlashcard(firstCard);
        RedoCommand command = new RedoCommand();
        try {
            CommandResult result = command.execute(model);
            assertArrayEquals(model.getFilteredFlashcardList().toArray(),
                    expectedModel.getFilteredFlashcardList().toArray());
        } catch (CommandException ex) {
            throw new AssertionError("Execution of command should not fail.", ex);
        }

    }

    @Test
    public void execute_redoUndoEditCommandTest_success() {
        Model expectedModel = new ModelManager(model.getFlashBack(), new UserPrefs());
        Flashcard flashcardInFilteredList = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        Flashcard editedFlashcard = new FlashcardBuilder(flashcardInFilteredList)
                .withQuestion(VALID_QUESTION_OCTOPUS).build();

        model.setFlashcard(model.getFilteredFlashcardList().get(0), editedFlashcard);
        model.commitFlashBack();
        model.undoFlashBack();

        expectedModel.setFlashcard(expectedModel.getFilteredFlashcardList().get(0), editedFlashcard);
        expectedModel.commitFlashBack();

        RedoCommand command = new RedoCommand();
        try {
            CommandResult result = command.execute(model);
            assertArrayEquals(model.getFilteredFlashcardList().toArray(),
                    expectedModel.getFilteredFlashcardList().toArray());
        } catch (CommandException ex) {
            throw new AssertionError("Execution of command should not fail.", ex);
        }
    }

    @Test
    public void execute_redoUndoAddCommand_success() {
        Model expectedModel = new ModelManager(model.getFlashBack(), new UserPrefs());
        Flashcard bob = new FlashcardBuilder().withQuestion("Bob").build();

        model.addFlashcard(bob);
        model.commitFlashBack();
        model.undoFlashBack();

        expectedModel.addFlashcard(bob);
        expectedModel.commitFlashBack();

        RedoCommand command = new RedoCommand();
        try {
            CommandResult result = command.execute(model);
            assertArrayEquals(model.getFilteredFlashcardList().toArray(),
                    expectedModel.getFilteredFlashcardList().toArray());
        } catch (CommandException ex) {
            throw new AssertionError("Execution of command should not fail.", ex);
        }
    }

    @Test
    public void execute_redoUndoClearCommand_success() {
        Model expectedModel = new ModelManager(model.getFlashBack(), new UserPrefs());

        model.setFlashBack(new FlashBack());
        model.commitFlashBack();
        model.undoFlashBack();

        expectedModel.setFlashBack(new FlashBack());
        expectedModel.commitFlashBack();

        RedoCommand command = new RedoCommand();
        try {
            CommandResult result = command.execute(model);
            assertArrayEquals(model.getFilteredFlashcardList().toArray(),
                    expectedModel.getFilteredFlashcardList().toArray());
        } catch (CommandException ex) {
            throw new AssertionError("Execution of command should not fail.", ex);
        }
    }

    @Test
    public void execute_redoCommand_failure() {
        assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_FAILURE);
    }
}

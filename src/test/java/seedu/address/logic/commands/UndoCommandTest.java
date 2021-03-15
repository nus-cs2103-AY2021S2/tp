package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalFlashcards.getTypicalFlashBack;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.testutil.FlashcardBuilder;

public class UndoCommandTest {
    private Model model;

    @BeforeEach
    public void setup() {
        model = new ModelManager(getTypicalFlashBack(), new UserPrefs());
    }

    @Test
    public void execute_undoAddCommandTest_success() {
        Flashcard firstCard = model.getFilteredFlashcardList().get(0);
        ObservableList<Flashcard> originalList = model.getFilteredFlashcardList();
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
        Flashcard flashcardInFilteredList = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        Flashcard editedFlashcard = new FlashcardBuilder(flashcardInFilteredList)
                .withQuestion(VALID_QUESTION_OCTOPUS).build();

        ObservableList<Flashcard> originalList = model.getFilteredFlashcardList();
        model.setFlashcard(model.getFilteredFlashcardList().get(0), editedFlashcard);
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
    public void execute_undoAddCommand_success() {
        ObservableList<Flashcard> originalList = model.getFilteredFlashcardList();

        Flashcard bob = new FlashcardBuilder().withQuestion("Bob").build();
        model.addFlashcard(bob);
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
    public void execute_undoCommand_failure() {
        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_FAILURE);
    }
}

package seedu.weeblingo.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.weeblingo.commons.core.Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAGS_SET_DIFFICULT;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAGS_SET_EASY;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAGS_SET_EASY_AND_DIFFICULT;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAGS_SET_HIRAGANA;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAG_DIFFICULT;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAG_EASY;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAG_HIRAGANA;
import static seedu.weeblingo.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.weeblingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.weeblingo.logic.commands.CommandTestUtil.showFlashcardAtIndex;
import static seedu.weeblingo.logic.commands.DeleteTagCommand.MESSAGE_NO_TAGS_TO_DELETE;
import static seedu.weeblingo.logic.commands.DeleteTagCommand.MESSAGE_TAG_DOES_NOT_EXIST;
import static seedu.weeblingo.testutil.TypicalFlashcards.getTypicalFlashcardBook;
import static seedu.weeblingo.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static seedu.weeblingo.testutil.TypicalIndexes.INDEX_SECOND_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.weeblingo.commons.core.index.Index;
import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.FlashcardBook;
import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.ModelManager;
import seedu.weeblingo.model.UserPrefs;
import seedu.weeblingo.model.flashcard.Flashcard;
import seedu.weeblingo.testutil.FlashcardBuilder;

public class DeleteCommandTest {

    @Test
    public void execute_delete_success() throws CommandException {
        Index targetIndex = INDEX_FIRST_FLASHCARD;

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(targetIndex, VALID_TAGS_SET_EASY);

        String expectedMessage = DeleteTagCommand.MESSAGE_SUCCESS;

        Flashcard taggedFlashcard = new FlashcardBuilder().withUserTags(VALID_TAG_EASY).build();

        ModelManager modelManager = new ModelManager(getTypicalFlashcardBook(), new UserPrefs());
        modelManager.switchModeLearn();
        Model model = modelManager;

        ModelManager expectedModelManager = new ModelManager(
                new FlashcardBook(model.getFlashcardBook()), new UserPrefs());
        expectedModelManager.switchModeLearn();
        Model expectedModel = expectedModelManager;

        model.setFlashcard(model.getFilteredFlashcardList().get(0), taggedFlashcard);

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteMultipleTag_success() throws CommandException {
        Index targetIndex = INDEX_FIRST_FLASHCARD;

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(targetIndex, VALID_TAGS_SET_EASY_AND_DIFFICULT);

        String expectedMessage = DeleteTagCommand.MESSAGE_SUCCESS;

        Flashcard taggedFlashcard = new FlashcardBuilder().withUserTags(VALID_TAG_EASY, VALID_TAG_DIFFICULT).build();

        ModelManager modelManager = new ModelManager(getTypicalFlashcardBook(), new UserPrefs());
        modelManager.switchModeLearn();
        Model model = modelManager;

        ModelManager expectedModelManager = new ModelManager(
                new FlashcardBook(model.getFlashcardBook()), new UserPrefs());
        expectedModelManager.switchModeLearn();
        Model expectedModel = expectedModelManager;

        model.setFlashcard(model.getFilteredFlashcardList().get(0), taggedFlashcard);

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteDefaultTag_failure() throws CommandException {
        Index targetIndex = INDEX_FIRST_FLASHCARD;

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(targetIndex, VALID_TAGS_SET_HIRAGANA);

        Flashcard taggedFlashcard = new FlashcardBuilder().withTags(VALID_TAG_HIRAGANA)
                .withUserTags(VALID_TAG_EASY).build();

        ModelManager modelManager = new ModelManager(getTypicalFlashcardBook(), new UserPrefs());
        modelManager.switchModeLearn();
        modelManager.setFlashcard(modelManager.getFilteredFlashcardList().get(0), taggedFlashcard);
        Model model = modelManager;

        assertCommandFailure(deleteTagCommand, model, MESSAGE_TAG_DOES_NOT_EXIST);
    }

    @Test
    public void execute_deleteNonExistentTag_failure() throws CommandException {
        Index targetIndex = INDEX_FIRST_FLASHCARD;

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(targetIndex, VALID_TAGS_SET_DIFFICULT);

        Flashcard taggedFlashcard = new FlashcardBuilder().withTags(VALID_TAG_HIRAGANA)
                .withUserTags(VALID_TAG_EASY).build();

        ModelManager modelManager = new ModelManager(getTypicalFlashcardBook(), new UserPrefs());
        modelManager.switchModeLearn();
        modelManager.setFlashcard(modelManager.getFilteredFlashcardList().get(0), taggedFlashcard);
        Model model = modelManager;

        assertCommandFailure(deleteTagCommand, model, MESSAGE_TAG_DOES_NOT_EXIST);
    }

    @Test
    public void execute_deleteWithNoTags_failure() throws CommandException {
        Index targetIndex = INDEX_FIRST_FLASHCARD;

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(targetIndex, VALID_TAGS_SET_EASY);

        Flashcard taggedFlashcard = new FlashcardBuilder().build();

        ModelManager modelManager = new ModelManager(getTypicalFlashcardBook(), new UserPrefs());
        modelManager.switchModeLearn();
        modelManager.setFlashcard(modelManager.getFilteredFlashcardList().get(0), taggedFlashcard);
        Model model = modelManager;

        assertCommandFailure(deleteTagCommand, model, MESSAGE_NO_TAGS_TO_DELETE);
    }

    @Test
    public void execute_invalidFlashcardIndex_failure() throws CommandException {
        ModelManager modelManager = new ModelManager(getTypicalFlashcardBook(), new UserPrefs());
        modelManager.switchModeLearn();
        Model model = modelManager;

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFlashcardList().size() + 1);

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(outOfBoundIndex, VALID_TAGS_SET_EASY);

        assertCommandFailure(deleteTagCommand, model, MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    /**
     * Delete a tag of a flashcard in a filtered list where index is larger than size of filtered list,
     * but smaller than size of flashcard book.
     */
    @Test
    public void execute_invalidFlashcardIndexFilteredList_failure() throws CommandException {
        Index targetIndex = INDEX_FIRST_FLASHCARD;
        Index outOfBoundIndex = INDEX_SECOND_FLASHCARD;

        ModelManager modelManager = new ModelManager(getTypicalFlashcardBook(), new UserPrefs());
        modelManager.switchModeLearn();
        Model model = modelManager;
        showFlashcardAtIndex(model, targetIndex);

        assertTrue(outOfBoundIndex.getZeroBased() < model.getFlashcardBook().getFlashcardList().size());

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(outOfBoundIndex, VALID_TAGS_SET_EASY);

        assertCommandFailure(deleteTagCommand, model, MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final DeleteTagCommand standardCommand = new DeleteTagCommand(INDEX_FIRST_FLASHCARD, VALID_TAGS_SET_EASY);

        // same values will return true
        DeleteTagCommand commandWithSameValues = new DeleteTagCommand(INDEX_FIRST_FLASHCARD, VALID_TAGS_SET_EASY);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object will return true
        assertTrue(standardCommand.equals(standardCommand));

        // null will return false
        assertFalse(standardCommand.equals(null));

        // different types will return false
        assertFalse(standardCommand.equals(new HelpCommand()));

        // different index will return false
        assertFalse(standardCommand.equals(new DeleteTagCommand(INDEX_SECOND_FLASHCARD, VALID_TAGS_SET_EASY)));

        // different tag will return false
        assertFalse(standardCommand.equals(new DeleteTagCommand(INDEX_FIRST_FLASHCARD, VALID_TAGS_SET_DIFFICULT)));
    }

}

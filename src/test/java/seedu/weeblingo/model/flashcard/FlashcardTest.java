package seedu.weeblingo.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_ANSWER_I;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_QUESTION_I;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAG_DIFFICULT;
import static seedu.weeblingo.testutil.Assert.assertThrows;
import static seedu.weeblingo.testutil.TypicalFlashcards.A_CARD;
import static seedu.weeblingo.testutil.TypicalFlashcards.I_CARD;

import org.junit.jupiter.api.Test;

import seedu.weeblingo.testutil.FlashcardBuilder;

public class FlashcardTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Flashcard flashcard = new FlashcardBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> flashcard.getWeeblingoTags().remove(0));
    }

    @Test
    public void isSameFlashcard() {
        // same object -> returns true
        assertTrue(A_CARD.isSameFlashcard(A_CARD));

        // null -> returns false
        assertFalse(A_CARD.isSameFlashcard(null));

        // different question, all other attributes same -> returns false
        Flashcard editedFlashcardQuestion = new FlashcardBuilder(A_CARD).withQuestion(VALID_QUESTION_I).build();
        assertFalse(A_CARD.isSameFlashcard(editedFlashcardQuestion));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Flashcard aCardCopy = new FlashcardBuilder(A_CARD).build();
        assertTrue(A_CARD.equals(aCardCopy));

        // same object -> returns true
        assertTrue(A_CARD.equals(A_CARD));

        // null -> returns false
        assertFalse(A_CARD.equals(null));

        // different type -> returns false
        assertFalse(A_CARD.equals(5));

        // different flashcard -> returns false
        assertFalse(A_CARD.equals(I_CARD));

        // different question -> returns false
        Flashcard editedFlashcardA = new FlashcardBuilder(A_CARD).withQuestion(VALID_QUESTION_I).build();
        assertFalse(A_CARD.equals(editedFlashcardA));

        // different answer -> returns false
        editedFlashcardA = new FlashcardBuilder(A_CARD).withAnswer(VALID_ANSWER_I).build();
        assertFalse(A_CARD.equals(editedFlashcardA));

        // different default tags -> returns false
        editedFlashcardA = new FlashcardBuilder(A_CARD).withTags(VALID_TAG_DIFFICULT).build();
        assertFalse(A_CARD.equals(editedFlashcardA));

        // different user tags -> returns false
        editedFlashcardA = new FlashcardBuilder(A_CARD).withUserTags(VALID_TAG_DIFFICULT).build();
        assertFalse(A_CARD.equals(editedFlashcardA));
    }
}

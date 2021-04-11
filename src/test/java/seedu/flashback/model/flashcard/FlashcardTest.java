package seedu.flashback.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashback.logic.commands.CommandTestUtil.VALID_ANSWER_OCTOPUS;
import static seedu.flashback.logic.commands.CommandTestUtil.VALID_CATEGORY_OCTOPUS;
import static seedu.flashback.logic.commands.CommandTestUtil.VALID_PRIORITY_OCTOPUS;
import static seedu.flashback.logic.commands.CommandTestUtil.VALID_QUESTION_OCTOPUS;
import static seedu.flashback.logic.commands.CommandTestUtil.VALID_TAG_EQUATION;
import static seedu.flashback.testutil.Assert.assertThrows;
import static seedu.flashback.testutil.TypicalFlashcards.AT;
import static seedu.flashback.testutil.TypicalFlashcards.PYTHAGOREAN;

import org.junit.jupiter.api.Test;

import seedu.flashback.testutil.FlashcardBuilder;

public class FlashcardTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Flashcard flashcard = new FlashcardBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> flashcard.getTags().remove(0));
    }

    @Test
    public void isSameFlashcard() {
        // same object -> returns true
        assertTrue(PYTHAGOREAN.isSameCard(PYTHAGOREAN));

        // null -> returns false
        assertFalse(PYTHAGOREAN.isSameCard(null));

        // same name, all other attributes different -> returns true
        Flashcard editedAlice = new FlashcardBuilder(PYTHAGOREAN).withAnswer(VALID_ANSWER_OCTOPUS)
                .withCategory(VALID_CATEGORY_OCTOPUS)
                .withPriority(VALID_PRIORITY_OCTOPUS).withTags(VALID_TAG_EQUATION).build();
        assertTrue(PYTHAGOREAN.isSameCard(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new FlashcardBuilder(PYTHAGOREAN).withQuestion(VALID_QUESTION_OCTOPUS).build();
        assertFalse(PYTHAGOREAN.isSameCard(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Flashcard editedBob = new FlashcardBuilder(AT).withQuestion(VALID_QUESTION_OCTOPUS.toLowerCase()).build();
        assertFalse(AT.isSameCard(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_QUESTION_OCTOPUS + " ";
        editedBob = new FlashcardBuilder(AT).withQuestion(nameWithTrailingSpaces).build();
        assertFalse(AT.isSameCard(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Flashcard aliceCopy = new FlashcardBuilder(PYTHAGOREAN).build();
        assertTrue(PYTHAGOREAN.equals(aliceCopy));

        // same object -> returns true
        assertTrue(PYTHAGOREAN.equals(PYTHAGOREAN));

        // null -> returns false
        assertFalse(PYTHAGOREAN.equals(null));

        // different type -> returns false
        assertFalse(PYTHAGOREAN.equals(5));

        // different person -> returns false
        assertFalse(PYTHAGOREAN.equals(AT));

        // different name -> returns false
        Flashcard editedAlice = new FlashcardBuilder(PYTHAGOREAN).withQuestion(VALID_QUESTION_OCTOPUS).build();
        assertFalse(PYTHAGOREAN.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new FlashcardBuilder(PYTHAGOREAN).withAnswer(VALID_ANSWER_OCTOPUS).build();
        assertFalse(PYTHAGOREAN.equals(editedAlice));

        // different email -> returns false
        editedAlice = new FlashcardBuilder(PYTHAGOREAN).withCategory(VALID_CATEGORY_OCTOPUS).build();
        assertFalse(PYTHAGOREAN.equals(editedAlice));

        // different address -> returns false
        editedAlice = new FlashcardBuilder(PYTHAGOREAN).withPriority(VALID_PRIORITY_OCTOPUS).build();
        assertFalse(PYTHAGOREAN.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new FlashcardBuilder(PYTHAGOREAN).withTags(VALID_TAG_EQUATION).build();
        assertFalse(PYTHAGOREAN.equals(editedAlice));
    }
}

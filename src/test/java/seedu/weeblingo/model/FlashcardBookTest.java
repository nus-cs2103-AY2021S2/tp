package seedu.weeblingo.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_ANSWER_A;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_QUESTION_A;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAG_DIFFICULT;
import static seedu.weeblingo.testutil.Assert.assertThrows;
import static seedu.weeblingo.testutil.TypicalFlashcards.A;
import static seedu.weeblingo.testutil.TypicalFlashcards.A_CARD;
import static seedu.weeblingo.testutil.TypicalFlashcards.getTypicalFlashcardBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.weeblingo.model.flashcard.Flashcard;
import seedu.weeblingo.model.flashcard.exceptions.DuplicateFlashcardException;
import seedu.weeblingo.model.score.Score;
import seedu.weeblingo.testutil.FlashcardBuilder;

public class FlashcardBookTest {

    private final FlashcardBook flashcardBook = new FlashcardBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), flashcardBook.getFlashcardList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> flashcardBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        FlashcardBook newData = getTypicalFlashcardBook();
        flashcardBook.resetData(newData);
        assertEquals(newData, flashcardBook);
    }

    @Test
    public void resetData_withDuplicateFlashcards_throwsDuplicateFlashcardException() {
        // Two flashcards with the same identity fields
        Flashcard duplicate = new FlashcardBuilder(A_CARD)
            .withQuestion(VALID_QUESTION_A)
            .withAnswer(VALID_ANSWER_A)
            .withTags(VALID_TAG_DIFFICULT)
            .build();
        List<Flashcard> newFlashcards = Arrays.asList(A, duplicate);
        // The second argument represents an empty score list
        FlashcardBookStub newData = new FlashcardBookStub(newFlashcards, new ArrayList<>());
        assertThrows(DuplicateFlashcardException.class, () -> flashcardBook.resetData(newData));
    }

    @Test
    public void hasFlashcard_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> flashcardBook.hasFlashcard(null));
    }

    @Test
    public void hasFlashcard_flashcardNotInAddressBook_returnsFalse() {
        assertFalse(flashcardBook.hasFlashcard(A_CARD));
    }

    @Test
    public void hasFlashcard_flashcardInAddressBook_returnsTrue() {
        flashcardBook.addFlashcard(A_CARD);
        assertTrue(flashcardBook.hasFlashcard(A));
    }

    @Test
    public void hasFlashcard_flashcardWithSameIdentityFieldsInAddressBook_returnsTrue() {
        flashcardBook.addFlashcard(A_CARD);
        Flashcard editedAlice = new FlashcardBuilder(A)
            .withAnswer(VALID_ANSWER_A)
            .withTags(VALID_TAG_DIFFICULT)
            .build();
        assertTrue(flashcardBook.hasFlashcard(editedAlice));
    }

    @Test
    public void getFlashcardList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> flashcardBook.getFlashcardList().remove(0));
    }

    /**
     * A stub ReadOnlyFlashcardBook whose flashcards list can violate interface constraints.
     */
    private static class FlashcardBookStub implements ReadOnlyFlashcardBook {
        private final ObservableList<Flashcard> flashcards = FXCollections.observableArrayList();
        private final ObservableList<Score> scores = FXCollections.observableArrayList();

        FlashcardBookStub(Collection<Flashcard> flashcards, Collection<Score> scores) {
            this.flashcards.setAll(flashcards);
            this.scores.setAll(scores);
        }

        @Override
        public ObservableList<Flashcard> getFlashcardList() {
            return flashcards;
        }

        @Override
        public ObservableList<Score> getScoreHistoryList() {
            return scores;
        }
    }

}

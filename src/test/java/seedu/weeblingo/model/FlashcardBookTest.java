package seedu.weeblingo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.weeblingo.testutil.Assert.assertThrows;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.weeblingo.model.flashcard.Flashcard;
import seedu.weeblingo.model.score.Score;

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

    //    @Test
    //    public void resetData_withValidReadOnlyAddressBook_replacesData() {
    //        FlashcardBook newData = getTypicalAddressBook();
    //        flashcardBook.resetData(newData);
    //        assertEquals(newData, flashcardBook);
    //    }

    //    @Test
    //    public void resetData_withDuplicateFlashcards_throwsDuplicateFlashcardException() {
    //        // Two flashcards with the same identity fields
    //        Flashcard editedAlice = new FlashcardBuilder(ALICE).withAnswer(VALID_ANSWER_B)
    //        .withTags(VALID_TAG_HUSBAND)
    //                .build();
    //        List<Flashcard> newFlashcards = Arrays.asList(ALICE, editedAlice);
    //        FlashcardBookStub newData = new FlashcardBookStub(newFlashcards);
    //
    //        assertThrows(DuplicateFlashcardException.class, () -> flashcardBook.resetData(newData));
    //    }

    @Test
    public void hasFlashcard_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> flashcardBook.hasFlashcard(null));
    }

    //    @Test
    //    public void hasFlashcard_flashcardNotInAddressBook_returnsFalse() {
    //        assertFalse(flashcardBook.hasFlashcard(ALICE));
    //    }

    //    @Test
    //    public void hasFlashcard_flashcardInAddressBook_returnsTrue() {
    //        flashcardBook.addFlashcard(ALICE);
    //        assertTrue(flashcardBook.hasFlashcard(ALICE));
    //    }

    //    @Test
    //    public void hasFlashcard_flashcardWithSameIdentityFieldsInAddressBook_returnsTrue() {
    //        flashcardBook.addFlashcard(ALICE);
    //        Flashcard editedAlice = new FlashcardBuilder(ALICE).withAnswer(VALID_ANSWER_B)
    //        .withTags(VALID_TAG_HUSBAND)
    //                .build();
    //        assertTrue(flashcardBook.hasFlashcard(editedAlice));
    //    }

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

package seedu.flashback.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashback.logic.commands.CommandTestUtil.VALID_PRIORITY_OCTOPUS;
import static seedu.flashback.logic.commands.CommandTestUtil.VALID_TAG_EQUATION;
import static seedu.flashback.testutil.Assert.assertThrows;
import static seedu.flashback.testutil.TypicalFlashcards.PYTHAGOREAN;
import static seedu.flashback.testutil.TypicalFlashcards.getTypicalFlashBack;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.flashback.model.flashcard.Flashcard;
import seedu.flashback.model.flashcard.exceptions.DuplicateFlashcardException;
import seedu.flashback.testutil.FlashcardBuilder;

public class FlashBackTest {

    private final FlashBack flashBack = new FlashBack();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), flashBack.getCardList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> flashBack.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyFlashBack_replacesData() {
        FlashBack newData = getTypicalFlashBack();
        flashBack.resetData(newData);
        assertEquals(newData, flashBack);
    }

    @Test
    public void resetData_withDuplicateFlashcards_throwsDuplicateFlashcardException() {
        // Two persons with the same identity fields
        Flashcard editedAlice = new FlashcardBuilder(PYTHAGOREAN)
                .withPriority(VALID_PRIORITY_OCTOPUS).withTags(VALID_TAG_EQUATION).build();
        List<Flashcard> newFlashcards = Arrays.asList(PYTHAGOREAN, editedAlice);
        FlashBackStub newData = new FlashBackStub(newFlashcards);

        assertThrows(DuplicateFlashcardException.class, () -> flashBack.resetData(newData));
    }

    @Test
    public void hasFlashcard_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> flashBack.hasCard(null));
    }

    @Test
    public void hasFlashcard_flashcardNotInFlashBack_returnsFalse() {
        assertFalse(flashBack.hasCard(PYTHAGOREAN));
    }

    @Test
    public void hasFlashcard_flashcardInFlashBack_returnsTrue() {
        flashBack.addCard(PYTHAGOREAN);
        assertTrue(flashBack.hasCard(PYTHAGOREAN));
    }

    @Test
    public void hasFlashcard_flashcardWithSameIdentityFieldsInFlashBack_returnsTrue() {
        flashBack.addCard(PYTHAGOREAN);
        Flashcard editedAlice = new FlashcardBuilder(PYTHAGOREAN)
                .withPriority(VALID_PRIORITY_OCTOPUS).withTags(VALID_TAG_EQUATION).build();
        assertTrue(flashBack.hasCard(editedAlice));
    }

    @Test
    public void getFlashcardList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> flashBack.getCardList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class FlashBackStub implements ReadOnlyFlashBack {
        private final ObservableList<Flashcard> flashcards = FXCollections.observableArrayList();

        FlashBackStub(Collection<Flashcard> flashcards) {
            this.flashcards.setAll(flashcards);
        }

        @Override
        public ObservableList<Flashcard> getCardList() {
            return flashcards;
        }
    }

}

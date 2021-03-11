package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_OCTOPUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_EQUATION;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFlashcards.PYTHAGOREAN;
import static seedu.address.testutil.TypicalFlashcards.getTypicalFlashBack;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.model.flashcard.exceptions.DuplicateFlashcardException;
import seedu.address.testutil.FlashcardBuilder;

public class FlashBackTest {

    private final FlashBack flashBack = new FlashBack();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), flashBack.getFlashcardList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> flashBack.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        FlashBack newData = getTypicalFlashBack();
        flashBack.resetData(newData);
        assertEquals(newData, flashBack);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Flashcard editedAlice = new FlashcardBuilder(PYTHAGOREAN)
                .withPriority(VALID_PRIORITY_OCTOPUS).withTags(VALID_TAG_EQUATION).build();
        List<Flashcard> newFlashcards = Arrays.asList(PYTHAGOREAN, editedAlice);
        FlashBackStub newData = new FlashBackStub(newFlashcards);

        assertThrows(DuplicateFlashcardException.class, () -> flashBack.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> flashBack.hasFlashcard(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(flashBack.hasFlashcard(PYTHAGOREAN));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        flashBack.addFlashcard(PYTHAGOREAN);
        assertTrue(flashBack.hasFlashcard(PYTHAGOREAN));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        flashBack.addFlashcard(PYTHAGOREAN);
        Flashcard editedAlice = new FlashcardBuilder(PYTHAGOREAN)
                .withPriority(VALID_PRIORITY_OCTOPUS).withTags(VALID_TAG_EQUATION).build();
        assertTrue(flashBack.hasFlashcard(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> flashBack.getFlashcardList().remove(0));
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
        public ObservableList<Flashcard> getFlashcardList() {
            return flashcards;
        }
    }

}

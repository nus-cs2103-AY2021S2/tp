package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Flashcard;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getFlashcardList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    //    @Test
    //    public void resetData_withValidReadOnlyAddressBook_replacesData() {
    //        AddressBook newData = getTypicalAddressBook();
    //        addressBook.resetData(newData);
    //        assertEquals(newData, addressBook);
    //    }

    //    @Test
    //    public void resetData_withDuplicateFlashcards_throwsDuplicateFlashcardException() {
    //        // Two flashcards with the same identity fields
    //        Flashcard editedAlice = new FlashcardBuilder(ALICE).withAnswer(VALID_ANSWER_B)
    //        .withTags(VALID_TAG_HUSBAND)
    //                .build();
    //        List<Flashcard> newFlashcards = Arrays.asList(ALICE, editedAlice);
    //        AddressBookStub newData = new AddressBookStub(newFlashcards);
    //
    //        assertThrows(DuplicateFlashcardException.class, () -> addressBook.resetData(newData));
    //    }

    @Test
    public void hasFlashcard_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasFlashcard(null));
    }

    //    @Test
    //    public void hasFlashcard_flashcardNotInAddressBook_returnsFalse() {
    //        assertFalse(addressBook.hasFlashcard(ALICE));
    //    }

    //    @Test
    //    public void hasFlashcard_flashcardInAddressBook_returnsTrue() {
    //        addressBook.addFlashcard(ALICE);
    //        assertTrue(addressBook.hasFlashcard(ALICE));
    //    }

    //    @Test
    //    public void hasFlashcard_flashcardWithSameIdentityFieldsInAddressBook_returnsTrue() {
    //        addressBook.addFlashcard(ALICE);
    //        Flashcard editedAlice = new FlashcardBuilder(ALICE).withAnswer(VALID_ANSWER_B)
    //        .withTags(VALID_TAG_HUSBAND)
    //                .build();
    //        assertTrue(addressBook.hasFlashcard(editedAlice));
    //    }

    @Test
    public void getFlashcardList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getFlashcardList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose flashcards list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Flashcard> flashcards = FXCollections.observableArrayList();

        AddressBookStub(Collection<Flashcard> flashcards) {
            this.flashcards.setAll(flashcards);
        }

        @Override
        public ObservableList<Flashcard> getFlashcardList() {
            return flashcards;
        }
    }

}

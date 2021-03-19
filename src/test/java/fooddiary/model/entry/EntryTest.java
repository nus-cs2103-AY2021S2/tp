package fooddiary.model.entry;

import fooddiary.testutil.EntryBuilder;
import org.junit.jupiter.api.Test;

import static fooddiary.logic.commands.CommandTestUtil.*;
import static fooddiary.testutil.Assert.assertThrows;
import static fooddiary.testutil.TypicalEntries.ALICE;
import static fooddiary.testutil.TypicalEntries.BOB;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntryTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Entry entry = new EntryBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> entry.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSameEntry(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameEntry(null));

        // same name, all other attributes different -> returns true
        Entry editedAlice = new EntryBuilder(ALICE).withRating(VALID_RATING_BOB).withReview(VALID_REVIEW_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_WESTERN).build();
        assertTrue(ALICE.isSameEntry(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new EntryBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameEntry(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Entry editedBob = new EntryBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameEntry(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new EntryBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameEntry(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Entry aliceCopy = new EntryBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Entry editedAlice = new EntryBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different rating -> returns false
        editedAlice = new EntryBuilder(ALICE).withRating(VALID_RATING_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different review -> returns false
        editedAlice = new EntryBuilder(ALICE).withReview(VALID_REVIEW_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new EntryBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new EntryBuilder(ALICE).withTags(VALID_TAG_FASTFOOD).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}

package fooddiary.model.entry;

import static fooddiary.logic.commands.CommandTestUtil.VALID_ADDRESS_B;
import static fooddiary.logic.commands.CommandTestUtil.VALID_NAME_B;
import static fooddiary.logic.commands.CommandTestUtil.VALID_PRICE_B;
import static fooddiary.logic.commands.CommandTestUtil.VALID_RATING_B;
import static fooddiary.logic.commands.CommandTestUtil.VALID_REVIEW_B;
import static fooddiary.logic.commands.CommandTestUtil.VALID_TAG_FASTFOOD;
import static fooddiary.logic.commands.CommandTestUtil.VALID_TAG_WESTERN;
import static fooddiary.testutil.Assert.assertThrows;
import static fooddiary.testutil.TypicalEntries.ENTRY_A;
import static fooddiary.testutil.TypicalEntries.VALID_ENTRY_B;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fooddiary.testutil.EntryBuilder;

public class EntryTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Entry entry = new EntryBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> entry.getTags().remove(0));
    }

    @Test
    public void isSameEntry() {
        // same object -> returns true
        assertTrue(ENTRY_A.isSameEntry(ENTRY_A));

        // null -> returns false
        assertFalse(ENTRY_A.isSameEntry(null));

        // same name, all other attributes different -> returns true
        Entry editedA = new EntryBuilder(ENTRY_A).withRating(VALID_RATING_B)
                .withPrice(VALID_PRICE_B).withReviews(VALID_REVIEW_B)
                .withAddress(VALID_ADDRESS_B).withTags(VALID_TAG_WESTERN).build();
        assertTrue(ENTRY_A.isSameEntry(editedA));

        // different name, all other attributes same -> returns false
        editedA = new EntryBuilder(ENTRY_A).withName(VALID_NAME_B).build();
        assertFalse(ENTRY_A.isSameEntry(editedA));

        // name differs in case, all other attributes same -> returns false
        Entry editedB = new EntryBuilder(VALID_ENTRY_B).withName(VALID_NAME_B.toLowerCase()).build();
        assertFalse(VALID_ENTRY_B.isSameEntry(editedB));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_B + " ";
        editedB = new EntryBuilder(VALID_ENTRY_B).withName(nameWithTrailingSpaces).build();
        assertFalse(VALID_ENTRY_B.isSameEntry(editedB));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Entry aCopy = new EntryBuilder(ENTRY_A).build();
        assertTrue(ENTRY_A.equals(aCopy));

        // same object -> returns true
        assertTrue(ENTRY_A.equals(ENTRY_A));

        // null -> returns false
        assertFalse(ENTRY_A.equals(null));

        // different type -> returns false
        assertFalse(ENTRY_A.equals(5));

        // different entry -> returns false
        assertFalse(ENTRY_A.equals(VALID_ENTRY_B));

        // different name -> returns false
        Entry editedA = new EntryBuilder(ENTRY_A).withName(VALID_NAME_B).build();
        assertFalse(ENTRY_A.equals(editedA));

        // different rating -> returns false
        editedA = new EntryBuilder(ENTRY_A).withRating(VALID_RATING_B).build();
        assertFalse(ENTRY_A.equals(editedA));

        // different price -> returns false
        editedA = new EntryBuilder(ENTRY_A).withPrice(VALID_PRICE_B).build();
        assertFalse(ENTRY_A.equals(editedA));

        // different review -> returns false
        editedA = new EntryBuilder(ENTRY_A).withReviews(VALID_REVIEW_B).build();
        assertFalse(ENTRY_A.equals(editedA));

        // different address -> returns false
        editedA = new EntryBuilder(ENTRY_A).withAddress(VALID_ADDRESS_B).build();
        assertFalse(ENTRY_A.equals(editedA));

        // different tags -> returns false
        editedA = new EntryBuilder(ENTRY_A).withTags(VALID_TAG_FASTFOOD).build();
        assertFalse(ENTRY_A.equals(editedA));
    }
}

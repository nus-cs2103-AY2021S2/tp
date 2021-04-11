package seedu.address.model.entry;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EntryBuilder;

public class EntryTest {

    private final Entry defaultEntry = new EntryBuilder().build();

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> defaultEntry.getTags().remove(0));
    }

    @Test
    public void isSameEntry() {

        // same object -> returns true
        assertTrue(defaultEntry.isSameEntry(defaultEntry));

        // null -> returns false
        assertFalse(defaultEntry.isSameEntry(null));

        // same attributes except name -> return false
        assertFalse(defaultEntry.isSameEntry(new EntryBuilder().withEntryName("Different Name")
                .withStartDate(EntryBuilder.DEFAULT_START_DATE).withEndDate(EntryBuilder.DEFAULT_END_DATE)
                .withTags(EntryBuilder.DEFAULT_TAG).build()));

        // same attributes except name with trailing whitespaces-> return false
        assertFalse(defaultEntry.isSameEntry(new EntryBuilder().withEntryName(EntryBuilder.DEFAULT_ENTRY_NAME + "     ")
                .withStartDate(EntryBuilder.DEFAULT_START_DATE).withEndDate(EntryBuilder.DEFAULT_END_DATE)
                .withTags(EntryBuilder.DEFAULT_TAG).build()));

        // same attributes except start date -> return false
        assertFalse(defaultEntry.isSameEntry(new EntryBuilder().withEntryName(EntryBuilder.DEFAULT_ENTRY_NAME)
                .withStartDate("2020-01-01 00:00").withEndDate(EntryBuilder.DEFAULT_END_DATE)
                .withTags(EntryBuilder.DEFAULT_TAG).build()));

        // same attributes except end date -> return false
        assertFalse(defaultEntry.isSameEntry(new EntryBuilder().withEntryName(EntryBuilder.DEFAULT_ENTRY_NAME)
                .withStartDate(EntryBuilder.DEFAULT_START_DATE).withEndDate("2025-01-01 00:00")
                .withTags(EntryBuilder.DEFAULT_TAG).build()));

        // same attributes except tag -> return false
        assertFalse(defaultEntry.isSameEntry(new EntryBuilder().withEntryName(EntryBuilder.DEFAULT_ENTRY_NAME)
                .withStartDate(EntryBuilder.DEFAULT_START_DATE).withEndDate("2025-01-01 00:00")
                .withTags("DifferentTag").build()));
    }

    @Test
    public void isOverlapping() {

        // date ranges do not coincide -> return false
        assertFalse(defaultEntry.overlapsWith(new EntryBuilder().withEntryName("Second Entry")
                .withStartDate("2222-01-01 00:00").withEndDate("2222-01-01 12:00").build()));

        // start date of second entry is equal to end date of default entry -> return false
        assertFalse(defaultEntry.overlapsWith(new EntryBuilder().withEntryName("Second Entry")
                .withStartDate("2021-04-05 19:00").withEndDate("2222-01-01 00:00").build()));

        // start date of second entry is in between the start date and end date of default entry -> return true
        assertTrue(defaultEntry.overlapsWith(new EntryBuilder().withEntryName("Second Entry")
                .withStartDate("2021-04-05 17:01").withEndDate("2021-04-05 20:00").build()));

        // start date and end date of second entry is in between start date and end date of default entry -> return true
        assertTrue(defaultEntry.overlapsWith(new EntryBuilder().withEntryName("Second Entry")
                .withStartDate("2021-04-05 17:01").withEndDate("2021-04-05 18:59").build()));

        // end date of second entry is in between start date and end date of default entry -> return true
        assertTrue(defaultEntry.overlapsWith(new EntryBuilder().withEntryName("Second Entry")
                .withStartDate("2020-01-01 00:00").withEndDate("2021-04-05 17:01").build()));
    }

    @Test
    public void equals() {

        // same object -> returns true
        assertTrue(defaultEntry.equals(defaultEntry));

        // null -> returns false
        assertFalse(defaultEntry.equals(null));

        // same attributes -> return True
        assertTrue(defaultEntry.equals(new EntryBuilder().withEntryName(EntryBuilder.DEFAULT_ENTRY_NAME)
                .withStartDate(EntryBuilder.DEFAULT_START_DATE).withEndDate(EntryBuilder.DEFAULT_END_DATE)
                .withTags(EntryBuilder.DEFAULT_TAG).build()));

        // same attributes except name -> return false
        assertFalse(defaultEntry.equals(new EntryBuilder().withEntryName("Different Name")
                .withStartDate(EntryBuilder.DEFAULT_START_DATE).withEndDate(EntryBuilder.DEFAULT_END_DATE)
                .withTags(EntryBuilder.DEFAULT_TAG).build()));

        // same attributes except name with trailing whitespaces-> return false
        assertFalse(defaultEntry.equals(new EntryBuilder().withEntryName(EntryBuilder.DEFAULT_ENTRY_NAME + "     ")
                .withStartDate(EntryBuilder.DEFAULT_START_DATE).withEndDate(EntryBuilder.DEFAULT_END_DATE)
                .withTags(EntryBuilder.DEFAULT_TAG).build()));

        // same attributes except start date -> return false
        assertFalse(defaultEntry.equals(new EntryBuilder().withEntryName(EntryBuilder.DEFAULT_ENTRY_NAME)
                .withStartDate("2020-01-01 00:00").withEndDate(EntryBuilder.DEFAULT_END_DATE)
                .withTags(EntryBuilder.DEFAULT_TAG).build()));

        // same attributes except end date -> return false
        assertFalse(defaultEntry.equals(new EntryBuilder().withEntryName(EntryBuilder.DEFAULT_ENTRY_NAME)
                .withStartDate(EntryBuilder.DEFAULT_START_DATE).withEndDate("2025-01-01 00:00")
                .withTags(EntryBuilder.DEFAULT_TAG).build()));

        // same attributes except tag -> return false
        assertFalse(defaultEntry.equals(new EntryBuilder().withEntryName(EntryBuilder.DEFAULT_ENTRY_NAME)
                .withStartDate(EntryBuilder.DEFAULT_START_DATE).withEndDate("2025-01-01 00:00")
                .withTags("DifferentTag").build()));
    }

}

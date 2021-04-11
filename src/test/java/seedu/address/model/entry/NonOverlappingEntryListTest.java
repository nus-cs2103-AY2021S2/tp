package seedu.address.model.entry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTeachingAssistant.CLASS_MEETING;
import static seedu.address.testutil.TypicalTeachingAssistant.CONSULTATION;
import static seedu.address.testutil.TypicalTeachingAssistant.NON_OVERDUE_ENTRY;
import static seedu.address.testutil.TypicalTeachingAssistant.OVERDUE_ENTRY;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.entry.exceptions.EntryNotFoundException;
import seedu.address.model.entry.exceptions.OverlappingEntryException;

public class NonOverlappingEntryListTest {

    private final NonOverlappingEntryList nonOverlappingEntryList = new NonOverlappingEntryList();

    @Test
    public void contains_nullEntry_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonOverlappingEntryList.contains(null));
    }

    @Test
    public void contains_entryNotInList_returnFalse() {
        assertFalse(nonOverlappingEntryList.contains(CONSULTATION));
    }

    @Test
    public void contains_entryInList_returnTrue() {
        nonOverlappingEntryList.add(CONSULTATION);
        assertTrue(nonOverlappingEntryList.contains(CONSULTATION));
    }

    @Test
    public void overlapsWith_nullEntryToCheck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonOverlappingEntryList.overlapsWith(null));
    }

    @Test
    public void overlapsWith_noOverlaps_success() {
        assertFalse(nonOverlappingEntryList.overlapsWith(CONSULTATION));
    }

    @Test
    public void overlapsWith_overlapFound_success() {
        nonOverlappingEntryList.add(CONSULTATION);
        assertTrue(nonOverlappingEntryList.overlapsWith(CONSULTATION));
    }

    @Test
    public void add_nullEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonOverlappingEntryList.add(null));
    }

    @Test
    public void add_overlappingEntry_throwsOverlappingEntryException() {
        nonOverlappingEntryList.add(CONSULTATION);
        assertThrows(OverlappingEntryException.class, () -> nonOverlappingEntryList.add(CONSULTATION));
    }

    @Test
    public void setEntry_nullTargetEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonOverlappingEntryList.setEntry(null, CONSULTATION));
    }

    @Test
    public void setEntry_nullEditedEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonOverlappingEntryList.setEntry(CONSULTATION, null));
    }

    @Test
    public void setEntry_targetNotInList_throwsEntryNotFoundException() {
        assertThrows(EntryNotFoundException.class, () -> nonOverlappingEntryList.setEntry(CONSULTATION, CONSULTATION));
    }

    @Test
    public void setEntry_overlappingTargetEntry_throwsOverlappingEntryException() {
        nonOverlappingEntryList.add(CONSULTATION);
        nonOverlappingEntryList.add(CLASS_MEETING);
        assertThrows(OverlappingEntryException.class, () -> nonOverlappingEntryList
                .setEntry(CLASS_MEETING, CONSULTATION));
    }

    @Test
    public void setEntry_success() {
        NonOverlappingEntryList expectedList = new NonOverlappingEntryList();
        expectedList.add(CLASS_MEETING);
        nonOverlappingEntryList.add(CONSULTATION);
        nonOverlappingEntryList.setEntry(CONSULTATION, CLASS_MEETING);
        assertEquals(expectedList, nonOverlappingEntryList);
    }

    @Test
    public void remove_nullTargetEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonOverlappingEntryList.remove(null));
    }

    @Test
    public void remove_entryNotInList_throwsEntryNotFoundException() {
        assertThrows(EntryNotFoundException.class, () -> nonOverlappingEntryList.remove(CONSULTATION));
    }

    @Test
    public void remove_success() {
        NonOverlappingEntryList expectedList = new NonOverlappingEntryList();
        nonOverlappingEntryList.add(CONSULTATION);
        nonOverlappingEntryList.remove(CONSULTATION);
        assertEquals(expectedList, nonOverlappingEntryList);
    }

    @Test
    public void setEntries_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonOverlappingEntryList
                .setEntries((NonOverlappingEntryList) null));
    }

    @Test
    public void setEntries_overlappingEntriesList_throwsOverlappingEntriesException() {
        List<Entry> listWithOverlappingEntries = Arrays.asList(CONSULTATION, CONSULTATION);
        assertThrows(OverlappingEntryException.class, () -> nonOverlappingEntryList
                .setEntries(listWithOverlappingEntries));
    }

    @Test
    public void setEntries_success() {
        List<Entry> validList = Arrays.asList(CONSULTATION, CLASS_MEETING);
        NonOverlappingEntryList expectedList = new NonOverlappingEntryList();
        expectedList.add(CONSULTATION);
        expectedList.add(CLASS_MEETING);
        nonOverlappingEntryList.setEntries(validList);
        assertEquals(expectedList, nonOverlappingEntryList);
    }

    @Test
    public void clearOverDueEntries_success() {
        List<Entry> validList = Arrays.asList(OVERDUE_ENTRY, NON_OVERDUE_ENTRY);
        NonOverlappingEntryList expectedList = new NonOverlappingEntryList();
        expectedList.add(NON_OVERDUE_ENTRY);
        nonOverlappingEntryList.setEntries(validList);
        nonOverlappingEntryList.clearOverdueEntries();
        assertEquals(expectedList, nonOverlappingEntryList);
    }

}

package fooddiary.model.entry;

import static fooddiary.logic.commands.CommandTestUtil.VALID_ADDRESS_B;
import static fooddiary.logic.commands.CommandTestUtil.VALID_TAG_WESTERN;
import static fooddiary.testutil.Assert.assertThrows;
import static fooddiary.testutil.TypicalEntries.ENTRY_A;
import static fooddiary.testutil.TypicalEntries.VALID_ENTRY_B;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import fooddiary.model.entry.exceptions.DuplicateEntryException;
import fooddiary.model.entry.exceptions.EntryNotFoundException;
import fooddiary.testutil.EntryBuilder;

public class UniqueEntryListTest {

    private final UniqueEntryList uniqueEntryList = new UniqueEntryList();

    @Test
    public void contains_nullEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.contains(null));
    }

    @Test
    public void contains_entryNotInList_returnsFalse() {
        assertFalse(uniqueEntryList.contains(ENTRY_A));
    }

    @Test
    public void contains_entryInList_returnsTrue() {
        uniqueEntryList.add(ENTRY_A);
        assertTrue(uniqueEntryList.contains(ENTRY_A));
    }

    @Test
    public void contains_entryWithSameIdentityFieldsInList_returnsTrue() {
        uniqueEntryList.add(ENTRY_A);
        Entry editedA = new EntryBuilder(ENTRY_A).withAddress(VALID_ADDRESS_B).withTags(VALID_TAG_WESTERN)
                .build();
        assertTrue(uniqueEntryList.contains(editedA));
    }

    @Test
    public void add_nullEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.add(null));
    }

    @Test
    public void add_duplicateEntry_throwsDuplicateEntryException() {
        uniqueEntryList.add(ENTRY_A);
        assertThrows(DuplicateEntryException.class, () -> uniqueEntryList.add(ENTRY_A));
    }

    @Test
    public void setEntry_nullTargetEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.setEntry(null, ENTRY_A));
    }

    @Test
    public void setEntry_nullEditedEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.setEntry(ENTRY_A, null));
    }

    @Test
    public void setEntry_targetEntryNotInList_throwsEntryNotFoundException() {
        assertThrows(EntryNotFoundException.class, () -> uniqueEntryList.setEntry(ENTRY_A, ENTRY_A));
    }

    @Test
    public void setEntry_editedEntryIsSameEntry_success() {
        uniqueEntryList.add(ENTRY_A);
        uniqueEntryList.setEntry(ENTRY_A, ENTRY_A);
        UniqueEntryList expectedUniqueEntryList = new UniqueEntryList();
        expectedUniqueEntryList.add(ENTRY_A);
        assertEquals(expectedUniqueEntryList, uniqueEntryList);
    }

    @Test
    public void setEntry_editedEntryHasSameIdentity_success() {
        uniqueEntryList.add(ENTRY_A);
        Entry editedA = new EntryBuilder(ENTRY_A).withAddress(VALID_ADDRESS_B).withTags(VALID_TAG_WESTERN)
                .build();
        uniqueEntryList.setEntry(ENTRY_A, editedA);
        UniqueEntryList expectedUniqueEntryList = new UniqueEntryList();
        expectedUniqueEntryList.add(editedA);
        assertEquals(expectedUniqueEntryList, uniqueEntryList);
    }

    @Test
    public void setEntry_editedEntryHasDifferentIdentity_success() {
        uniqueEntryList.add(ENTRY_A);
        uniqueEntryList.setEntry(ENTRY_A, VALID_ENTRY_B);
        UniqueEntryList expectedUniqueEntryList = new UniqueEntryList();
        expectedUniqueEntryList.add(VALID_ENTRY_B);
        assertEquals(expectedUniqueEntryList, uniqueEntryList);
    }

    @Test
    public void setEntry_editedEntryHasNonUniqueIdentity_throwsDuplicateEntryException() {
        uniqueEntryList.add(ENTRY_A);
        uniqueEntryList.add(VALID_ENTRY_B);
        assertThrows(DuplicateEntryException.class, () -> uniqueEntryList.setEntry(ENTRY_A, VALID_ENTRY_B));
    }

    @Test
    public void remove_nullEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.remove(null));
    }

    @Test
    public void remove_entryDoesNotExist_throwsEntryNotFoundException() {
        assertThrows(EntryNotFoundException.class, () -> uniqueEntryList.remove(ENTRY_A));
    }

    @Test
    public void remove_existingEntry_removesEntry() {
        uniqueEntryList.add(ENTRY_A);
        uniqueEntryList.remove(ENTRY_A);
        UniqueEntryList expectedUniqueEntryList = new UniqueEntryList();
        assertEquals(expectedUniqueEntryList, uniqueEntryList);
    }

    @Test
    public void setEntries_nullUniqueEntryList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.setEntry((UniqueEntryList) null));
    }

    @Test
    public void setEntries_uniqueEntryList_replacesOwnListWithProvidedUniqueEntryList() {
        uniqueEntryList.add(ENTRY_A);
        UniqueEntryList expectedUniqueEntryList = new UniqueEntryList();
        expectedUniqueEntryList.add(VALID_ENTRY_B);
        uniqueEntryList.setEntry(expectedUniqueEntryList);
        assertEquals(expectedUniqueEntryList, uniqueEntryList);
    }

    @Test
    public void setEntries_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.setEntry((List<Entry>) null));
    }

    @Test
    public void setEntries_list_replacesOwnListWithProvidedList() {
        uniqueEntryList.add(ENTRY_A);
        List<Entry> entryList = Collections.singletonList(VALID_ENTRY_B);
        uniqueEntryList.setEntry(entryList);
        UniqueEntryList expectedUniqueEntryList = new UniqueEntryList();
        expectedUniqueEntryList.add(VALID_ENTRY_B);
        assertEquals(expectedUniqueEntryList, uniqueEntryList);
    }

    @Test
    public void setEntries_listWithDuplicateEntries_throwsDuplicateEntryException() {
        List<Entry> listWithDuplicateEntries = Arrays.asList(ENTRY_A, ENTRY_A);
        assertThrows(DuplicateEntryException.class, () -> uniqueEntryList.setEntry(listWithDuplicateEntries));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueEntryList.asUnmodifiableObservableList().remove(0));
    }
}

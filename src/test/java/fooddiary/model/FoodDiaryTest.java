package fooddiary.model;

import static fooddiary.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static fooddiary.logic.commands.CommandTestUtil.VALID_TAG_WESTERN;
import static fooddiary.testutil.Assert.assertThrows;
import static fooddiary.testutil.TypicalEntries.ALICE;
import static fooddiary.testutil.TypicalEntries.getTypicalFoodDiary;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import fooddiary.model.entry.Entry;
import fooddiary.model.entry.exceptions.DuplicateEntryException;
import fooddiary.testutil.EntryBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class FoodDiaryTest {

    private final FoodDiary foodDiary = new FoodDiary();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), foodDiary.getEntryList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> foodDiary.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        FoodDiary newData = getTypicalFoodDiary();
        foodDiary.resetData(newData);
        assertEquals(newData, foodDiary);
    }

    @Test
    public void resetData_withDuplicateEntries_throwsDuplicateEntryException() {
        // Two entries with the same identity fields
        Entry editedAlice = new EntryBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_WESTERN)
                .build();
        List<Entry> newEntries = Arrays.asList(ALICE, editedAlice);
        FoodDiaryStub newData = new FoodDiaryStub(newEntries);

        assertThrows(DuplicateEntryException.class, () -> foodDiary.resetData(newData));
    }

    @Test
    public void hasEntry_nullEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> foodDiary.hasEntry(null));
    }

    @Test
    public void hasEntry_entryNotInAddressBook_returnsFalse() {
        assertFalse(foodDiary.hasEntry(ALICE));
    }

    @Test
    public void hasEntry_entryInAddressBook_returnsTrue() {
        foodDiary.addEntry(ALICE);
        assertTrue(foodDiary.hasEntry(ALICE));
    }

    @Test
    public void hasEntry_entryWithSameIdentityFieldsInAddressBook_returnsTrue() {
        foodDiary.addEntry(ALICE);
        Entry editedAlice = new EntryBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_WESTERN)
                .build();
        assertTrue(foodDiary.hasEntry(editedAlice));
    }

    @Test
    public void getEntryList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> foodDiary.getEntryList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose entries list can violate interface constraints.
     */
    private static class FoodDiaryStub implements ReadOnlyFoodDiary {
        private final ObservableList<Entry> entries = FXCollections.observableArrayList();

        FoodDiaryStub(Collection<Entry> entries) {
            this.entries.setAll(entries);
        }

        @Override
        public ObservableList<Entry> getEntryList() {
            return entries;
        }
    }

}

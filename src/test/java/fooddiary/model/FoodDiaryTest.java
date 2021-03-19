package fooddiary.model;

import fooddiary.model.entry.Entry;
import fooddiary.model.entry.exceptions.DuplicateEntryException;
import fooddiary.testutil.PersonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static fooddiary.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static fooddiary.logic.commands.CommandTestUtil.VALID_TAG_WESTERN;
import static fooddiary.testutil.Assert.assertThrows;
import static fooddiary.testutil.TypicalPersons.ALICE;
import static fooddiary.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.*;

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
        FoodDiary newData = getTypicalAddressBook();
        foodDiary.resetData(newData);
        assertEquals(newData, foodDiary);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Entry editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_WESTERN)
                .build();
        List<Entry> newEntries = Arrays.asList(ALICE, editedAlice);
        FoodDiaryStub newData = new FoodDiaryStub(newEntries);

        assertThrows(DuplicateEntryException.class, () -> foodDiary.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> foodDiary.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(foodDiary.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        foodDiary.addEntry(ALICE);
        assertTrue(foodDiary.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        foodDiary.addEntry(ALICE);
        Entry editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_WESTERN)
                .build();
        assertTrue(foodDiary.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> foodDiary.getEntryList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
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

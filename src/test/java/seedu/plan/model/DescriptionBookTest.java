package seedu.plan.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
/*
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
*/

// import java.util.Arrays;
//import java.util.Collection;
import java.util.Collections;
// import java.util.List;

import org.junit.jupiter.api.Test;

//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import seedu.address.model.plan.Plan;
// import seedu.address.model.plan.exceptions.DuplicatePersonException;
// import seedu.address.testutil.PersonBuilder;

public class DescriptionBookTest {

    private final ModulePlanner modulePlanner = new ModulePlanner();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), modulePlanner.getPersonList());
    }

    /*
    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two plans with the same identity fields
        Plan editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Plan> newPlans = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPlans);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }
     */

    /*
    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPlan(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPlan(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPlan(ALICE);
        assertTrue(addressBook.hasPlan(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPlan(ALICE);
        Plan editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPlan(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    */

    /*
    /**
     * A stub ReadOnlyAddressBook whose plans list can violate interface constraints.
     */
    /*
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Plan> plans = FXCollections.observableArrayList();

        AddressBookStub(Collection<Plan> plans) {
            this.plans.setAll(plans);
        }

        @Override
        public ObservableList<Plan> getPersonList() {
            return plans;
        }

        @Override
        public get
    }
    */
}

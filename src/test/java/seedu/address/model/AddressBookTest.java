package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppObjects.ALICE;
import static seedu.address.testutil.TypicalAppObjects.getTypicalPatientRecords;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PatientBuilder;


public class AddressBookTest {

    private final AddressBook<Patient> patientAddressBook = new AddressBook<>();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), patientAddressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> patientAddressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook<Patient> newData = getTypicalPatientRecords();
        patientAddressBook.resetData(newData);
        assertEquals(newData, patientAddressBook);
    }

    @Test
    public void resetData_withDuplicatePatients_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Patient editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Patient> newPatients = Arrays.asList(ALICE, editedAlice);
        AddressBookStub<Patient> newData = new AddressBookStub<>(newPatients);

        assertThrows(DuplicatePersonException.class, () -> patientAddressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> patientAddressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_patientNotInAddressBook_returnsFalse() {
        assertFalse(patientAddressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_patientInAddressBook_returnsTrue() {
        patientAddressBook.addPerson(ALICE);
        assertTrue(patientAddressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_patientWithSameIdentityFieldsInAddressBook_returnsTrue() {
        patientAddressBook.addPerson(ALICE);
        Patient editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(patientAddressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> patientAddressBook.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub<T extends Person> implements ReadOnlyAddressBook<T> {
        private final ObservableList<T> persons = FXCollections.observableArrayList();

        AddressBookStub(Collection<T> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<T> getPersonList() {
            return persons;
        }
    }

}

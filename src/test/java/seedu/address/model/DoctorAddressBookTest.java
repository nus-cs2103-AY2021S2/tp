package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SEVERE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppObjects.DR_GREY;
import static seedu.address.testutil.TypicalAppObjects.getTypicalDoctorRecords;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.DoctorBuilder;


public class DoctorAddressBookTest {

    private final AddressBook<Doctor> doctorAddressBook = new AddressBook<>();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), doctorAddressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> doctorAddressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook<Doctor> newData = getTypicalDoctorRecords();
        doctorAddressBook.resetData(newData);
        assertEquals(newData, doctorAddressBook);
    }

    @Test
    public void resetData_withDuplicateDoctors_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Doctor editedDrGrey = new DoctorBuilder(DR_GREY).withTags(VALID_TAG_SEVERE)
                .build();
        List<Doctor> newDoctors = Arrays.asList(DR_GREY, editedDrGrey);
        AddressBookStub<Doctor> newData = new AddressBookStub<>(newDoctors);

        assertThrows(DuplicatePersonException.class, () -> doctorAddressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> doctorAddressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_doctorNotInAddressBook_returnsFalse() {
        assertFalse(doctorAddressBook.hasPerson(DR_GREY));
    }

    @Test
    public void hasPerson_doctorInAddressBook_returnsTrue() {
        doctorAddressBook.addPerson(DR_GREY);
        assertTrue(doctorAddressBook.hasPerson(DR_GREY));
    }

    @Test
    public void hasPerson_doctorWithSameIdentityFieldsInAddressBook_returnsTrue() {
        doctorAddressBook.addPerson(DR_GREY);
        Doctor editedDrGrey = new DoctorBuilder(DR_GREY).withTags(VALID_TAG_SEVERE)
                .build();
        assertTrue(doctorAddressBook.hasPerson(editedDrGrey));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> doctorAddressBook.getPersonList().remove(0));
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

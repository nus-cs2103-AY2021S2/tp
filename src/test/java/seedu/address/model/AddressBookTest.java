package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRemindMe.ALICE;
import static seedu.address.testutil.TypicalRemindMe.getTypicalRemindMe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class AddressBookTest {

    private final RemindMe remindMe = new RemindMe();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), remindMe.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> remindMe.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyRemindMe_replacesData() {

        RemindMe newData = getTypicalRemindMe();
        remindMe.resetData(newData);

        assertEquals(newData, remindMe);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        RemindMeStub newData = new RemindMeStub(newPersons, new ArrayList<>());
        assertThrows(DuplicatePersonException.class, () -> remindMe.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> remindMe.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(remindMe.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        remindMe.addPerson(ALICE);
        assertTrue(remindMe.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        remindMe.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(remindMe.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> remindMe.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

    /**
     * A stub ReadOnlyRemindMe whose persons list can violate interface constraints.
     */
    private static class RemindMeStub implements ReadOnlyRemindMe {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Module> modules = FXCollections.observableArrayList();
        RemindMeStub(Collection<Person> persons, Collection<Module> modules) {
            this.persons.setAll(persons);
            this.modules.setAll(modules);
        }

        @Override
        public ObservableList<Module> getModuleList() {
            return modules;
        }


        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }
}

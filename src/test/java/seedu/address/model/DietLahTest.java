package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalDietLah;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.food.UniqueFoodList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class DietLahTest {

    private final DietLah dietLah = new DietLah();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), dietLah.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> dietLah.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyDietLah_replacesData() {
        DietLah newData = getTypicalDietLah();
        dietLah.resetData(newData);
        assertEquals(newData, dietLah);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        DietLahStub newData = new DietLahStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> dietLah.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> dietLah.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInDietLah_returnsFalse() {
        assertFalse(dietLah.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInDietLah_returnsTrue() {
        dietLah.addPerson(ALICE);
        assertTrue(dietLah.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInDietLah_returnsTrue() {
        dietLah.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(dietLah.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> dietLah.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyDietLah whose persons list can violate interface constraints.
     */
    private static class DietLahStub implements ReadOnlyDietLah {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        DietLahStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public UniqueFoodList getFoodList() {
            // TODO: Improve abstraction
            return null;
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}

package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalWardrobe;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class WardrobeTest {

    private final Wardrobe wardrobe = new Wardrobe();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), wardrobe.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wardrobe.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyWardrobe_replacesData() {
        Wardrobe newData = getTypicalWardrobe();
        wardrobe.resetData(newData);
        assertEquals(newData, wardrobe);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE)
                .withAddress(VALID_ADDRESS_BOB)
                .withDescriptions(VALID_DESCRIPTION_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        WardrobeStub newData = new WardrobeStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> wardrobe.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wardrobe.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInWardrobe_returnsFalse() {
        assertFalse(wardrobe.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInWardrobe_returnsTrue() {
        wardrobe.addPerson(ALICE);
        assertTrue(wardrobe.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInWardrobe_returnsTrue() {
        wardrobe.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE)
                .withAddress(VALID_ADDRESS_BOB)
                .withDescriptions(VALID_DESCRIPTION_HUSBAND)
                .build();
        assertTrue(wardrobe.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> wardrobe.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyWardrobe whose persons list can violate interface constraints.
     */
    private static class WardrobeStub implements ReadOnlyWardrobe {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        WardrobeStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}

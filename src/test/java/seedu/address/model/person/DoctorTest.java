package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppObjects.DR_GREY;
import static seedu.address.testutil.TypicalAppObjects.ZOHN;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DoctorBuilder;

public class DoctorTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new DoctorBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSameDoctor() {
        // same object -> returns true
        assertTrue(DR_GREY .isSamePerson(DR_GREY));

        // null -> returns false
        assertFalse(DR_GREY .isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new DoctorBuilder(DR_GREY).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(DR_GREY.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new DoctorBuilder(DR_GREY).withName(VALID_NAME_BOB).build();
        assertFalse(DR_GREY.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new DoctorBuilder(ZOHN).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(ZOHN.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new DoctorBuilder(ZOHN).withName(nameWithTrailingSpaces).build();
        assertFalse(ZOHN.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new DoctorBuilder(DR_GREY).build();
        assertTrue(DR_GREY.equals(aliceCopy));

        // same object -> returns true
        assertTrue(DR_GREY.equals(DR_GREY));

        // null -> returns false
        assertFalse(DR_GREY.equals(null));

        // different type -> returns false
        assertFalse(DR_GREY.equals(5));

        // different person -> returns false
        assertFalse(DR_GREY.equals(ZOHN));

        // different name -> returns false
        Person editedAlice = new DoctorBuilder(DR_GREY).withName(VALID_NAME_BOB).build();
        assertFalse(DR_GREY.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new DoctorBuilder(DR_GREY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DR_GREY.equals(editedAlice));
    }
}

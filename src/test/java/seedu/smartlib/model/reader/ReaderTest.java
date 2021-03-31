package seedu.smartlib.model.reader;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.smartlib.testutil.Assert.assertThrows;
import static seedu.smartlib.testutil.TypicalModels.ALICE;
import static seedu.smartlib.testutil.TypicalModels.BOB;

import org.junit.jupiter.api.Test;

import seedu.smartlib.testutil.ReaderBuilder;

public class ReaderTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Reader reader = new ReaderBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> reader.getTags().remove(0));
    }

    @Test
    public void isSameReader() {
        // same object -> returns true
        assertTrue(ALICE.isSameReader(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameReader(null));

        // same name, all other attributes different -> returns true
        Reader editedAlice = new ReaderBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameReader(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new ReaderBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameReader(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Reader editedBob = new ReaderBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameReader(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new ReaderBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameReader(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Reader aliceCopy = new ReaderBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different reader -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Reader editedAlice = new ReaderBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new ReaderBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new ReaderBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new ReaderBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ReaderBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}

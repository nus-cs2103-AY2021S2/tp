package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.ALICE;
import static seedu.address.testutil.TypicalContacts.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ContactBuilder;

public class ContactTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
<<<<<<< Updated upstream:src/test/java/seedu/address/model/contact/ContactTest.java
        Contact contact = new PersonBuilder().build();
=======
        Contact contact = new ContactBuilder().build();
>>>>>>> Stashed changes:src/test/java/seedu/address/model/person/PersonTest.java
        assertThrows(UnsupportedOperationException.class, () -> contact.getTags().remove(0));
    }

    @Test
    public void isSameContact() {
        // same object -> returns true
        assertTrue(ALICE.isSameContact(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameContact(null));

        // same name and tags, all other attributes different -> returns true
<<<<<<< Updated upstream:src/test/java/seedu/address/model/contact/ContactTest.java
        Contact editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
=======
        Contact editedAlice = new ContactBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
>>>>>>> Stashed changes:src/test/java/seedu/address/model/person/PersonTest.java
                .withAddress(VALID_ADDRESS_BOB).build();
        assertTrue(ALICE.isSameContact(editedAlice));

        // different name and same tags, all other attributes same -> returns false
        editedAlice = new ContactBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameContact(editedAlice));

        // different tags and same name, all other attributes same -> returns false
        editedAlice = new ContactBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.isSameContact(editedAlice));

        // different tags and different name, all other attributes same -> returns false
        editedAlice = new ContactBuilder(ALICE).withName(VALID_NAME_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.isSameContact(editedAlice));

        // name differs in case, all other attributes same -> returns false
<<<<<<< Updated upstream:src/test/java/seedu/address/model/contact/ContactTest.java
        Contact editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));
=======
        Contact editedBob = new ContactBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameContact(editedBob));
>>>>>>> Stashed changes:src/test/java/seedu/address/model/person/PersonTest.java

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new ContactBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameContact(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
<<<<<<< Updated upstream:src/test/java/seedu/address/model/contact/ContactTest.java
        Contact aliceCopy = new PersonBuilder(ALICE).build();
=======
        Contact aliceCopy = new ContactBuilder(ALICE).build();
>>>>>>> Stashed changes:src/test/java/seedu/address/model/person/PersonTest.java
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different contact -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
<<<<<<< Updated upstream:src/test/java/seedu/address/model/contact/ContactTest.java
        Contact editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
=======
        Contact editedAlice = new ContactBuilder(ALICE).withName(VALID_NAME_BOB).build();
>>>>>>> Stashed changes:src/test/java/seedu/address/model/person/PersonTest.java
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new ContactBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new ContactBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new ContactBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ContactBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}

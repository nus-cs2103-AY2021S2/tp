package dog.pawbook.model.managedentity.owner;

import static dog.pawbook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static dog.pawbook.testutil.Assert.assertThrows;
import static dog.pawbook.testutil.TypicalOwners.ALICE;
import static dog.pawbook.testutil.TypicalOwners.BOB;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dog.pawbook.model.managedentity.Name;
import dog.pawbook.testutil.OwnerBuilder;

public class OwnerTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Owner owner = new OwnerBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> owner.getTags().remove(0));
    }

    @Test
    public void getName() {
        assertTrue(ALICE.getName().equals(new Name("Alice Pauline")));
        assertTrue(BOB.getName().equals(new Name("Bob Choo")));
    }

    @Test
    public void getPhone() {
        assertTrue(ALICE.getPhone().equals(new Phone("94351253")));
        assertTrue(BOB.getPhone().equals(new Phone("22222222")));
    }

    @Test
    public void getEmail() {
        assertTrue(ALICE.getEmail().equals(new Email("alice@example.com")));
        assertTrue(BOB.getEmail().equals(new Email("bob@example.com")));
    }

    @Test
    public void getAddress() {
        assertTrue(ALICE.getAddress().equals(new Address("123, Jurong West Ave 6, #08-111")));
        assertTrue(BOB.getAddress().equals(new Address("Block 123, Bobby Street 3")));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Owner aliceCopy = new OwnerBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different owner -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Owner editedAlice = new OwnerBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new OwnerBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new OwnerBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new OwnerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new OwnerBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}

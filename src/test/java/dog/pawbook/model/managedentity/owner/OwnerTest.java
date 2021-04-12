package dog.pawbook.model.managedentity.owner;

import static dog.pawbook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static dog.pawbook.testutil.TypicalEntities.ALICE;
import static dog.pawbook.testutil.TypicalEntities.APPLE;
import static dog.pawbook.testutil.TypicalEntities.BOB;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dog.pawbook.model.managedentity.Name;
import dog.pawbook.testutil.OwnerBuilder;

public class OwnerTest {

    @Test
    public void isSameAs() {
        // same object -> returns true
        assertTrue(ALICE.isSameAs(ALICE));

        // different entity type -> return false
        assertFalse(ALICE.isSameAs(APPLE));

        // null -> returns false
        assertFalse(ALICE.isSameAs(null));

        // same name and phone, all other attributes different -> returns true
        Owner editedAlice = new OwnerBuilder(ALICE).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).withDogs().build();
        assertTrue(ALICE.isSameAs(editedAlice));

        // same name and email, all other attributes different -> returns true
        editedAlice = new OwnerBuilder(ALICE).withPhone(VALID_PHONE_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).withDogs().build();
        assertTrue(ALICE.isSameAs(editedAlice));

        // same phone and email, all other attributes different -> returns false
        editedAlice = new OwnerBuilder(ALICE).withName(VALID_NAME_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).withDogs().build();
        assertFalse(ALICE.isSameAs(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new OwnerBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameAs(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Owner editedBob = new OwnerBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameAs(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new OwnerBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameAs(editedBob));
    }

    @Test
    public void getName() {
        assertEquals(new Name("Alice Pauline"), ALICE.getName());
        assertEquals(new Name("Bob Choo"), BOB.getName());
    }

    @Test
    public void getPhone() {
        assertEquals(new Phone("94351253"), ALICE.getPhone());
        assertEquals(new Phone("22222222"), BOB.getPhone());
    }

    @Test
    public void getEmail() {
        assertEquals(new Email("alice@example.com"), ALICE.getEmail());
        assertEquals(new Email("bob@example.com"), BOB.getEmail());
    }

    @Test
    public void getAddress() {
        assertEquals(new Address("123, Jurong West Ave 6, #08-111"), ALICE.getAddress());
        assertEquals(new Address("Block 123, Bobby Street 3"), BOB.getAddress());
    }

    @Test
    public void hashcode() {
        Owner owner = new OwnerBuilder(ALICE).build();

        // same values -> returns same hashcode
        assertEquals(owner.hashCode(), new OwnerBuilder(ALICE).build().hashCode());

        // different name -> returns different hashcode
        assertNotEquals(owner.hashCode(), new OwnerBuilder(ALICE).withName("Peter").build().hashCode());

        // different phone -> returns different hashcode
        assertNotEquals(owner.hashCode(), new OwnerBuilder(ALICE).withPhone("90019001").build().hashCode());

        // different email -> returns different hashcode
        assertNotEquals(owner.hashCode(), new OwnerBuilder(ALICE).withEmail("test123@example.com").build().hashCode());

        // different address -> returns different hashcode
        assertNotEquals(owner.hashCode(), new OwnerBuilder(ALICE).withAddress("Block 123 Street 123")
                .build().hashCode());
    }

    @Test
    public void equals() {
        // same values -> returns true
        Owner aliceCopy = new OwnerBuilder(ALICE).build();
        assertEquals(aliceCopy, ALICE);

        // same object -> returns true
        assertEquals(ALICE, ALICE);

        // different entity type -> return false
        assertNotEquals(ALICE, APPLE);

        // null -> returns false
        assertNotEquals(ALICE, null);

        // different type -> returns false
        assertNotEquals(ALICE, 5);

        // different owner -> returns false
        assertNotEquals(BOB, ALICE);

        // different name -> returns false
        Owner editedAlice = new OwnerBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertNotEquals(editedAlice, ALICE);

        // different phone -> returns false
        editedAlice = new OwnerBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertNotEquals(editedAlice, ALICE);

        // different email -> returns false
        editedAlice = new OwnerBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertNotEquals(editedAlice, ALICE);

        // different address -> returns false
        editedAlice = new OwnerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertNotEquals(editedAlice, ALICE);

        // different tags -> returns false
        editedAlice = new OwnerBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertNotEquals(editedAlice, ALICE);
    }

}

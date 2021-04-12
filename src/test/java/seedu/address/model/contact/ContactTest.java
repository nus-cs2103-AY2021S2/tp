package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ContactBuilder;

/**
 * Contains unit tests for {@code Contact}.
 */
public class ContactTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Contact contact = new ContactBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> contact.getTags().remove(0));
    }

    @Test
    public void isSameContact() {
        Contact alice = new ContactBuilder().withName("Alice").withPhone("87654321").withEmail("alice@email.com")
                .withTags("student", "junior").build();
        Contact bob = new ContactBuilder().withName("Bob").withPhone("98765432").withEmail("bob@email.com")
                .withTags("student", "senior").build();

        // same object -> returns true
        assertTrue(alice.isSameContact(alice));

        // null -> returns false
        assertFalse(alice.isSameContact(null));

        // totally different attributes -> returns false
        assertFalse(alice.isSameContact(bob));

        // same name, all other attributes different -> returns false
        bob = new ContactBuilder(bob).withName("Alice").build();
        assertFalse(alice.isSameContact(bob));

        // different name, all other attributes same -> returns false
        bob = new ContactBuilder(alice).withName("Bob").build();
        assertFalse(alice.isSameContact(bob));

        // name differs in case, all other attributes same -> returns false
        bob = new ContactBuilder(alice).withName("bob").build();
        assertFalse(alice.isSameContact(bob));

        // attributes differ in case -> returns true
        bob = new ContactBuilder().withName("alice").withPhone("87654321").withEmail("alice@EMAIL.COM")
                .withTags("student", "JUNIOR").build();
        assertTrue(alice.isSameContact(bob));
    }
}

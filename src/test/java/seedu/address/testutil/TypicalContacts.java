package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TeachingAssistant;
import seedu.address.model.contact.Contact;

/**
 * A utility class containing a list of {@code Contact} objects to be used in tests.
 */
public class TypicalContacts {

    public static final Contact ALICE = new ContactBuilder()
            .withName("Alice Pauline")
            .withPhone("94351253")
            .withEmail("alice@example.com")
            .withTags("friends")
            .build();

    public static final Contact AMY = new ContactBuilder()
            .withName("Amy Bee")
            .withPhone("11111111")
            .withEmail("amy@example.com")
            .withTags("friends")
            .build();

    public static final Contact BENSON = new ContactBuilder()
            .withName("Benson Meier")
            .withPhone("98765432")
            .withEmail("johnd@example.com")
            .withTags("owesMoney", "friends")
            .build();

    public static final Contact BOB = new ContactBuilder()
            .withName("Bob Choo")
            .withPhone("22222222")
            .withEmail("bob@example.com")
            .withTags("owesMoney", "friends")
            .build();

    public static final Contact CARL = new ContactBuilder()
            .withName("Carl Kurz")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .build();
    public static final Contact DANIEL = new ContactBuilder()
            .withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withTags("friends")
            .build();
    public static final Contact ELLE = new ContactBuilder()
            .withName("Elle Meyer")
            .withPhone("94822241")
            .withEmail("werner@example.com")
            .build();
    public static final Contact FIONA = new ContactBuilder()
            .withName("Fiona Kunz")
            .withPhone("94824270")
            .withEmail("lydia@example.com")
            .build();
    public static final Contact GEORGE = new ContactBuilder()
            .withName("George Best")
            .withPhone("94824425")
            .withEmail("anna@example.com")
            .build();

    // Manually added - Contact's details found in {@code CommandTestUtil}

    private TypicalContacts() {} // prevents instantiation

    /**
     * Returns an {@code TeachingAssistant} with all the typical contacts.
     */
    public static TeachingAssistant getTypicalContactsTeachingAssistant() {
        TeachingAssistant ab = new TeachingAssistant();
        for (Contact contact : getTypicalContacts()) {
            ab.addContact(contact);
        }
        return ab;
    }

    public static List<Contact> getTypicalContacts() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

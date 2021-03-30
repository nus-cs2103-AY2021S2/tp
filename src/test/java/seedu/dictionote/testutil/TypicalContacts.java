package seedu.dictionote.testutil;

import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.dictionote.model.ContactsList;
import seedu.dictionote.model.contact.Contact;

/**
 * A utility class containing a list of {@code Contact} objects to be used in tests.
 */
public class TypicalContacts {

    public static final Contact ALICE = new ContactBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@aexample.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Contact BENSON = new ContactBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@bexample.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Contact CARL = new ContactBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@aexample.com").withAddress("wall street").build();
    public static final Contact DANIEL = new ContactBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@aexample.com").withAddress("10th street").withTags("friends").build();
    public static final Contact ELLE = new ContactBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@bexample.com").withAddress("michegan ave").build();
    public static final Contact FIONA = new ContactBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@bexample.com").withAddress("little tokyo").withTags("friends").build();
    public static final Contact GEORGE = new ContactBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@bexample.com").withAddress("4th street").build();

    // Manually added
    public static final Contact HOON = new ContactBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@aexample.com").withAddress("little india").build();
    public static final Contact IDA = new ContactBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@bexample.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Contact AMY = new ContactBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Contact BOB = new ContactBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalContacts() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static ContactsList getTypicalContactsList() {
        ContactsList ab = new ContactsList();
        for (Contact contact : getTypicalContacts()) {
            ab.addContact(contact);
        }
        return ab;
    }

    public static List<Contact> getTypicalContacts() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_JANE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLACKLIST_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLACKLIST_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLACKLIST_JANE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_JANE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODE_OF_CONTACT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODE_OF_CONTACT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODE_OF_CONTACT_JANE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_JANE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_JANE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_JANE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline").withPhone("94351253")
            .withEmail("alice@example.com").withAddress("123, Jurong West Ave 6, #08-111")
            .withModeOfContact("email").withBlacklist(false)
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier").withPhone("98765432")
            .withEmail("johnd@example.com").withAddress("311, Clementi Ave 2, #02-25")
            .withModeOfContact("address").withBlacklist(false)
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withModeOfContact("phone").withBlacklist(true).build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withModeOfContact("phone").withBlacklist(false)
            .withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withModeOfContact("email").withBlacklist(false).build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withModeOfContact("address").withBlacklist(true).build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withModeOfContact("phone").withBlacklist(false).build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india")
            .withModeOfContact("phone").withBlacklist(false).build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave")
            .withModeOfContact("email").withBlacklist(false).build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withRemark(VALID_REMARK_AMY)
            .withModeOfContact(VALID_MODE_OF_CONTACT_AMY).withBlacklist(VALID_BLACKLIST_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withRemark(VALID_REMARK_BOB)
            .withModeOfContact(VALID_MODE_OF_CONTACT_BOB).withBlacklist(VALID_BLACKLIST_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();
    public static final Person JANE = new PersonBuilder().withName(VALID_NAME_JANE).withPhone(VALID_PHONE_JANE)
            .withEmail(VALID_EMAIL_JANE).withAddress(VALID_ADDRESS_JANE).withRemark(VALID_REMARK_JANE)
            .withModeOfContact(VALID_MODE_OF_CONTACT_JANE).withBlacklist(VALID_BLACKLIST_JANE)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with all the typical persons but not
     * sorted in alphabetical order to test the sort commands.
     */
    public static AddressBook getUnsortedTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getUnsortedTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    /**
     * Returns an {@code AddressBook} where each person is blacklisted to test the massblacklist
     * commands.
     */
    public static AddressBook getBlacklistedTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getBlacklistedTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE, JANE));
    }

    public static List<Person> getUnsortedTypicalPersons() {
        return new ArrayList<>(Arrays.asList(BENSON, ELLE, CARL, ALICE, JANE, GEORGE, FIONA, DANIEL));
    }

    public static List<Person> getBlacklistedTypicalPersons() {
        return new ArrayList<>(Arrays.asList(CARL, FIONA, JANE));
    }
}

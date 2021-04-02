package seedu.address.testutil;

import static seedu.address.logic.commands.persons.PersonCommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.VALID_GROUP_FRIEND;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.VALID_GROUP_HUSBAND;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.VALID_PHONE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.person.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withGroups("table tennis").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withGroups("CS2106", "table tennis").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withGroups("table tennis").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();
    public static final Person JENNY = new PersonBuilder().withName("Jenny Li").withPhone("9482432")
            .withEmail("jenny@example.com").withAddress("5th road")
            .withGroups("badminton", "CS2106").build();
    public static final Person WENDY = new PersonBuilder().withName("Wendy Chen").withPhone("9482222")
            .withEmail("wendy@example.com").withAddress("7th way")
            .withGroups("badminton", "CS2103", "CS2106").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code PersonCommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withGroups(VALID_GROUP_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withGroups(VALID_GROUP_HUSBAND, VALID_GROUP_FRIEND)
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

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE, JENNY, WENDY));
    }
}

package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DRESSCODE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DRESSCODE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SIZE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SIZE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withDressCode("FORMAL").withEmail("alice@example.com")
            .withSize("53")
            .withDescriptions("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withDressCode("FORMAL")
            .withEmail("johnd@example.com").withSize("32")
            .withDescriptions("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withSize("93")
            .withEmail("heinz@example.com").withDressCode("FORMAL").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withSize("33")
            .withEmail("cornelia@example.com").withDressCode("CASUAL").withDescriptions("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withSize("24")
            .withEmail("werner@example.com").withDressCode("CASUAL").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withSize("27")
            .withEmail("lydia@example.com").withDressCode("CASUAL").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withSize("42")
            .withEmail("anna@example.com").withDressCode("ACTIVE").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withSize("24")
            .withEmail("stefan@example.com").withDressCode("ACTIVE").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withSize("31")
            .withEmail("hans@example.com").withDressCode("ACTIVE").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withSize(VALID_SIZE_AMY)
            .withEmail(VALID_EMAIL_AMY).withDressCode(VALID_DRESSCODE_AMY).withDescriptions(VALID_DESCRIPTION_FRIEND)
            .build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withSize(VALID_SIZE_BOB)
            .withEmail(VALID_EMAIL_BOB).withDressCode(VALID_DRESSCODE_BOB).withDescriptions(VALID_DESCRIPTION_HUSBAND,
                    VALID_DESCRIPTION_FRIEND).build();


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
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

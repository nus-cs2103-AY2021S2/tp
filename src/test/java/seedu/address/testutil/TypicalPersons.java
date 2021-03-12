package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
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

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withBirthday("2000-10-10")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withBirthday("1988-07-21")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withBirthday("2006-01-07")
            .build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withBirthday("1962-03-27")
            .withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withBirthday("1998-08-03")
            .build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withBirthday("1997-12-15")
            .build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withBirthday("1961-08-26")
            .build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
            .withBirthday("1999-11-11")
            .build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller")
            .withBirthday("2005-05-05")
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withBirthday(VALID_BIRTHDAY_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
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
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

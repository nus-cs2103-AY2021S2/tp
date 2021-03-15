package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.RemindMe;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .build();

    public static final Module CS2103 = new Module(new Title("CS2103"));


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

    public static RemindMe getTypicalRemindMe() {
        RemindMe rm = new RemindMe();
        for (Person person : getTypicalPersons()) {
            rm.addPerson(person);
        }
        for (Module module : getTypicalModules()) {
            rm.addModule(module);
        }
        return rm;
    }

    public static ArrayList<Module> getTypicalModules() {
        return new ArrayList<Module>(Arrays.asList(CS2103));
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

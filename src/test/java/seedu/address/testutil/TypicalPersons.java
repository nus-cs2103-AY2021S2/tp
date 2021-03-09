package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.plan.Plan;

/**
 * A utility class containing a list of {@code Plan} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Plan ALICE = new PersonBuilder()
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withTags("friends").build();
    public static final Plan BENSON = new PersonBuilder()
            .withAddress("311, Clementi Ave 2, #02-25")
            .withTags("owesMoney", "friends").build();
    public static final Plan CARL = new PersonBuilder().withAddress("wall street").build();
    public static final Plan DANIEL = new PersonBuilder()
            .withAddress("10th street").withTags("friends").build();
    public static final Plan ELLE = new PersonBuilder().withAddress("michegan ave").build();
    public static final Plan FIONA = new PersonBuilder().withAddress("little tokyo").build();
    public static final Plan GEORGE = new PersonBuilder().withAddress("4th street").build();

    // Manually added
    public static final Plan HOON = new PersonBuilder().withAddress("little india").build();
    public static final Plan IDA = new PersonBuilder().withAddress("chicago ave").build();

    // Manually added - Plan's details found in {@code CommandTestUtil}
    public static final Plan AMY = new PersonBuilder().withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Plan BOB = new PersonBuilder().withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Plan plan : getTypicalPersons()) {
            ab.addPerson(plan);
        }
        return ab;
    }

    public static List<Plan> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

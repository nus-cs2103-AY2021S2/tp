package seedu.timeforwheels.testutil;

import static seedu.timeforwheels.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.VALID_DONE;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.VALID_TAG_FRAGILE;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.VALID_TAG_HEAVY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.timeforwheels.model.DeliveryList;
import seedu.timeforwheels.model.customer.Customer;

/**
 * A utility class containing a list of {@code Customer} objects to be used in tests.
 */
public class TypicalCustomers {

    public static final Customer ALICE = new CustomerBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withRemark("Busy").withDone("[✓]")
            .withTags("liquid").build();
    public static final Customer BENSON = new CustomerBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432").withRemark("Very Busy")
            .withTags("hot", "fragile").withDone("[✓]").build();
    public static final Customer CARL = new CustomerBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withDone("[✓]").withAddress("wall street").build();
    public static final Customer DANIEL = new CustomerBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withDone("[✓]").withAddress("10th street").withTags("heavy").build();
    public static final Customer ELLE = new CustomerBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withDone("[X]").withAddress("michegan ave").build();
    public static final Customer FIONA = new CustomerBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withDone("[X]").withAddress("little tokyo").build();
    public static final Customer GEORGE = new CustomerBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withDone("[X]").withAddress("4th street").build();

    // Manually added
    public static final Customer HOON = new CustomerBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Customer IDA = new CustomerBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Customer's details found in {@code CommandTestUtil}
    public static final Customer AMY = new CustomerBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRAGILE)
            .withDone(VALID_DONE).build();
    public static final Customer BOB = new CustomerBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HEAVY, VALID_TAG_FRAGILE)
            .withDone(VALID_DONE).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalCustomers() {} // prevents instantiation

    /**
     * Returns an {@code DeliveryList} with all the typical customers.
     */
    public static DeliveryList getTypicalDeliveryList() {
        DeliveryList ab = new DeliveryList();
        for (Customer customer : getTypicalCustomers()) {
            ab.addCustomer(customer);
        }
        return ab;
    }

    public static DeliveryList getCompletedDeliveryList() {
        DeliveryList ab = new DeliveryList();
        for (Customer customer : getCompletedCustomers()) {
            ab.addCustomer(customer);
        }
        return ab;
    }

    public static List<Customer> getTypicalCustomers() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Customer> getCompletedCustomers() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL));
    }

    public static List<Customer> getUncompletedCustomers() {
        return new ArrayList<>(Arrays.asList(ELLE, FIONA, GEORGE));
    }
}

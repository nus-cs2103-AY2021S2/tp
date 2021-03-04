package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPIRYDATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPIRYDATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.item.Item;

/**
 * A utility class containing a list of {@code Item} objects to be used in tests.
 */
public class TypicalItems {

    public static final Item ALICE = new ItemBuilder().withName("Alice Pauline")
        .withLocation("123, Jurong West Ave 6, #08-111").withExpiryDate("alice@example.com")
        .withPhone("94351253")
        .withTags("friends").build();
    public static final Item BENSON = new ItemBuilder().withName("Benson Meier")
        .withLocation("311, Clementi Ave 2, #02-25")
        .withExpiryDate("johnd@example.com").withPhone("98765432")
        .withTags("owesMoney", "friends").build();
    public static final Item CARL = new ItemBuilder().withName("Carl Kurz").withPhone("95352563")
        .withExpiryDate("heinz@example.com").withLocation("wall street").build();
    public static final Item DANIEL = new ItemBuilder().withName("Daniel Meier").withPhone("87652533")
        .withExpiryDate("cornelia@example.com").withLocation("10th street").withTags("friends").build();
    public static final Item ELLE = new ItemBuilder().withName("Elle Meyer").withPhone("9482224")
        .withExpiryDate("werner@example.com").withLocation("michegan ave").build();
    public static final Item FIONA = new ItemBuilder().withName("Fiona Kunz").withPhone("9482427")
        .withExpiryDate("lydia@example.com").withLocation("little tokyo").build();
    public static final Item GEORGE = new ItemBuilder().withName("George Best").withPhone("9482442")
        .withExpiryDate("anna@example.com").withLocation("4th street").build();

    // Manually added
    public static final Item HOON = new ItemBuilder().withName("Hoon Meier").withPhone("8482424")
        .withExpiryDate("stefan@example.com").withLocation("little india").build();
    public static final Item IDA = new ItemBuilder().withName("Ida Mueller").withPhone("8482131")
        .withExpiryDate("hans@example.com").withLocation("chicago ave").build();

    // Manually added - Item's details found in {@code CommandTestUtil}
    public static final Item AMY = new ItemBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
        .withExpiryDate(VALID_EXPIRYDATE_AMY).withLocation(VALID_LOCATION_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Item BOB = new ItemBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
        .withExpiryDate(VALID_EXPIRYDATE_BOB).withLocation(VALID_LOCATION_BOB).withTags(VALID_TAG_HUSBAND,
            VALID_TAG_FRIEND)
        .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalItems() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical itemss.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Item item : getTypicalItems()) {
            ab.addItem(item);
        }
        return ab;
    }

    public static List<Item> getTypicalItems() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

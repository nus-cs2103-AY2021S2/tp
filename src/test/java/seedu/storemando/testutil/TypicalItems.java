package seedu.storemando.testutil;

import static seedu.storemando.logic.commands.CommandTestUtil.VALID_EXPIRYDATE_AMY;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_EXPIRYDATE_BOB;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_LOCATION_AMY;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_QUANTITY_AMY;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_QUANTITY_BOB;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.storemando.model.StoreMando;
import seedu.storemando.model.item.Item;

/**
 * A utility class containing a list of {@code Item} objects to be used in tests.
 */
public class TypicalItems {

    public static final Item ALICE = new ItemBuilder().withName("Chocolate Milk")
        .withLocation("Freezer").withExpiryDate("2021-08-03")
        .withQuantity("10")
        .withTags("Expiring").build();
    public static final Item BENSON = new ItemBuilder().withName("Bread")
        .withLocation("Dining Table")
        .withExpiryDate("2020-08-08").withQuantity("1")
        .withTags("Expiring", "Favourite").build();
    public static final Item CARL = new ItemBuilder().withName("Oil").withQuantity("1")
        .withExpiryDate("2023-10-10").withLocation("Kitchen drawer").build();
    public static final Item DANIEL = new ItemBuilder().withName("Cadbury").withQuantity("2")
        .withExpiryDate("2020-10-10").withLocation("Bedroom").withTags("Melt").build();
    public static final Item ELLE = new ItemBuilder().withName("Nescafe Powder").withQuantity("1")
        .withExpiryDate("2020-10-10").withLocation("Kitchen drawer").build();
    public static final Item FIONA = new ItemBuilder().withName("Lipton Tea Sachets").withQuantity("30")
        .withExpiryDate("2020-10-10").withLocation("Kitchen Cabinet").build();
    public static final Item GEORGE = new ItemBuilder().withName("Instant Prata").withQuantity("20")
        .withExpiryDate("2021-10-08").withLocation("Freezer").build();

    // Manually added
    public static final Item HOON = new ItemBuilder().withName("Hoon Meier").withQuantity("8482424")
        .withExpiryDate("2010-10-10").withLocation("little india").build();
    public static final Item IDA = new ItemBuilder().withName("Ida Mueller").withQuantity("8482131")
        .withExpiryDate("2019-10-08").withLocation("chicago ave").build();


    // Manually added - Item's details found in {@code CommandTestUtil}
    public static final Item AMY = new ItemBuilder().withName(VALID_NAME_AMY)
        .withQuantity(VALID_QUANTITY_AMY).withExpiryDate(VALID_EXPIRYDATE_AMY).withLocation(VALID_LOCATION_AMY)
        .withTags(VALID_TAG_FRIEND).build();
    public static final Item BOB = new ItemBuilder().withName(VALID_NAME_BOB).withQuantity(VALID_QUANTITY_BOB)
        .withExpiryDate(VALID_EXPIRYDATE_BOB).withLocation(VALID_LOCATION_BOB).withTags(VALID_TAG_HUSBAND,
            VALID_TAG_FRIEND)
        .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalItems() {
    } // prevents instantiation

    /**
     * Returns an {@code StoreMando} with all the typical items.
     */
    public static StoreMando getTypicalStoreMando() {
        StoreMando ab = new StoreMando();
        for (Item item : getTypicalItems()) {
            ab.addItem(item);
        }
        return ab;
    }

    /**
     * Returns an {@code StoreMando} with all the typical items sorted by quantity.
     */
    public static StoreMando getTypicalStoreMandoSortedByQuantity() {
        StoreMando ab = new StoreMando();
        for (Item item : getSortedByQuantityTypicalItems()) {
            ab.addItem(item);
        }
        return ab;
    }

    /**
     * Returns an {@code StoreMando} with all the typical itemss.
     */
    public static StoreMando getTypicalStoreMandoSortedByExpiryDate() {
        StoreMando ab = new StoreMando();
        for (Item item : getSortedByExpiryDateTypicalItems()) {
            ab.addItem(item);
        }
        return ab;
    }

    public static List<Item> getTypicalItems() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Item> getSortedByQuantityTypicalItems() {
        return new ArrayList<>(Arrays.asList(BENSON, ELLE, CARL, DANIEL, ALICE, GEORGE, FIONA));
    }

    public static List<Item> getSortedByExpiryDateTypicalItems() {
        return new ArrayList<>(Arrays.asList(BENSON, ELLE, DANIEL, FIONA, ALICE, GEORGE, CARL));
    }
}

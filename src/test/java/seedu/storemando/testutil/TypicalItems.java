package seedu.storemando.testutil;

import static seedu.storemando.logic.commands.CommandTestUtil.VALID_EXPIRYDATE_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_EXPIRYDATE_CHEESE;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_LOCATION_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_LOCATION_CHEESE;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_NAME_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_NAME_CHEESE;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_QUANTITY_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_QUANTITY_CHEESE;
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

    public static final Item MILK = new ItemBuilder().withName("Chocolate Milk")
        .withLocation("Freezer").withExpiryDate("2021-08-03")
        .withQuantity("10")
        .withTags("Expiring").build();
    public static final Item BREAD = new ItemBuilder().withName("Bread")
        .withLocation("Dining Table")
        .withExpiryDate("2020-08-08").withQuantity("1")
        .withTags("Expiring", "Favourite").build();
    public static final Item OIL = new ItemBuilder().withName("Oil").withQuantity("1")
        .withExpiryDate("2023-10-10").withLocation("Kitchen drawer").build();
    public static final Item CADBURY = new ItemBuilder().withName("Cadbury").withQuantity("2")
        .withExpiryDate("2020-10-10").withLocation("Bedroom").withTags("Melt").build();
    public static final Item NESCAFE = new ItemBuilder().withName("Nescafe Powder").withQuantity("1")
        .withExpiryDate("2020-10-10").withLocation("Kitchen drawer").build();
    public static final Item LIPTON = new ItemBuilder().withName("Lipton Tea Sachets").withQuantity("30")
        .withExpiryDate("2020-10-10").withLocation("Kitchen Cabinet").build();
    public static final Item PRATA = new ItemBuilder().withName("Instant Prata").withQuantity("20")
        .withExpiryDate("2021-10-08").withLocation("Freezer").build();

    // Manually added
    public static final Item TOOTHBRUSH = new ItemBuilder().withName("Electric Toothbrush").withQuantity("2")
        .withExpiryDate("No Expiry Date").withLocation("Toilet Cabinet").build();
    public static final Item CHAIR = new ItemBuilder().withName("Dining Chair").withQuantity("6")
        .withExpiryDate("No Expiry Date").withLocation("Storeroom").build();


    // Manually added - Item's details found in {@code CommandTestUtil}
    public static final Item CHEESE = new ItemBuilder().withName(VALID_NAME_CHEESE)
        .withQuantity(VALID_QUANTITY_CHEESE).withExpiryDate(VALID_EXPIRYDATE_CHEESE).withLocation(VALID_LOCATION_CHEESE)
        .withTags(VALID_TAG_FRIEND).build();
    public static final Item BANANA = new ItemBuilder().withName(VALID_NAME_BANANA).withQuantity(VALID_QUANTITY_BANANA)
        .withExpiryDate(VALID_EXPIRYDATE_BANANA).withLocation(VALID_LOCATION_BANANA).withTags(VALID_TAG_HUSBAND,
            VALID_TAG_FRIEND)
        .build();

    public static final String KEYWORD_MATCHING_TOOTHBRUSH = "Toothbrush"; // A keyword that matches TOOTHBRUSH

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
        return new ArrayList<>(Arrays.asList(MILK, BREAD, OIL, CADBURY, NESCAFE, LIPTON, PRATA));
    }

    public static List<Item> getSortedByQuantityTypicalItems() {
        return new ArrayList<>(Arrays.asList(BREAD, NESCAFE, OIL, CADBURY, MILK, PRATA, LIPTON));
    }

    public static List<Item> getSortedByExpiryDateTypicalItems() {
        return new ArrayList<>(Arrays.asList(BREAD, NESCAFE, CADBURY, LIPTON, MILK, PRATA, OIL));
    }
}

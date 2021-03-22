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

    public static final Item APPLE = new ItemBuilder().withName("Apples")
        .withLocation("Kitchen Basket").withExpiryDate("2021-04-08")
        .withQuantity("9")
        .withTags("Expiring").build();
    public static final Item FAKEAPPLE = new ItemBuilder().withName("apples")
        .withLocation("Kitchen Basket").withExpiryDate("2021-04-08")
        .withQuantity("9")
        .withTags("Expiring").build();
    public static final Item BREAD = new ItemBuilder().withName("Bread")
        .withLocation("Dining Table")
        .withExpiryDate("2021-04-08").withQuantity("1")
        .withTags("Expiring", "Favourite").build();
    public static final Item FAKEBREAD = new ItemBuilder().withName("Bread")
        .withLocation("dining Table")
        .withExpiryDate("2021-04-08").withQuantity("1")
        .withTags("Expiring", "Favourite").build();
    public static final Item CREAM = new ItemBuilder().withName("Cream Cheese").withQuantity("1")
        .withExpiryDate("2023-10-10").withLocation("Kitchen Drawer").build();
    public static final Item DONUT = new ItemBuilder().withName("Donut").withQuantity("5")
        .withExpiryDate("2021-10-10").withLocation("Refrigerator").withTags("Favourite").build();
    public static final Item EGGS = new ItemBuilder().withName("Eggs").withQuantity("10")
        .withExpiryDate("2021-10-10").withLocation("Kitchen Drawer").build();
    public static final Item FLOUR = new ItemBuilder().withName("Flour").withQuantity("3")
        .withExpiryDate("2021-10-09").withLocation("Kitchen Cabinet").build();
    public static final Item GINGER = new ItemBuilder().withName("Ginger").withQuantity("20")
        .withExpiryDate("2021-10-08").withLocation("Kitchen Basket").build();

    // Manually added
    public static final Item HEATER = new ItemBuilder().withName("Heater").withQuantity("2")
        .withExpiryDate("No Expiry Date").withLocation("Toilet Cabinet").build();
    public static final Item IRON = new ItemBuilder().withName("Ironing Board").withQuantity("6")
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
     * Returns an {@code StoreMando} with all the typical items sorted by increasing quantity.
     */
    public static StoreMando getTypicalStoreMandoSortedByIncreasingQuantity() {
        StoreMando ab = new StoreMando();
        for (Item item : getSortedByIncreasingQuantityTypicalItems()) {
            ab.addItem(item);
        }
        return ab;
    }

    /**
     * Returns an {@code StoreMando} with all the typical items sorted by decreasing quantity.
     */
    public static StoreMando getTypicalStoreMandoSortedByDecreasingQuantity() {
        StoreMando ab = new StoreMando();
        for (Item item : getSortedByDecreasingQuantityTypicalItems()) {
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
        return new ArrayList<>(Arrays.asList(APPLE, BREAD, CREAM, DONUT, EGGS, FLOUR, GINGER));
    }

    public static List<Item> getSortedByIncreasingQuantityTypicalItems() {
        return new ArrayList<>(Arrays.asList(BREAD, CREAM, FLOUR, DONUT, APPLE, EGGS, GINGER));
    }

    public static List<Item> getSortedByDecreasingQuantityTypicalItems() {
        return new ArrayList<>(Arrays.asList(GINGER, EGGS, APPLE, DONUT, FLOUR, BREAD, CREAM));
    }

    public static List<Item> getSortedByExpiryDateTypicalItems() {
        return new ArrayList<>(Arrays.asList(BREAD, APPLE, GINGER, FLOUR, DONUT, EGGS, CREAM));
    }
}

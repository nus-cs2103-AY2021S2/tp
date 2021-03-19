package seedu.storemando.model.item;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.storemando.testutil.TypicalItems.APPLE;
import static seedu.storemando.testutil.TypicalItems.BREAD;
import static seedu.storemando.testutil.TypicalItems.CREAM;
import static seedu.storemando.testutil.TypicalItems.DONUT;
import static seedu.storemando.testutil.TypicalItems.EGGS;
import static seedu.storemando.testutil.TypicalItems.FLOUR;
import static seedu.storemando.testutil.TypicalItems.HEATER;
import static seedu.storemando.testutil.TypicalItems.IRON;

import org.junit.jupiter.api.Test;

import seedu.storemando.testutil.ItemBuilder;

class ItemComparatorByExpiryDateTest {

    private final ItemComparatorByExpiryDate comparator = new ItemComparatorByExpiryDate();

    @Test
    public void compare_sameItem_success() {
        assertEquals(0, comparator.compare(HEATER, HEATER));
        assertEquals(0, comparator.compare(DONUT, DONUT));
    }

    @Test
    public void compare_itemsWithDifferentExpiryDate_success() {
        assertTrue(comparator.compare(APPLE, CREAM) < 0);
        assertTrue(comparator.compare(BREAD, DONUT) < 0);
        assertTrue(comparator.compare(APPLE, EGGS) < 0);

        assertTrue(comparator.compare(EGGS, FLOUR) > 0);
        assertTrue(comparator.compare(CREAM, DONUT) > 0);
        assertTrue(comparator.compare(CREAM, FLOUR) > 0);

    }
    @Test
    public void compare_itemsWithSameExpiryDate_success() {
        assertTrue(comparator.compare(APPLE, BREAD) > 0);
    }

    @Test
    public void compare_itemsWithNoExpiryDate_success() {
        assertTrue(comparator.compare(HEATER, IRON) < 0);
        assertTrue(comparator.compare(IRON, HEATER) > 0);
    }

    @Test
    public void compare_itemsWithSameAttributesExceptItemName_success() {
        Item kettle = new ItemBuilder().withName("Kettle").withQuantity("6")
            .withExpiryDate("No Expiry Date").withLocation("Storeroom").build();
        Item oranges = new ItemBuilder().withName("Oranges")
            .withLocation("Kitchen Basket").withExpiryDate("2021-04-08")
            .withQuantity("9")
            .withTags("Expiring").build();

        assertTrue(comparator.compare(kettle, IRON) > 0);
        assertTrue(comparator.compare(oranges, APPLE) > 0);
    }

    @Test
    public void compare_itemsWithSameAttributesExceptLocation_success() {
        Item ironingBoard = new ItemBuilder().withName("Ironing Board").withQuantity("6")
            .withExpiryDate("No Expiry Date").withLocation("Kitchen").build();
        Item apples = new ItemBuilder().withName("Apples")
            .withLocation("Hall").withExpiryDate("2021-04-08")
            .withQuantity("9")
            .withTags("Expiring").build();

        assertTrue(comparator.compare(ironingBoard, IRON) < 0);
        assertTrue(comparator.compare(apples, APPLE) < 0);
    }
}
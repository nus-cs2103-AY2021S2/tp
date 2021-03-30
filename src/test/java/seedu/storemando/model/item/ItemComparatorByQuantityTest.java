package seedu.storemando.model.item;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.testutil.TypicalItems.APPLE;
import static seedu.storemando.testutil.TypicalItems.BREAD;
import static seedu.storemando.testutil.TypicalItems.CREAM;
import static seedu.storemando.testutil.TypicalItems.DONUT;
import static seedu.storemando.testutil.TypicalItems.EGGS;
import static seedu.storemando.testutil.TypicalItems.FLOUR;
import static seedu.storemando.testutil.TypicalItems.GINGER;
import static seedu.storemando.testutil.TypicalItems.HEATER;

import org.junit.jupiter.api.Test;

import seedu.storemando.testutil.ItemBuilder;

class ItemComparatorByIncreasingQuantityTest {

    private final ItemComparatorByIncreasingQuantity comparator = new ItemComparatorByIncreasingQuantity();

    @Test
    public void compare_sameItem_success() {
        assertEquals(0, comparator.compare(HEATER, HEATER));
        assertEquals(0, comparator.compare(DONUT, DONUT));
    }

    @Test
    public void compare_itemsWithSameQuantities_success() {
        assertTrue(comparator.compare(BREAD, CREAM) < 0);
    }

    @Test
    public void compare_itemsWithDifferentQuantities_success() {
        assertTrue(comparator.compare(APPLE, BREAD) > 0);
        assertTrue(comparator.compare(DONUT, BREAD) > 0);
        assertTrue(comparator.compare(EGGS, FLOUR) > 0);

        assertTrue(comparator.compare(APPLE, EGGS) < 0);
        assertTrue(comparator.compare(BREAD, APPLE) < 0);
        assertTrue(comparator.compare(CREAM, FLOUR) < 0);

    }

    @Test
    public void compare_itemsWithSameQuantityAndLocation_success() {
        Item peanuts = new ItemBuilder().withName("Peanuts").withQuantity("20")
            .withLocation("Kitchen Basket").build();
        assertTrue(comparator.compare(peanuts, GINGER) > 0);
    }
}

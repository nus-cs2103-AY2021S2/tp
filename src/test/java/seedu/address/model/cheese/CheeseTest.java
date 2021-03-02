package seedu.address.model.cheese;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_CAMEMBERT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MANUFACTURE_DATE_1;
import static seedu.address.testutil.TypicalCheese.CAMEMBERT;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CheeseBuilder;

/**
 * Represents a Cheese in the address book.
 */
public class CheeseTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Cheese cheese = new CheeseBuilder().build();
    }

    @Test
    public void isSameCheese() {
        assertTrue(CAMEMBERT.isSameCheese(CAMEMBERT));

        assertFalse(CAMEMBERT.isSameCheese(null));

        Cheese editedCamembert =
            new CheeseBuilder(CAMEMBERT).withExpiryDate("2021-08-04")
                .withCheeseType("Brie").build();
        assertTrue(CAMEMBERT.isSameCheese(editedCamembert));

        Cheese editedCamembert2 =
            new CheeseBuilder().withCheeseType(VALID_CHEESE_TYPE_CAMEMBERT)
                .withExpiryDate(VALID_EXPIRY_DATE_1)
                .withManufactureDate(VALID_MANUFACTURE_DATE_1)
                .build();
        assertFalse(CAMEMBERT.isSameCheese(editedCamembert2));
    }

    @Test
    public void equals() {
        Cheese camembertCopy = new CheeseBuilder(CAMEMBERT).build();
        assertTrue(CAMEMBERT.equals(camembertCopy));

        assertTrue(CAMEMBERT.equals(CAMEMBERT));

        assertFalse(CAMEMBERT.equals(null));

        assertFalse(CAMEMBERT.equals(5));

        Cheese editedCamembert =
            new CheeseBuilder(CAMEMBERT).withExpiryDate("2021-08-04")
                .withCheeseType("Brie").build();
        assertFalse(CAMEMBERT.equals(editedCamembert));
    }
}

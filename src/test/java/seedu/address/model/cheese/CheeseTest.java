package seedu.address.model.cheese;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalCheese.CAMEMBERT;
import static seedu.address.testutil.TypicalCheese.FETA;

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
        // same object -> return true
        assertTrue(CAMEMBERT.isSameCheese(CAMEMBERT));

        // null -> returns false
        assertFalse(CAMEMBERT.isSameCheese(null));

        // same id , all other attributes different -> returns true
        Cheese editedCamembert =
            new CheeseBuilder(CAMEMBERT).withExpiryDate(VALID_EXPIRY_DATE_2)
                .withCheeseType(VALID_CHEESE_TYPE_FETA)
                .withManufactureDate(VALID_MANUFACTURE_DATE_2).build();
        assertTrue(CAMEMBERT.isSameCheese(editedCamembert));

        // different id, all other attributes same -> returns false
        Cheese editedCamembert2 =
            new CheeseBuilder().withCheeseType(VALID_CHEESE_TYPE_CAMEMBERT)
                .withExpiryDate(VALID_EXPIRY_DATE_1)
                .withManufactureDate(VALID_MANUFACTURE_DATE_1)
                .build();
        assertFalse(CAMEMBERT.isSameCheese(editedCamembert2));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Cheese camembertCopy = new CheeseBuilder(CAMEMBERT).build();
        assertTrue(CAMEMBERT.equals(camembertCopy));

        // same object -> returns true
        assertTrue(CAMEMBERT.equals(CAMEMBERT));

        // null --> returns false
        assertFalse(CAMEMBERT.equals(null));

        // different type -> returns false
        assertFalse(CAMEMBERT.equals(5));

        // different cheese -> returns false
        assertFalse(CAMEMBERT.equals(FETA));

        // different expire date -> returns false
        Cheese editedCamembert =
            new CheeseBuilder(CAMEMBERT).withExpiryDate(VALID_EXPIRY_DATE_2).build();
        assertFalse(CAMEMBERT.equals(editedCamembert));

        // different cheese type -> returns false
        editedCamembert = new CheeseBuilder(CAMEMBERT)
                .withCheeseType(VALID_CHEESE_TYPE_FETA).build();
        assertFalse(CAMEMBERT.equals(editedCamembert));

        // different id --> return false
        editedCamembert = new CheeseBuilder()
                .withCheeseType(VALID_CHEESE_TYPE_CAMEMBERT)
                .withManufactureDate(VALID_MANUFACTURE_DATE_1)
                .withExpiryDate(VALID_EXPIRY_DATE_1).build();
        assertFalse(CAMEMBERT.equals(editedCamembert));

        // different manufacturing date -> return false
        editedCamembert = new CheeseBuilder(CAMEMBERT)
                .withManufactureDate(VALID_MANUFACTURE_DATE_2).build();
        assertFalse(CAMEMBERT.equals(editedCamembert));
    }
}

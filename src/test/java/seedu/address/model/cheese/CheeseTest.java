package seedu.address.model.cheese;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EXPIRY_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MANUFACTURE_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MATURITY_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_CAMEMBERT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_FETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MANUFACTURE_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MANUFACTURE_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATURITY_DATE_2;
import static seedu.address.testutil.Assert.assertThrows;
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
        assertThrows(NullPointerException.class, () -> new CheeseBuilder().withCheeseType(null).build());
    }

    @Test
    public void constructor_maturityDateBeforeManufactureDate_throwsIllegalArgumentException() {
        CheeseBuilder cheeseBuilder = new CheeseBuilder().withMaturityDate(INVALID_MATURITY_DATE_1);
        assertThrows(IllegalArgumentException.class,
            "The maturity date of the cheese should be after the manufacture date.", () -> cheeseBuilder.build());
    }

    @Test
    public void constructor_expiryDateBeforeManufactureDate_throwsIllegalArgumentException() {
        CheeseBuilder cheeseBuilder = new CheeseBuilder().withExpiryDate(INVALID_EXPIRY_DATE_1);
        assertThrows(IllegalArgumentException.class,
            "The expiry date of the cheese should be after the manufacture date.", () -> cheeseBuilder.build());
    }

    @Test
    public void constructor_expiryDateBeforeMaturityDate_throwsIllegalArgumentException() {
        CheeseBuilder cheeseBuilder = new CheeseBuilder().withManufactureDate(INVALID_MANUFACTURE_DATE_1)
                                            .withExpiryDate(INVALID_EXPIRY_DATE_1);
        assertThrows(IllegalArgumentException.class,
            "The expiry date of the cheese should be after the maturity date.", () -> cheeseBuilder.build());
    }

    @Test
    public void idAutoIncrement() {
        final int cheeseId = 10;
        Cheese cheese = new CheeseBuilder().withCheeseId(cheeseId).build();
        Cheese cheese2 = new CheeseBuilder().build();
        assertTrue(cheese.getCheeseId().compareTo(cheese2.getCheeseId()) < 0);
    }

    @Test
    public void isSameCheese() {
        // Same object -> return true
        assertTrue(CAMEMBERT.isSameCheese(CAMEMBERT));

        // Null -> returns false
        assertFalse(CAMEMBERT.isSameCheese(null));

        // Same id, all other attributes different -> returns true
        Cheese editedCamembert =
            new CheeseBuilder(CAMEMBERT).withExpiryDate(VALID_EXPIRY_DATE_2)
                .withCheeseType(VALID_CHEESE_TYPE_FETA)
                .withManufactureDate(VALID_MANUFACTURE_DATE_2).build();
        assertTrue(CAMEMBERT.isSameCheese(editedCamembert));

        // Different id, all other attributes same -> returns false
        Cheese newSimilarCamembert =
            new CheeseBuilder().withCheeseType(VALID_CHEESE_TYPE_CAMEMBERT)
                .withExpiryDate(VALID_EXPIRY_DATE_1)
                .withManufactureDate(VALID_MANUFACTURE_DATE_1)
                .build();

        assertFalse(CAMEMBERT.isSameCheese(newSimilarCamembert));
    }

    @Test
    public void equals() {
        // Same values -> returns true
        Cheese camembertCopy = new CheeseBuilder(CAMEMBERT).build();
        assertTrue(CAMEMBERT.equals(camembertCopy));

        // Same object -> returns true
        assertTrue(CAMEMBERT.equals(CAMEMBERT));

        // Null -> returns false
        assertFalse(CAMEMBERT.equals(null));

        // Different type -> returns false
        assertFalse(CAMEMBERT.equals(5));

        // Different cheese -> returns false
        assertFalse(CAMEMBERT.equals(FETA));

        // Different id --> return false
        Cheese editedCamembert = new CheeseBuilder()
                .withCheeseType(VALID_CHEESE_TYPE_CAMEMBERT)
                .withManufactureDate(VALID_MANUFACTURE_DATE_1)
                .withExpiryDate(VALID_EXPIRY_DATE_1).build();
        assertFalse(CAMEMBERT.equals(editedCamembert));

        // Different cheese type -> returns false
        editedCamembert = new CheeseBuilder(CAMEMBERT)
                .withCheeseType(VALID_CHEESE_TYPE_FETA).build();
        assertFalse(CAMEMBERT.equals(editedCamembert));

        // Different manufacturing date -> return false
        editedCamembert = new CheeseBuilder(CAMEMBERT)
                .withManufactureDate(VALID_MANUFACTURE_DATE_2).build();
        assertFalse(CAMEMBERT.equals(editedCamembert));

        // Different maturity date --> return false
        editedCamembert = new CheeseBuilder(CAMEMBERT)
                .withMaturityDate(VALID_MATURITY_DATE_2).build();
        assertFalse(CAMEMBERT.equals(editedCamembert));

        // Different expire date -> returns false
        editedCamembert =
            new CheeseBuilder(CAMEMBERT).withExpiryDate(VALID_EXPIRY_DATE_2).build();
        assertFalse(CAMEMBERT.equals(editedCamembert));
    }
}

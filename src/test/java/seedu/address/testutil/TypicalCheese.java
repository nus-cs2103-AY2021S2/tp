package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_CAMEMBERT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_FETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MANUFACTURE_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MANUFACTURE_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATURITY_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATURITY_DATE_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.cheese.Cheese;

/**
 * A utility class containing a list of {@code Cheese} objects to be used in tests.
 */
public class TypicalCheese {
    public static final Cheese CAMEMBERT = new CheeseBuilder().withCheeseType(VALID_CHEESE_TYPE_CAMEMBERT)
        .withManufactureDate(VALID_MANUFACTURE_DATE_1).withMaturityDate(VALID_MATURITY_DATE_1)
        .withExpiryDate(VALID_EXPIRY_DATE_1).withCheeseId(1)
        .build();
    public static final Cheese FETA = new CheeseBuilder().withCheeseType(VALID_CHEESE_TYPE_FETA)
        .withManufactureDate(VALID_MANUFACTURE_DATE_2).withMaturityDate(VALID_MATURITY_DATE_2)
        .withExpiryDate(VALID_EXPIRY_DATE_2).withCheeseId(2)
        .build();

    public static List<Cheese> getTypicalCheeses() {
        return new ArrayList<>(Arrays.asList(CAMEMBERT, FETA));
    }
}

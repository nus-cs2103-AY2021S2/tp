package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_CAMEMBERT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_FETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MANUFACTURE_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MANUFACTURE_DATE_2;

import seedu.address.model.cheese.Cheese;

public class TypicalCheese {
    public static final Cheese CAMEMBERT = new CheeseBuilder().withCheeseType(VALID_CHEESE_TYPE_CAMEMBERT)
        .withManufactureDate(VALID_MANUFACTURE_DATE_1).withExpiryDate(VALID_EXPIRY_DATE_1)
        .build();
    public static final Cheese FETA = new CheeseBuilder().withCheeseType(VALID_CHEESE_TYPE_FETA)
        .withManufactureDate(VALID_MANUFACTURE_DATE_2).withExpiryDate(VALID_EXPIRY_DATE_2)
        .build();
}

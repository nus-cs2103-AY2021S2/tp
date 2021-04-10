package chim.testutil;

import static chim.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_BRIE;
import static chim.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_CAMEMBERT;
import static chim.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_FETA;
import static chim.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_GOUDA;
import static chim.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_MOZZARELLA;
import static chim.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_1;
import static chim.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_2;
import static chim.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_3;
import static chim.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_4;
import static chim.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_5;
import static chim.logic.commands.CommandTestUtil.VALID_MANUFACTURE_DATE_1;
import static chim.logic.commands.CommandTestUtil.VALID_MANUFACTURE_DATE_2;
import static chim.logic.commands.CommandTestUtil.VALID_MANUFACTURE_DATE_3;
import static chim.logic.commands.CommandTestUtil.VALID_MANUFACTURE_DATE_4;
import static chim.logic.commands.CommandTestUtil.VALID_MANUFACTURE_DATE_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chim.model.cheese.Cheese;

/**
 * A utility class containing a list of {@code Cheese} objects to be used in tests.
 */
public class TypicalCheese {
    public static final Cheese CAMEMBERT = new CheeseBuilder().withCheeseType(VALID_CHEESE_TYPE_CAMEMBERT)
            .withManufactureDate(VALID_MANUFACTURE_DATE_1)
            .withExpiryDate(VALID_EXPIRY_DATE_1).withCheeseId(1)
            .withAssignStatus(true)
            .build();

    public static final Cheese FETA = new CheeseBuilder().withCheeseType(VALID_CHEESE_TYPE_FETA)
            .withManufactureDate(VALID_MANUFACTURE_DATE_2)
            .withExpiryDate(VALID_EXPIRY_DATE_2).withCheeseId(2)
            .withAssignStatus(true)
            .build();

    public static final Cheese BRIE = new CheeseBuilder().withCheeseType(VALID_CHEESE_TYPE_BRIE)
            .withManufactureDate(VALID_MANUFACTURE_DATE_3)
            .withExpiryDate(VALID_EXPIRY_DATE_3).withCheeseId(3)
            .withAssignStatus(false)
            .build();

    public static final Cheese MOZZARELLA = new CheeseBuilder().withCheeseType(VALID_CHEESE_TYPE_MOZZARELLA)
            .withManufactureDate(VALID_MANUFACTURE_DATE_4).withExpiryDate(VALID_EXPIRY_DATE_4)
            .withCheeseId(4).withAssignStatus(false)
            .build();

    public static final Cheese CAMEMBERT_2 = new CheeseBuilder().withCheeseType(VALID_CHEESE_TYPE_CAMEMBERT)
            .withManufactureDate(VALID_MANUFACTURE_DATE_1).withExpiryDate(VALID_EXPIRY_DATE_1)
            .withCheeseId(5).withAssignStatus(true)
            .build();

    public static final Cheese GOUDA = new CheeseBuilder().withCheeseType(VALID_CHEESE_TYPE_GOUDA)
            .withManufactureDate(VALID_MANUFACTURE_DATE_5).withExpiryDate(VALID_EXPIRY_DATE_5)
            .withCheeseId(6).withAssignStatus(false)
            .build();


    public static List<Cheese> getTypicalCheeses() {
        return new ArrayList<>(Arrays.asList(CAMEMBERT, FETA, BRIE, MOZZARELLA, CAMEMBERT_2, GOUDA));
    }
}

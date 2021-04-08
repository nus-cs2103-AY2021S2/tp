package chim.testutil;

import static chim.logic.parser.CliSyntax.PREFIX_CHEESE_TYPE;
import static chim.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static chim.logic.parser.CliSyntax.PREFIX_MANUFACTURE_DATE;
import static chim.logic.parser.CliSyntax.PREFIX_QUANTITY;

import chim.logic.commands.AddCheeseCommand;
import chim.model.cheese.Cheese;

/**
 * A utility class for Cheese.
 */
public class CheeseUtil {

    /**
     * Returns an add command string for adding the {@code cheese}.
     */
    public static String getAddCommand(Cheese cheese, int numberOfCheeses) {
        return AddCheeseCommand.COMMAND_WORD + " " + getOrderDetails(cheese, numberOfCheeses);
    }

    /**
     * Returns the part of command string for the given {@code customer}'s details.
     */
    public static String getOrderDetails(Cheese cheese, int numberOfCheeses) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_CHEESE_TYPE + cheese.getCheeseType().value + " ");
        sb.append(PREFIX_QUANTITY + Integer.toString(numberOfCheeses) + " ");
        sb.append(PREFIX_MANUFACTURE_DATE + cheese.getManufactureDate().toJsonString() + " ");
        cheese.getExpiryDate().ifPresent(x -> sb.append(PREFIX_EXPIRY_DATE + x.toJsonString() + " "));
        return sb.toString();
    }
}

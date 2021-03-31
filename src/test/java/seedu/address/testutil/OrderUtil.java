package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CHEESE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import seedu.address.logic.commands.AddOrderCommand;
import seedu.address.model.customer.Phone;
import seedu.address.model.order.Order;


/**
 * A utility class for Order.
 */
public class OrderUtil {

    /**
     * Returns an add command string for adding the {@code order}.
     */
    public static String getAddCommand(Order order, Phone phone) {
        return AddOrderCommand.COMMAND_WORD + " " + getOrderDetails(order, phone);
    }

    /**
     * Returns the part of command string for the given {@code customer}'s details.
     */
    public static String getOrderDetails(Order order, Phone phone) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_CHEESE_TYPE + order.getCheeseType().value + " ");
        sb.append(PREFIX_PHONE + phone.value + " ");
        sb.append(PREFIX_QUANTITY + order.getQuantity().toString() + " ");
        sb.append(PREFIX_ORDER_DATE + order.getOrderDate().toJsonString() + " ");
        return sb.toString();
    }
}

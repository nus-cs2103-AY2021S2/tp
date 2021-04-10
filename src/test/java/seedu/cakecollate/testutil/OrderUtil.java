package seedu.cakecollate.testutil;

import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_ORDER_DESCRIPTION;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Map;
import java.util.Set;

import seedu.cakecollate.logic.commands.AddCommand;
import seedu.cakecollate.logic.commands.EditCommand.EditOrderDescriptor;
import seedu.cakecollate.model.order.Order;
import seedu.cakecollate.model.order.OrderDescription;
import seedu.cakecollate.model.tag.Tag;

/**
 * A utility class for Order.
 */
public class OrderUtil {

    /**
     * Returns an add command string for adding the {@code order}.
     */
    public static String getAddCommand(Order order) {
        return AddCommand.COMMAND_WORD + " " + getOrderDetails(order);
    }

    /**
     * Returns the part of command string for the given {@code order}'s details.
     */
    public static String getOrderDetails(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + order.getName().fullName + " ");
        sb.append(PREFIX_PHONE + order.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + order.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + order.getAddress().value + " ");

        // for each order description in map, for quantity number of times, add order description to command string
        for (Map.Entry<OrderDescription, Integer> entry : order.getOrderDescriptions().entrySet()) {
            OrderDescription o = entry.getKey();
            for (int i = 0; i < entry.getValue(); i++) {
                sb.append(PREFIX_ORDER_DESCRIPTION + o.value + " ");
            }
        }

        order.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );

        sb.append(PREFIX_DATE + order.getDeliveryDate().toString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditOrderDescriptor}'s details.
     */
    public static String getEditOrderDescriptorDetails(EditOrderDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));

        if (descriptor.getOrderDescriptions().isPresent()) {
            Map<OrderDescription, Integer> orderDescriptionMap = descriptor.getOrderDescriptions().get();
            if (orderDescriptionMap.isEmpty()) {
                sb.append(PREFIX_ORDER_DESCRIPTION);
            } else {
                // for each order description in map, for quantity times, add order description to command string
                for (Map.Entry<OrderDescription, Integer> entry : orderDescriptionMap.entrySet()) {
                    OrderDescription o = entry.getKey();
                    for (int i = 0; i < entry.getValue(); i++) {
                        sb.append(PREFIX_ORDER_DESCRIPTION + o.value + " ");
                    }
                }
            }
        }

        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG).append(" ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        descriptor.getDeliveryDate()
                .ifPresent(deliveryDate -> sb.append(PREFIX_DATE).append(deliveryDate.toString()).append(" "));
        return sb.toString();
    }
}

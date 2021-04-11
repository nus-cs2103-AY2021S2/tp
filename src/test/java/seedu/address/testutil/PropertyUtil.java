package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSTAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORTING_KEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORTING_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAGS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.Set;

import seedu.address.logic.commands.AddPropertyCommand;
import seedu.address.logic.commands.EditPropertyCommand.EditPropertyDescriptor;
import seedu.address.logic.commands.SortPropertyCommand.SortPropertyDescriptor;
import seedu.address.model.property.Property;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.DateTimeFormat;

/**
 * A utility class for Property.
 */
public class PropertyUtil {

    /**
     * Returns an add command string for adding the {@code property}.
     */
    public static String getAddPropertyCommand(Property property) {
        return AddPropertyCommand.COMMAND_WORD + " " + getPropertyDetails(property);
    }

    /**
     * Returns the part of command string for the given {@code property}'s details.
     */
    public static String getPropertyDetails(Property property) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + property.getName().name + " ");
        sb.append(PREFIX_TYPE + property.getPropertyType().propertyType + " ");
        sb.append(PREFIX_ADDRESS + property.getAddress().propertyAddress + " ");
        sb.append(PREFIX_POSTAL + property.getPostalCode().postal + " ");
        sb.append(PREFIX_DEADLINE + property.getDeadline().deadline.format(DateTimeFormat.INPUT_DATE_FORMAT) + " ");
        sb.append(PREFIX_TAGS);
        property.getTags().stream().forEach(s -> sb.append(s.tagName + ", "));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPropertyDescriptor}'s details.
     */
    public static String getEditPropertyDescriptorDetails(EditPropertyDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.name).append(" "));
        descriptor.getType().ifPresent(type -> sb.append(PREFIX_TYPE).append(type.propertyType).append(" "));
        descriptor.getAddress().ifPresent(address ->
                sb.append(PREFIX_ADDRESS).append(address.propertyAddress).append(" "));
        descriptor.getPostalCode().ifPresent(postal -> sb.append(PREFIX_POSTAL).append(postal.postal).append(" "));
        descriptor.getDeadline().ifPresent(deadline -> sb.append(PREFIX_DEADLINE)
                .append(deadline.deadline.format(DateTimeFormat.INPUT_DATE_FORMAT)).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            sb.append(PREFIX_TAGS);
            tags.forEach(s -> sb.append(s.tagName).append(", "));
        }
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code SortPropertyDescriptor}'s details.
     */
    public static String getSortPropertyDescriptorDetails(SortPropertyDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getSortingOrder().ifPresent(order ->
                sb.append(PREFIX_SORTING_ORDER).append(order.value).append(" "));
        descriptor.getPropertySortingKey().ifPresent(key ->
                sb.append(PREFIX_SORTING_KEY).append(key.value).append(" "));
        return sb.toString();
    }
}

package seedu.us.among.testutil;

import java.util.Set;

import seedu.us.among.logic.commands.AddCommand;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.tag.Tag;
import seedu.us.among.logic.commands.EditCommand;
import seedu.us.among.logic.parser.CliSyntax;

/**
 * A utility class for Endpoint.
 */
public class EndpointUtil {

    /**
     * Returns an add command string for adding the {@code endpoint}.
     */
    public static String getAddCommand(Endpoint endpoint) {
        return AddCommand.COMMAND_WORD + " " + getEndpointDetails(endpoint);
    }

    /**
     * Returns the part of command string for the given {@code endpoint}'s details.
     */
    public static String getEndpointDetails(Endpoint endpoint) {
        StringBuilder sb = new StringBuilder();
        sb.append(CliSyntax.PREFIX_NAME + endpoint.getName().fullName + " ");
        sb.append(CliSyntax.PREFIX_PHONE + endpoint.getPhone().value + " ");
        sb.append(CliSyntax.PREFIX_EMAIL + endpoint.getEmail().value + " ");
        sb.append(CliSyntax.PREFIX_ADDRESS + endpoint.getAddress().value + " ");
        endpoint.getTags().stream().forEach(
            s -> sb.append(CliSyntax.PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditEndpointDescriptor}'s details.
     */
    public static String getEditEndpointDescriptorDetails(EditCommand.EditEndpointDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(CliSyntax.PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(CliSyntax.PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(CliSyntax.PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(CliSyntax.PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(CliSyntax.PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(CliSyntax.PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}

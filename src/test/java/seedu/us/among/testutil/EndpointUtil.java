package seedu.us.among.testutil;

import static seedu.us.among.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.us.among.logic.commands.AddCommand;
import seedu.us.among.logic.commands.EditCommand;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.tag.Tag;

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
        sb.append(PREFIX_NAME + endpoint.getName().fullName + " ");
        sb.append(PREFIX_ADDRESS + endpoint.getAddress().value + " ");
        endpoint.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditEndpointDescriptor}'s details.
     */
    public static String getEditEndpointDescriptorDetails(EditCommand.EditEndpointDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS)
                .append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}

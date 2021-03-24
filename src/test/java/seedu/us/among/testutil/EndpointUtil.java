package seedu.us.among.testutil;

import static seedu.us.among.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_DATA;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_HEADER;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_METHOD;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.us.among.logic.commands.AddCommand;
import seedu.us.among.logic.commands.EditCommand;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.header.Header;
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
        sb.append(PREFIX_METHOD + endpoint.getMethod().methodName + " ");
        sb.append(PREFIX_ADDRESS + endpoint.getAddress().value + " ");
        sb.append(PREFIX_DATA + endpoint.getData().value + " ");
        endpoint.getHeaders().stream().forEach(s -> sb.append(PREFIX_HEADER + s.headerName + " "));
        endpoint.getTags().stream().forEach(s -> sb.append(PREFIX_TAG + s.tagName + " "));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given
     * {@code EditEndpointDescriptor}'s details.
     */
    public static String getEditEndpointDescriptorDetails(EditCommand.EditEndpointDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getMethod().ifPresent(name -> sb.append(PREFIX_METHOD)
                .append(" ")
                .append(name.methodName)
                .append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS)
                .append(" ")
                .append(address.value)
                .append(" "));
        descriptor.getData().ifPresent(data -> sb.append(PREFIX_DATA)
                .append(" ")
                .append(data.value)
                .append(" "));

        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG).append(" ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(" ").append(s.tagName).append(" "));
            }
        }
        if (descriptor.getHeaders().isPresent()) {
            Set<Header> headers = descriptor.getHeaders().get();
            if (headers.isEmpty()) {
                sb.append(PREFIX_HEADER);
            } else {
                headers.forEach(s -> sb.append(PREFIX_HEADER).append(" ").append(s.headerName).append(" "));
            }
        }
        return sb.toString();
    }
}

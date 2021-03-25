package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.event.Event;

public class EventUtil {

    /**
     * Returns an add command string for adding the {@code event}.
     */
    public static String getAddCommand(Event event) {
        return AddCommand.COMMAND_WORD + " " + getEventDetails(event);
    }

    public static String getEventDetails(Event event) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + event.getName().eventName + " ");
        sb.append(PREFIX_DESCRIPTION + event.getDescription().description + " ");
        sb.append(PREFIX_STATUS + event.getStatus().name() + " ");
        return sb.toString();
    }

    public static String getEditEventDescriptorDetails(EditCommand.EditEventDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getEventName().ifPresent(eventName -> sb.append(PREFIX_NAME)
                .append(eventName.eventName).append(" "));
        descriptor.getDescription().ifPresent(description -> sb.append(PREFIX_DESCRIPTION)
                .append(description.description).append(" "));
        descriptor.getEventStatus().ifPresent(eventStatus -> sb.append(PREFIX_STATUS)
                .append(eventStatus.name()).append(" "));
        return sb.toString();
    }
}

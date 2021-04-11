package seedu.partyplanet.testutil;

import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.partyplanet.logic.commands.EAddCommand;
import seedu.partyplanet.logic.commands.EEditCommand.EditEventDescriptor;
import seedu.partyplanet.model.event.Event;

/**
 * A utility class for Event.
 */
public class EventUtil {

    /**
     * Returns an eAdd command string for adding the {@code event}.
     */
    public static String getEAddCommand(Event event) {
        return EAddCommand.COMMAND_WORD + " " + getEventDetails(event);
    }

    /**
     * Returns the part of command string for the given {@code event}'s details.
     */
    public static String getEventDetails(Event event) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + " " + event.getName().fullName + " ");
        sb.append(PREFIX_DATE + " " + event.getEventDate().value + " ");
        sb.append(PREFIX_REMARK + " " + event.getRemark().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditEventDescriptor}'s details.
     */
    public static String getEditEventDescriptorDetails(EditEventDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME + " " + name.fullName + " "));
        descriptor.getDate().ifPresent(eventDate -> sb.append(PREFIX_DATE + " " + eventDate.value + " "));
        descriptor.getRemark().ifPresent(remark -> sb.append(PREFIX_REMARK + " " + remark.value + " "));
        return sb.toString();
    }
}

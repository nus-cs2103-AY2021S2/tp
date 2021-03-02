package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM;

import seedu.address.logic.commands.AddResidentCommand;
import seedu.address.logic.commands.EditResidentCommand.EditPersonDescriptor;
import seedu.address.model.resident.Resident;

/**
 * A utility class for Resident.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code resident}.
     */
    public static String getAddCommand(Resident resident) {
        return AddResidentCommand.COMMAND_WORD + " " + getPersonDetails(resident);
    }

    /**
     * Returns the part of command string for the given {@code resident}'s details.
     */
    public static String getPersonDetails(Resident resident) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + resident.getName().fullName + " ");
        sb.append(PREFIX_PHONE + resident.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + resident.getEmail().value + " ");
        sb.append(PREFIX_ROOM + resident.getRoom().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        return sb.toString();
    }
}

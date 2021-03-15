package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLEAN_STATUS_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESIDENCE_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESIDENCE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditResidenceDescriptor;
import seedu.address.model.residence.Residence;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class ResidenceUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Residence residence) {
        return AddCommand.COMMAND_WORD + " " + getResidenceDetails(residence);
    }

    /**
     * Returns the part of command string for the given {@code residence}'s details.
     */
    public static String getResidenceDetails(Residence residence) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_RESIDENCE_NAME + residence.getResidenceName().fullName + " ");
        sb.append(PREFIX_RESIDENCE_ADDRESS + residence.getResidenceAddress().value + " ");
        sb.append(PREFIX_BOOKING_DETAILS + residence.getBookingDetails().value + " ");
        residence.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditResidenceDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getResidenceName().ifPresent(name -> sb.append(PREFIX_RESIDENCE_NAME).append(name.fullName).append(" "));
        descriptor.getResidenceAddress().ifPresent(phone -> sb.append(PREFIX_RESIDENCE_ADDRESS).append(address.value).append(" "));
        descriptor.getBookingDetails().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getCleanStatusTag().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
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

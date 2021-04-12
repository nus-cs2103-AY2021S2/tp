package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CLEAN_STATUS_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESIDENCE_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditResidenceDescriptor;
import seedu.address.model.residence.Residence;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Residence.
 */
public class ResidenceUtil {

    /**
     * Returns an add command string for adding the {@code residence}.
     */
    public static String getAddCommand(Residence residence) {
        return AddCommand.COMMAND_WORD + " " + getResidenceDetails(residence);
    }

    /**
     * Returns the part of command string for the given {@code residence}'s details.
     */
    public static String getResidenceDetails(Residence residence) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + residence.getResidenceName().getValue() + " ");
        sb.append(PREFIX_RESIDENCE_ADDRESS + residence.getResidenceAddress().getValue() + " ");
        sb.append(PREFIX_CLEAN_STATUS_TAG + residence.getCleanStatusTag().getValue() + " ");

        residence.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditResidenceDescriptor}'s details.
     */
    public static String getEditResidenceDescriptorDetails(EditResidenceDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getResidenceName().ifPresent(name -> sb.append(PREFIX_NAME)
                .append(name.getValue()).append(" "));
        descriptor.getResidenceAddress().ifPresent(address -> sb.append(PREFIX_RESIDENCE_ADDRESS)
                .append(address.getValue()).append(" "));
        descriptor.getCleanStatusTag().ifPresent(cleanStatusTag -> sb.append(PREFIX_CLEAN_STATUS_TAG)
                .append(cleanStatusTag.getValue()).append(" "));
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

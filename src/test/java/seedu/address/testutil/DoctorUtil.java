package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.doctor.AddDoctorCommand;
import seedu.address.logic.commands.doctor.EditDoctorCommand.EditDoctorDescriptor;
import seedu.address.model.person.Doctor;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class DoctorUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Doctor person) {
        return AddDoctorCommand.COMMAND_WORD + " " + getDoctorDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getDoctorDetails(Doctor doctor) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + doctor.getName().fullName + " ");
        doctor.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPatientDescriptor}'s details.
     */
    public static String getEditDoctorDescriptorDetails(EditDoctorDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
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

package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT_START;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_IN_LIST;

import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.appointment.AddAppointmentCommand;
import seedu.address.logic.commands.appointment.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.logic.commands.appointment.FindAppointmentCommand;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class AppointmentUtil {

    /**
     * Returns an add command string for adding the {@code appointment}.
     */
    public static String getAddCommand(Appointment appointment) {
        return AddAppointmentCommand.COMMAND_WORD + " " + getAddAppointmentDetails(appointment);
    }

    /**
     * Returns the part of command string for the given {@code appointment}'s details.
     */
    public static String getAddAppointmentDetails(Appointment appointment) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_PATIENT).append(INDEX_FIRST_IN_LIST.getOneBased()).append(" ");
        sb.append(PREFIX_DOCTOR).append(INDEX_FIRST_IN_LIST.getOneBased()).append(" ");
        sb.append(PREFIX_TIMESLOT_START + appointment.getAppointmentStart().toString().replace("T", " ") + " ");
        sb.append(PREFIX_TIMESLOT_END + appointment.getAppointmentEnd().toString().replace("T", " ") + " ");
        appointment.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    public static String getFindCommand(List<String> patientNameKeywords, List<String> doctorNameKeywords,
            List<String> timeStartKeywords, List<String> tagKeywords) {
        return FindAppointmentCommand.COMMAND_WORD + " " + AppointmentUtil.getFindAppointmentDetails(
                patientNameKeywords, doctorNameKeywords, timeStartKeywords, tagKeywords);
    }

    public static String getFindAppointmentDetails(List<String> patientNameKeywords, List<String> doctorNameKeywords,
            List<String> timeStartKeywords, List<String> tagKeywords) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_PATIENT).append(" ");
        patientNameKeywords.forEach(keyword -> sb.append(keyword).append(" "));
        sb.append(PREFIX_DOCTOR).append(" ");
        doctorNameKeywords.forEach(keyword -> sb.append(keyword).append(" "));
        sb.append(PREFIX_TAG).append(" ");
        tagKeywords.forEach(keyword -> sb.append(keyword).append(" "));
        sb.append(PREFIX_TIMESLOT_START).append(" ");
        timeStartKeywords.forEach(keyword -> sb.append(keyword).append(" "));

        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPatientDescriptor}'s details.
     */
    public static String getEditAppointmentDescriptorDetails(EditAppointmentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getPatientIndex().ifPresent(ptIndex -> sb.append(PREFIX_PATIENT)
                .append(ptIndex.getOneBased()).append(" "));
        descriptor.getDoctorIndex().ifPresent(drIndex -> sb.append(PREFIX_DOCTOR)
                .append(drIndex.getOneBased()).append(" "));
        descriptor.getStart().ifPresent(start -> sb.append(PREFIX_TIMESLOT_START)
                .append(start.toString().replace("T", " ")).append(" "));
        descriptor.getEnd().ifPresent(end -> sb.append(PREFIX_TIMESLOT_END)
                .append(end.toString().replace("T", " ")).append(" "));
        descriptor.getDuration().ifPresent(dur -> sb.append(PREFIX_TIMESLOT_DURATION)
                .append(dur).append(" "));
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

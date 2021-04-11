package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORTING_KEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORTING_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.logic.commands.SortAppointmentCommand.SortAppointmentDescriptor;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.util.DateTimeFormat;

/**
 * A utility class for Appointment.
 */
public class AppointmentUtil {

    /**
     * Returns an add command string for adding the {@code appointment}.
     */
    public static String getAddAppointmentCommand(Appointment appointment) {
        return AddAppointmentCommand.COMMAND_WORD + " " + getAppointmentDetails(appointment);
    }

    /**
     * Returns the part of command string for the given {@code appointment}'s details.
     */
    public static String getAppointmentDetails(Appointment appointment) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + appointment.getName().name + " ");
        sb.append(PREFIX_REMARK + appointment.getRemarks().remark + " ");
        sb.append(PREFIX_DATE + appointment.getDate().date.format(DateTimeFormat.INPUT_DATE_FORMAT) + " ");
        sb.append(PREFIX_TIME + appointment.getTime().time.format(DateTimeFormat.INPUT_TIME_FORMAT) + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditAppointmentDescriptor}'s details.
     */
    public static String getEditAppointmentDescriptorDetails(EditAppointmentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.name).append(" "));
        descriptor.getRemarks().ifPresent(remark -> sb.append(PREFIX_REMARK).append(remark.remark).append(" "));
        descriptor.getDate().ifPresent(date -> sb.append(PREFIX_DATE)
                .append(date.date.format(DateTimeFormat.INPUT_DATE_FORMAT)).append(" "));
        descriptor.getTime().ifPresent(time -> sb.append(PREFIX_TIME)
                .append(time.time.format(DateTimeFormat.INPUT_TIME_FORMAT)).append(" "));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code SortAppointmentDescriptor}'s details.
     */
    public static String getSortAppointmentDescriptorDetails(SortAppointmentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getSortingOrder().ifPresent(order ->
                sb.append(PREFIX_SORTING_ORDER).append(order.value).append(" "));
        descriptor.getAppointmentSortingKey().ifPresent(key ->
                sb.append(PREFIX_SORTING_KEY).append(key.value).append(" "));
        return sb.toString();
    }
}

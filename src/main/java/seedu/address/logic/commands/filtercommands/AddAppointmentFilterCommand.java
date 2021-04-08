package seedu.address.logic.commands.filtercommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_TO;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.filter.AppointmentFilter;

/**
 * Adds appointment filters.
 */
public class AddAppointmentFilterCommand extends Command {
    public static final String COMMAND_WORD = "add_appointment_filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds appointment filters."
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME]... "
            + "[" + PREFIX_SUBJECT_NAME + "SUBJECT_NAME]... "
            + "[" + PREFIX_TIME_FROM + "DATE_TIME_FROM]... "
            + "[" + PREFIX_TIME_TO + "DATE_TIME_TO]... "
            + "[" + PREFIX_LOCATION + "LOCATION]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SUBJECT_NAME + "English "
            + PREFIX_TIME_FROM + "2021-04-01 10:00 AM "
            + PREFIX_LOCATION + "Clementi";

    public static final String MESSAGE_SUCCESS = "New appointment filter added: %1$s";
    public static final String MESSAGE_DUPLICATE = "A filter in the appointment filter already exists";

    private final AppointmentFilter appointmentFilter;

    /**
     * Creates a AddAppointmentFilterCommand.
     */
    public AddAppointmentFilterCommand(AppointmentFilter appointmentFilter) {
        requireNonNull(appointmentFilter);
        this.appointmentFilter = appointmentFilter;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasAnyAppointmentFilter(appointmentFilter)) {
            throw new CommandException(MESSAGE_DUPLICATE);
        }

        model.addAppointmentFilter(appointmentFilter);
        return new CommandResult(String.format(MESSAGE_SUCCESS, appointmentFilter));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAppointmentFilterCommand // instanceof handles nulls
                && appointmentFilter.equals(((AddAppointmentFilterCommand) other).appointmentFilter));
    }
}

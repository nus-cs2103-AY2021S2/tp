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
 * Deletes appointment filters.
 */
public class DeleteAppointmentFilterCommand extends Command {
    public static final String COMMAND_WORD = "delete_appointment_filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes appointment filters."
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

    public static final String MESSAGE_SUCCESS = "Appoinment filters deleted: %1$s";
    public static final String MESSAGE_NOT_FOUND = "A given filter does not exist";

    private final AppointmentFilter appointmentFilter;

    /**
     * Creates a DeleteAppointmentFilterCommand.
     */
    public DeleteAppointmentFilterCommand(AppointmentFilter appointmentFilter) {
        requireNonNull(appointmentFilter);
        this.appointmentFilter = appointmentFilter;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasAllAppointmentFilters(appointmentFilter)) {
            throw new CommandException(MESSAGE_NOT_FOUND);
        }

        model.removeAppointmentFilter(appointmentFilter);
        return new CommandResult(String.format(MESSAGE_SUCCESS, appointmentFilter));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteAppointmentFilterCommand // instanceof handles nulls
                && appointmentFilter.equals(((DeleteAppointmentFilterCommand) other).appointmentFilter));
    }
}

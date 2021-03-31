package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.appointment.AppointmentPredicateList;

/**
 * Finds and lists all appointments in appointment book containing any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "find appointment";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all appointments whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: [n/NAME] [r/REMARKS] [d/DATE]* [t/TIME]*\n"
            + "Note that all parameters marked with * are limited to only 1 in each query. "
            + "Example: " + COMMAND_WORD + " n/charlie\n"
            + COMMAND_WORD + " r/meet on 25th d/25-11-21";

    private final AppointmentPredicateList predicate;

    public FindAppointmentCommand(AppointmentPredicateList predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(predicate.combine());
        int appointmentListSize = model.getFilteredAppointmentList().size();
        return new CommandResult(
                String.format(appointmentListSize > 1
                        ? Messages.MESSAGE_APPOINTMENT_LISTED_OVERVIEW
                        : Messages.MESSAGE_APPOINTMENT_LISTED_OVERVIEW_SINGULAR, appointmentListSize));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindAppointmentCommand // instanceof handles nulls
                && predicate.equals(((FindAppointmentCommand) other).predicate)); // state check
    }
}


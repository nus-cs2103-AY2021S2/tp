package seedu.address.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.appointment.NamePredicate;

/**
 * Finds and lists all appointments in list whose attributes contains any of the argument keywords.
 * Keyword matching is case insensitive.
 * TODO: Possibly find by other predicates (i.e., subject name, date, location) in the future.
 */
public class FindAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "find_appointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all appointments whose tutor name contain any of"
            + " the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NamePredicate predicate;

    public FindAppointmentCommand(NamePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPOINTMENT_LISTED_OVERVIEW, model.getFilteredAppointmentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindAppointmentCommand // instanceof handles nulls
                && predicate.equals(((FindAppointmentCommand) other).predicate)); // state check
    }
}

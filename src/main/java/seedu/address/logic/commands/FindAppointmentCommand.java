package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;



public class FindAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "findAppt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all Appointments with fields containing any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " lunch meeting ptm";

    private final Predicate<Appointment> predicate;

    /**
     * @param predicate Predicate to find the {@code Appointment} by.
     */
    public FindAppointmentCommand(Predicate<Appointment> predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredAppointmentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW,
                        model.getFilteredAppointmentList().size()));

    }
}

package seedu.address.logic.commands.appointmentcommands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_TO;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds an appointment to the appointment list.
 */
public class AddAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "add_appointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointment to the appointment list. "
            + "Parameters: "
            + PREFIX_EMAIL + "NAME "
            + PREFIX_SUBJECT_NAME + "SUBJECT "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME_FROM + "TIME FROM "
            + PREFIX_TIME_TO + "TIME TO "
            + PREFIX_LOCATION + "LOCATION\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EMAIL + "chloelim@example.com "
            + PREFIX_SUBJECT_NAME + "English "
            + PREFIX_DATE + "2021-3-1 "
            + PREFIX_TIME_FROM + "10:00am "
            + PREFIX_TIME_TO + "12:00pm "
            + PREFIX_LOCATION + "Bedok";

    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists in the list";

    /**
     * More to be implemented
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}

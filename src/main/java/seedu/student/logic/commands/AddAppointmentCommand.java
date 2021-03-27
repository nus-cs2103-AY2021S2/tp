package seedu.student.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.student.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.student.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.student.logic.parser.CliSyntax.PREFIX_MATRICULATION_NUMBER;
import static seedu.student.logic.parser.CliSyntax.PREFIX_START_TIME;

import seedu.student.logic.commands.exceptions.CommandException;
import seedu.student.model.Model;
import seedu.student.model.appointment.Appointment;

/**
 * Adds a person to the address book.
 */
public class AddAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "addAppt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointment to Vax@NUS. "
            + "Parameters: "
            + PREFIX_MATRICULATION_NUMBER + "MATRICULATION NUMBER "
            + PREFIX_DATE + "DATE "
            + PREFIX_START_TIME + "START TIME "
            + PREFIX_END_TIME + "END_TIME \n"
            + PREFIX_MATRICULATION_NUMBER + "A1234567X "
            + PREFIX_DATE + "2021-03-14 "
            + PREFIX_START_TIME + "10:00 "
            + PREFIX_END_TIME + "10:30 \n";


    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "The appointment already exists in the records";
    public static final String MESSAGE_OVERLAPPING_APPOINTMENT = "The appointment overlaps with existing records";

    private final Appointment toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddAppointmentCommand(Appointment appointment) {
        requireNonNull(appointment);
        toAdd = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasAppointment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        } else if (model.hasOverlappingAppointment(toAdd)) {
            throw new CommandException(MESSAGE_OVERLAPPING_APPOINTMENT);
        }

        model.addAppointment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAppointmentCommand // instanceof handles nulls
                && toAdd.equals(((AddAppointmentCommand) other).toAdd));
    }
}

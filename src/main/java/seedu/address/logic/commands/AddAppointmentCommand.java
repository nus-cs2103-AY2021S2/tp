package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.model.appointment.Date.MESSAGE_DATE_OVER;
import static seedu.address.model.appointment.Time.MESSAGE_TIME_OVER;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Date;
import seedu.address.model.appointment.Time;

/**
 * Adds an appointment to the app.
 */
public class AddAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "add appointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointment to the app. \n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_REMARK + "REMARK "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME + "TIME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Meet Jacob "
            + PREFIX_REMARK + "For collection of commission "
            + PREFIX_DATE + "19-05-2021 "
            + PREFIX_TIME + "1930";

    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists in the app";

    private final Appointment toAdd;

    /**
     * Creates an AddAppointmentCommand to add the specified {@code Appointment}.
     */
    public AddAppointmentCommand(Appointment appointment) {
        requireNonNull(appointment);
        toAdd = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        requireNonNull(toAdd);

        if (model.hasAppointment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        Date date = toAdd.getDate();
        Time time = toAdd.getTime();

        if (date.isOver()) {
            throw new CommandException(MESSAGE_DATE_OVER);
        } else if (date.isToday() && time.isOver()) {
            throw new CommandException(MESSAGE_TIME_OVER);
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

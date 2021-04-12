package seedu.address.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_ADD_EDIT_COMMAND_ERROR;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_APPOINTMENT;
import static seedu.address.commons.core.Messages.MESSAGE_TUTOR_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_TUTOR_DOES_NOT_TEACH_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_TO;

import seedu.address.commons.util.DateTimeValidationUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

/**
 * Adds an appointment to the appointment list.
 */
public class AddAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "add_appointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointment to the appointment list. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_SUBJECT_NAME + "SUBJECT "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME_FROM + "TIME FROM "
            + PREFIX_TIME_TO + "TIME TO "
            + PREFIX_LOCATION + "LOCATION\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Chloe Lim "
            + PREFIX_SUBJECT_NAME + "English "
            + PREFIX_DATE + "2021-3-1 "
            + PREFIX_TIME_FROM + "10:00am "
            + PREFIX_TIME_TO + "12:00pm "
            + PREFIX_LOCATION + "Bedok";

    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";

    private final Appointment toAdd;

    /**
     * Primary constructor to accept an appointment and add it to appointment list.
     *
     * @param appointment Appointment to add
     */
    public AddAppointmentCommand(Appointment appointment) {
        requireNonNull(appointment);
        toAdd = appointment;
    }

    /**
     * Main execute method that creates adds a new appointment to the appointment list
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult indicating success or failure of add operation
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasAppointment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        if (!model.hasTutorByName(toAdd.getName())) {
            throw new CommandException(MESSAGE_TUTOR_DOES_NOT_EXIST);
        }

        if (!model.doesTutorTeachSubject(toAdd.getName(), toAdd.getSubject())) {
            throw new CommandException(String.format(MESSAGE_TUTOR_DOES_NOT_TEACH_SUBJECT,
                    toAdd.getSubject()));
        }

        boolean isValidateSuccess = DateTimeValidationUtil.validateDateTime(model, toAdd);

        if (isValidateSuccess) {
            model.addAppointment(toAdd);
            model.resetPredicates();
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), TabName.APPOINTMENT);
        } else {
            throw new CommandException(MESSAGE_ADD_EDIT_COMMAND_ERROR);
        }
    }
}

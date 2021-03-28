package seedu.student.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.student.logic.commands.exceptions.CommandException;
import seedu.student.model.Model;
import seedu.student.model.appointment.Appointment;
import seedu.student.model.student.MatriculationNumber;
import seedu.student.model.student.Student;

public class DeleteApptCommand extends Command {
    public static final String COMMAND_WORD = "deleteAppt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the appointment of the student identified by their unique matriculation number"
            + " assigned by NUS.\n"
            + "Parameters: Matriculation Number \n"
            + "Example: " + COMMAND_WORD + " A1234567X";

    public static final String MESSAGE_DELETE_APPT_SUCCESS = "Deleted Appointment: Name; %s, %s"; // appointment
    public static final String MESSAGE_NONEXISTENT_APPT = "No appointment for that matriculation number exists!";

    private final MatriculationNumber matriculationNumber;

    public DeleteApptCommand(MatriculationNumber matriculationNumber) {
        this.matriculationNumber = matriculationNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Appointment appointmentToDelete = model.getAppointment(matriculationNumber);
        Student student = model.getStudent(matriculationNumber);
        if (appointmentToDelete == null || student == null) {
            throw new CommandException(MESSAGE_NONEXISTENT_APPT);
        }
        model.deleteAppointment(appointmentToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_APPT_SUCCESS, student.getName(), appointmentToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteApptCommand // instanceof handles nulls
                && matriculationNumber.equals(((DeleteApptCommand) other).matriculationNumber)); // state check
    }
}

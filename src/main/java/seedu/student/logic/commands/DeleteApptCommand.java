package seedu.student.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.student.commons.core.LogsCenter;
import seedu.student.logic.commands.exceptions.CommandException;
import seedu.student.model.Model;
import seedu.student.model.ModelManager;
import seedu.student.model.appointment.Appointment;
import seedu.student.model.student.MatriculationNumber;
import seedu.student.model.student.Student;

/**
 * Deletes a student's appointment identified using the student's unique matriculation number.
 */
public class DeleteApptCommand extends Command {
    public static final String COMMAND_WORD = "deleteAppt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the appointment of the student identified by their unique matriculation number. \n"
            + "Parameters: Matriculation Number in the format of A + 7 digit numeric sequence + alphabet. \n"
            + "Example: " + COMMAND_WORD + " A1234567X";

    public static final String MESSAGE_DELETE_APPT_SUCCESS = "Deleted Appointment: Name; %s, %s"; // appointment
    public static final String MESSAGE_NONEXISTENT_APPT = "No appointment for matriculation number %s exists.";

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private final MatriculationNumber matriculationNumber;

    /**
     * Creates a DeleteApptCommand object responsible for deleting the appointment of the student with the
     * specified matriculation number.
     *
     * @param matriculationNumber Matriculation number of the student who's appointment you want to delete.
     */
    public DeleteApptCommand(MatriculationNumber matriculationNumber) {
        this.matriculationNumber = matriculationNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Appointment appointmentToDelete = model.getAppointment(matriculationNumber);
        Student student = model.getStudent(matriculationNumber);
        if (appointmentToDelete == null || student == null) {
            if (student == null) {
                logger.info("Student with a matriculation number of " + matriculationNumber
                        + " does not exist in Vax@NUS");
            }
            if (appointmentToDelete == null) {
                logger.info("Appointment of a student with a matriculation number of " + matriculationNumber
                        + " does not exist in Vax@NUS");
            }
            throw new CommandException(String.format(MESSAGE_NONEXISTENT_APPT, matriculationNumber));
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

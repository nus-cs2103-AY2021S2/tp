package seedu.student.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.student.commons.core.LogsCenter;
import seedu.student.model.Model;
import seedu.student.model.ModelManager;
import seedu.student.model.appointment.AppointmentContainsMatriculationNumberPredicate;
import seedu.student.model.appointment.AppointmentListContainsMatriculationNumberPredicate;
import seedu.student.model.student.StudentContainsMatriculationNumberPredicate;
import seedu.student.model.student.exceptions.MatriculationNumberDoesNotExistException;

/**
 * Finds and lists all persons in student book whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command {


    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds student and appointment whose "
            + "matriculation number matches the specified keywords (case-sensitive) and displays it.\n"
            + "Parameters: KEYWORD \n"
            + "Example: " + COMMAND_WORD + " A01234567R";

    public static final String MESSAGE_STUDENTS_AND_APPOINTMENT_FOUND =
            "A student with matriculation number %s is found \n"
                    + "If they have an appointment, their appointment will also be listed.";

    public static final String MESSAGE_NO_STUDENT_FOUND =
            "No student with matriculation number %s was found. \n";


    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final StudentContainsMatriculationNumberPredicate predicate;
    private final AppointmentContainsMatriculationNumberPredicate appointmentPredicate;
    private final AppointmentListContainsMatriculationNumberPredicate appointmentListPredicate;

    /**
     *  Creates a FindCommand object responsible for deleting a student by matriculation number.
     * @param studentPredicate
     * @param appointmentPredicate
     */
    public FindCommand(StudentContainsMatriculationNumberPredicate studentPredicate,
                       AppointmentListContainsMatriculationNumberPredicate appointmentListPredicate,
                       AppointmentContainsMatriculationNumberPredicate appointmentPredicate) {

        this.predicate = studentPredicate;
        this.appointmentPredicate = appointmentPredicate;
        this.appointmentListPredicate = appointmentListPredicate;
    }

    @Override
    public CommandResult execute(Model model) throws MatriculationNumberDoesNotExistException {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        model.updateFilteredAppointmentList(appointmentListPredicate, appointmentPredicate);

        int filteredStudentListSize = model.getFilteredStudentList().size();
        int filteredAppointmentListSize = model.getFilteredAppointmentList().size();

        assert (filteredStudentListSize >= 0 && filteredAppointmentListSize >= 0);

        if (filteredStudentListSize == 0) {
            model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
            model.updateFilteredAppointmentList(Model.PREDICATE_SHOW_ALL_APPOINTMENT_LISTS,
                    Model.PREDICATE_SHOW_ALL_APPOINTMENTS);

            logger.info("Student with a matriculation number of " + predicate.getKeyword()
                    + "does not exist in Vax@NUS");

            throw new MatriculationNumberDoesNotExistException(String.format(MESSAGE_NO_STUDENT_FOUND,
                    predicate.getKeyword()));

        } else if (filteredAppointmentListSize == 0) {
            return new CommandResult(String.format(MESSAGE_STUDENTS_AND_APPOINTMENT_FOUND,
                    model.getFilteredStudentList().size()));
        } else {
            return new CommandResult(String.format(MESSAGE_STUDENTS_AND_APPOINTMENT_FOUND,
                    predicate.getKeyword()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}

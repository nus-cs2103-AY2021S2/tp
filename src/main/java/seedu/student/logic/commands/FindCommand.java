package seedu.student.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.student.commons.core.Messages;
import seedu.student.model.Model;
import seedu.student.model.appointment.AppointmentContainsMatriculationNumberPredicate;
import seedu.student.model.student.StudentContainsMatriculationNumberPredicate;

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

    private final StudentContainsMatriculationNumberPredicate predicate;
    private final AppointmentContainsMatriculationNumberPredicate appointmentPredicate;

    /**
     *  Creates a FindCommand object responsible for deleting a student by matriculation number.
     * @param studentPredicate
     * @param appointmentPredicate
     */
    public FindCommand(StudentContainsMatriculationNumberPredicate studentPredicate ,
                       AppointmentContainsMatriculationNumberPredicate appointmentPredicate) {
        this.predicate = studentPredicate;
        this.appointmentPredicate = appointmentPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        model.updateFilteredAppointmentList(appointmentPredicate);

        int filteredStudentListSize = model.getFilteredStudentList().size();
        int filteredAppointmentListSize = model.getFilteredStudentList().size();

        assert (filteredStudentListSize >= 0 && filteredAppointmentListSize >= 0);

        if (filteredStudentListSize == 0) {
            return new CommandResult(String.format(Messages.MESSAGE_NONEXISTENT_MATRIC_NUM,
                    model.getFilteredStudentList().size()));
        } else if (filteredAppointmentListSize == 0) {
            return new CommandResult(String.format(Messages.MESSAGE_NONEXISTENT_APPOINTMENT,
                    model.getFilteredStudentList().size()));
        } else {
            return new CommandResult(String.format(Messages.MESSAGE_STUDENTS_ARE_LISTED
                            + Messages.MESSAGE_APPOINTMENT_IS_LISTED, model.getFilteredStudentList().size()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}

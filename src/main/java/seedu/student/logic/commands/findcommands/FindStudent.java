package seedu.student.logic.commands.findcommands;

import seedu.student.commons.core.Messages;
import seedu.student.logic.commands.CommandResult;
import seedu.student.model.Model;
import seedu.student.model.appointment.AppointmentContainsMatriculationNumberPredicate;
import seedu.student.model.appointment.AppointmentListContainsMatriculationNumberPredicate;
import seedu.student.model.student.StudentContainsMatriculationNumberPredicate;

import static java.util.Objects.requireNonNull;

/**
 * Finds and lists all persons in student book whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindStudent extends FindCommand {

    private final StudentContainsMatriculationNumberPredicate predicate;

    /**
     *  Creates a FindCommand object responsible for finding a student by matriculation number.
     * @param studentPredicate
     */
    public FindStudent(StudentContainsMatriculationNumberPredicate studentPredicate) {
        this.predicate = studentPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
      //  model.updateFilteredAppointmentList(appointmentListPredicate, appointmentPredicate);

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
            return new CommandResult(String.format(Messages.MESSAGE_STUDENTS_AND_APPOINTMENT_FOUND,
                    predicate.getKeyword()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindStudent // instanceof handles nulls
                && predicate.equals(((FindStudent) other).predicate)); // state check
    }
}

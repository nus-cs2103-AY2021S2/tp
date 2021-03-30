package seedu.student.logic.commands.findcommands;

import seedu.student.logic.commands.CommandResult;
import seedu.student.model.Model;
import seedu.student.model.appointment.AppointmentContainsMatriculationNumberPredicate;
import seedu.student.model.appointment.AppointmentListContainsMatriculationNumberPredicate;
import seedu.student.model.student.StudentContainsMatriculationNumberPredicate;

import static java.util.Objects.requireNonNull;

public class FindStudentAndAppointmentCommand extends FindCommand {

    private final StudentContainsMatriculationNumberPredicate predicate;
    private final AppointmentContainsMatriculationNumberPredicate appointmentPredicate;
    private final AppointmentListContainsMatriculationNumberPredicate appointmentListPredicate;

    /**
     *  Creates a FindCommand object responsible for deleting a student by matriculation number.
     * @param studentPredicate
     * @param appointmentPredicate
     */
    public FindStudentAndAppointmentCommand(StudentContainsMatriculationNumberPredicate studentPredicate,
                                            AppointmentListContainsMatriculationNumberPredicate appointmentListPredicate,
                                            AppointmentContainsMatriculationNumberPredicate appointmentPredicate) {
        this.predicate = studentPredicate;
        this.appointmentPredicate = appointmentPredicate;
        this.appointmentListPredicate = appointmentListPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        FindStudent findStudent  = new FindStudent(this.predicate);
        FindAppointments findAppointments  = new FindAppointments(this.appointmentPredicate,
                this.appointmentListPredicate);

        CommandResult commandResult = findStudent.execute(model);
        findAppointments.execute(model);
        return commandResult;
    }
}

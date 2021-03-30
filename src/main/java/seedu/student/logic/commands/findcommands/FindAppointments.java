package seedu.student.logic.commands.findcommands;

import seedu.student.commons.core.Messages;
import seedu.student.logic.commands.CommandResult;
import seedu.student.logic.commands.exceptions.CommandException;
import seedu.student.model.Model;
import seedu.student.model.appointment.AppointmentContainsMatriculationNumberPredicate;
import seedu.student.model.appointment.AppointmentListContainsMatriculationNumberPredicate;

import static java.util.Objects.requireNonNull;

public class FindAppointments extends FindCommand {

    private final AppointmentContainsMatriculationNumberPredicate appointmentPredicate;
    private final AppointmentListContainsMatriculationNumberPredicate appointmentListPredicate;

    public FindAppointments(AppointmentContainsMatriculationNumberPredicate appointmentPredicate, AppointmentListContainsMatriculationNumberPredicate appointmentListPredicate) {
        this.appointmentPredicate = appointmentPredicate;
        this.appointmentListPredicate = appointmentListPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(appointmentListPredicate, appointmentPredicate);
        return new CommandResult(String.format(Messages.MESSAGE_STUDENTS_AND_APPOINTMENT_FOUND,
                "matric number"));
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindStudent // instanceof handles nulls
                && appointmentPredicate.equals(((FindAppointments) other).appointmentPredicate)); // state check
    }
}

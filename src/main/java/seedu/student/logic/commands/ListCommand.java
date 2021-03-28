package seedu.student.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.student.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;
import static seedu.student.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import seedu.student.commons.core.Messages;
import seedu.student.model.Model;

/**
 * Lists all students and appointments in the student book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all students and appointments";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);

        return new CommandResult(
                String.format(Messages.MESSAGE_ALL_STUDENTS_AND_APPOINTMENT_ARE_LISTED, model.getFilteredStudentList().size(),
                        model.getFilteredAppointmentList().size()));
    }
}

package seedu.student.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.student.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;
import static seedu.student.model.Model.PREDICATE_SHOW_ALL_APPOINTMENT_LISTS;
import static seedu.student.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import seedu.student.commons.core.Messages;
import seedu.student.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENT_LISTS, PREDICATE_SHOW_ALL_APPOINTMENTS);
        return new CommandResult(Messages.MESSAGE_ALL_STUDENTS_AND_APPOINTMENT_ARE_LISTED);
    }
}

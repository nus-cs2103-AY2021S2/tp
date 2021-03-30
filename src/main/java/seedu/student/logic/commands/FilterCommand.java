package seedu.student.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.student.commons.core.Messages;
import seedu.student.model.Model;
import seedu.student.model.student.Student;

/**
 * Finds and lists all students in student book whose student entries field matches the argument keyword.
 * Keyword matching is case sensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Display student records with "
            + "fields that matches the specified keyword (case-sensitive).\n"
            + "Parameters: KEYWORD...\n"
            + "Example: " + COMMAND_WORD + " vaccinated";

    private final Predicate<Student> predicate;

    public FilterCommand(Predicate<Student> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        if (model.getFilteredStudentList().size() == 0) {
            return new CommandResult(
                    String.format(Messages.MESSAGE_NO_STUDENTS_ARE_LISTED, model.getFilteredStudentList().size()));
        } else {
            return new CommandResult(
                    String.format(Messages.MESSAGE_STUDENTS_ARE_LISTED, model.getFilteredStudentList().size()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && predicate.equals(((FilterCommand) other).predicate)); // state check
    }
}

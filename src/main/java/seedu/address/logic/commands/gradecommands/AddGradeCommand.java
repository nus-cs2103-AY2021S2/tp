package seedu.address.logic.commands.gradecommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADED_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_NAME;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.grade.Grade;

/**
 * Adds a grade to the grade list.
 */
public class AddGradeCommand extends Command {

    public static final String COMMAND_WORD = "add_grade";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a grade to the grade book. "
            + "Parameters: "
            + PREFIX_SUBJECT_NAME + "SUBJECT NAME "
            + PREFIX_GRADED_ITEM + "GRADED ITEM "
            + PREFIX_GRADE + "GRADE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SUBJECT_NAME + "Science "
            + PREFIX_GRADED_ITEM + "Midterm "
            + PREFIX_GRADE + "A\n";
    public static final String MESSAGE_SUCCESS = "New grade added: %1$s";
    public static final String MESSAGE_DUPLICATE_GRADE = "This grade already exists in the grade book";

    private final Grade toAdd;

    /**
     * Creates an AddGradeCommand to add the specified {@code Grade}
     */
    public AddGradeCommand(Grade grade) {
        requireNonNull(grade);
        toAdd = grade;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasGrade(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_GRADE);
        }

        model.addGrade(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddGradeCommand // instanceof handles nulls
                && toAdd.equals(((AddGradeCommand) other).toAdd));
    }

}

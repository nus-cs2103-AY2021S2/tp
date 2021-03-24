package seedu.address.logic.commands.gradecommands;

import seedu.address.logic.commands.Command;
import seedu.address.model.person.Person;

import static java.util.Objects.requireNonNull;

public class AddGradeCommand extends Command {

    public static final String COMMAND_WORD = "add_grade";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a grade to the address book. ";
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
}

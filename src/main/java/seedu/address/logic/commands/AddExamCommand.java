package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Exam;

public class AddExamCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an exam to the module. "
            + "Parameters: "
            + PREFIX_MODULE + "MODULE NAME "
            + PREFIX_EXAM + "EXAM DATE "
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_MODULE + "CS2103T "
            + PREFIX_EXAM + "25/3/2021 0900";

    public static final String MESSAGE_SUCCESS = "New exam added: %1$s";
    public static final String MESSAGE_DUPLICATE_EXAM = "This exam already exists at this date";

    private final Exam toAdd;

    /**
     * Creates an AddExamComamnd to add the specified {@code Exam}.
     */
    public AddExamCommand(Exam exam) {
        requireNonNull(exam);
        toAdd = exam;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasExam(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXAM);
        }

        model.addExam(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddExamCommand)
                && toAdd.equals(((AddExamCommand) other).toAdd);
    }
}

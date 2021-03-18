package seedu.address.logic.commands.addcommand;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Exam;
import seedu.address.model.module.Module;

public class AddExamCommand extends AddCommand {

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
    public static final String MESSAGE_MODULE_NOT_FOUND = "Module for exam has to be created "
            + "first";

    private final Module target;
    private final Exam toAdd;

    /**
     * Creates an AddExamComamnd to add the specified {@code Exam}.
     */
    public AddExamCommand(Module module, Exam exam) {
        requireAllNonNull(module, exam);
        target = module;
        toAdd = exam;
    }

    /**
     * Executes the AddExamComamnd get add an {@code Exam} into RemindMe.
     *
     * @param model {@code Model} which the command should operate on.
     * @return The CommandResult for the success of the command.
     * @throws CommandException when the exam to add is already in RemindMe.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasModule(target)) {
            throw new CommandException(MESSAGE_MODULE_NOT_FOUND);
        }
        if (model.hasExam(target, toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXAM);
        }

        model.addExam(target, toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    /**
     * Checks whether {@code other} is of the same Command and Exam.
     *
     * @param other other Object to compare with this.
     * @return true if they are similar, else false.
     */
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddExamCommand)
                && toAdd.equals(((AddExamCommand) other).toAdd);
    }
}

package seedu.address.logic.commands.editcommand;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Description;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

public class EditAssignmentCommand extends EditCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a module in RemindMe."
            + "Parameters: "
            + PREFIX_MODULE + " MODULE TITLE "
            + PREFIX_ASSIGNMENT + " ASSIGNMENT INDEX "
            + "[" + PREFIX_ASSIGNMENT + " NEW DESCRIPTION OR"
            + PREFIX_DEADLINE + " NEW DEADLINE]"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE + "CS2103T"
            + PREFIX_ASSIGNMENT + "1"
            + PREFIX_ASSIGNMENT + "Complete DG";

    public static final String MESSAGE_SUCCESS = "Assignment edited: %1$s";
    public static final String MESSAGE_NO_MODULE = "This module does not exists in RemindMe";
    public static final String MESSAGE_NO_ASSIGNMENT = "This module does not contain any assignment at this index.";
    public static final String MESSAGE_NO_CHANGE = "The input given does not change anything!";

    private final Module module;
    private final int toEdit;
    private final Description descriptionEdit;
    private final LocalDateTime dateEdit;

    /**
     * Creates an EditModuleCommand to edit the specified {@code Module}.
     */
    public EditAssignmentCommand(Module module, int index, Description description, LocalDateTime date) {
        requireNonNull(module);
        this.module = module;
        toEdit = index;
        descriptionEdit = description;
        dateEdit = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasModule(module)) {
            throw new CommandException(MESSAGE_NO_MODULE);
        }

        if (!model.hasAssignment(module, toEdit)) {
            throw new CommandException(MESSAGE_NO_ASSIGNMENT);
        }

        
        return new CommandResult(String.format(MESSAGE_SUCCESS, edit));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof EditModuleCommand)
                && edit.equals(((EditModuleCommand) other).edit)
                && toEdit == ((EditModuleCommand) other).toEdit;
    }
}

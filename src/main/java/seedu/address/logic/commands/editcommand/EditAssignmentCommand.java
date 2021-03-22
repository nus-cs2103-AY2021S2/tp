package seedu.address.logic.commands.editcommand;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.Description;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

public class EditAssignmentCommand extends EditCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a module in RemindMe."
            + "Parameters: "
            + PREFIX_MODULE + " MODULE TITLE "
            + PREFIX_ASSIGNMENT + " ASSIGNMENT INDEX "
            + "[" + PREFIX_DESCRIPTION + " NEW DESCRIPTION OR"
            + PREFIX_DEADLINE + " NEW DEADLINE]"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE + "CS2103T"
            + PREFIX_ASSIGNMENT + "1"
            + PREFIX_DESCRIPTION + "Complete DG";

    public static final String MESSAGE_SUCCESS = "Assignment edited: %1$s";
    public static final String MESSAGE_NO_MODULE = "This module does not exists in RemindMe";
    public static final String MESSAGE_NO_ASSIGNMENT = "This module does not contain any assignment at this index.";
    public static final String MESSAGE_NO_CHANGE = "The input given does not change anything!";
    public static final String MESSAGE_TWO_CHANGES = "Only one field can be changed at a time.";
    public static final String MESSAGE_NO_VALID_CHANGES = "Please input a valid edit.";

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

        Module targetMod = model.getModule(module);
        Assignment target = targetMod.getAssignment(toEdit);

        if (descriptionEdit.equals(target.description) || dateEdit.equals(target.deadline)) {
            throw new CommandException(MESSAGE_NO_CHANGE);
        }

        if (!descriptionEdit.equals(null) && !dateEdit.equals(null)) {
            throw new CommandException(MESSAGE_TWO_CHANGES);
        }

        if (descriptionEdit.equals(null) && dateEdit.equals(null)) {
            throw new CommandException(MESSAGE_NO_VALID_CHANGES);
        }

        if (descriptionEdit.equals(null)) {
            model.editAssignment(module, toEdit, dateEdit);
        } else if (dateEdit.equals(null)) {
            model.editAssignment(module, toEdit, descriptionEdit);
        }

        Module editedMod = model.getModule(module);
        Assignment edited = editedMod.getAssignment(toEdit);

        return new CommandResult(String.format(MESSAGE_SUCCESS, edited));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof EditAssignmentCommand)
                && module.equals(((EditAssignmentCommand) other).module)
                && toEdit == ((EditAssignmentCommand) other).toEdit
                && dateEdit.equals(((EditAssignmentCommand) other).dateEdit)
                && descriptionEdit.equals(((EditAssignmentCommand) other).descriptionEdit);
    }
}

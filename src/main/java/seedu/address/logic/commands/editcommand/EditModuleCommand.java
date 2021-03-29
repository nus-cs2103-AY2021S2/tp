package seedu.address.logic.commands.editcommand;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;

public class EditModuleCommand extends EditCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a module in RemindMe."
            + "Parameters: "
            + "MODULE INDEX "
            + PREFIX_MODULE + "NEW MODULE TITLE\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_MODULE + "CS2103T";

    public static final String MESSAGE_SUCCESS = "Module edited: %1$s";
    public static final String MESSAGE_NO_MODULE = "This module does not exists in RemindMe";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in RemindMe";

    private final int toEdit;
    private final Title edit;

    /**
     * Creates an EditModuleCommand to edit the specified {@code Module}.
     */
    public EditModuleCommand(int index, Title title) {
        requireNonNull(title);
        toEdit = index;
        edit = title;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasModule(toEdit)) {
            throw new CommandException(MESSAGE_NO_MODULE);
        }

        Module target = new Module(edit);

        if (model.hasModule(target)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.editModule(toEdit, edit);
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

package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Resets the application to the specified reset type.
 */
public class ResetCommand extends Command {
    public static final String COMMAND_WORD = "reset";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Resets the DietLAH! application to blank or the default template.\n"
            + "Command usage: reset t/blank OR t/template";

    public static final String MESSAGE_SUCCESS = "DietLAH! application successfully reset to ";

    public static final String MESSAGE_FAILURE = "Unable to reset. Please choose the reset type t/blank or t/template";

    private String type;

    /**
     * Creates a ResetCommand command based on the specified type.
     */
    public ResetCommand(String type) {
        this.type = type;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        if (this.type.equals("blank")) {
            model.resetToBlank();
        } else if (this.type.equals("template")) {
            model.resetToTemplate();
        } else {
            throw new CommandException(MESSAGE_FAILURE);
        }
        return new CommandResult(MESSAGE_SUCCESS + this.type + ".");
    }
}

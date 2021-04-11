package seedu.smartlib.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.smartlib.model.Model;
import seedu.smartlib.model.SmartLib;

/**
 * Clears the SmartLib reader and book records .
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear-everything-in-my-smartlib";
    public static final String MESSAGE_SUCCESS = "SmartLib has been cleared!";

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setSmartLib(new SmartLib());
        return new CommandResult(MESSAGE_SUCCESS);
    }

}

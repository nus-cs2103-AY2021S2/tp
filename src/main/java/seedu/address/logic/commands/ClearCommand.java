package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.FlashBack;
import seedu.address.model.Model;

/**
 * Clears all the cards in FlashBack.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "FlashBack has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFlashBack(new FlashBack());
        model.commitFlashBack();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

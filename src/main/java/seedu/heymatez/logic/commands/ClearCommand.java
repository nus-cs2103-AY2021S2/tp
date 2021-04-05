package seedu.heymatez.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.heymatez.model.HeyMatez;
import seedu.heymatez.model.Model;

/**
 * Clears HeyMatez.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "HEY MATEz has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setHeyMatez(new HeyMatez());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

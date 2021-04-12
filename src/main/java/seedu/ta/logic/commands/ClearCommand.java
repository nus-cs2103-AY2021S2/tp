package seedu.ta.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.ta.model.Model;
import seedu.ta.model.TeachingAssistant;

/**
 * Clears the data in Teaching Assistant.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    public static final String MESSAGE_SUCCESS = "Teaching Assistant has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTeachingAssistant(new TeachingAssistant());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

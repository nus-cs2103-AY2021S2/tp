package seedu.weeblingo.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.weeblingo.commons.core.Messages;
import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.Model;

/**
 * Reveals answer for current quiz question
 */
public class CheckCommand extends Command {

    public static final String COMMAND_WORD = "check";

    public static final String MESSAGE_SUCCESS = "Answer to the question is shown.\n"
            + "Enter \"end\" to end the quiz "
            + "and \"next\" to move to the next question.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.getCurrentFlashcard();
        } catch (NullPointerException e) {
            throw new CommandException(Messages.NO_QUIZ_ERROR_MESSAGE);
        }
        return new CommandResult(MESSAGE_SUCCESS, false, false, true, true);
    }
}

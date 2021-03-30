package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


public class ReviewCommand extends Command {
    public static final String COMMAND_WORD = "review";
    public static final String MESSAGE_REVIEW_SUCCESS = "Enter the review mode";
    public static final String MESSAGE_REVIEW_FAIL = "There are no cards to review";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.getFilteredFlashcardList().size() == 0) {
            throw new CommandException(MESSAGE_REVIEW_FAIL);
        }
        return new CommandResult(MESSAGE_REVIEW_SUCCESS, false, false, true);
    }
}

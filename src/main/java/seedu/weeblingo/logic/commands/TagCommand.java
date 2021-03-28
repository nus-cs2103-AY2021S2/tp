package seedu.weeblingo.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.Mode;
import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.flashcard.Flashcard;

/**
 * Adds a tag to a Flashcard
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_SUCCESS = "Tag added successfully!";

    private Flashcard toTag;

    private String tag;

    public TagCommand(Flashcard toTag, String tag) {
        this.toTag = toTag;
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.tagFlashcard(toTag, tag);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true,
                model.getMode().equals(Mode.MODE_QUIZ) ? false : true);
    }
}

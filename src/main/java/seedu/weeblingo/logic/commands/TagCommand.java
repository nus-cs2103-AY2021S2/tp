package seedu.weeblingo.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Set;

import seedu.weeblingo.commons.core.index.Index;
import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.Mode;
import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.flashcard.Flashcard;
import seedu.weeblingo.model.tag.Tag;

/**
 * Adds a tag to a Flashcard
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_SUCCESS = "Tag added successfully!";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Tags the indicated flashcard with the requested tag(s).\n"
            + "Parameters: FLASHCARD_INDEX, TAG...\n"
            + "Example: " + COMMAND_WORD + " 2 t/very difficult t/revise by tomorrow";

    private Index index;

    private Set<Tag> tags;

    public TagCommand(Index index, Set<Tag> tags) {
        this.index = index;
        this.tags = tags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        System.out.println(index.getZeroBased() + "  " + tags);
        return new CommandResult(MESSAGE_SUCCESS, false, false);
    }

}

package seedu.flashback.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.flashback.commons.core.Messages;
import seedu.flashback.commons.core.index.Index;
import seedu.flashback.logic.commands.exceptions.CommandException;
import seedu.flashback.model.Model;
import seedu.flashback.model.flashcard.Flashcard;

public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_VIEW_SUCCESS = "Viewed Flashcard: \n%1$s";
    public static final String MESSAGE_USAGE =
            "Views the flashcard identified by the index number used in the displayed flashcard list. "
            + "\n" + "Parameters: INDEX (must be a positive integer)"
            + "\n" + "Example: " + COMMAND_WORD + " 1";

    private final Index index;

    /**
     * Creates a ViewCommand to view a flashcard at a specified {@code index}
     */
    public ViewCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Flashcard> list = model.getFilteredFlashcardList();
        if (index.getZeroBased() >= list.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }
        Flashcard viewCard = list.get(index.getZeroBased());
        return new CommandResult(String.format(MESSAGE_VIEW_SUCCESS, viewCard), index.getZeroBased());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof ViewCommand)) {
            return false;
        } else {
            ViewCommand other = (ViewCommand) obj;
            return this.index.equals(other.index);
        }
    }
}

package seedu.flashback.logic.commands;

import static seedu.flashback.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.flashback.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;

import java.util.List;

import seedu.flashback.commons.core.Messages;
import seedu.flashback.commons.core.index.Index;
import seedu.flashback.logic.commands.exceptions.CommandException;
import seedu.flashback.model.Model;
import seedu.flashback.model.flashcard.Flashcard;
import seedu.flashback.model.flashcard.Remark;


/**
 * Changes the remark of an existing person in the address book.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the remark of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "r/ [REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "r/ Likes to swim";
    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to Flashcard: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Flashcard: %1$s";

    private final Index index;
    private final Remark remark;

    /**
     * @param index of the person in the filtered person list to edit the remark
     * @param remark of the person to be updated to
     */
    public RemarkCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Flashcard> lastShownList = model.getFilteredFlashcardList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        Flashcard flashcardToEdit = lastShownList.get(index.getZeroBased());
        Flashcard editedFlashcard = new Flashcard(flashcardToEdit.getQuestion(),
                flashcardToEdit.getAnswer(), flashcardToEdit.getCategory(),
                flashcardToEdit.getPriority(), remark, flashcardToEdit.getTags());
        model.setFlashcard(flashcardToEdit, editedFlashcard);
        model.updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);

        return new CommandResult(generateSuccessMessage(editedFlashcard));
    }

    /**
     * Generates a command execution success message based on whether the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Flashcard flashcardToEdit) {
        String message = !remark.value.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, flashcardToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkCommand)) {
            return false;
        }

        // state check
        RemarkCommand e = (RemarkCommand) other;
        return index.equals(e.index)
                && remark.equals(e.remark);
    }
}

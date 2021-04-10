package seedu.weeblingo.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.weeblingo.model.Mode.MODE_LEARN;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import seedu.weeblingo.commons.core.LogsCenter;
import seedu.weeblingo.commons.core.Messages;
import seedu.weeblingo.commons.core.index.Index;
import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.flashcard.Answer;
import seedu.weeblingo.model.flashcard.Flashcard;
import seedu.weeblingo.model.flashcard.Question;
import seedu.weeblingo.model.tag.Tag;

/**
 * Deletes the user tag(s) of a Flashcard.
 */
public class DeleteTagCommand extends Command {

    public static final String COMMAND_WORD = "deleteTag";

    public static final String MESSAGE_SUCCESS = "Tag(s) deleted successfully!";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the requested tag(s), or all user tags from the specified flashcard if no tags are provided.\n"
            + "Parameters: FLASHCARD_INDEX, (optional) TAG...\n"
            + "Example: " + COMMAND_WORD + " 2";

    public static final String MESSAGE_TAG_DOES_NOT_EXIST = "The given tag is not part of the user tags.";

    public static final String MESSAGE_NO_TAGS_TO_DELETE = "There are no user tags to delete!";

    private static final Logger logger = LogsCenter.getLogger(DeleteTagCommand.class);

    private Index index;

    private Set<Tag> tags;

    /**
     * Creates a DeleteTagCommand representing a user command to delete tags from a flashcard.
     *
     * @param index The index of the flashcard to be deleted.
     * @param tags The tags to be deleted.
     */
    public DeleteTagCommand(Index index, Set<Tag> tags) {
        requireNonNull(index);
        requireNonNull(tags);
        this.index = index;
        this.tags = tags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getCurrentMode() != MODE_LEARN) {
            throw new CommandException(Messages.MESSAGE_TAG_NOT_IN_LEARN_MODE);
        }

        List<Flashcard> lastShownList = model.getFilteredFlashcardList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        Flashcard flashcardToDeleteTags = lastShownList.get(index.getZeroBased());

        if (flashcardToDeleteTags.getUserTags().equals(Collections.emptySet())) {
            throw new CommandException(MESSAGE_NO_TAGS_TO_DELETE);
        }

        boolean isAllTagsExist;
        for (Tag t : tags) {
            isAllTagsExist = checkIfTagExists(t, flashcardToDeleteTags);

            if (!isAllTagsExist) {
                throw new CommandException(MESSAGE_TAG_DOES_NOT_EXIST);
            }
        }

        Flashcard flashcardWithDeletedTags = createDeletedTagFlashcard(flashcardToDeleteTags, tags);

        logger.info("Deleting tag(s) from flashcard at index "
                + index.getOneBased());

        model.setFlashcard(flashcardToDeleteTags, flashcardWithDeletedTags);
        return new CommandResult(MESSAGE_SUCCESS, false, false);
    }

    /**
     * Checks if the given tag exists in the given Flashcard's user tags
     *
     * @param tag The tag to be checked.
     * @param flashcard The flashcard to be checked.
     * @return true if the tag exists, false otherwise.
     */
    private boolean checkIfTagExists(Tag tag, Flashcard flashcard) {
        for (Tag otherTag : flashcard.getUserTags()) {
            if (otherTag.equals(tag)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a copy of the provided flashcard, but with edited tag values.
     *
     * @param flashcardToEdit The flashcard that requires editing.
     * @param userTagsToRemove The tags that the user wishes to remove from the flashcard.
     * @return A new flashcard with the tags removed.
     */
    private static Flashcard createDeletedTagFlashcard(Flashcard flashcardToEdit, Set<Tag> userTagsToRemove) {
        assert userTagsToRemove != null;

        Question question = flashcardToEdit.getQuestion();
        Answer answer = flashcardToEdit.getAnswer();
        Set<Tag> tags = flashcardToEdit.getWeeblingoTags();
        Set<Tag> userTags = flashcardToEdit.getUserTags();
        if (userTagsToRemove.equals(Collections.emptySet())) {
            userTags = Collections.emptySet();
        } else {
            userTags.removeAll(userTagsToRemove);
        }

        return new Flashcard(question, answer, tags, userTags);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTagCommand // instanceof handles nulls
                && index.equals(((DeleteTagCommand) other).index)
                && tags.equals(((DeleteTagCommand) other).tags));
    }
}

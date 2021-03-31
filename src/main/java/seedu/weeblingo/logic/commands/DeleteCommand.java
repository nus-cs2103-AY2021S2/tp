package seedu.weeblingo.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.weeblingo.model.Mode.MODE_LEARN;
import static seedu.weeblingo.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import seedu.weeblingo.commons.core.Messages;
import seedu.weeblingo.commons.core.index.Index;
import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.flashcard.Answer;
import seedu.weeblingo.model.flashcard.Flashcard;
import seedu.weeblingo.model.flashcard.Question;
import seedu.weeblingo.model.tag.Tag;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_SUCCESS = "Tag(s) deleted successfully!";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the requested tag(s), or all tags from the specified flashcard is no tags are provided.\n"
            + "Parameters: FLASHCARD_INDEX, (optional) TAG...\n"
            + "Example: " + COMMAND_WORD + " 2";

    public static final String MESSAGE_TAG_DOES_NOT_EXIST = "The given tag does not exist.";

    public static final String MESSAGE_NO_TAGS_TO_DELETE = "There are no user tags to delete!";

    private Index index;

    private Set<Tag> tags;

    /**
     * Creates a DeleteCommand representing a user command to delete tags from a flashcard.
     *
     * @param index The index of the flashcard to be deleted.
     * @param tags The tags to be deleted.
     */
    public DeleteCommand(Index index, Set<Tag> tags) {
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

        boolean isAllTagsExist = true;
        for (Tag t : tags) {
            isAllTagsExist = isAllTagsExist && checkIfTagExists(t, flashcardToDeleteTags);
        }

        if (!isAllTagsExist) {
            throw new CommandException(MESSAGE_TAG_DOES_NOT_EXIST);
        }

        Flashcard flashcardWithDeletedTags = createDeletedTagFlashcard(flashcardToDeleteTags, tags);

        model.setFlashcard(flashcardToDeleteTags, flashcardWithDeletedTags);
        model.updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);

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
}

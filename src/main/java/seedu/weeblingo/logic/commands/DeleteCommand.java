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

    private boolean checkIfTagExists(Tag tag, Flashcard flashcard) {
        for (Tag otherTag : flashcard.getUserTags()) {
            if (otherTag.equals(tag)) {
                return true;
            }
        }
        return false;
    }

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

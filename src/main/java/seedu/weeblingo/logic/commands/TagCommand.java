package seedu.weeblingo.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.weeblingo.model.Mode.MODE_LEARN;
import static seedu.weeblingo.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;

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

    public static final String MESSAGE_DUPLICATE_TAG = "Duplicate tags are not allowed.";

    private Index index;

    private Set<Tag> tags;

    /**
     * Creates a TagCommand representing a user command to add tags to a flashcard.
     *
     * @param index The index of the flashcard to have tags added to.
     * @param tags The tags to be added.
     */
    public TagCommand(Index index, Set<Tag> tags) {
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

        Flashcard flashcardToTag = lastShownList.get(index.getZeroBased());
        Flashcard taggedFlashcard = createTaggedFlashcard(flashcardToTag, tags);

        for (Tag t : flashcardToTag.getUserTags()) {
            if (checkUserTagsForDuplicates(t)) {
                throw new CommandException(MESSAGE_DUPLICATE_TAG);
            }
        }

        model.setFlashcard(flashcardToTag, taggedFlashcard);
        model.updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);

        return new CommandResult(MESSAGE_SUCCESS, false, false);
    }

    /**
     * Checks if the given tag(s) already exists in the given Flashcard's user tags
     *
     * @param tag The tag to be checked.
     * @return true if the tag(s) already exist, false otherwise.
     */
    private boolean checkUserTagsForDuplicates(Tag tag) {
        for (Tag otherTag : tags) {
            if (tag.equals(otherTag)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a copy of the provided flashcard, but with edited tag values.
     * Previous tags are kept and not overwritten.
     *
     * @param flashcardToEdit The flashcard that requires editing.
     * @param userTagsToAdd The tags that the user wishes to add to the flashcard.
     * @return A new flashcard with tags added.
     */
    private static Flashcard createTaggedFlashcard(Flashcard flashcardToEdit, Set<Tag> userTagsToAdd) {
        assert userTagsToAdd != null;

        Question question = flashcardToEdit.getQuestion();
        Answer answer = flashcardToEdit.getAnswer();
        Set<Tag> tags = flashcardToEdit.getWeeblingoTags();
        Set<Tag> userTags = flashcardToEdit.getUserTags();
        userTags.addAll(userTagsToAdd);

        return new Flashcard(question, answer, tags, userTags);
    }

}

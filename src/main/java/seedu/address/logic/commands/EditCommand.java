package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.flashcard.Answer;
import seedu.address.model.flashcard.Category;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.model.flashcard.Priority;
import seedu.address.model.flashcard.Question;
import seedu.address.model.flashcard.Remark;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing flashcard in flashcard list.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_QUESTION + "NAME] "
            + "[" + PREFIX_ANSWER + "PHONE] "
            + "[" + PREFIX_CATEGORY + "EMAIL] "
            + "[" + PREFIX_PRIORITY + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ANSWER + "91234567 "
            + PREFIX_CATEGORY + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final EditFlashcardDescriptor editFlashcardDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editFlashcardDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditFlashcardDescriptor editFlashcardDescriptor) {
        requireNonNull(index);
        requireNonNull(editFlashcardDescriptor);

        this.index = index;
        this.editFlashcardDescriptor = new EditFlashcardDescriptor(editFlashcardDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Flashcard> lastShownList = model.getFilteredFlashcardList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        Flashcard flashcardToEdit = lastShownList.get(index.getZeroBased());
        Flashcard editedFlashcard = createEditedPerson(flashcardToEdit, editFlashcardDescriptor);

        if (!flashcardToEdit.isSameFlashcard(editedFlashcard) && model.hasFlashcard(editedFlashcard)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setFlashcard(flashcardToEdit, editedFlashcard);
        model.updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedFlashcard));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editFlashcardDescriptor}.
     */
    private static Flashcard createEditedPerson(Flashcard flashcardToEdit, EditFlashcardDescriptor editFlashcardDescriptor) {
        assert flashcardToEdit != null;

        Question updatedQuestion = editFlashcardDescriptor.getQuestion().orElse(flashcardToEdit.getQuestion());
        Answer updatedAnswer = editFlashcardDescriptor.getAnswer().orElse(flashcardToEdit.getAnswer());
        Category updatedCategory = editFlashcardDescriptor.getCategory().orElse(flashcardToEdit.getCategory());
        Priority updatedPriority = editFlashcardDescriptor.getPriority().orElse(flashcardToEdit.getPriority());
        Remark updatedRemark = flashcardToEdit.getRemark();
        Set<Tag> updatedTags = editFlashcardDescriptor.getTags().orElse(flashcardToEdit.getTags());

        return new Flashcard(updatedQuestion, updatedAnswer, updatedCategory,
                updatedPriority, updatedRemark, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editFlashcardDescriptor.equals(e.editFlashcardDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditFlashcardDescriptor {
        private Question question;
        private Answer answer;
        private Category category;
        private Priority priority;
        private Set<Tag> tags;

        public EditFlashcardDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditFlashcardDescriptor(EditFlashcardDescriptor toCopy) {
            setQuestion(toCopy.question);
            setAnswer(toCopy.answer);
            setCategory(toCopy.category);
            setPriority(toCopy.priority);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(question, answer, category, priority, tags);
        }

        public void setQuestion(Question question) {
            this.question = question;
        }

        public Optional<Question> getQuestion() {
            return Optional.ofNullable(question);
        }

        public void setAnswer(Answer answer) {
            this.answer = answer;
        }

        public Optional<Answer> getAnswer() {
            return Optional.ofNullable(answer);
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public Optional<Category> getCategory() {
            return Optional.ofNullable(category);
        }

        public void setPriority(Priority priority) {
            this.priority = priority;
        }

        public Optional<Priority> getPriority() {
            return Optional.ofNullable(priority);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditFlashcardDescriptor)) {
                return false;
            }

            // state check
            EditFlashcardDescriptor e = (EditFlashcardDescriptor) other;

            return getQuestion().equals(e.getQuestion())
                    && getAnswer().equals(e.getAnswer())
                    && getCategory().equals(e.getCategory())
                    && getPriority().equals(e.getPriority())
                    && getTags().equals(e.getTags());
        }
    }
}

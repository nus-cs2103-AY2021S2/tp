package seedu.address.model.flashcard;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Flashcard}'s fields matches the keywords given.
 */
public class FlashcardFilterPredicate implements Predicate<Flashcard> {
    private final List<String> questions;
    private final List<String> categories;
    private final List<String> priorities;
    private final List<String> tags;

    /**
     * Constructs a FlashcardFilterPredicate object with the given keyword list for questions, categories
     * priorities, and tags.
     *
     * @param questions keyword list for questions.
     * @param categories keyword list for categories.
     * @param priorities keyword list for priorities.
     * @param tags keyword list for tags.
     */
    public FlashcardFilterPredicate(List<String> questions, List<String> categories,
            List<String> priorities, List<String> tags) {
        requireNonNull(questions);
        requireNonNull(categories);
        requireNonNull(priorities);
        requireNonNull(tags);
        this.questions = questions;
        this.categories = categories;
        this.priorities = priorities;
        this.tags = tags;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        assert (questions != null);
        assert (categories != null);
        assert (priorities != null);
        assert (tags != null);

        return testQuestions(flashcard) && testCategories(flashcard)
                && testPriorities(flashcard) && testTags(flashcard);
    }

    private boolean testQuestions(Flashcard flashcard) {
        return questions.isEmpty() || questions.stream()
                .anyMatch(keyword -> StringUtil
                        .sentenceContainsPartWordIgnoreCase(flashcard.getQuestion().fullQuestion, keyword));
    }

    private boolean testCategories(Flashcard flashcard) {
        return categories.isEmpty() || categories.stream()
                .anyMatch(keyword -> StringUtil
                        .sentenceContainsPartWordIgnoreCase(flashcard.getCategory().value, keyword));
    }

    private boolean testPriorities(Flashcard flashcard) {
        return priorities.isEmpty() || priorities.stream()
                .anyMatch(keyword -> StringUtil
                        .sentenceContainsPartWordIgnoreCase(flashcard.getPriority().value, keyword));
    }

    private boolean testTags(Flashcard flashcard) {
        return tags.isEmpty() || tags.stream()
                .anyMatch(keyword -> CollectionUtil
                        .tagContainsPartWordIgnoreCase(flashcard.getTags(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FlashcardFilterPredicate // instanceof handles nulls
                && questions.equals(((FlashcardFilterPredicate) other).questions) // state check
                && categories.equals(((FlashcardFilterPredicate) other).categories)
                && priorities.equals(((FlashcardFilterPredicate) other).priorities)
                && tags.equals(((FlashcardFilterPredicate) other).tags));
    }
}

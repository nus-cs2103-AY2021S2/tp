package seedu.address.model.flashcard;

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

    public FlashcardFilterPredicate(List<String> questions, List<String> categories,
            List<String> priorities, List<String> tags) {
        this.questions = questions;
        this.categories = categories;
        this.priorities = priorities;
        this.tags = tags;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        return testQuestions(flashcard) && testCategories(flashcard)
                && testPriorities(flashcard) && testTags(flashcard);
    }

    private boolean testQuestions(Flashcard flashcard) {
        return questions == null || questions.stream()
                .anyMatch(keyword -> StringUtil
                        .sentenceContainsPartWordIgnoreCase(flashcard.getQuestion().fullQuestion, keyword));
    }

    private boolean testCategories(Flashcard flashcard) {
        return categories == null || categories.stream()
                .anyMatch(keyword -> StringUtil
                        .sentenceContainsPartWordIgnoreCase(flashcard.getCategory().value, keyword));
    }

    private boolean testPriorities(Flashcard flashcard) {
        return priorities == null || priorities.stream()
                .anyMatch(keyword -> StringUtil
                        .sentenceContainsPartWordIgnoreCase(flashcard.getPriority().value, keyword));
    }

    private boolean testTags(Flashcard flashcard) {
        return tags == null || tags.stream()
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

package seedu.address.model.flashcard;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Flashcard}'s {@code fields} matches any of the keywords given.
 */
public class FlashcardContainsKeywordsPredicate implements Predicate<Flashcard> {
    private final List<String> keywords;
    /**
     * Creates a FlashcardContainsKeywordsPredicate with list of keywords to match.
     *
     * @param keywords list of keywords to match, cannot be null.
     */
    public FlashcardContainsKeywordsPredicate(List<String> keywords) {
        requireNonNull(keywords);
        this.keywords = keywords;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil
                        .sentenceContainsPartWordIgnoreCase(flashcard.getQuestion().fullQuestion, keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil
                        .sentenceContainsPartWordIgnoreCase(flashcard.getAnswer().value, keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil
                        .sentenceContainsPartWordIgnoreCase(flashcard.getCategory().value, keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil
                        .sentenceContainsPartWordIgnoreCase(flashcard.getPriority().value, keyword))
                || keywords.stream()
                .anyMatch(keyword -> CollectionUtil
                        .tagContainsPartWordIgnoreCase(flashcard.getTags(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FlashcardContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((FlashcardContainsKeywordsPredicate) other).keywords)); // state check
    }
}

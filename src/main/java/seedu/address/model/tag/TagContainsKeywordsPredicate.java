package seedu.address.model.tag;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.flashcard.Flashcard;

/**
 * Tests that a {@code Flashcard}'s {@code Tag} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Flashcard> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        return keywords.stream()
                .anyMatch(keyword -> CollectionUtil.tagContainsWordIgnoreCase(flashcard.getTags(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }
}

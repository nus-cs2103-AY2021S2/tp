package seedu.dictionote.model.contact;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Tag}s match all of the keywords given.
 */
public class TagsContainKeywordsPredicate implements Predicate<Contact> {
    private final List<String> keywords;

    public TagsContainKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Contact contact) {
        return keywords.stream()
                .allMatch(keyword -> contact.getTags()
                        .toString()
                        .toLowerCase()
                        .contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagsContainKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagsContainKeywordsPredicate) other).keywords)); // state check
    }

}

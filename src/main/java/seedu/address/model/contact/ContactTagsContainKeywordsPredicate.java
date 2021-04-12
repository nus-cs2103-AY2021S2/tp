package seedu.address.model.contact;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Contact}'s {@code Tags} matches all of the keywords given.
 */
public class ContactTagsContainKeywordsPredicate implements Predicate<Contact> {

    private final List<String> keywords;

    public ContactTagsContainKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Contact contact) {
        return keywords.stream()
                .allMatch(keyword -> contact.getTags().stream()
                        .anyMatch(tag -> StringUtil.containsWordIgnoreCase(tag.tagName, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactTagsContainKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ContactTagsContainKeywordsPredicate) other).keywords)); // state check
    }
}

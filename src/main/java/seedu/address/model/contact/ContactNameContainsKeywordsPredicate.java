package seedu.address.model.contact;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Contact}'s {@code ContactName} matches all of the keywords given.
 */
public class ContactNameContainsKeywordsPredicate implements Predicate<Contact> {
    private final List<String> keywords;

    public ContactNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Contact contact) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(contact.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ContactNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}


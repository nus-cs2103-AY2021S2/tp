package seedu.address.model.contact.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.contact.Contact;

/**
 * Tests that a {@code Contact}'s {@code Name} matches any of the keywords given.
 */
public abstract class FieldContainsKeywordsPredicate implements Predicate<Contact> {
    private final List<String> keywords;

    public FieldContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    @Override
    public abstract boolean test(Contact contact);

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FieldContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((FieldContainsKeywordsPredicate) other).keywords)); // state check
    }
}

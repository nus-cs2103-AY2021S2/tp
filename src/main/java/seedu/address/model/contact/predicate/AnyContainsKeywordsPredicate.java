package seedu.address.model.contact.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.contact.Contact;

/**
 * Tests that any of {@code Contact}'s fields matches any of the keywords given.
 */
public class AnyContainsKeywordsPredicate implements Predicate<Contact> {
    private final List<String> keywords;

    public AnyContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Contact contact) {
        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(keywords);
        AddressContainsKeywordsPredicate addressPredicate = new AddressContainsKeywordsPredicate(keywords);
        PhoneContainsKeywordsPredicate phonePredicate = new PhoneContainsKeywordsPredicate(keywords);
        EmailContainsKeywordsPredicate emailPredicate = new EmailContainsKeywordsPredicate(keywords);

        return namePredicate.test(contact) || addressPredicate.test(contact) || phonePredicate.test(contact)
                || emailPredicate.test(contact);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AnyContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AnyContainsKeywordsPredicate) other).keywords)); // state check
    }
}

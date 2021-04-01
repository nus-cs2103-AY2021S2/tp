package seedu.address.model.person.predicate;

import seedu.address.model.person.Person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that any of {@code Person}'s fields matches any of the keywords given.
 */
public class AnyContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public AnyContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(keywords);
        AddressContainsKeywordsPredicate addressPredicate = new AddressContainsKeywordsPredicate(keywords);
        PhoneContainsKeywordsPredicate phonePredicate = new PhoneContainsKeywordsPredicate(keywords);
        EmailContainsKeywordsPredicate emailPredicate = new EmailContainsKeywordsPredicate(keywords);

        return namePredicate.test(person) || addressPredicate.test(person) || phonePredicate.test(person)
                || emailPredicate.test(person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AnyContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AnyContainsKeywordsPredicate) other).keywords)); // state check
    }
}

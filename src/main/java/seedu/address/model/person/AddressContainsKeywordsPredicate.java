//@@author wongkokian
package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Address} matches any of the keywords given.
 */
public class AddressContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public AddressContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> person.getAddress().get().value.toLowerCase().contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AddressContainsKeywordsPredicate) other).keywords)); // state check
    }

}

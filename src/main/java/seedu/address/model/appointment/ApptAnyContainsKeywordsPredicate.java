package seedu.address.model.appointment;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class ApptAnyContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public ApptAnyContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword)
                        || StringUtil.containsWordIgnoreCase(person.getEmail().getValue(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ApptAnyContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ApptAnyContainsKeywordsPredicate) other).keywords)); // state check
    }
}

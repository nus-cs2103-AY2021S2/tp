package seedu.booking.model.person;

import java.util.function.Predicate;

import seedu.booking.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Email} matches the keyword.
 */
public class EmailContainsKeywordsPredicate implements Predicate<Person> {
    private final String keyword;

    public EmailContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsWordIgnoreCase(keyword, String.valueOf(person.getEmail()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((EmailContainsKeywordsPredicate) other).keyword)); // state check
    }

}

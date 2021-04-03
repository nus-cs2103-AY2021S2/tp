package seedu.booking.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.booking.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class PhoneContainsKeywordsPredicate implements Predicate<Person> {
    private final String keyword;

    public static final String MESSAGE_CONSTRAINTS = "Keyword(s) for name should not be empty";

    public PhoneContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return keyword.equals(person.getPhone().toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((PhoneContainsKeywordsPredicate) other).keyword)); // state check
    }

}
package seedu.booking.model.person;

import java.util.function.Predicate;

import seedu.booking.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Email} matches the keyword.
 */
public class EmailMatchesKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    public EmailMatchesKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsWordIgnoreCase(keyword, String.valueOf(person.getEmail()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailMatchesKeywordPredicate // instanceof handles nulls
                && keyword.equals(((EmailMatchesKeywordPredicate) other).keyword)); // state check
    }

}

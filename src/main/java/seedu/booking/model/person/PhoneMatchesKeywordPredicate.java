package seedu.booking.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Phone} matches the given phone number.
 */
public class PhoneMatchesKeywordPredicate implements Predicate<Person> {
    public static final String MESSAGE_CONSTRAINTS = "Phone number cannot be empty.";

    private final String keyword;

    public PhoneMatchesKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return keyword.equals(person.getPhone().toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneMatchesKeywordPredicate // instanceof handles nulls
                && keyword.equals(((PhoneMatchesKeywordPredicate) other).keyword)); // state check
    }

}

package seedu.booking.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Phone} matches the given phone number.
 */
public class PhoneContainsKeywordsPredicate implements Predicate<Person> {
    private final String keyword;

    public static final String MESSAGE_CONSTRAINTS = "Phone number cannot be empty.";

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
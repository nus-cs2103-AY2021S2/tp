package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class GenderContainsKeywordsPredicate implements Predicate<Person> {

    private final String gender;

    /**
     * Constructs an {@code GenderContainsKeywordsPredicate} to be used in the predicate test
     *
     * @param keywords a user filter search input.
     */
    public GenderContainsKeywordsPredicate(String keywords) {
        this.gender = keywords;
    }

    @Override
    public boolean test(Person person) {

        return gender == null || StringUtil.containsWordIgnoreCase(person.getGender().value, gender);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GenderContainsKeywordsPredicate // instanceof handles nulls
                && gender.equals(((GenderContainsKeywordsPredicate) other).gender)); // state check
    }

}

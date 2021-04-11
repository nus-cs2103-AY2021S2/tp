package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

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

        if (gender.equalsIgnoreCase("M")) {
            return gender == null || StringUtil.containsWordIgnoreCase(person.getGender().value, "Male")
                    || StringUtil.containsWordIgnoreCase(person.getGender().value, gender);
        } else if (gender.equalsIgnoreCase("N")) {
            return gender == null || StringUtil.containsWordIgnoreCase(person.getGender().value, "Non-binary")
                    || StringUtil.containsWordIgnoreCase(person.getGender().value, gender);
        } else {
            return gender == null || StringUtil.containsWordIgnoreCase(person.getGender().value, "Female")
                    || StringUtil.containsWordIgnoreCase(person.getGender().value, gender);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GenderContainsKeywordsPredicate // instanceof handles nulls
                && gender.equals(((GenderContainsKeywordsPredicate) other).gender)); // state check
    }

}

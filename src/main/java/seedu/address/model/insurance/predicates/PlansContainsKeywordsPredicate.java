package seedu.address.model.insurance.predicates;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

public class PlansContainsKeywordsPredicate implements Predicate<Person> {


    private final String plan;

    /**
     * Constructs an {@code PlansContainsKeywordsPredicate} to be used in the predicate test
     *
     * @param keywords a user filter search input.
     */
    public PlansContainsKeywordsPredicate(String keywords) {
        this.plan = keywords;
    }

    @Override
    public boolean test(Person person) {

        return plan == null || StringUtil.containsPlansIgnoreCase(person.getPlans(), plan);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PlansContainsKeywordsPredicate // instanceof handles nulls
                && plan.equals(((PlansContainsKeywordsPredicate) other).plan)); // state check
    }

}

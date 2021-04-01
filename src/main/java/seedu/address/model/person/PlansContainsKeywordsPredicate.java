package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.AgeParser;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PlansContainsKeywordsPredicate implements Predicate<Person> {


    private final String plan;

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

package seedu.address.model.person.predicates;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.parser.AgeParser;
import seedu.address.model.person.Person;

public class AgeIsInRangePredicate implements Predicate<Person> {

    private final String age;
    private final List<Integer> ageRangeList;

    /**
     * Constructs an {@code AgeIsInRangePredicate} to be used in the predicate test
     *
     * @param keywords a user filter search input.
     */
    public AgeIsInRangePredicate(String keywords) {
        this.age = keywords;
        this.ageRangeList = new AgeParser(age).value();
    }

    @Override
    public boolean test(Person person) {

        boolean isInAgeRange = ageRangeList.size() == 1
                ? Period.between(person.getBirthdate().value, LocalDate.now()).getYears() == ageRangeList.get(0)
                : Period.between(person.getBirthdate().value, LocalDate.now()).getYears() >= ageRangeList.get(0)
                && Period.between(person.getBirthdate().value, LocalDate.now()).getYears() <= ageRangeList.get(1);

        return isInAgeRange;

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AgeIsInRangePredicate // instanceof handles nulls
                && age.equals(((AgeIsInRangePredicate) other).age)); // state check
    }

}

package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.AgeParser;
import seedu.address.logic.parser.exceptions.ParseException;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class AgeIsInRangePredicate implements Predicate<Person> {

    private final String age;
    private final List<Integer> ageRangeList;

    public AgeIsInRangePredicate(String keywords) {
        this.age = keywords;
        this.ageRangeList = new AgeParser(age).value();
    }

//    address, gender, tags, insurance plan name, age
//    keywords.stream()
//            .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));

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

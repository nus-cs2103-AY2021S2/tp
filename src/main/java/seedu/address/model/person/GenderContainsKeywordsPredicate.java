package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.AgeParser;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class GenderContainsKeywordsPredicate implements Predicate<Person> {


    private final String gender;

    public GenderContainsKeywordsPredicate(String keywords) {
        this.gender = keywords;
    }

//    address, gender, tags, insurance plan name, age
//    keywords.stream()
//            .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));

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

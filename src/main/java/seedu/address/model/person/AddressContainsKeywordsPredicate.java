package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.AgeParser;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class AddressContainsKeywordsPredicate implements Predicate<Person> {


    private final String address;

    public AddressContainsKeywordsPredicate(String keywords) {
        this.address = keywords;
    }

    @Override
    public boolean test(Person person) {

        return address == null || StringUtil.containsWordIgnoreCase(person.getAddress().value, address);

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressContainsKeywordsPredicate // instanceof handles nulls
                && address.equals(((AddressContainsKeywordsPredicate) other).address)); // state check
    }

}

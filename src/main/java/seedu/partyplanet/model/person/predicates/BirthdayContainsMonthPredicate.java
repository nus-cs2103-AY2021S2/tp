package seedu.partyplanet.model.person.predicates;

import static seedu.partyplanet.logic.parser.ParserUtil.parseMonthInteger;

import java.util.function.Predicate;

import seedu.partyplanet.logic.parser.exceptions.ParseException;
import seedu.partyplanet.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Birthday} matches the birthday month given.
 */
public class BirthdayContainsMonthPredicate implements Predicate<Person> {
    private final int month;

    public BirthdayContainsMonthPredicate(String keywords) throws ParseException {
        this.month = parseMonthInteger(keywords);
    }

    @Override
    public boolean test(Person person) {
        return person.getBirthday().getMonth() == month;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BirthdayContainsMonthPredicate // instanceof handles nulls
                && month == ((BirthdayContainsMonthPredicate) other).month); // state check
    }

}

package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

public class AddressContainsKeywordsPredicate implements Predicate<Person> {

    private final String address;

    /**
     * Constructs an {@code AddressContainsKeywordsPredicate} to be used in the predicate test
     *
     * @param keywords a user filter search input.
     */
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

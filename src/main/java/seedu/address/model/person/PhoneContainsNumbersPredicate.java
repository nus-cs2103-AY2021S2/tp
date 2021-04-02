package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class PhoneContainsNumbersPredicate implements Predicate<Person> {
    private final List<String> numbers;

    public PhoneContainsNumbersPredicate(List<String> numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean test(Person person) {
        return numbers.stream()
                .anyMatch(number -> StringUtil.isSubstring(person.getPhone().value, number));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneContainsNumbersPredicate // instanceof handles nulls
                && numbers.equals(((PhoneContainsNumbersPredicate) other).numbers)); // state check
    }
}

package seedu.address.model.common;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Tests that an {@code Event}'s {@code Name} matches any of the keywords given.
 */
public class DatePastPredicate implements Predicate<Date> {
    private final LocalDate nowDate = LocalDate.now();

    public DatePastPredicate() { }

    @Override
    public boolean test(Date date) {
        return nowDate.isBefore(date.getDate());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DatePastPredicate // instanceof handles nulls
                && nowDate.equals(((DatePastPredicate) other).nowDate)); // state check
    }
}

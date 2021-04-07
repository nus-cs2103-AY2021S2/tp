package seedu.address.model.common;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Tests that the given {@code Date} is today or after today.
 */
public class DatePastPredicate implements Predicate<Date> {
    private final LocalDate nowDate = LocalDate.now();

    public DatePastPredicate() { }

    /**
     * Returns true if the given date is today or after today and false, otherwise.
     *
     * @param date date to compare with.
     * @return Returns true if the given date is today or after today and false, otherwise.
     */
    @Override
    public boolean test(Date date) {
        return nowDate.isBefore(date.getDate()) || nowDate.isEqual(date.getDate());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DatePastPredicate // instanceof handles nulls
                && nowDate.equals(((DatePastPredicate) other).nowDate)); // state check
    }
}

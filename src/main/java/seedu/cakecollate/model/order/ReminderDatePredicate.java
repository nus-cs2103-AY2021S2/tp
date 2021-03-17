package seedu.cakecollate.model.order;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Tests that a {@code Orders}'s {@code DeliveryDate} is within 3 days of the current date.
 */
public class ReminderDatePredicate implements Predicate<Order> {

    private long days;

    public ReminderDatePredicate(long days) {
        this.days = days;
    }

    @Override
    public boolean test(Order order) {
        return isWithinXDays(order);
    }

    /**
     * Returns true if the order's delivery date is within X days of the current date.
     */
    public boolean isWithinXDays(Order order) {
        LocalDate toTest = order.getDeliveryDate().getValue();
        LocalDate dateToday = LocalDate.now();
        LocalDate acceptableDate = dateToday.plusDays(days + 1);
        return toTest.isAfter(dateToday.minusDays(1L)) && toTest.isBefore(acceptableDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReminderDatePredicate // instanceof handles nulls
                && days == (((ReminderDatePredicate) other).days)); // state check
    }

}


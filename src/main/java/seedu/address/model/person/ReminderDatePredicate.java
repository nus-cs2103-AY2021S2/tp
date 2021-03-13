package seedu.address.model.person;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Tests that a {@code Orders}'s {@code DeliveryDate} is within 3 days of the current date.
 */
public class ReminderDatePredicate implements Predicate<Person> {

    private static final long DELIVERY_DATE_REMIND = 4L;

    // Constructor is empty because the command "remind" is not dependent on
    // any variable that is parsed in.
    public ReminderDatePredicate() {
    }

    @Override
    public boolean test(Person person) {
        return isWithinThreeDays(person);
    }

    /**
     * Returns true if the order's delivery date is within 3 days of the current date.
     */
    public boolean isWithinThreeDays(Person person) {
        LocalDate toTest = person.getDeliveryDate().getValue();
        LocalDate dateToday = LocalDate.now();
        LocalDate acceptableDate = dateToday.plusDays(DELIVERY_DATE_REMIND);
        return toTest.isEqual(dateToday) || toTest.isBefore(acceptableDate);
    }
}

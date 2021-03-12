package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Orders}'s {@code DeliveryDate} is within 3 days of the current date.
 */
public class ReminderDatePredicate implements Predicate<Person> {

    // Constructor is empty because the command "remind" is not dependent on
    // any variable that is parsed in.
    public ReminderDatePredicate() {
    }

    @Override
    public boolean test(Person person) {
        return DeliveryDate.isWithinThreeDays(person);
    }
}

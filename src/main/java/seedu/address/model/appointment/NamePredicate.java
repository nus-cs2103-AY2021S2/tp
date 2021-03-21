package seedu.address.model.appointment;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Name;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 * Since the {@code Name} is tagged to the Person object, the Person object needs to be retrieve first.
 * Then, {@code Email} will be extracted out to perform the Predicate Search.
 */
public class NamePredicate implements Predicate<Appointment> {
    private final List<Name> names;

    public NamePredicate(List<Name> names) {
        this.names = names;
    }

    @Override
    public boolean test(Appointment appointment) {
        return names.stream().anyMatch(email -> email.equals(appointment.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NamePredicate // instanceof handles nulls
                && names.equals(((NamePredicate) other).names)); // state check
    }
}

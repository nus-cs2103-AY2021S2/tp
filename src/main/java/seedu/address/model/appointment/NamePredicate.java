package seedu.address.model.appointment;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Name;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 * Since the {@code Name} is tagged to the Person object, the Person object needs to be retrieve first.
 * Then, {@code Email} will be extracted out to perform the Predicate Search.
 */
public class NamePredicate implements Predicate<Appointment> {
    private final List<Name> keywords;

    public NamePredicate(List<Name> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Appointment appointment) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(appointment.getName().fullName, keyword.fullName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NamePredicate // instanceof handles nulls
                && keywords.equals(((NamePredicate) other).keywords)); // state check
    }
}

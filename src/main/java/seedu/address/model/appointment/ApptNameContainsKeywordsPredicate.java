package seedu.address.model.appointment;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class ApptNameContainsKeywordsPredicate implements Predicate<Appointment> {
    private final List<String> names;

    public ApptNameContainsKeywordsPredicate(List<String> names) {
        this.names = names;
    }

    @Override
    public boolean test(Appointment appointment) {
        return names.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(appointment.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ApptNameContainsKeywordsPredicate // instanceof handles nulls
                && names.equals(((ApptNameContainsKeywordsPredicate) other).names)); // state check
    }
}

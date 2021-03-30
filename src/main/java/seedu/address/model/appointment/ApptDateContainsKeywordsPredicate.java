package seedu.address.model.appointment;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class ApptDateContainsKeywordsPredicate implements Predicate<Appointment> {
    private final List<String> names;

    public ApptDateContainsKeywordsPredicate(List<String> names) {
        this.names = names;
    }

    @Override
    public boolean test(Appointment appointment) {
        return names.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(appointment.getDateTime().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ApptDateContainsKeywordsPredicate // instanceof handles nulls
                && names.equals(((ApptDateContainsKeywordsPredicate) other).names)); // state check
    }
}

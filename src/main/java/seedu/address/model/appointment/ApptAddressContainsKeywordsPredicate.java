package seedu.address.model.appointment;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class ApptAddressContainsKeywordsPredicate implements Predicate<Appointment> {
    private final List<String> names;

    public ApptAddressContainsKeywordsPredicate(List<String> names) {
        this.names = names;
    }

    @Override
    public boolean test(Appointment appointment) {
        return names.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(appointment.getAddress().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ApptAddressContainsKeywordsPredicate // instanceof handles nulls
                && names.equals(((ApptAddressContainsKeywordsPredicate) other).names)); // state check
    }
}

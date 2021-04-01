package seedu.address.model.appointment;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class ApptFieldContainsKeywordsPredicate implements Predicate<Appointment> {
    private final List<String> keywords;

    public ApptFieldContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    @Override
    public boolean test(Appointment appointment) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(appointment.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ApptFieldContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ApptFieldContainsKeywordsPredicate) other).keywords)); // state check
    }
}

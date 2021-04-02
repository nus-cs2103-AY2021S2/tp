package seedu.address.model.appointment.predicate;

import java.util.List;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.appointment.Appointment;

/**
 * Tests that a {@code Contact}'s {@code Name} matches any of the keywords given.
 */
public class ApptNameContainsKeywordsPredicate extends ApptFieldContainsKeywordsPredicate {

    public ApptNameContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Appointment appointment) {
        return super.getKeywords().stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(appointment.getName().fullName, keyword));
    }
}

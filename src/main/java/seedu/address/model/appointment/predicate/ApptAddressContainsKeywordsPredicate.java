package seedu.address.model.appointment.predicate;

import java.util.List;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.appointment.Appointment;

/**
 * Tests that a {@code Appointment}'s {@code Address} matches any of the keywords given.
 */
public class ApptAddressContainsKeywordsPredicate extends ApptFieldContainsKeywordsPredicate {

    public ApptAddressContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Appointment appointment) {
        return super.getKeywords().stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(appointment.getAddress().toString(), keyword));
    }

}

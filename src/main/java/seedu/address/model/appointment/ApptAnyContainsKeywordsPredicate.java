package seedu.address.model.appointment;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class ApptAnyContainsKeywordsPredicate implements Predicate<Appointment> {
    private final List<String> keywords;

    public ApptAnyContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Appointment appointment) {
        ApptNameContainsKeywordsPredicate namePredicate = new ApptNameContainsKeywordsPredicate(keywords);
        ApptDateContainsKeywordsPredicate datePredicate = new ApptDateContainsKeywordsPredicate(keywords);
        ApptAddressContainsKeywordsPredicate addressPredicate = new ApptAddressContainsKeywordsPredicate(keywords);

        return namePredicate.test(appointment) || datePredicate.test(appointment)
                || addressPredicate.test(appointment);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ApptAnyContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ApptAnyContainsKeywordsPredicate) other).keywords)); // state check
    }
}

package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.ApptFieldContainsKeywordsPredicate;

import java.util.List;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class AddressContainsKeywordsPredicate extends FieldContainsKeywordsPredicate {

    public AddressContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Person person) {
        return super.getKeywords().stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(person.getAddress().toString(), keyword));
    }

}

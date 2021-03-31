package seedu.address.model.appointment;

import java.util.List;
import java.util.Set;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class ApptContactsContainKeywordsPredicate extends ApptFieldContainsKeywordsPredicate {

    public ApptContactsContainKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Appointment appointment) {
        Set<Person> persons = appointment.getContacts();

        for (Person person : persons) {
            boolean isFound = super.getKeywords().stream().anyMatch(keyword ->
                    StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
            if (isFound) {
                return true;
            }
        }

        return false;
    }

}

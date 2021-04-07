package seedu.address.model.appointment;

import java.util.List;
import java.util.Set;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.contact.Contact;

/**
 * Tests that a {@code Contact}'s {@code Name} matches any of the keywords given.
 */
public class ApptContactsContainKeywordsPredicate extends ApptFieldContainsKeywordsPredicate {

    public ApptContactsContainKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Appointment appointment) {
        Set<Contact> contacts = appointment.getContacts();

        for (Contact contact : contacts) {
            boolean isFound = super.getKeywords().stream().anyMatch(keyword ->
                    StringUtil.containsWordIgnoreCase(contact.getName().fullName, keyword));
            if (isFound) {
                return true;
            }
        }

        return false;
    }

}

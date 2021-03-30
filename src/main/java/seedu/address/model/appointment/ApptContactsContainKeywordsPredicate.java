package seedu.address.model.appointment;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

import java.util.List;
import java.util.stream.Stream;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class ApptContactsContainKeywordsPredicate extends ApptFieldContainsKeywordsPredicate {

    public ApptContactsContainKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Appointment appointment) {
        Stream<Person> contacts = appointment.getContacts().stream();
        Stream<String> keywords = super.getKeywords().stream();

        return contacts.anyMatch( contact ->
                keywords.anyMatch(keyword ->
                StringUtil.containsWordIgnoreCase(contact.getName().fullName, keyword)));
    }

}

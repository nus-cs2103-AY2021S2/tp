package seedu.address.model.contact.predicate;

import java.util.List;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.contact.Contact;

/**
 * Tests that a {@code Contact}'s {@code Phone} matches any of the keywords given.
 */
public class PhoneContainsKeywordsPredicate extends FieldContainsKeywordsPredicate {

    public PhoneContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Contact contact) {
        return super.getKeywords().stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(contact.getPhone().toString(), keyword));
    }
}

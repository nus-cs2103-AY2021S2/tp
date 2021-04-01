package seedu.address.model.contact;

import java.util.List;

import seedu.address.commons.util.StringUtil;


/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class AddressContainsKeywordsPredicate extends FieldContainsKeywordsPredicate {

    public AddressContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Contact contact) {
        return super.getKeywords().stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(contact.getAddress().toString(), keyword));
    }

}

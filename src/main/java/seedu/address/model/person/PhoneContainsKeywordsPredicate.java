package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;

import java.util.List;

/**
 * Tests that a {@code Person}'s {@code Phone} matches any of the keywords given.
 */
public class PhoneContainsKeywordsPredicate extends FieldContainsKeywordsPredicate {

    public PhoneContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Person person) {
        return super.getKeywords().stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(person.getPhone().toString(), keyword));
    }
}

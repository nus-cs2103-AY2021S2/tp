package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;

import java.util.List;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class EmailContainsKeywordsPredicate extends FieldContainsKeywordsPredicate {

    public EmailContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Person person) {
        return super.getKeywords().stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(person.getEmail().toString(), keyword));
    }
}

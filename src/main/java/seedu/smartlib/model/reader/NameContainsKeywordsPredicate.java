package seedu.smartlib.model.reader;

import java.util.List;
import java.util.function.Predicate;

import seedu.smartlib.commons.util.StringUtil;

/**
 * Tests that a {@code Reader}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Reader> {
    private final List<String> keywords;

    /**
     * Creates a NameContainsKeywordPredicate
     * @param keywords list of keywords
     */
    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Reader reader) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(reader.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}

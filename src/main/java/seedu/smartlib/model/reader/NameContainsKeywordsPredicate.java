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
     * Creates a NameContainsKeywordPredicate.
     *
     * @param keywords list of keywords
     */
    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Tests if the reader's name contains any of the keywords.
     *
     * @param reader the reader to be tested.
     * @return true if the reader's name contains any of the keywords, and false otherwise.
     */
    @Override
    public boolean test(Reader reader) {
        return keywords
                .stream()
                .anyMatch(keyword -> {
                    if (!keyword.equals("")) {
                        return StringUtil.containsWordIgnoreCase(reader.getName().toString(), keyword);
                    }
                    return false;
                });
    }

    /**
     * Checks if this NameContainsKeywordPredicate is equal to another NameContainsKeywordPredicate.
     *
     * @param other the other NameContainsKeywordPredicate to be compared.
     * @return true if this NameContainsKeywordPredicate is equal to the other NameContainsKeywordPredicate,
     *         and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}

package seedu.address.model.garment;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Abstract class that tests that a {@code Garment}'s attribute matches any of the keywords given.
 */
public abstract class ContainsKeywordsPredicate implements Predicate<Garment> {
    protected final List<String> keywords;

    public ContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public abstract boolean test(Garment garment);

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ContainsKeywordsPredicate) other).keywords)); // state check
    }
}
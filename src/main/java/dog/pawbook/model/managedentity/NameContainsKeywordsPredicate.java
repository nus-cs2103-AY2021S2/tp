package dog.pawbook.model.managedentity;

import java.util.List;
import java.util.function.Predicate;

import dog.pawbook.commons.util.StringUtil;
import javafx.util.Pair;

/**
 * Tests that a {@code Owner}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Pair<Integer, Entity>> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Pair<Integer, Entity> integerEntityPair) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(integerEntityPair.getValue()
                        .getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}

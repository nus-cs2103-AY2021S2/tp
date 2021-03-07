package dog.pawbook.model.owner;

import java.util.List;
import java.util.function.Predicate;

import dog.pawbook.commons.util.StringUtil;

/**
 * Tests that a {@code Owner}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Owner> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Owner owner) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(owner.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}

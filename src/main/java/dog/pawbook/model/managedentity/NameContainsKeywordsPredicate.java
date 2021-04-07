package dog.pawbook.model.managedentity;

import java.util.List;
import java.util.function.Predicate;

import javafx.util.Pair;

/**
 * Tests that a {@code Owner}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Pair<Integer, Entity>> {
    private final List<String> keywords;

    /**
     * Constructs a NameContainsKeywordsPredicate instance.
     *
     * @param keywords list of keywords provided by user.
     */
    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Pair<Integer, Entity> integerEntityPair) {
        return keywords.stream()
                .map(keyword -> keyword.toLowerCase())
                .anyMatch(keyword -> integerEntityPair.getValue().getName().fullName.toLowerCase().contains(keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}

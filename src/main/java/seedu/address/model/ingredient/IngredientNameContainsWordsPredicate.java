package seedu.address.model.ingredient;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Predicate that checks if ingredient's name has specified keywords
 */
public class IngredientNameContainsWordsPredicate implements Predicate<Ingredient> {
    private final List<String> keywords;

    /**
     * Initialize predicate with list of keywords
     * @param keywords keywords to search for
     */
    public IngredientNameContainsWordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Ingredient ingredient) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(ingredient.getName(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IngredientNameContainsWordsPredicate // instanceof handles nulls
                && keywords.equals(((IngredientNameContainsWordsPredicate) other).keywords)); // state check
    }
}

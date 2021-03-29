package seedu.address.model.ingredient;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.PersonNameContainsWordsPredicate;

import java.util.List;
import java.util.function.Predicate;

public class IngredientNameContainsWordsPredicate implements Predicate<Ingredient> {
    private final List<String> keywords;

    public IngredientNameContainsWordsPredicate(List<String> keywords) { this.keywords = keywords; }

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

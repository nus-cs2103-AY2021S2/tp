package seedu.address.commons.util;

import java.util.function.Predicate;

import seedu.address.model.ingredient.Ingredient;

public class PredicateUtil {
    public static <T> Predicate<T> composePredicates(Predicate<T>[] predicates) {
        assert predicates.length > 0;

        Predicate<T> wrapperPredicate = x -> true;
        for (Predicate<T> pred : predicates) {
            wrapperPredicate = wrapperPredicate.and(pred);
        }
        return wrapperPredicate;
    }
}

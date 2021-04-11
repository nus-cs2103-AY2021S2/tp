package seedu.address.commons.util;

import java.util.List;
import java.util.function.Predicate;

public class PredicateUtil {
    /**
     * Method to compose predicates into one predicate.
     * @param predicates list of predicates to compose
     * @param <T> predicate item
     * @return composed predicate
     */
    public static <T> Predicate<T> composePredicates(List<Predicate<T>> predicates) {
        assert predicates != null && predicates.size() > 0;

        Predicate<T> wrapperPredicate = x -> true;
        for (Predicate<T> pred : predicates) {
            wrapperPredicate = wrapperPredicate.and(pred);
        }
        return wrapperPredicate;
    }
}

package seedu.address.model.property;

import java.util.List;
import java.util.function.Predicate;

/**
 * List of {@code Predicate<Property>}, supports equality comparisons
 * with other {@code PropertyPredicateLists}, and combining of all
 * predicates it holds into a single predicate.
 */
public class PropertyPredicateList {
    private List<Predicate<Property>> predicates;

    public PropertyPredicateList(List<Predicate<Property>> predicates) {
        this.predicates = predicates;
    }

    /**
     * Combines all predicates in its internal list into a single
     * predicate.
     * @return {@code Predicate<Property>} that is combined with and
     */
    public Predicate<Property> combine() {
        return this.predicates.stream().reduce(Predicate::and).orElse(x -> true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof PropertyPredicateList
                && this.predicates.size() == ((PropertyPredicateList) other).predicates.size()) {
            for (int i = 0; i < predicates.size(); i++) {
                if (this.predicates.get(i) != ((PropertyPredicateList) other).predicates.get(i)) {
                    return false;
                }
            }
            return true;
        }

        return false;

    }
}

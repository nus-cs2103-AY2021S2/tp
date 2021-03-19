package seedu.address.model.property;

import java.util.List;
import java.util.function.Predicate;

public class PropertyPredicateList {
    List<Predicate<Property>> predicates;

    public PropertyPredicateList(List<Predicate<Property>> predicates) {
        this.predicates = predicates;
    }

    public Predicate<Property> combine() {
        return this.predicates.stream().reduce(x -> true, Predicate::and);
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

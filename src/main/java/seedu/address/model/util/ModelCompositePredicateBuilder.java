package seedu.address.model.util;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Builder class for { @code ModelCompositePredicate }.
 * @param <U>
 */
public class ModelCompositePredicateBuilder<U> {
    public final List<ModelSinglePredicate<U>> modelPredicateList;

    public ModelCompositePredicateBuilder() {
        this.modelPredicateList = new ArrayList<>();
    }

    /**
     * Simple constructor to convert single predicates into a composite predicate.
     * @param modelSinglePredicate
     * @return
     */
    public ModelCompositePredicateBuilder<U> compose(ModelSinglePredicate<U> modelSinglePredicate) {
        requireNonNull(modelSinglePredicate);
        modelPredicateList.add(modelSinglePredicate);
        return this;
    }

    public ModelCompositePredicate<U> build() {
        return new ModelCompositePredicate<>(Collections.unmodifiableList(modelPredicateList));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ModelCompositePredicateBuilder // instanceof handles nulls
            && modelPredicateList.equals(((ModelCompositePredicateBuilder) other).modelPredicateList)); // state check
    }

}

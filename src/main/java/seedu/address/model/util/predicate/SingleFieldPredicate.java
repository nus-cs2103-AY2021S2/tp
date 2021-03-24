package seedu.address.model.util.predicate;

import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.ListUtil.compareListWithoutOrder;

import java.util.Collections;
import java.util.List;

/**
 * Class to hold single predicate conditions for fields.
 */
public abstract class SingleFieldPredicate<U> extends FieldPredicate<U> {

    private final List<String> keywords;

    /**
     * Creates new {@code SingleFieldPredicate} object by given keywords.
     */
    public SingleFieldPredicate(List<String> keywords) {
        requireNonNull(keywords);
        this.keywords = keywords;
    }

    public List<String> getKeywords() {
        return Collections.unmodifiableList(this.keywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof SingleFieldPredicate // instanceof handles nulls
            && compareListWithoutOrder(keywords, ((SingleFieldPredicate<?>) other).keywords)); // state check
    }

    // Use keywords as hash for object
    @Override
    public int hashCode() {
        return hash(keywords);
    }

}

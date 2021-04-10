package chim.model.util.predicate;

import static chim.commons.util.ListUtil.compareListWithoutOrder;
import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to hold single predicate conditions for fields.
 */
public abstract class SingleFieldPredicate<U> extends FieldPredicate<U> {

    protected final List<String> keywords;

    /**
     * Creates new {@code SingleFieldPredicate} object by given keywords.
     */
    public SingleFieldPredicate(List<String> keywords) {
        requireNonNull(keywords);
        assert isValidKeywords(keywords);
        this.keywords = keywords;
    }

    public List<String> getKeywords() {
        return Collections.unmodifiableList(this.keywords);
    }

    public static boolean isValidKeywords(List<String> keywords) {
        return !keywords.isEmpty();
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

    @Override
    public String toString() {
        return keywords.stream().collect(Collectors.joining(" or "));
    }
}

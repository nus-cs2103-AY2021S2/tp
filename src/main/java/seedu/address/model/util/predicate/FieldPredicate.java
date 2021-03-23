package seedu.address.model.util.predicate;

import java.util.Comparator;
import java.util.function.Predicate;

public abstract class FieldPredicate<U> implements Predicate<U>, Comparator<U> {

    private static final FieldPredicate<?> DEFAULT_PREDICATE = new FieldPredicate<>() {
        @Override
        public double getSimilarityScore(Object o) {
            return 0;
        }

        @Override
        public boolean test(Object o) {
            return true;
        }

        @Override
        public boolean useDefaultOrdering() {
            return true;
        }
    };

    private static final FieldPredicate<?> EMPTY_PREDICATE = new FieldPredicate<>() {
        @Override
        public double getSimilarityScore(Object o) {
            return 0;
        }

        @Override
        public boolean test(Object o) {
            return false;
        }
    };

    public static <U> FieldPredicate<U> getDefaultPredicate() {
        @SuppressWarnings("unchecked")
        FieldPredicate<U> u = (FieldPredicate<U>) DEFAULT_PREDICATE;
        return u;
    }

    public static <U> FieldPredicate<U> getEmptyPredicate() {
        @SuppressWarnings("unchecked")
        FieldPredicate<U> u = (FieldPredicate<U>) EMPTY_PREDICATE;
        return u;
    }

    public abstract double getSimilarityScore(U u);

    @Override
    public int compare(U u, U otherU) {
        double score1 = getSimilarityScore(u);
        double score2 = getSimilarityScore(otherU);

        if (score1 < score2) {
            return 1;
        } else if (score1 > score2) {
            return -1;
        }
        return 0;
    }

    /**
     * Use to set field predicate to use natural ordering
     * Needed as the sorted list's comparator has to be set to null
     *
     * {@Code getSimilarityScore} will not be used if this is true
     */
    public boolean useDefaultOrdering() {
        return false;
    }

}

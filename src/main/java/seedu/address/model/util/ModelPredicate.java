package seedu.address.model.util;

import java.util.Comparator;
import java.util.function.Predicate;

public abstract class ModelPredicate<U> implements Predicate<U>, Comparator<U> {

    public static <U> ModelPredicate<U> getDefaultPredicate() {
        return new ModelPredicate<U>() {
            @Override
            public double getSimilarityScore(U u) {
                return 0;
            }

            @Override
            public boolean test(U u) {
                return true;
            }
        };
    }

    public static <U> ModelPredicate<U> getEmptyPredicate() {
        return new ModelPredicate<U>() {
            @Override
            public double getSimilarityScore(U u) {
                return 0;
            }

            @Override
            public boolean test(U u) {
                return false;
            }
        };
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

}

package dog.pawbook.model.managedentity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javafx.util.Pair;

/**
 * Tests that a {@code Owner}'s {@code Name} matches any of the keywords given.
 */
public class RelatedEntityPredicate implements Predicate<Pair<Integer, Entity>> {

    private final List<Integer> relatedIds;

    public RelatedEntityPredicate(ArrayList<Integer> relatedIds) {
        this.relatedIds = relatedIds;
    }

    @Override
    public boolean test(Pair<Integer, Entity> integerEntityPair) {
        return relatedIds.stream()
                .anyMatch(x -> integerEntityPair.getKey().equals(x));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RelatedEntityPredicate // instanceof handles nulls
                && relatedIds.equals(((RelatedEntityPredicate) other).relatedIds)); // state check
    }

}

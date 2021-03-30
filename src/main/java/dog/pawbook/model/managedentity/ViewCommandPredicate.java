package dog.pawbook.model.managedentity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javafx.util.Pair;

/**
 * Tests that an Entity matches a supplied list of IDs.
 */
public class ViewCommandPredicate implements Predicate<Pair<Integer, Entity>> {

    private final List<Integer> relatedIds;

    public ViewCommandPredicate(ArrayList<Integer> relatedIds) {
        this.relatedIds = relatedIds;
    }

    @Override
    public boolean test(Pair<Integer, Entity> idEntityPair) {
        return relatedIds.stream().anyMatch(id -> idEntityPair.getKey().equals(id));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommandPredicate // instanceof handles nulls
                        && relatedIds.equals(((ViewCommandPredicate) other).relatedIds)); // state check
    }

}

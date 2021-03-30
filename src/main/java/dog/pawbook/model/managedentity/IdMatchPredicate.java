package dog.pawbook.model.managedentity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javafx.util.Pair;

/**
 * Tests that the ID of an Entity is within a supplied list of IDs.
 */
public class IdMatchPredicate implements Predicate<Pair<Integer, Entity>> {

    private final List<Integer> relatedIds = new ArrayList<>();

    /**
     * Construct a predicate from a list of IDs.
     */
    public IdMatchPredicate(ArrayList<Integer> idList) {
        this.relatedIds.addAll(idList);
    }

    /**
     * Construct a predicate for a single ID.
     */
    public IdMatchPredicate(Integer id) {
        this.relatedIds.add(id);
    }

    @Override
    public boolean test(Pair<Integer, Entity> idEntityPair) {
        return relatedIds.stream().anyMatch(id -> idEntityPair.getKey().equals(id));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IdMatchPredicate // instanceof handles nulls
                && relatedIds.equals(((IdMatchPredicate) other).relatedIds)); // state check
    }

}

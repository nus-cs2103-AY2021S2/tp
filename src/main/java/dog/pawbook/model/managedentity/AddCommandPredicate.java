package dog.pawbook.model.managedentity;

import java.util.function.Predicate;

import javafx.util.Pair;

/**
 * Tests that a {@code Owner}'s {@code Name} matches any of the keywords given.
 */
public class AddCommandPredicate implements Predicate<Pair<Integer, Entity>> {
    private final Entity toAdd;

    public AddCommandPredicate(Entity toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public boolean test(Pair<Integer, Entity> integerEntityPair) {
        return integerEntityPair.getValue().equals(toAdd);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommandPredicate // instanceof handles nulls
                && toAdd.equals(((AddCommandPredicate) other).toAdd)); // state check
    }

}

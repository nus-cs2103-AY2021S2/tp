package dog.pawbook.model.managedentity;

import java.util.function.Predicate;

import javafx.util.Pair;

/**
 * Tests that a {@code Owner}'s {@code Name} matches any of the keywords given.
 */
public class EditCommandPredicate implements Predicate<Pair<Integer, Entity>> {
    private final Integer toEdit;

    public EditCommandPredicate(Integer toEdit) {
        this.toEdit = toEdit;
    }

    @Override
    public boolean test(Pair<Integer, Entity> integerEntityPair) {
        return integerEntityPair.getKey().equals(toEdit);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditCommandPredicate // instanceof handles nulls
                && toEdit.equals(((EditCommandPredicate) other).toEdit)); // state check
    }

}

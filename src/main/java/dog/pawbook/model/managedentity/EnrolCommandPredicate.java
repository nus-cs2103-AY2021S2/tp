package dog.pawbook.model.managedentity;

import java.util.function.Predicate;

import javafx.util.Pair;

/**
 * Tests that a {@code Owner}'s {@code Name} matches any of the keywords given.
 */
public class EnrolCommandPredicate implements Predicate<Pair<Integer, Entity>> {

    private final Integer dogId;
    private final Integer programId;

    public EnrolCommandPredicate(Integer dogId, Integer programId ) {
        this.dogId = dogId;
        this.programId = programId;
    }

    @Override
    public boolean test(Pair<Integer, Entity> integerEntityPair) {
        return integerEntityPair.getKey().equals(dogId) ||
                integerEntityPair.getKey().equals(programId);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EnrolCommandPredicate // instanceof handles nulls
                && dogId.equals(((EnrolCommandPredicate) other).dogId)
                && programId.equals(((EnrolCommandPredicate) other).programId)); // state check
    }

}

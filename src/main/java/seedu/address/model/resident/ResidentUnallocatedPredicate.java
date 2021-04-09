package seedu.address.model.resident;

import java.util.function.Predicate;

/**
 * Tests that a {@code Resident}'s {@code Name} matches any of the keywords given.
 */
public class ResidentUnallocatedPredicate implements Predicate<Resident> {
    private final String unallocatedKeyword;

    public ResidentUnallocatedPredicate() {
        this.unallocatedKeyword = Room.UNALLOCATED_REGEX;
    }

    @Override
    public boolean test(Resident resident) {
        return unallocatedKeyword.equals(resident.getRoom().toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ResidentUnallocatedPredicate // instanceof handles nulls
                && unallocatedKeyword.equals(((ResidentUnallocatedPredicate) other).unallocatedKeyword)); // state check
    }

}

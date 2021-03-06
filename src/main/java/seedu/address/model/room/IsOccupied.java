package seedu.address.model.room;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class IsOccupied {
    public static final String MESSAGE_CONSTRAINTS =
            "Room occupation status can only be y or n in lowercase alphabets";

    public static final String VALIDATION_REGEX = "y|n";

    public final boolean isOccupied;

    /**
     * Constructs a {@code IsOccupied}.
     *
     * @param occupiedStatus A valid occupation status.
     */
    public IsOccupied(String occupiedStatus) {
        requireNonNull(occupiedStatus);
        checkArgument(isValidOccupationStatus(occupiedStatus), MESSAGE_CONSTRAINTS);
        this.isOccupied = occupiedStatus.equals("y") ? true : false;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidOccupationStatus(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return isOccupied ? "Yes" : "No";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsOccupied // instanceof handles nulls
                && isOccupied == ((IsOccupied) other).isOccupied); // state check
    }
}

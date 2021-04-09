package seedu.address.model.room;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class IsOccupied {
    public static final String MESSAGE_CONSTRAINTS =
            "Room occupancy status can only be y or n in lowercase alphabets";

    public static final String VALIDATION_REGEX = "y|n";
    public static final String OCCUPIED = "y";
    public static final String UNOCCUPIED = "n";
    public final boolean isOccupied;

    /**
     * Constructs a {@code IsOccupied}.
     *
     * @param occupancyStatus A valid occupancy status.
     */
    public IsOccupied(String occupancyStatus) {
        occupancyStatus = occupancyStatus.toLowerCase();
        requireNonNull(occupancyStatus);
        checkArgument(isValidOccupancyStatus(occupancyStatus), MESSAGE_CONSTRAINTS);
        this.isOccupied = occupancyStatus.equals("y") ? true : false;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidOccupancyStatus(String test) {
        test = test.toLowerCase();
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return isOccupied ? "Y" : "N";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsOccupied // instanceof handles nulls
                && isOccupied == ((IsOccupied) other).isOccupied); // state check
    }
}

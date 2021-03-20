package seedu.address.model.room;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.util.EnumUtil;


/**
 * Represents a RoomType in the hostel management system.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class RoomType {
    public static final String MESSAGE_CONSTRAINTS =
            "The valid options for Room Type are: " + String.join(", ", EnumUtil.getNames(RoomTypeOptions.class));

    public final RoomTypeOptions value;

    /**
     * Constructs a {@code RoomNumber}.
     *
     * @param value A valid room type.
     */
    public RoomType(String value) {
        value = value.toUpperCase();
        requireNonNull(value);
        checkArgument(isValidRoomType(value), MESSAGE_CONSTRAINTS);
        this.value = RoomTypeOptions.valueOf(value);
    }

    /**
     * Returns true if a given string is a valid name. Tests are case insensitive.
     */
    public static boolean isValidRoomType(String test) {
        test = test.toUpperCase();
        try {
            RoomTypeOptions.valueOf(test);
            return true;
        } catch (IllegalArgumentException ex) {
            // Unable to parse
            return false;
        }
    }


    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoomType // instanceof handles nulls
                && value.equals(((RoomType) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

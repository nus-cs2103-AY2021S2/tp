package seedu.address.model.garment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Garment's last used date in the wardrobe.
 * Guarantees: is valid as declared in {@link #isValidLastUse(String)}
 */
public class LastUse {

    public static final String MESSAGE_CONSTRAINTS = "LastUse must take in a valid date: YYYY-MM-DD";

    public static final String VALIDATION_REGEX = "\\d{4}-[01]\\d-[0-3]\\d";

    public LocalDate value;

    /**
     * Constructs an {@code lastuse}.
     *
     * @param lastusestr A valid string LastUse.
     */
    public LastUse(String lastusestr) {
        requireNonNull(lastusestr);
        checkArgument(isValidLastUse(lastusestr), MESSAGE_CONSTRAINTS);
        String[] date = lastusestr.split("-");
        value = LocalDate.of(Integer.parseInt(date[0]),
                Integer.parseInt(date[1]),
                Integer.parseInt(date[2]));
    }

    /**
     * Constructs an {@code lastuse}.
     *
     * @param otherDate A valid Date object.
     */
    public LastUse(LocalDate otherDate){
        int year = otherDate.getYear();
        int month = otherDate.getMonthValue();
        int day = otherDate.getDayOfMonth();
        String lastusestr = year + "-" + String.format("%02d", month) +  "-"
                + String.format("%02d", day);
        requireNonNull(lastusestr);
        checkArgument(isValidLastUse(lastusestr), MESSAGE_CONSTRAINTS);
        String[] date = lastusestr.split("-");
        value = LocalDate.of(Integer.parseInt(date[0]),
                Integer.parseInt(date[1]),
                Integer.parseInt(date[2]));
    }

    /**
     * Returns true if a given LastUse input is a valid Date Object.
     */
    public static boolean isValidLastUse(String test) {
        if(test.matches(VALIDATION_REGEX) == false) {
            return false;
        } else {
            try {
                String[] date = test.split("-");
                LocalDate dateTest = LocalDate.of(Integer.parseInt(date[0]),
                        Integer.parseInt(date[1]),
                        Integer.parseInt(date[2]));
                return true;
            } catch(DateTimeException e) {
                return false;
            }
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Type // instanceof handles nulls
                && value.equals(((Type) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

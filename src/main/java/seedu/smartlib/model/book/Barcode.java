package seedu.smartlib.model.book;

import static seedu.smartlib.commons.util.AppUtil.checkArgument;

/**
 * Represents an Barcode of a book in SmartLib.
 * Guarantees: immutable; is valid as declared in {@link #isValidBarcode(int)}.
 */
public class Barcode {

    public static final int MIN_VALUE = 1000000000;
    public static final int MAX_VALUE = 2000000000;
    public static final int TEMP_BARCODE_VALUE = 0;

    public static final String MESSAGE_CONSTRAINTS =
            "Barcodes should only contain numbers, and it should only contain 10 digits.";

    private final int value;

    /**
     * Constructs a {@code barcode}.
     *
     * @param barcode A valid barcode.
     */
    public Barcode(int barcode) {
        assert(isValidBarcode(barcode));
        checkArgument(isValidBarcode(barcode), MESSAGE_CONSTRAINTS);
        value = barcode;
    }

    /**
     * Returns the value of the barcode.
     *
     * @return The value of the barcode.
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns true if a given int is a valid barcode.
     *
     * @param barcode integer to be tested.
     * @return true if a given int is a valid barcode.
     */
    public static boolean isValidBarcode(int barcode) {
        return (barcode >= Barcode.MIN_VALUE && barcode <= Barcode.MAX_VALUE)
                || barcode == TEMP_BARCODE_VALUE;
    }

    /**
     * Returns this Barcode in String format.
     *
     * @return this Barcode in String format.
     */
    @Override
    public String toString() {
        return value + "";
    }

    /**
     * Checks if this Barcode is equal to another Barcode.
     *
     * @param other the other Barcode to be compared.
     * @return true if this Barcode is equal to the other Barcode, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Barcode // instanceof handles nulls
                && value == ((Barcode) other).getValue()); // state check
    }

    /**
     * Generates a hashcode for this Barcode.
     *
     * @return the hashcode for this Barcode.
     */
    @Override
    public int hashCode() {
        return toString().hashCode();
    }

}

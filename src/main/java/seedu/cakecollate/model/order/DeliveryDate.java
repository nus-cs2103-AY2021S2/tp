package seedu.cakecollate.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.cakecollate.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;


/**
 * Represents an Order's delivery date in CakeCollate.
 * Guarantees: immutable; is valid as declared in {@link #isValidFormat(String)}
 */
public class DeliveryDate implements Comparable<DeliveryDate> {

    public static final String MESSAGE_EMPTY = "Delivery date cannot be blank.";
    public static final String MESSAGE_CONSTRAINTS_FORMAT =
            "Delivery date should be of the format dd/mm/yyyy, dd-mm-yyyy, "
            + "dd.mm.yyyy or dd MMM yyyy and adhere to the following constraints:\n"
            + "1. The delivery date should have a valid day, month and year.\n"
            + "For example:\n"
            + "01/01/2022, 01-01-2022, 01.01.2022, 01 Jan 2022";
    public static final String MESSAGE_CONSTRAINTS_VALUE =
            "Delivery date should be today's date or a future date.\n"
            + "Today's date is: " + DateTimeFormatter.ofPattern("dd MMM yyyy").format(LocalDate.now()) + "\n"
            + "You have entered: %1$s";
    private static final DateTimeFormatter ACCEPTABLE_FORMATS =
            DateTimeFormatter.ofPattern("[dd/MM/uuuu][dd-MM-uuuu][dd.MM.uuuu][dd MMM uuuu]");

    public final LocalDate value;

    /**
     * Constructs a {@code DeliveryDate}.
     *
     * @param deliveryDate A valid delivery date.
     */
    public DeliveryDate(String deliveryDate) {
        requireNonNull(deliveryDate);
        checkArgument(isValidFormat(deliveryDate), MESSAGE_CONSTRAINTS_FORMAT);
        value = LocalDate.parse(deliveryDate, ACCEPTABLE_FORMATS);
    }

    /**
     * Returns true if a given {@code String} is a valid date X working days after current date.
     * X should be positive.
     * This method should only be called after validating {@code test} using {@link DeliveryDate#isValidFormat(String)}.
     */
    public static boolean isXDaysLater(String test, long x) {
        assert x >= 0;
        LocalDate toTest = LocalDate.parse(test, ACCEPTABLE_FORMATS);
        LocalDate dateToday = LocalDate.now();
        LocalDate acceptableDate = dateToday.plusDays(x);
        return toTest.isAfter(acceptableDate) || toTest.isEqual(acceptableDate);
    }

    /**
     * Returns true if a given {@code String} is a valid delivery date format.
     */
    public static boolean isValidFormat(String test) {
        // Test if date is valid using LocalDate.parse()
        try {
            LocalDate.parse(test, ACCEPTABLE_FORMATS.withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public LocalDate getValue() {
        return this.value;
    }

    /**
     * Return a {@code String} of possible formats of {@code DeliveryDate} concatenated. This {@code String} is
     * used to test against keywords from the {@code FindCommand}.
     */
    public String getTestString() {
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter format3 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter format4 = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return value.getMonth().toString() + " "
                + format1.format(value) + " "
                + format2.format(value) + " "
                + format3.format(value) + " "
                + format4.format(value) + " ";
    }

    @Override
    public String toString() {
        DateTimeFormatter toDisplay = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return toDisplay.format(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeliveryDate
                && value.equals(((DeliveryDate) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Uses the compareTo already defined by the LocalDate class to compare delivery dates.
     * This is needed for sorting order lists according to delivery dates.
     */
    @Override
    public int compareTo(DeliveryDate o) {
        return this.value.compareTo(o.value);
    }
}

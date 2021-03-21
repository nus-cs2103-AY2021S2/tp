package seedu.partyplanet.model.date;

import static java.util.Objects.requireNonNull;
import static seedu.partyplanet.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;


/** Represents a Date in PartyPlanet.
 * Guarantees: immutable; is always valid.
 */
public class Date implements Comparable<Date> {
    public static final String MESSAGE_CONSTRAINTS = "    - yyyy-mm-dd (ISO format)\n"
            + "    - dd.mm.yyyy\n"
            + "    - dd/mm/yyyy\n"
            + "    - dd mmm yyyy\n"
            + "    - mmm dd yyyy";

    public static final String MESSAGE_DATE_CONSTRAINTS = "Birthday should not be a date in the future";
    public static final String EMPTY_DATE_STRING = "";

    protected static final DateTimeFormatter[] VALID_FORMATS = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("d.M.yyyy"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("d MMM yyyy"),
            DateTimeFormatter.ofPattern("d MMMM yyyy"),
            DateTimeFormatter.ofPattern("MMM d yyyy"),
            DateTimeFormatter.ofPattern("MMMM d yyyy"),
    };
    protected static final DateTimeFormatter[] VALID_FORMATS_WITHOUT_YEAR = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("--MM-dd"),
            DateTimeFormatter.ofPattern("d/M"),
            DateTimeFormatter.ofPattern("d MMM"),
            DateTimeFormatter.ofPattern("d MMMM"),
            DateTimeFormatter.ofPattern("MMM d"),
            DateTimeFormatter.ofPattern("MMMM d"),
    };
    protected static final DateTimeFormatter ISO_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected static final DateTimeFormatter ISO_FORMAT_WITHOUT_YEAR = DateTimeFormatter.ofPattern("--MM-dd");
    protected static final DateTimeFormatter READABLE_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy");
    protected static final DateTimeFormatter READABLE_FORMAT_WITHOUT_YEAR = DateTimeFormatter.ofPattern("d MMM");

    /** In "dd mmm yyyy" formatted string for human-readable display */
    public final String displayValue;
    /** In ISO-8601 format for easy comparison */
    public final String value;
    protected int month; // implemented for quick retrieval
    protected boolean hasYear;
    protected boolean isEmpty = false;

    /**
     * Constructs a {@code Date}.
     * Date  can optionally contain a year.
     * Some invalid dates are mapped to the nearest valid date, e.g. 29 Feb 2021 -> 28 Feb 2021.
     *
     * @param inputDate A valid date.
     */
    public Date(String inputDate) {
        requireNonNull(inputDate);
        TemporalAccessor date = parseDate(toTitleCase(inputDate));
        hasYear = date instanceof LocalDate;
        if (hasYear) {
            value = ISO_FORMAT.format(date);
            displayValue = READABLE_FORMAT.format(date);
            month = ((LocalDate) date).getMonthValue();
        } else {
            value = ISO_FORMAT_WITHOUT_YEAR.format(date);
            displayValue = READABLE_FORMAT_WITHOUT_YEAR.format(date);
            month = ((MonthDay) date).getMonthValue();
        }
        checkArgument(isValidDate(value), MESSAGE_CONSTRAINTS);
    }

    /**
     * Constructs an empty date.
     */
    public Date() {
        value = EMPTY_DATE_STRING;
        displayValue = EMPTY_DATE_STRING;
        isEmpty = true;
    }

    /**
     * Attempts to match {@code date} to any parsing rule.
     * If valid, a LocalDate or MonthDay is returned depending on whether a year exists,
     * otherwise a DateTimeException will be thrown.
     */
    public static TemporalAccessor parseDate(String date) throws DateTimeException {
        for (DateTimeFormatter dateFormat: VALID_FORMATS) {
            try {
                return LocalDate.parse(date, dateFormat);
            } catch (DateTimeException e) {
                continue;
            }
        }
        for (DateTimeFormatter dateFormat: VALID_FORMATS_WITHOUT_YEAR) {
            try {
                return MonthDay.parse(date, dateFormat);
            } catch (DateTimeException e) {
                continue;
            }
        }
        throw new DateTimeException(MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns title case for strings.
     * Required for user inputs in arbitrary case, which DateTimeFormatter does not support parsing for.
     */
    private static String toTitleCase(String date) {
        StringBuilder titleCase = new StringBuilder(date.length());
        boolean nextCapitalize = true;
        for (char c: date.toCharArray()) {
            if (!Character.isLetter(c)) {
                nextCapitalize = true;
            } else if (nextCapitalize) {
                c = Character.toUpperCase(c);
                nextCapitalize = false;
            } else {
                c = Character.toLowerCase(c);
            }
            titleCase.append(c);
        }
        return titleCase.toString();
    }

    /**
     * Returns true if a given Date is an empty date.
     */
    public static boolean isEmptyDate(Date date) {
        return date.isEmpty;
    }

    /**
     * Returns true if a given date string is a valid date not in the future.
     */
    public static boolean isFuture(String test) {
        assert isValidDate(test);
        return isFuture(test, LocalDate.now());
    }

    /**
     * Returns true if a given date string is a valid date not after {@code reference}.
     * Exposes {@reference} date as a parameter for unit testing.
     * Note: Dates without years which are parsed successfully are always considered valid.
     */
    public static boolean isFuture(String test, LocalDate reference) {
        assert isValidDate(test);
        String referenceDate = ISO_FORMAT.format(reference);
        TemporalAccessor date = parseDate(test);
        String testDate = (date instanceof LocalDate ? ISO_FORMAT : ISO_FORMAT_WITHOUT_YEAR).format(date);
        return testDate.compareTo(referenceDate) > 0;
    }

    /**
     * Returns true if a given string is a valid date, otherwise false.
     * Implemented for unit testing.
     */
    public static boolean isValidDate(String test) {
        try {
            parseDate(test);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    /**
     * Returns the month value of the date, in the range [1-12].
     * Required for feature to filter contact dates by month.
     */
    public int getMonth() {
        if (isEmpty) {
            throw new IllegalArgumentException("Date is empty");
        }
        return month;
    }

    @Override
    public int compareTo(Date other) {
        return getMonthDayString().compareTo(other.getMonthDayString());
    }

    /**
     * Returns month and day as "mm-dd" in ISO format.
     * For easier comparison between Birthday objects.
     *
     * Note: Can consider refactoring this to rely on (month,day) integer pairs instead.
     */
    private String getMonthDayString() {
        if (isEmpty) {
            throw new IllegalArgumentException("Date is empty");
        }
        return hasYear ? value.substring(5) : value.substring(2);
    }

    @Override
    public String toString() {
        return displayValue;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Date)) {
            return false;
        }
        if (isEmpty == isEmptyDate((Date) other)) {
            return true;
        }
        return value.equals(((Date) other).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

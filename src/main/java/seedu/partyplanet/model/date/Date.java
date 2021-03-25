package seedu.partyplanet.model.date;

import static java.util.Objects.requireNonNull;
import static seedu.partyplanet.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


/** Represents a Date in PartyPlanet.
 * Guarantees: immutable; is always valid.
 */
public class Date implements Comparable<Date> {

    public static final String MESSAGE_YEAR_FORMATS = "    - yyyy-mm-dd (ISO format)\n"
            + "    - dd.mm.yyyy\n"
            + "    - dd/mm/yyyy\n"
            + "    - dd mmm yyyy\n"
            + "    - mmm dd yyyy";
    public static final String MESSAGE_NOYEAR_FORMATS = "    - --mm-dd (ISO format)\n"
            + "    - dd/mm\n"
            + "    - dd mmm\n"
            + "    - mmm dd";
    public static final String MESSAGE_CONSTRAINTS = "Dates should be in one of the following formats:\n"
            + MESSAGE_YEAR_FORMATS + "\n" + MESSAGE_NOYEAR_FORMATS;
    public static final String EMPTY_DATE_STRING = "";

    protected static final int MIN_YEAR = 0;
    protected static final int NON_YEAR = -1;

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
    protected static final DateTimeFormatter READABLE_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy");
    protected static final DateTimeFormatter READABLE_FORMAT_WITHOUT_YEAR = DateTimeFormatter.ofPattern("d MMM");

    /** In "dd mmm yyyy" formatted string for human-readable display */
    public final String value;
    protected LocalDate dateValue;
    protected boolean isEmpty = false;

    /**
     * Constructs an empty date.
     */
    public Date() {
        value = EMPTY_DATE_STRING;
        isEmpty = true;
    }

    /**
     * Constructs a {@code Date}.
     * Date can optionally contain a year.
     * Some invalid dates are mapped to the nearest valid date, e.g. 29 Feb 2021 -> 28 Feb 2021.
     *
     * @param inputDate A valid date string.
     */
    public Date(String inputDate) {
        requireNonNull(inputDate);
        checkArgument(isValidDate(inputDate), MESSAGE_CONSTRAINTS);
        dateValue = parseDate(inputDate);
        if (dateValue.getYear() == NON_YEAR) {
            value = READABLE_FORMAT_WITHOUT_YEAR.format(dateValue);
        } else {
            value = READABLE_FORMAT.format(dateValue);
        }
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
     * Attempts to match {@code date} to any parsing rule.
     * If valid, a LocalDate is returned, otherwise a DateTimeException will be thrown.
     */
    public static LocalDate parseDate(String date) throws DateTimeException {
        date = toTitleCase(date);
        for (DateTimeFormatter dateFormat: VALID_FORMATS) {
            try {
                LocalDate parsedDate = LocalDate.parse(date, dateFormat);
                if (parsedDate.getYear() < MIN_YEAR) {
                    continue;
                }
                return parsedDate;
            } catch (DateTimeException e) {
                continue;
            }
        }
        for (DateTimeFormatter dateFormat: VALID_FORMATS_WITHOUT_YEAR) {
            try {
                return MonthDay.parse(date, dateFormat).atYear(NON_YEAR);
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
     * Returns date with year removed for pure monthDay comparison.
     */
    public static Date getDateWithoutYear(Date date) {
        if (isEmptyDate(date)) {
            return new Date();
        }
        return new Date(READABLE_FORMAT_WITHOUT_YEAR.format(date.dateValue));
    }

    /**
     * Returns true if a given Date is an empty date.
     */
    public static boolean isEmptyDate(Date date) {
        return date.isEmpty;
    }

    /**
     * Returns the number of days left till the date, relative to current date.
     */
    public long getDaysLeft() {
        return getDaysLeft(false);
    }

    /**
     * Returns the number of days left till the date, relative to current date.
     * If {@code ignoreYear} is set to 'true', the number of days to the next month and day
     * is returned, i.e. the date is treated as a yearly event.
     */
    public long getDaysLeft(boolean ignoreYear) {
        return getDaysLeft(ignoreYear, LocalDate.now());
    }

    /**
     * Returns the number of days left till the date, relative to {@code reference}.
     * If {@code ignoreYear} is set to 'true', the number of days to the next month and day
     * is returned, i.e. the date is treated as a yearly event.
     * If date is empty, the maximum possible days remaining value is returned (LONG_MAX).
     */
    public long getDaysLeft(boolean ignoreYear, LocalDate reference) {
        if (isEmpty) {
            return Long.MAX_VALUE;
        }
        if (!ignoreYear) {
            return ChronoUnit.DAYS.between(reference, dateValue);
        }
        LocalDate upcomingDate = dateValue.withYear(reference.getYear());
        if (reference.isAfter(upcomingDate)) {
            upcomingDate = dateValue.withYear(reference.getYear() + 1);
        }
        return ChronoUnit.DAYS.between(reference, upcomingDate);
    }

    /**
     * Returns the month value of the date, in the range [1-12] if the month exists,
     * otherwise 0 is returned if no month is available.
     * Required for feature to filter contact dates by month.
     */
    public int getMonth() {
        if (isEmpty) {
            return 0;
        }
        return dateValue.getMonthValue();
    }

    /**
     * Returns true if date contains a year, false otherwise.
     */
    public boolean hasYear() {
        if (isEmpty) {
            return false;
        }
        return dateValue.getYear() != NON_YEAR;
    }

    /**
     * Returns 0 if equal, otherwise positive (negative) integer if date is earlier (later).
     * Empty dates are always treated as later dates.
     */
    @Override
    public int compareTo(Date other) {
        if (isEmpty && isEmptyDate(other)) {
            return 0;
        }
        if (isEmpty) {
            return 1;
        }
        if (isEmptyDate(other)) {
            return -1;
        }
        return dateValue.compareTo(other.dateValue);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Date)) {
            return false;
        }
        if (isEmpty && isEmptyDate((Date) other)) {
            return true;
        }
        return value.equals(((Date) other).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

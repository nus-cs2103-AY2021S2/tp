package seedu.address.model.util;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Specify the date and time formats used as input and output.
 */
public class DateTimeFormat {
    /**
     * User input date format, e.g. 23-01-2021.
     */
    public static final DateTimeFormatter INPUT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);

    /**
     * User input time format in 24-hour clock, e.g. 1933.
     */
    public static final DateTimeFormatter INPUT_TIME_FORMAT =
            DateTimeFormatter.ofPattern("HHmm").withResolverStyle(ResolverStyle.STRICT);

    /**
     * Output date format, e.g. Jan 23, 2021.
     */
    public static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd, uuuu");

    /**
     * Output time format, e.g. 7:33PM.
     */
    public static final DateTimeFormatter OUTPUT_TIME_FORMAT = DateTimeFormatter.ofPattern("h:mma");
}

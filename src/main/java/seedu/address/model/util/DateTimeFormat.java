package seedu.address.model.util;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Specify the date and time formats used as input and output.
 */
public class DateTimeFormat {
    /**
     * User input date format, e.g. 23-1-2021.
     */
    public static final DateTimeFormatter INPUT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("d-M-u").withResolverStyle(ResolverStyle.STRICT);

    /**
     * User input time format in 24-hour clock, e.g. 1800.
     */
    public static final DateTimeFormatter INPUT_TIME_FORMAT = DateTimeFormatter.ofPattern("HHmm");

    /**
     * Output date format, e.g. Jan 23, 2021.
     */
    public static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d, u");

    /**
     * Output time format, e.g. 7:33PM.
     */
    public static final DateTimeFormatter OUTPUT_TIME_FORMAT = DateTimeFormatter.ofPattern("h:mma");
}

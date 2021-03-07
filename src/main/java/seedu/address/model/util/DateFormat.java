package seedu.address.model.util;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Specify the date formats used as input and output.
 */
public class DateFormat {
    /**
     * User input date format, e.g. 23-1-2021.
     */
    public static final DateTimeFormatter INPUT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("d-M-u").withResolverStyle(ResolverStyle.STRICT);

    /**
     * Output date format, e.g. Jan 23 2021.
     */
    public static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d u");
}

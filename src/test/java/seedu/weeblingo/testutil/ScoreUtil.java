package seedu.weeblingo.testutil;

/**
 * A utility class containing a list of {@code Score} objects to be used in tests.
 */
public class ScoreUtil {

    public static final String DATETIME_VALID_1 = "10-04-2021 23:30:06"; // normal
    public static final String DATETIME_VALID_2 = "29-02-2021 23:30:06"; // special case
    public static final String DATETIME_VALID_3 = "01-01-2021 23:30:06"; // first day
    public static final String DATETIME_VALID_4 = "31-12-2021 23:30:06"; // last day
    public static final String DATETIME_VALID_5 = "10-04-2021 00:00:00"; // start of the day
    public static final String DATETIME_VALID_6 = "10-04-2021 23:59:59"; // end of the day

    public static final String DATETIME_INVALID_1 = "sahdasdywe"; // not in datetime format
    public static final String DATETIME_INVALID_2 = "2020-01-01 00:00:00"; // not in specified date format
    public static final String DATETIME_INVALID_3 = "32-12-2021 23:30:06"; // exceed the maximum day
    public static final String DATETIME_INVALID_4 = "31-12-2021"; // only the date
    public static final String DATETIME_INVALID_5 = "23:30:06"; // only the time
    public static final String DATETIME_INVALID_6 = "28-02-2021 23:69:00"; // exceed the maximum minute value

    public static final String[] DATA_VALID_1 = new String[]{"1", "1"};
    public static final String[] DATA_VALID_2 = new String[]{"1000", "1000"};
    public static final String[] DATA_VALID_3 = new String[]{"10", "5"};
    public static final String[] DATA_VALID_4 = new String[]{"10", "0"};

    public static final String[] DATA_INVALID_1 = new String[]{"0", "1"};
    public static final String[] DATA_INVALID_2 = new String[]{"-1", "1"};
    public static final String[] DATA_INVALID_3 = new String[]{"1", "-1"};
    public static final String[] DATA_INVALID_4 = new String[]{"0", "1"};
    public static final String[] DATA_INVALID_5 = new String[]{"(*&*(&", "1"};
    public static final String[] DATA_INVALID_6 = new String[]{"1.1", "1"};

    public static final String TIME_VALID = "00:03:30";

    public static final String TIME_INVALID_1 = "$%^&*()";
    public static final String TIME_INVALID_2 = "";
    public static final String TIME_INVALID_3 = "0:03:30";
    public static final String TIME_INVALID_4 = "00:03:30-";
    public static final String TIME_INVALID_5 = "000:03:30";
    public static final String TIME_INVALID_6 = "61:03:30";

    public static final String RATIO_VALID = "50.000%";
}

package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's Recurring Schedule in the planner.
 * Guarantees: immutable; is valid as declared in {@link #isValidRecurringSchedule(String)}
 */
public class RecurringSchedule {

    public static final String DATE_REGEX = "\\[\\d{2}\\s[a-zA-Z]{3}\\s\\d{4}\\]"; // example format : [23 Oct 2019]
    public static final String DAYSOFWEEK_REGEX = "\\[[a-zA-Z]{3}\\]"; // example format : [Mon]

    // regular expression for weekly recurring schedule
    public static final String WEEKLY_REGEX = DATE_REGEX + DAYSOFWEEK_REGEX + "\\[[a-zA-Z]{6}\\]";
    // regular expression for biweekly recurring schedule
    public static final String BIWEEKLY_REGEX = DATE_REGEX + DAYSOFWEEK_REGEX + "\\[[a-zA-Z]{8}\\]";

    public static final String MESSAGE_CONSTRAINTS = "Recurring Schedule should consists of end date,"
            + " days of week which are alphabet letters that are case-insensitive represented in these options "
            + "(mon, tue, wed, thu, fri, sat, sun)"
            + " and frequency of week that are also case-insensitive represented in these options (weekly, biweekly) "
            + "in the following example format: [23 Oct 2019][Mon][weekly]";

    public final String value;

    /**
     * Constructs an {@code RecurringSchedule}.
     *
     * @param recurringSchedule A valid recurring schedule text.
     */
    public RecurringSchedule(String recurringSchedule) {
        requireNonNull(recurringSchedule);
        checkArgument(isValidRecurringSchedule(recurringSchedule), MESSAGE_CONSTRAINTS);
        value = recurringSchedule;
    }

    /**
     * Returns if a given string is a valid recurring schedule text.
     */
    public static boolean isValidRecurringSchedule(String test) {
        boolean isBiWeekly = test.matches(BIWEEKLY_REGEX);
        boolean isWeekly = test.matches(WEEKLY_REGEX);
        return isBiWeekly || isWeekly;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecurringSchedule // instanceof handles nulls
                && value.equals(((RecurringSchedule) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

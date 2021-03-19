package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's Recurring Schedule in the planner.
 * Guarantees: immutable; is valid and can be empty as declared in {@link #isValidRecurringScheduleInput(String)}
 */
public class RecurringSchedule {

    public static final String DATE_REGEX = "\\[\\d{2}\\s[a-zA-Z]{3}\\s\\d{4}\\]"; // example format: [23 Oct 2019]
    public static final String DAYSOFWEEK_REGEX = "\\[[a-zA-Z]{3}\\]"; // example format: [Mon]

    // regular expression for weekly recurring schedule
    public static final String WEEKLY_REGEX = DATE_REGEX + DAYSOFWEEK_REGEX + "\\[[a-zA-Z]{6}\\]";
    // regular expression for biweekly recurring schedule
    public static final String BIWEEKLY_REGEX = DATE_REGEX + DAYSOFWEEK_REGEX + "\\[[a-zA-Z]{8}\\]";

    public static final String MESSAGE_CONSTRAINTS = "Recurring Schedule should consists of:"
            + "\n\n1) Starting Date"
            + "\n\n2) Days of week which are alphabet letters that are case-insensitive represented in these options\n "
            + "=====> (mon, tue, wed, thu, fri, sat, sun)"
            + "\n\n3) Frequency of week that are also case-insensitive represented in these options\n"
            + "=====> (weekly, biweekly) "
            + "\n\n and without blank space arguments but can be empty if nothing is entered :-)"
            + "\n\nHere is an example: [23 Oct 2019][Mon][weekly]";

    public final String value;

    /**
     * Recurring Schedule constructor
     *
     * @param recurringSchedule A valid recurring schedule text
     */
    public RecurringSchedule(String recurringSchedule) {
        requireNonNull(recurringSchedule);
        checkArgument(isValidRecurringScheduleInput(recurringSchedule), MESSAGE_CONSTRAINTS);
        value = recurringSchedule;
    }

    /**
     * Returns if a given string is a valid recurring schedule text
     * Accepts empty input recurring schedule since it is optional for a task to have recurring schedule
     *
     * @param test Input string of recurring schedule
     * @return State of whether recurring schedule is valid and non-empty in boolean format
     */
    public static boolean isValidRecurringScheduleInput(String test) {
        assert test != null;
        boolean isBiWeekly = test.matches(BIWEEKLY_REGEX);
        boolean isWeekly = test.matches(WEEKLY_REGEX);
        boolean isValidRecurringSchedule = isBiWeekly || isWeekly;

        return isValidRecurringSchedule || isEmptyRecurringScheduleInput(test);
    }

    /**
     * Used to check whether recurring schedule is empty
     *
     * @return State of whether recurring schedule is empty in boolean format
     */
    public static boolean isEmptyRecurringScheduleInput(String test) {
        return test.equals("");
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

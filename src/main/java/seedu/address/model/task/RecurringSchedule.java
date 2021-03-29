package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Represents a Task's Recurring Schedule in the planner.
 * Guarantees: immutable; is valid and can be empty as declared in {@link #isValidRecurringScheduleInput(String)}
 */
public class RecurringSchedule implements RecurringDates {
    public static final String FIELD_NAME = "RecurringSchedule";

    // example format: [23/10/2021]
    public static final String DATE_REGEX = "\\[(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}]";
    public static final String DAYSOFWEEK_REGEX = "\\[(mon|tue|wed|thu|fri|sat|sun)]";
    public static final String WEEKFREQUENCY_REGEX = "\\[(weekly|biweekly)]";

    public static final String VALIDATION_REGEX = DATE_REGEX + DAYSOFWEEK_REGEX + WEEKFREQUENCY_REGEX;

    public static final String MESSAGE_CONSTRAINTS = "Recurring Schedule conditions should consists of:"
            + "\n\n1) Ending Date should be ahead of current date"
            + "\n\n2) Days of week which are alphabet letters that are case-insensitive represented in these options\n "
            + "=====> (mon, tue, wed, thu, fri, sat, sun)"
            + "\n\n3) Frequency of week that are also case-insensitive represented in these options\n"
            + "=====> (weekly, biweekly) "
            + "\n\n and without blank space arguments but can be empty if nothing is entered :-)"
            + "\n\nHere is an example: [23/10/2021][Mon][weekly]";

    public static final String INVALID_ENDDATE = "End date should be ahead of current date "
            + "or the input end is less than a week without matching days found !!!";

    public final String value;
    public final String output;

    private boolean isEmptyRecurringSchedule;
    private List<String> weekDates;

    /**
     * Recurring Schedule constructor
     *
     * @param recurringSchedule A valid recurring schedule text
     */
    public RecurringSchedule(String recurringSchedule) {
        requireNonNull(recurringSchedule);
        checkArgument(isValidRecurringScheduleInput(recurringSchedule), MESSAGE_CONSTRAINTS);

        isEmptyRecurringSchedule = isEmptyRecurringScheduleInput(recurringSchedule);
        weekDates = new ArrayList<>();
        value = recurringSchedule;
        output = isEmptyRecurringSchedule ? "" : generateRecurringDates(recurringSchedule);
    }

    /**
     * Returns if a given string is a valid recurring schedule text
     * Accepts empty input recurring schedule since it is optional for a task to have recurring schedule
     *
     * @param test Input string of recurring schedule
     * @return State of whether recurring schedule is valid and non-empty in boolean format
     */
    public static boolean isValidRecurringScheduleInput(String test) {
        assert test != null : "Input recurring schedule is null !!!";

        Pattern pattern = Pattern.compile(VALIDATION_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(test);
        boolean isValidRecurringSchedule = matcher.matches();

        return isValidRecurringSchedule || isEmptyRecurringScheduleInput(test);
    }

    /**
     * Used to check whether recurring schedule is empty
     *
     * @param test Input string of recurring schedule
     * @return State of whether recurring schedule is empty in boolean format
     */
    public static boolean isEmptyRecurringScheduleInput(String test) {
        return test.equals("");
    }

    /**
     * Returns if the given end date within the recurring schedule have not expired
     * Date is being checked using checkExpiryDate method in RecurringDatesUtil
     * And considered expired when end date is before current system date
     * Or less than a week after current system date without any matching days
     *
     * @return State of whether date in recurring schedule has expired
     */
    public boolean isExpired() {
        // Less than a week when the weekDates is empty, no recurringDates can be added to weekDates
        boolean isLessThanAWeek = (weekDates.isEmpty() && !isEmptyRecurringSchedule);
        boolean isExpiredDate = RecurringDatesUtil.checkExpiryDate() || isLessThanAWeek;
        return isExpiredDate;
    }

    /**
     * Used to generate the output of the dates of the recurring schedule
     * When the recurring schedule is present (not an empty string input)
     * Using methods formatRecurringSchedule, calculateNumWeeksBetweenDates, findWeekDates in RecurringDatesUtil
     *
     * @param recurringSchedule Input string of recurring schedule requirements given by the user
     * @return Output string of recurring dates in the PlanIt application
     */
    @Override
    public String generateRecurringDates(String recurringSchedule) {
        String recurringScheduleDetail = RecurringDatesUtil.formatRecurringSchedule(recurringSchedule);
        int numWeeks = RecurringDatesUtil.calculateNumWeeksBetweenDates();
        weekDates = RecurringDatesUtil.findWeekDates(numWeeks);

        String recurringScheduleOutput = recurringScheduleDetail + "\n\nHere are the Recurring Sessions:\n"
                + weekDates.stream().collect(Collectors.joining("\n"));
        return recurringScheduleOutput;
    }

    /**
     * Checks if the provided date string is in this schedule's weekly dates.
     * Will return false if there is no dates in the schedule.
     *
     * @param dateString Date to be checked.
     * @return Boolean indicating if the date is in this schedule.
     */
    public boolean isInRecurringSchedule(String dateString) {
        return weekDates.stream().anyMatch(date -> date.equals(dateString));
    }

    /**
     * Checks if the Recurring Schedule input is empty string.
     *
     * @return Boolean if the String of Recurring Schedule is empty.
     */
    public boolean isEmptyValue() {
        return isEmptyRecurringSchedule;
    }

    @Override
    public String toString() {
        return output;
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

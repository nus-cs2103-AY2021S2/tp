package seedu.address.model.task;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Represents a Task's Recurring Schedule in the planner.
 * Guarantees: immutable; is valid and can be empty as declared in {@link #isValidRecurringScheduleInput(String)}
 */
public class RecurringSchedule {
    public static final String FIELD_NAME = "RecurringSchedule";

    // example format: [23/10/2019]
    public static final String DATE_REGEX = "\\[(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}]";
    public static final String DAYSOFWEEK_REGEX = "\\[(mon|tue|wed|thu|fri|sat|sun)]";
    public static final String WEEKFREQUENCY_REGEX = "\\[(weekly|biweekly)]";

    public static final String VALIDATION_REGEX = DATE_REGEX + DAYSOFWEEK_REGEX + WEEKFREQUENCY_REGEX;

    public static final String MESSAGE_CONSTRAINTS = "Recurring Schedule should consists of:"
            + "\n\n1) Ending Date should be ahead of current date"
            + "\n\n2) Days of week which are alphabet letters that are case-insensitive represented in these options\n "
            + "=====> (mon, tue, wed, thu, fri, sat, sun)"
            + "\n\n3) Frequency of week that are also case-insensitive represented in these options\n"
            + "=====> (weekly, biweekly) "
            + "\n\n and without blank space arguments but can be empty if nothing is entered :-)"
            + "\n\nHere is an example: [23/10/2021][Mon][weekly]";

    public static final String INVALID_ENDDATE = "End date should be ahead of current date";
    public static final ArrayList<String> DAYSOFWEEKS = new ArrayList<>(
            Arrays.asList("", "mon", "tue", "wed", "thu", "fri", "sat", "sun"));
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static LocalDate currentDate;
    private static LocalDate endDate;
    public final String value;
    public final String output;
    private boolean emptyRecurringSchedule;
    private List<String> weekDates;
    private String dayOfWeek;
    private String weekFreq;

    /**
     * Recurring Schedule constructor
     *
     * @param recurringSchedule A valid recurring schedule text
     */
    public RecurringSchedule(String recurringSchedule) {
        requireNonNull(recurringSchedule);
        checkArgument(isValidRecurringScheduleInput(recurringSchedule), MESSAGE_CONSTRAINTS);
        emptyRecurringSchedule = isEmptyRecurringScheduleInput(recurringSchedule);
        currentDate = LocalDate.now();
        weekDates = new ArrayList<>();
        value = recurringSchedule;
        output = emptyRecurringSchedule ? "***Optional***"
                : generateRecurringSchedule(recurringSchedule);
    }

    /**
     * Used to check whether recurring schedule is empty
     *
     * @return State of whether recurring schedule is empty in boolean format
     */
    private void formatRecurringScheduleInput(String recurringSchedule) {
        if (!emptyRecurringSchedule) {
            String[] recurringScheduleData = recurringSchedule.replaceAll("\\]", "").split("\\[");
            endDate = LocalDate.parse(recurringScheduleData[1], FORMATTER);
            dayOfWeek = recurringScheduleData[2].toLowerCase();
            weekFreq = recurringScheduleData[3].toLowerCase();
        }
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
        Pattern pattern = Pattern.compile(VALIDATION_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(test);
        boolean isValidRecurringSchedule = matcher.matches();

        return isValidRecurringSchedule || isEmptyRecurringScheduleInput(test);
    }

    /**
     * Used to check whether recurring schedule is empty
     *
     * @return State of whether recurring schedule is empty in boolean format
     */
    public boolean isExpired() {
        boolean isExpiredDate = endDate.isBefore(currentDate) || (weekDates.isEmpty() && !emptyRecurringSchedule);
        return isExpiredDate;
    }

    /**
     * Used to check whether recurring schedule is empty
     *
     * @return State of whether recurring schedule is empty in boolean format
     */
    public static boolean isEmptyRecurringScheduleInput(String test) {
        return test.equals("");
    }

    /**
     * Used to check whether recurring schedule is empty
     *
     * @return State of whether recurring schedule is empty in boolean format
     */
    private int calculateNumberOfWeeksBetweenDates() {
        LocalDate startingDate = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        LocalDate endingDate = endDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY));

        long daysBetweenTwoDates = ChronoUnit.DAYS.between(startingDate, endingDate);
        int numWeeks = (int) Math.ceil(daysBetweenTwoDates / 7.0);
        return numWeeks;
    }

    /**
     * Used to check whether recurring schedule is empty
     *
     * @return State of whether recurring schedule is empty in boolean format
     */
    private void findWeekDates(int numWeeks) {
        int dayOfWeekInNum = DAYSOFWEEKS.indexOf(dayOfWeek);
        LocalDate nextDate = currentDate;

        for (int i = 0; i < numWeeks; i++) {
            DayOfWeek selectedDay = DayOfWeek.of(dayOfWeekInNum);
            TemporalAdjuster upcomingDay = TemporalAdjusters.next(selectedDay);
            nextDate = nextDate.with(upcomingDay);

            if (nextDate.isBefore(endDate) || nextDate.isEqual(endDate)) {
                weekDates.add(FORMATTER.format(nextDate));
            }

            boolean isBiWeekly = weekFreq.equals("biweekly");
            if (isBiWeekly) {
                nextDate = nextDate.with(upcomingDay);
            }
        }
    }

    /**
     * Used to check whether recurring schedule is empty
     *
     * @return State of whether recurring schedule is empty in boolean format
     */
    private String generateRecurringSchedule(String recurringSchedule) {
        formatRecurringScheduleInput(recurringSchedule);
        assert currentDate.isBefore(endDate) || currentDate.isEqual(endDate);

        int numWeeks = calculateNumberOfWeeksBetweenDates();
        findWeekDates(numWeeks);
        String recurringScheduleOutput = recurringSchedule + "\n\nRecurring Sessions:\n"
                + weekDates.stream().collect(Collectors.joining("\n"));
        return recurringScheduleOutput;
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

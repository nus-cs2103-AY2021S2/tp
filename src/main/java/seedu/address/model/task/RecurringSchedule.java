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
            + "\n\n1) Ending Date"
            + "\n\n2) Days of week which are alphabet letters that are case-insensitive represented in these options\n "
            + "=====> (mon, tue, wed, thu, fri, sat, sun)"
            + "\n\n3) Frequency of week that are also case-insensitive represented in these options\n"
            + "=====> (weekly, biweekly) "
            + "\n\n and without blank space arguments but can be empty if nothing is entered :-)"
            + "\n\nHere is an example: [23/10/2019][Mon][weekly]";

    public static final String INVALID_ENDDATE = "End date should be ahead of current date";
    public static final ArrayList<String> DAYSOFWEEKS = new ArrayList<>(
            Arrays.asList("", "mon", "tue", "wed", "thu", "fri", "sat", "sun"));
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public final String value;

    /**
     * Recurring Schedule constructor
     *
     * @param recurringSchedule A valid recurring schedule text
     */
    public RecurringSchedule(String recurringSchedule) {
        requireNonNull(recurringSchedule);
        checkArgument(isValidRecurringScheduleInput(recurringSchedule), MESSAGE_CONSTRAINTS);
        value = isEmptyRecurringScheduleInput(recurringSchedule) ? recurringSchedule
                : generateRecurringSchedule(recurringSchedule);
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
    public static boolean isEmptyRecurringScheduleInput(String test) {
        return test.equals("");
    }

    /**
     * Used to check whether recurring schedule is empty
     *
     * @return State of whether recurring schedule is empty in boolean format
     */
    private List<String> formatRecurringScheduleInput(String recurringSchedule) {
        String[] recurringScheduleData = recurringSchedule.replaceAll("\\]", "").split("\\[");
        String endDate = recurringScheduleData[1];
        String dayOfWeek = recurringScheduleData[2].toLowerCase();
        String weekFreq = recurringScheduleData[3].toLowerCase();

        List<String> updatedRecurringScheduleData = new ArrayList<>(Arrays.asList(endDate, dayOfWeek, weekFreq));
        return updatedRecurringScheduleData;
    }

    /**
     * Used to check whether recurring schedule is empty
     *
     * @return State of whether recurring schedule is empty in boolean format
     */
    private int calculateNumberOfWeeksBetweenDates(LocalDate currentDate, LocalDate formattedEndDate) {
        LocalDate startingDate = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        LocalDate endingDate = formattedEndDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY));

        long daysBetweenTwoDates = ChronoUnit.DAYS.between(startingDate, endingDate);
        int numWeeks = (int) Math.ceil(daysBetweenTwoDates / 7.0);
        return numWeeks;
    }

    /**
     * Used to check whether recurring schedule is empty
     *
     * @return State of whether recurring schedule is empty in boolean format
     */
    private List<String> findWeekDates(LocalDate currentDate, LocalDate formattedEndDate,
                                       List<String> formattedRecurringSchedule, int numWeeks) {
        List<String> weekDates = new ArrayList<>();
        String dayOfWeekInput = formattedRecurringSchedule.get(1);
        String weekFreqInput = formattedRecurringSchedule.get(2);
        int dayOfWeekInNum = DAYSOFWEEKS.indexOf(dayOfWeekInput);

        for (int i = 0; i < numWeeks; i++) {
            DayOfWeek selectedDay = DayOfWeek.of(dayOfWeekInNum);
            TemporalAdjuster upcomingDay = TemporalAdjusters.next(selectedDay);
            currentDate = currentDate.with(upcomingDay);

            if (currentDate.isBefore(formattedEndDate) || currentDate.isEqual(formattedEndDate)) {
                weekDates.add(FORMATTER.format(currentDate));
            }

            boolean isBiWeekly = weekFreqInput.equals("biweekly");
            if (isBiWeekly) {
                currentDate = currentDate.with(upcomingDay);
            }
        }
        return weekDates;
    }

    /*
    private static void checkValidEndDate(LocalDate currentDate, LocalDate formattedEndDate) {
        if (formattedEndDate.isBefore(currentDate)) {
            throw new IllegalArgumentException(INVALID_ENDDATE);
        }
    }
     */

    /**
     * Used to check whether recurring schedule is empty
     *
     * @return State of whether recurring schedule is empty in boolean format
     */
    private String generateRecurringSchedule(String recurringSchedule) {
        List<String> formattedRecurringSchedule = formatRecurringScheduleInput(recurringSchedule);
        String endDateInput = formattedRecurringSchedule.get(0);

        LocalDate currentDate = LocalDate.now();
        LocalDate formattedEndDate = LocalDate.parse(endDateInput, FORMATTER);
        //checkValidEndDate(currentDate, formattedEndDate);
        int numWeeks = calculateNumberOfWeeksBetweenDates(currentDate, formattedEndDate);

        List<String> weekDates = findWeekDates(currentDate, formattedEndDate, formattedRecurringSchedule, numWeeks);
        String recurringScheduleOutput = weekDates.stream().collect(Collectors.joining("\n"));
        return recurringScheduleOutput;
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

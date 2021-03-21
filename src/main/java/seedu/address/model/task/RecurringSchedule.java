package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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

    // example format: [23/10/2019]
    public static final String DATE_REGEX = "\\[(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}]";
    public static final String OLDDATE_REGEX = "\\[\\d{2}\\/\\d{2}\\/\\d{4}\\]";
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

    public final String value;

    /**
     * Recurring Schedule constructor
     *
     * @param recurringSchedule A valid recurring schedule text
     */
    public RecurringSchedule(String recurringSchedule) {
        requireNonNull(recurringSchedule);
        checkArgument(isValidRecurringScheduleInput(recurringSchedule), MESSAGE_CONSTRAINTS);
        if (!isEmptyRecurringScheduleInput(recurringSchedule)) {
            value = generateRecurringSchedule(recurringSchedule);
        } else {
            value = recurringSchedule;
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
    public static boolean isEmptyRecurringScheduleInput(String test) {
        return test.equals("");
    }

    /**
     * Used to check whether recurring schedule is empty
     *
     * @return State of whether recurring schedule is empty in boolean format
     */
    public String generateRecurringSchedule(String recurringSchedule) {
        //[21/03/2021][fri][weekly]
        ArrayList<String> daysOfWeeks = new ArrayList<String>(
                Arrays.asList("mon", "tue", "wed", "thu", "fri", "sat", "sun"));
        String[] data = recurringSchedule.replaceAll("\\]", "").split("\\[");
        String endDate = data[1];
        String dayOfWeek = data[2].toLowerCase();
        String weekFreq = data[3].toLowerCase();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate currentDate = LocalDate.now();
        int inputDayInt = daysOfWeeks.indexOf(dayOfWeek) + 1;
        LocalDate actualEndDate = LocalDate.parse(endDate, formatter);
        //System.out.println(currentDate);
        // System.out.println(actualEndDate);

        LocalDate startingDate = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

        LocalDate endingDate = actualEndDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY));

        long daysBetweenTwoDates = ChronoUnit.DAYS.between(startingDate, endingDate);
        int numberOfWeeks = (int) Math.ceil(daysBetweenTwoDates / 7.0);

        List<String> dates = new ArrayList<>();
        for (int i = 0; i < numberOfWeeks; i++) {
            if (weekFreq.equals("biweekly") && i % 2 != 0) {
                continue;
            }
            currentDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.of(inputDayInt)));
            if (currentDate.isBefore(actualEndDate) || currentDate.isEqual(actualEndDate)) {
                dates.add(formatter.format(currentDate));
            }

            if (weekFreq.equals("biweekly")) {
                currentDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.of(1)));
            }
        }

        String result = dates.stream().collect(Collectors.joining("\n"));

        return result;
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

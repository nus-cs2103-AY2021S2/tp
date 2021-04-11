package seedu.address.model.task.attributes;

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
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.model.task.ValidDateFormatter;

/**
 * Represents a Task's Recurring Schedule in the planner.
 * Guarantees: immutable; is valid and can be empty as declared in {@link #isValidRecurringScheduleInput(String)}
 */
public class RecurringSchedule implements Attribute {
    public static final String FIELD_NAME = "RecurringSchedule";

    // example format: [23/10/2021]
    public static final String DATE_REGEX = "\\[(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}]";
    public static final String DAYSOFWEEK_REGEX = "\\[(mon|tue|wed|thu|fri|sat|sun)]";
    public static final String WEEKFREQUENCY_REGEX = "\\[(weekly|biweekly)]";
    public static final String VALIDATION_REGEX = DATE_REGEX + DAYSOFWEEK_REGEX + WEEKFREQUENCY_REGEX;

    public static final String MESSAGE_CONSTRAINTS = "Recurring Schedule should be in this format:"
            + "\n\n1) Ending Date in dd/mm/yyyy format (requires leading zero for day, month) "
            + "should be ahead of current date"
            + "\n\n2) Days of week (case-insensitive) : mon, tue, wed, thu, fri, sat, sun"
            + "\n\n3) Frequency of week (case-insensitive) : weekly, biweekly"
            + "\n\n and without white space between arguments but can be empty if nothing is entered :-)"
            + "\n\nHere is an example: [23/10/2021][Mon][weekly]";

    public static final String INVALID_END_DATE = "End date should be ahead of current date "
            + "or the input end date is less than a week without matching days found !!!";

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Index 0 is placed as empty string so when days of week is retrieved, no additional increment is required
    public static final List<String> WEEK_DAYS = Arrays.asList("", "mon", "tue", "wed", "thu", "fri", "sat", "sun");

    public final String value;
    public final String output;
    private List<String> weekDates = new ArrayList<>();
    private String dayOfWeek;
    private String weekFreq;
    private boolean isValidDateRange;
    private final LocalDate currentDate = LocalDate.now();
    private Optional<LocalDate> endDate = Optional.empty();

    /**
     * Recurring Schedule constructor
     *
     * @param recurringSchedule A valid recurring schedule text
     */
    public RecurringSchedule(String recurringSchedule) {
        requireNonNull(recurringSchedule);
        checkArgument(isValidRecurringScheduleInput(recurringSchedule), MESSAGE_CONSTRAINTS);
        value = recurringSchedule;
        output = isEmptyValue() ? "" : generateRecurringDates(recurringSchedule);
    }

    /**
     * Checks if the Recurring Schedule input is empty string.
     *
     * @return Boolean if the String of Recurring Schedule is empty.
     */
    public boolean isEmptyValue() {
        return value.equals("");
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
        return isValidRecurringSchedule || test.equals("");
    }

    /**
     * Used to format the input of recurring schedule before the recurring dates can be generated
     * When the recurring schedule is not optional (empty string input)
     *
     * @return String format output of recurring schedule detail in user-centric form
     */
    private String formatRecurringSchedule() {
        String outputDate = FORMATTER.format(endDate.get());
        String outputRecurringScheduleDetail = " Every " + dayOfWeek + " " + weekFreq + " until " + outputDate;

        return outputRecurringScheduleDetail;
    }

    /**
     * Used to check whether the input date is within range for recurring schedule when recurring schedule is non-empty
     *
     * @return Boolean result of whether the input date is in the correct range for recurring schedule
     */
    public boolean isInvalidDateRange() {
        return !isValidDateRange && !isEmptyValue();
    }

    /**
     * Returns if the given end date within the recurring schedule have not expired
     * And considered expired when end date is before current system date
     * Or less than a week after current system date without any matching days
     *
     * @return State of whether date in recurring schedule has expired
     */
    public boolean isExpired() {
        // Less than a week when the weekDates is empty, no recurringDates can be added to weekDates
        boolean isLessThanAWeek = (weekDates.isEmpty() && !isEmptyValue());
        boolean isExpired = endDate.isEmpty() ? false : endDate.get().isBefore(currentDate) || isLessThanAWeek;
        return isExpired;
    }

    /**
     * Used to count the number of weeks between current system date and given end date provided by the user
     * A week is being counted from Sunday of the previous week up till the Saturday of the coming week
     * In the event, the dates range is shorter than a week, the number of weeks will be considered 0
     *
     * @return Number of weeks in integer format
     */
    private int calculateNumWeeksBetweenDates() {
        LocalDate startingDate = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        LocalDate endingDate = endDate.get().with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY));

        long daysBetweenDates = ChronoUnit.DAYS.between(startingDate, endingDate);
        int numWeeks = (int) Math.ceil(daysBetweenDates / 7.0);
        return numWeeks;
    }

    /**
     * Used to generate the recurring dates from the recurring schedule conditions provided by the user
     *
     * @param numWeeks Number of weeks between current date and end date
     * @return List of selected week dates that fall within the range of current date and end date
     *         as well as falling on the chosen day of week
     */
    private List<String> findWeekDates(int numWeeks) {
        List<String> weekDates = new ArrayList<>();
        int dayOfWeekInNum = WEEK_DAYS.indexOf(dayOfWeek);
        LocalDate nextDate = currentDate;

        for (int i = 0; i < numWeeks; i++) {
            DayOfWeek selectedDay = DayOfWeek.of(dayOfWeekInNum);
            TemporalAdjuster upcomingDay = TemporalAdjusters.next(selectedDay);
            nextDate = nextDate.with(upcomingDay);

            boolean isValidNextDate = nextDate.isBefore(endDate.get()) || nextDate.isEqual(endDate.get());
            if (isValidNextDate) {
                weekDates.add(FORMATTER.format(nextDate));
            }

            boolean isBiWeekly = weekFreq.equals("biweekly");
            if (isBiWeekly) {
                nextDate = nextDate.with(upcomingDay);
            }
        }
        return weekDates;
    }

    /**
     * Used to generate the output of the dates of the recurring schedule
     * When the recurring schedule is present (not an empty string input)
     *
     * @return Output string of recurring dates in the PlanIt application
     */
    public String generateRecurringDates(String recurringSchedule) {
        String[] recurringScheduleData = recurringSchedule.replaceAll("\\]", "").split("\\[");
        isValidDateRange = ValidDateFormatter.isValid(recurringScheduleData[1]);
        endDate = Optional.of(LocalDate.parse(recurringScheduleData[1], FORMATTER));
        dayOfWeek = recurringScheduleData[2].toLowerCase();
        weekFreq = recurringScheduleData[3].toLowerCase();

        String recurringScheduleDetail = formatRecurringSchedule();
        int numWeeks = calculateNumWeeksBetweenDates();
        weekDates = findWeekDates(numWeeks);

        String recurringScheduleOutput = recurringScheduleDetail + "\n\nHere are the Recurring Sessions:\n"
                + String.join("\n", weekDates);
        if (weekDates.isEmpty()) {
            return "";
        } else {
            return recurringScheduleOutput;
        }
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

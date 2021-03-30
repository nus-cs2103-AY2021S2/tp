package seedu.address.model.task;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Contains utility methods used for parsing strings in the Recurring Schedule class
 */
public class RecurringDatesUtil {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Index 0 is placed as empty string so when days of week is retrieved, no additional increment is required
    public static final ArrayList<String> DAYSOFWEEKS = new ArrayList<>(
            Arrays.asList("", "mon", "tue", "wed", "thu", "fri", "sat", "sun"));

    private static String dayOfWeek;
    private static String weekFreq;

    private static final LocalDate currentDate = LocalDate.now();
    private static LocalDate endDate;

    /**
     * Check whether end date given by user is expired (before current date)
     *
     * @return Boolean result of whether end date has expired
     */
    public static boolean checkExpiryDate() {
        return endDate.isBefore(currentDate);
    }

    /**
     * Used to format the input of recurring schedule before the recurring dates can be generated
     * When the recurring schedule is not optional (empty string input)
     *
     * @param recurringSchedule Input string of recurring schedule
     * @return String format output of recurring schedule detail in user-centric form
     */
    public static String formatRecurringSchedule(String recurringSchedule) {
        String[] recurringScheduleData = recurringSchedule.replaceAll("\\]", "").split("\\[");
        endDate = LocalDate.parse(recurringScheduleData[1], FORMATTER);
        dayOfWeek = recurringScheduleData[2].toLowerCase();
        weekFreq = recurringScheduleData[3].toLowerCase();

        String outputRecurringScheduleDetail = " every " + dayOfWeek + " " + weekFreq + " until "
                + recurringScheduleData[1];
        return outputRecurringScheduleDetail;
    }

    /**
     * Used to generate the recurring dates from the recurring schedule conditions provided by the user
     *
     * @param numWeeks Number of weeks between current date and end date
     * @return List of selected week dates that fall within the range of current date and end date
     *         as well as falling on the chosen day of week
     */
    public static List<String> findWeekDates(int numWeeks) {
        List<String> weekDates = new ArrayList<>();
        int dayOfWeekInNum = DAYSOFWEEKS.indexOf(dayOfWeek);
        LocalDate nextDate = currentDate;

        for (int i = 0; i < numWeeks; i++) {
            DayOfWeek selectedDay = DayOfWeek.of(dayOfWeekInNum);
            TemporalAdjuster upcomingDay = TemporalAdjusters.next(selectedDay);
            nextDate = nextDate.with(upcomingDay);

            boolean isValidNextDate = nextDate.isBefore(endDate) || nextDate.isEqual(endDate);
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
     * Used to count the number of weeks between current system date and given end date provided by the user
     * A week is being counted from Sunday of the previous week up till the Saturday of the coming week
     * In the event, the dates range is shorter than a week, the number of weeks will be considered 0
     *
     * @return Number of weeks in integer format
     */
    public static int calculateNumWeeksBetweenDates() {
        LocalDate startingDate = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        LocalDate endingDate = endDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY));

        long daysBetweenDates = ChronoUnit.DAYS.between(startingDate, endingDate);
        int numWeeks = (int) Math.ceil(daysBetweenDates / 7.0);
        return numWeeks;
    }
}

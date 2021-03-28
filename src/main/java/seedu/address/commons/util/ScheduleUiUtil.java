package seedu.address.commons.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;
import java.util.function.BiPredicate;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import seedu.address.model.Event;

/*
Code adapted from AY2021S1-CS2103T-F12-2/tp (https://github.com/AY2021S1-CS2103T-F12-2/tp)
 */
public class ScheduleUiUtil {

    // This part needs to synchronize with TimeScaleCell
    public static final double INITIAL_PADDING = 9.0; //The paddingTop is set to 10, but 9 is more accurate.
    public static final double MARGIN_PER_HOUR = 40.0;
    public static final double MARGIN_PER_MINUTE = MARGIN_PER_HOUR / 60.0;
    public static final double CURRENT_TIME_POINTER_PADDING = 5.0;

    public static final BiPredicate<Event, LocalDateTime> IN_THE_DAY = (task, startDate) -> (
            task.getDateTime().isEqual(startDate)
                    || task.getDateTime().isAfter(startDate))
            && task.getDateTime().isBefore(startDate.plusDays(1));
    public static final BiPredicate<Event, LocalDateTime> IN_THE_CALENDAR = (task, startDate) -> (
            task.getDateTime().isEqual(startDate)
                    || task.getDateTime().isAfter(startDate))
            && task.getDateTime().isBefore(startDate.plusDays(35));


    /**
     * Method used to check if the time format is hh:mm AM/PM
     *
     * @return
     */
    public static boolean checkTimePattern(String time) {
        String[] splitTime = time.split(" ");
        String[] splitHourMinute = splitTime[0].split(":");
        String hour = splitHourMinute[0];
        String minute = splitHourMinute[1];
        return splitTime[1].matches("(AM)|(PM)") && hour.matches("(1[0-2])|[1-9]")
                && minute.matches("(0[0-9])|([0-5][0-9])");
    }

    /**
     * This method transforms "HH:mm" to "hh:mm AM/PM"
     */
    public static String toAmPmTime(String formattedTime) {
        String[] splitTime = formattedTime.split(":");
        int hour = Integer.parseInt(splitTime[0]);
        //make sure that minutes have a trailing 0.
        String minute = splitTime[1];

        if (hour > 12) {
            hour -= 12;
            return String.format("%d:%s PM", hour, minute);
        } else if (hour == 12) {
            return String.format("%d:%s PM", hour, minute);
        } else if (hour == 0) {
            return String.format("%d:%s AM", 12, minute);
        } else {
            return String.format("%d:%s AM", hour, minute);
        }
    }

    /**
     * Convert string formmatted time from calendar box to local date.
     * @param formattedTime string format of time from calendar box.
     * @return
     */
    public static LocalDate calendarTextToDate(String formattedTime) {
        DateTimeFormatter df = new DateTimeFormatterBuilder()
                .parseCaseInsensitive().appendPattern("MMM d yyyy").toFormatter(Locale.ENGLISH);
        return LocalDate.parse(formattedTime, df);
    }

    /**
     * This method calculates the margin from "HH:mm";
     * Still need to check if it is accurate.
     */
    public static double getMarginFromTime(String primitiveTime) {
        String[] splitTime = primitiveTime.split(":");
        int hour = Integer.parseInt(splitTime[0]);
        int minute = Integer.parseInt(splitTime[1]);

        return INITIAL_PADDING + hour * MARGIN_PER_HOUR + minute * MARGIN_PER_MINUTE;

    }

    /**
     * This method calculates the margin from "HH:mm";
     * Still need to check if it is accurate.
     */
    public static double getMarginFromDateTime(LocalDateTime time) {
        int hour = time.getHour();
        int minute = time.getMinute();
        return INITIAL_PADDING + hour * MARGIN_PER_HOUR + minute * MARGIN_PER_MINUTE;

    }

    /**
     * Replaces one component with another component within parent.
     *
     * @param beReplaced the component to be replaced.
     * @param toReplace  the component to show.
     * @param parent     the parent Node.
     */
    public static void replaceComponent(Node beReplaced, Node toReplace, Pane parent) {
        int idx = parent.getChildren().indexOf(beReplaced);
        parent.getChildren().remove(idx);
        parent.getChildren().add(idx, toReplace);
    }
}

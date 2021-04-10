package seedu.address.model.schedule;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TypicalSchedules {
    public static final LocalDateTime START_DATE_ONE =
            LocalDateTime.of(2021, 3, 3, 0, 00, 00);
    public static final LocalDateTime END_DATE_ONE =
            LocalDateTime.of(2021, 3, 3, 23, 59, 59);
    public static final Schedulable SHORT_SCHEDULE_ONE = new SimplePeriod("one", START_DATE_ONE, END_DATE_ONE);

    public static final LocalDateTime START_DATE_TWO =
            LocalDateTime.of(2021, 3, 3, 0, 00, 00);
    public static final LocalDateTime END_DATE_TWO =
            LocalDateTime.of(2021, 3, 4, 0 , 0, 0);
    public static final LocalDateTime END_DATE_TWO_ROUND_DOWN =
            LocalDate.of(2021, 3, 3).atTime(LocalTime.MAX);



    public static final Schedulable SCHEDULE_TWO = new SimplePeriod("two", START_DATE_TWO, END_DATE_TWO);
    public static final Schedulable SCHEDULE_THREE = new SimplePeriod("two", START_DATE_TWO, END_DATE_TWO_ROUND_DOWN);


    public static final LocalDateTime START_DATE_FOUR =
            LocalDate.of(2021, 3, 3).atTime(LocalTime.MAX);
    public static final LocalDateTime END_DATE_FOUR =
            LocalDateTime.of(2021, 3, 6, 23, 59, 59);
    public static final Schedulable SHORT_SCHEDULE_FOUR = new SimplePeriod("four", START_DATE_FOUR, END_DATE_FOUR);

    public static final LocalDateTime START_DATE_FIVE =
            LocalDateTime.of(2021, 3, 4,0,0,0);
    public static final LocalDateTime END_DATE_FIVE =
            LocalDate.of(2021, 3, 4).atTime(LocalTime.MAX);

    public static final LocalDateTime START_DATE_SIX =
            LocalDateTime.of(2021, 3, 5,0,0,0);
    public static final LocalDateTime END_DATE_SIX =
            LocalDate.of(2021, 3, 5).atTime(LocalTime.MAX);

    public static final LocalDateTime START_DATE_SEVEN =
            LocalDateTime.of(2021, 3, 6,0,0,0);
    public static final LocalDateTime END_DATE_SEVEN =
            LocalDateTime.of(2021, 3, 6, 23, 59, 59);

    public static final Schedulable LONG_SCHEDULABLE =
            new SimplePeriod("five", START_DATE_FOUR, END_DATE_FOUR);
    public static final Schedulable LONG_SCHEDULABLE_PART_ONE =
            new SimplePeriod("five", START_DATE_FIVE, END_DATE_FIVE);
    public static final Schedulable LONG_SCHEDULABLE_PART_TWO =
            new SimplePeriod("five", START_DATE_SIX, END_DATE_SIX);
    public static final Schedulable LONG_SCHEDULABLE_PART_THREE =
            new SimplePeriod("five", START_DATE_SEVEN, END_DATE_SEVEN);




}

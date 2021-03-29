package seedu.address.model.person;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.person.Goal.Frequency.MONTHLY;
import static seedu.address.model.person.Goal.Frequency.NONE;
import static seedu.address.model.person.Goal.Frequency.WEEKLY;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;

public class StreakTest {

    @Test
    public void empty() {
        Streak streak = Streak.empty();
        assertEquals(0, streak.getValue());
    }

    @Test
    public void from_none() {
        Goal noneGoal = new Goal(NONE);
        assertEquals(0, Streak.from(noneGoal, Collections.emptyList()).getValue());
    }

    public void testFromWeekly(List<LocalDate> dates) {
        Goal weeklyGoal = new Goal(WEEKLY);

        Event oneWeekBefore = new EventBuilder().withDate(dates.get(0)).build();
        Event twoWeeksBefore = new EventBuilder().withDate(dates.get(1)).build();
        Event threeWeeksBefore = new EventBuilder().withDate(dates.get(2)).build();
        Event fourWeeksBefore = new EventBuilder().withDate(dates.get(3)).build();

        List<Event> meetings = new ArrayList<>();
        meetings.add(oneWeekBefore);
        meetings.add(twoWeeksBefore);
        meetings.add(threeWeeksBefore);
        meetings.add(fourWeeksBefore);

        // Meet once every week
        assertEquals(4, Streak.from(weeklyGoal, meetings).getValue());

        // Meet more times on some weeks
        Event extra1 = new EventBuilder().withDate(dates.get(0).minusDays(1)).build();
        Event extra2 = new EventBuilder().withDate(dates.get(2).minusDays(2)).build();
        meetings.add(extra1);
        meetings.add(extra2);

        assertEquals(6, Streak.from(weeklyGoal, meetings).getValue());

        meetings.remove(extra1);
        assertEquals(5, Streak.from(weeklyGoal, meetings).getValue());

        meetings.remove(extra2);

        // Gaps in dates
        meetings.remove(oneWeekBefore);
        assertEquals(0, Streak.from(weeklyGoal, meetings).getValue());

        meetings.add(oneWeekBefore);
        meetings.remove(twoWeeksBefore);
        assertEquals(1, Streak.from(weeklyGoal, meetings).getValue());

        meetings.add(twoWeeksBefore);
        meetings.remove(fourWeeksBefore);
        assertEquals(3, Streak.from(weeklyGoal, meetings).getValue());
    }

    public void testFromWeeklySameDayPerWeek(LocalDate now) {
        List<LocalDate> dates = new ArrayList<>();
        dates.add(now.minusWeeks(1));
        dates.add(now.minusWeeks(2));
        dates.add(now.minusWeeks(3));
        dates.add(now.minusWeeks(4));
        testFromWeekly(dates);
    }

    @Test
    public void from_weeklyStartOnAnyDay_sameDayPerWeek() {
        LocalDate now = LocalDate.now();
        testFromWeeklySameDayPerWeek(now);
    }

    @Test
    public void from_weeklyStartOnSunday_sameDayPerWeek() {
        LocalDate now = LocalDate.now().with(TemporalAdjusters.nextOrSame(SUNDAY));
        testFromWeeklySameDayPerWeek(now);
    }

    @Test
    public void from_weeklyStartOnMonday_randomDaysPerWeek() {
        LocalDate latest = LocalDate.now().with(TemporalAdjusters.previousOrSame(MONDAY));
        List<LocalDate> dates = new ArrayList<>();
        dates.add(latest.minusWeeks(1));
        dates.add(dates.get(0).minusDays(6)); // last Tuesday
        dates.add(dates.get(1).minusDays(6)); // last Wednesday
        dates.add(dates.get(2).minusDays(4)); // last Saturday
        testFromWeekly(dates);
    }

    public void testFromMonthly(List<LocalDate> dates) {
        Goal monthlyGoal = new Goal(MONTHLY);

        Event oneMonthBefore = new EventBuilder().withDate(dates.get(0)).build();
        Event twoMonthsBefore = new EventBuilder().withDate(dates.get(1)).build();
        Event threeMonthsBefore = new EventBuilder().withDate(dates.get(2)).build();
        Event fourMonthsBefore = new EventBuilder().withDate(dates.get(3)).build();

        List<Event> meetings = new ArrayList<>();
        meetings.add(oneMonthBefore);
        meetings.add(twoMonthsBefore);
        meetings.add(threeMonthsBefore);
        meetings.add(fourMonthsBefore);

        // Meet once every month
        assertEquals(4, Streak.from(monthlyGoal, meetings).getValue());

        // Meet more times on some months
        Event extra1 = new EventBuilder().withDate(dates.get(0).minusDays(5)).build();
        Event extra2 = new EventBuilder().withDate(dates.get(2).minusDays(7)).build();
        meetings.add(extra1);
        meetings.add(extra2);

        assertEquals(6, Streak.from(monthlyGoal, meetings).getValue());

        meetings.remove(extra1);
        assertEquals(5, Streak.from(monthlyGoal, meetings).getValue());

        meetings.remove(extra2);

        // Gaps in months
        meetings.remove(oneMonthBefore);
        assertEquals(0, Streak.from(monthlyGoal, meetings).getValue());

        meetings.add(oneMonthBefore);
        meetings.remove(twoMonthsBefore);
        assertEquals(1, Streak.from(monthlyGoal, meetings).getValue());

        meetings.add(twoMonthsBefore);
        meetings.remove(fourMonthsBefore);
        assertEquals(3, Streak.from(monthlyGoal, meetings).getValue());
    }

    public void testFromMonthlySameDayPerMonth(LocalDate now) {
        List<LocalDate> dates = new ArrayList<>();
        dates.add(now.minusMonths(1));
        dates.add(now.minusMonths(2));
        dates.add(now.minusMonths(3));
        dates.add(now.minusMonths(4));

        testFromMonthly(dates);
    }

    @Test
    public void from_monthlyStartOnAnyDay_sameDayPerMonth() {
        LocalDate now = LocalDate.now();
        testFromMonthlySameDayPerMonth(now);
    }

    @Test
    public void from_monthlyStartOnLastDay_sameDayPerMonth() {
        LocalDate now = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        testFromMonthlySameDayPerMonth(now);
    }

    @Test
    public void from_weeklyStartOnMonday_randomDaysPerMonth() {
        LocalDate latest = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        List<LocalDate> dates = new ArrayList<>();
        dates.add(latest.minusMonths(1));
        dates.add(dates.get(0).minusMonths(1).plusDays(5));
        dates.add(dates.get(1).minusMonths(1).plusDays(6));
        dates.add(dates.get(2).minusMonths(1).plusDays(4));
        testFromMonthly(dates);
    }
}

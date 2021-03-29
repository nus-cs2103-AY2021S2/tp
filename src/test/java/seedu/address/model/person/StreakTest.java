package seedu.address.model.person;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.person.Goal.Frequency.MONTHLY;
import static seedu.address.model.person.Goal.Frequency.NONE;
import static seedu.address.model.person.Goal.Frequency.WEEKLY;
import static seedu.address.model.person.Goal.Frequency.YEARLY;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;

public class StreakTest {

    private final Streak weeklyStreak;
    private final Streak emptyStreak;

    {
        List<Event> meetings = new ArrayList<>();
        LocalDate now = LocalDate.now();
        Event event1 = new EventBuilder().withDate(now).build();
        Event event2 = new EventBuilder().withDate(now.minusWeeks(1)).build();
        meetings.add(event1);
        meetings.add(event2);

        Goal weeklyGoal = new Goal(WEEKLY);
        weeklyStreak = Streak.from(weeklyGoal, meetings);
        emptyStreak = Streak.empty();
    }

    @Test
    public void empty() {
        assertEquals(0, emptyStreak.getValue());
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
    public void from_monthlyStartOnFirstDay_randomDaysPerMonth() {
        LocalDate latest = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        List<LocalDate> dates = new ArrayList<>();
        dates.add(latest.minusMonths(1));
        dates.add(dates.get(0).minusMonths(1).plusDays(5));
        dates.add(dates.get(1).minusMonths(1).plusDays(6));
        dates.add(dates.get(2).minusMonths(1).plusDays(4));
        testFromMonthly(dates);
    }

    public void testFromYearly(List<LocalDate> dates) {
        Goal yearlyGoal = new Goal(YEARLY);

        Event oneYearBefore = new EventBuilder().withDate(dates.get(0)).build();
        Event twoYearsBefore = new EventBuilder().withDate(dates.get(1)).build();
        Event threeYearsBefore = new EventBuilder().withDate(dates.get(2)).build();
        Event fourYearsBefore = new EventBuilder().withDate(dates.get(3)).build();

        List<Event> meetings = new ArrayList<>();
        meetings.add(oneYearBefore);
        meetings.add(twoYearsBefore);
        meetings.add(threeYearsBefore);
        meetings.add(fourYearsBefore);

        // Meet once every year
        assertEquals(4, Streak.from(yearlyGoal, meetings).getValue());

        // Meet more times on some years
        Event extra1 = new EventBuilder().withDate(dates.get(0).minusMonths(5).minusDays(5)).build();
        Event extra2 = new EventBuilder().withDate(dates.get(2).minusMonths(2).minusDays(7)).build();
        meetings.add(extra1);
        meetings.add(extra2);

        assertEquals(6, Streak.from(yearlyGoal, meetings).getValue());

        meetings.remove(extra1);
        assertEquals(5, Streak.from(yearlyGoal, meetings).getValue());

        meetings.remove(extra2);

        // Gaps in years
        meetings.remove(oneYearBefore);
        assertEquals(0, Streak.from(yearlyGoal, meetings).getValue());

        meetings.add(oneYearBefore);
        meetings.remove(twoYearsBefore);
        assertEquals(1, Streak.from(yearlyGoal, meetings).getValue());

        meetings.add(twoYearsBefore);
        meetings.remove(fourYearsBefore);
        assertEquals(3, Streak.from(yearlyGoal, meetings).getValue());
    }

    public void testFromYearlySameDayPerYear(LocalDate now) {
        List<LocalDate> dates = new ArrayList<>();
        dates.add(now.minusYears(1));
        dates.add(now.minusYears(2));
        dates.add(now.minusYears(3));
        dates.add(now.minusYears(4));

        testFromYearly(dates);
    }

    @Test
    public void from_yearlyStartOnAnyDay_sameDayPerYear() {
        LocalDate now = LocalDate.now();
        testFromYearlySameDayPerYear(now);
    }

    @Test
    public void from_yearlyStartOnLastDay_sameDayPerYear() {
        LocalDate now = LocalDate.now().with(TemporalAdjusters.lastDayOfYear());
        testFromYearlySameDayPerYear(now);
    }

    @Test
    public void from_yearlyStartOnFirstDay_randomDaysPerYear() {
        LocalDate latest = LocalDate.now().with(TemporalAdjusters.firstDayOfYear());
        List<LocalDate> dates = new ArrayList<>();
        dates.add(latest.minusYears(1));
        dates.add(dates.get(0).minusYears(1).plusMonths(1).plusDays(2));
        dates.add(dates.get(1).minusYears(1).plusMonths(3).plusDays(3));
        dates.add(dates.get(2).minusYears(1).plusMonths(4).plusDays(12));
        testFromYearly(dates);
    }

    @Test
    public void toUi() {
        assertEquals("0", emptyStreak.toUi());
        assertEquals("2", weeklyStreak.toUi());
    }

    @Test
    public void compareTo() {
        assertEquals(1, emptyStreak.compareTo(weeklyStreak));
        assertEquals(-1, weeklyStreak.compareTo(emptyStreak));
    }
}

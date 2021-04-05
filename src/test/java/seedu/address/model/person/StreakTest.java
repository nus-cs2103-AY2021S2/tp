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

import seedu.address.testutil.MeetingBuilder;

public class StreakTest {

    private final List<Meeting> sampleMeetings;
    private final Streak weeklyStreak;
    private final Streak emptyStreak;

    {
        List<Meeting> meetings = new ArrayList<>();
        LocalDate now = LocalDate.now();
        Meeting event1 = new MeetingBuilder().withDate(now).build();
        Meeting event2 = new MeetingBuilder().withDate(now.minusWeeks(1)).build();
        meetings.add(event1);
        meetings.add(event2);

        Goal weeklyGoal = new Goal(WEEKLY);
        weeklyStreak = Streak.from(weeklyGoal, meetings);
        emptyStreak = Streak.empty();

        sampleMeetings = new ArrayList<>();
        sampleMeetings.addAll(meetings);
    }

    @Test
    public void empty() {
        assertEquals(0, emptyStreak.getValue());
    }

    @Test
    public void from_noneGoal_returnsEmptyStreak() {
        Goal noneGoal = new Goal(NONE);
        assertEquals(0, Streak.from(noneGoal, sampleMeetings).getValue());
    }

    @Test
    public void from_emptyMeetings_returnsEmptyStreak() {
        Goal weeklyGoal = new Goal(WEEKLY);
        assertEquals(0, Streak.from(weeklyGoal, Collections.emptyList()).getValue());
    }

    @Test
    public void from_lateByOneDay_returnsStreakBroken() {
        Goal weeklyGoal = new Goal(WEEKLY);

        List<Meeting> meetings = new ArrayList<>();
        LocalDate now = LocalDate.now().with(TemporalAdjusters.previousOrSame(MONDAY));
        Meeting event1 = new MeetingBuilder().withDate(now.minusWeeks(1).minusDays(1)).build();
        meetings.add(event1);

        assertEquals(0, Streak.from(weeklyGoal, meetings).getValue());
    }

    public void testFrom(List<LocalDate> dates, Goal goal, List<LocalDate> extra) {
        Meeting onePeriodBefore = new MeetingBuilder().withDate(dates.get(0)).build();
        Meeting twoPeriodsBefore = new MeetingBuilder().withDate(dates.get(1)).build();
        Meeting threePeriodsBefore = new MeetingBuilder().withDate(dates.get(2)).build();
        Meeting fourPeriodsBefore = new MeetingBuilder().withDate(dates.get(3)).build();

        List<Meeting> meetings = new ArrayList<>();
        meetings.add(onePeriodBefore);
        meetings.add(twoPeriodsBefore);
        meetings.add(threePeriodsBefore);
        meetings.add(fourPeriodsBefore);

        // Meet once every time period
        assertEquals(4, Streak.from(goal, meetings).getValue());

        // Meet more on some time periods
        Meeting extra1 = new MeetingBuilder().withDate(extra.get(0)).build();
        Meeting extra2 = new MeetingBuilder().withDate(extra.get(1)).build();
        meetings.add(extra1);
        meetings.add(extra2);

        assertEquals(6, Streak.from(goal, meetings).getValue());

        meetings.remove(extra1);
        assertEquals(5, Streak.from(goal, meetings).getValue());

        meetings.remove(extra2);

        // Gaps in time periods
        meetings.remove(onePeriodBefore);
        assertEquals(0, Streak.from(goal, meetings).getValue());

        meetings.add(onePeriodBefore);
        meetings.remove(twoPeriodsBefore);
        assertEquals(1, Streak.from(goal, meetings).getValue());

        meetings.add(twoPeriodsBefore);
        meetings.remove(fourPeriodsBefore);
        assertEquals(3, Streak.from(goal, meetings).getValue());
    }

    public void testFromWeekly(List<LocalDate> dates) {
        List<LocalDate> extraDates = new ArrayList<>();
        extraDates.add(dates.get(0).minusDays(2));
        extraDates.add(dates.get(2).minusDays(3));
        testFrom(dates, new Goal(WEEKLY), extraDates);
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
        List<LocalDate> extraDates = new ArrayList<>();
        extraDates.add(dates.get(0).minusDays(10));
        extraDates.add(dates.get(2).minusDays(15));
        testFrom(dates, new Goal(MONTHLY), extraDates);
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
        List<LocalDate> extraDates = new ArrayList<>();
        extraDates.add(dates.get(0).minusMonths(5).minusDays(5));
        extraDates.add(dates.get(2).minusMonths(2).minusDays(7));
        testFrom(dates, new Goal(YEARLY), extraDates);
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

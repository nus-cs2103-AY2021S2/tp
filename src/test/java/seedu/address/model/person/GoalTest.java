package seedu.address.model.person;

import org.junit.jupiter.api.Test;
import seedu.address.logic.parser.exceptions.ParseException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class GoalTest {

    private static final List<String> VALID_WEEK_INPUTS = Arrays.stream(new String[] {
            "w", "week", "weekly"}).collect(Collectors.toList());
    private static final List<String> VALID_MONTH_INPUTS = Arrays.stream(new String[] {
            "m", "month", "monthly"}).collect(Collectors.toList());
    private static final List<String> VALID_YEAR_INPUTS = Arrays.stream(new String[] {
            "y", "year", "yearly"}).collect(Collectors.toList());

    @Test
    public void goal_equals_success() {
        Goal g = new Goal();
        assertEquals(g, g);
    }

    @Test
    public void goal_equals_failure() {
        Object o = new Object();
        assertNotEquals(new Goal(), o);
    }

    @Test
    public void goal_emptyGoal_success() {
        assertEquals(new Goal(Goal.Frequency.NONE), new Goal());
    }

    @Test
    public void goal_getFrequency_success() {
        assertEquals(Goal.Frequency.NONE, new Goal(Goal.Frequency.NONE).getFrequency());
    }

    @Test
    public void goal_isNoneFrequency_sucess() {
        assertTrue(new Goal(Goal.Frequency.NONE).isNoneFrequency());
        assertFalse(new Goal(Goal.Frequency.WEEKLY).isNoneFrequency());
    }

    @Test
    public void goal_frequencyInputCheck_success() {
        for (String s : VALID_WEEK_INPUTS) {
            Goal.Frequency f = assertDoesNotThrow(() -> Goal.parseFrequency(s));
            assertEquals(Goal.Frequency.WEEKLY, f);
        }
        for (String s : VALID_MONTH_INPUTS) {
            Goal.Frequency f = assertDoesNotThrow(() -> Goal.parseFrequency(s));
            assertEquals(Goal.Frequency.MONTHLY, f);
        }
        for (String s : VALID_YEAR_INPUTS) {
            Goal.Frequency f = assertDoesNotThrow(() -> Goal.parseFrequency(s));
            assertEquals(Goal.Frequency.YEARLY, f);
        }
    }

    @Test
    public void goal_isValidGoal() {
        assertTrue(Goal.isValidGoal("WEEKLY"));
        assertTrue(Goal.isValidGoal("MONTHLY"));
        assertTrue(Goal.isValidGoal("YEARLY"));
    }

    @Test
    public void goal_getGoalDeadline_success() {
        LocalDate date = LocalDate.of(2021, 3, 30);
        assertNull(new Goal().getGoalDeadline(date));
        assertEquals(LocalDate.of(2021, 4, 11),
                new Goal(Goal.Frequency.WEEKLY).getGoalDeadline(date));
        assertEquals(LocalDate.of(2021, 4, 30),
                new Goal(Goal.Frequency.MONTHLY).getGoalDeadline(date));
        assertEquals(LocalDate.of(2022, 12, 31),
                new Goal(Goal.Frequency.YEARLY).getGoalDeadline(date));
    }

    @Test
    public void goal_toString_success() {
        assertEquals(Goal.Frequency.WEEKLY.toString(), new Goal(Goal.Frequency.WEEKLY).toString());
        assertEquals(Goal.Frequency.MONTHLY.toString(), new Goal(Goal.Frequency.MONTHLY).toString());
        assertEquals(Goal.Frequency.YEARLY.toString(), new Goal(Goal.Frequency.YEARLY).toString());
        assertEquals(Goal.Frequency.NONE.toString(), new Goal(Goal.Frequency.NONE).toString());
    }

    @Test
    public void goal_parseFrequency_failure() {
        Exception e = assertThrows(ParseException.class, () -> Goal.parseFrequency("aaa"));
        assertEquals(
                new ParseException("Invalid frequency string aaa provided").getMessage(),
                e.getMessage());
    }
}

package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.person.Goal.Frequency;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class GoalTest {

    private static final List<String> VALID_WEEK_INPUTS = Arrays.asList("w", "week", "weekly");
    private static final List<String> VALID_MONTH_INPUTS = Arrays.asList("m", "month", "monthly");
    private static final List<String> VALID_YEAR_INPUTS = Arrays.asList("y", "year", "yearly");

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
        assertEquals(new Goal(Frequency.NONE), new Goal());
    }

    @Test
    public void goal_getFrequency_success() {
        assertEquals(Frequency.NONE, new Goal(Frequency.NONE).getFrequency());
        assertEquals(Frequency.WEEKLY, new Goal(Frequency.WEEKLY).getFrequency());
        assertEquals(Frequency.MONTHLY, new Goal(Frequency.MONTHLY).getFrequency());
        assertEquals(Frequency.YEARLY, new Goal(Frequency.YEARLY).getFrequency());
    }

    @Test
    public void goal_isNoneFrequency_sucess() {
        assertTrue(new Goal(Frequency.NONE).isNoneFrequency());
        assertFalse(new Goal(Frequency.WEEKLY).isNoneFrequency());
    }

    @Test
    public void goal_frequencyInputCheck_success() {
        for (String s : VALID_WEEK_INPUTS) {
            Frequency f = assertDoesNotThrow(() -> Goal.parseFrequency(s));
            assertEquals(Frequency.WEEKLY, f);
        }
        for (String s : VALID_MONTH_INPUTS) {
            Frequency f = assertDoesNotThrow(() -> Goal.parseFrequency(s));
            assertEquals(Frequency.MONTHLY, f);
        }
        for (String s : VALID_YEAR_INPUTS) {
            Frequency f = assertDoesNotThrow(() -> Goal.parseFrequency(s));
            assertEquals(Frequency.YEARLY, f);
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
                new Goal(Frequency.WEEKLY).getGoalDeadline(date));
        assertEquals(LocalDate.of(2021, 4, 30),
                new Goal(Frequency.MONTHLY).getGoalDeadline(date));
        assertEquals(LocalDate.of(2022, 12, 31),
                new Goal(Frequency.YEARLY).getGoalDeadline(date));
    }

    @Test
    public void goal_toString_success() {
        assertEquals(Frequency.WEEKLY.toString(), new Goal(Frequency.WEEKLY).toString());
        assertEquals(Frequency.MONTHLY.toString(), new Goal(Frequency.MONTHLY).toString());
        assertEquals(Frequency.YEARLY.toString(), new Goal(Frequency.YEARLY).toString());
        assertEquals(Frequency.NONE.toString(), new Goal(Frequency.NONE).toString());
    }

    @Test
    public void goal_parseFrequency_failure() {
        Exception e = assertThrows(ParseException.class, () -> Goal.parseFrequency("aaa"));
        assertEquals(
                new ParseException("Invalid frequency string aaa provided").getMessage(),
                e.getMessage());
    }
}

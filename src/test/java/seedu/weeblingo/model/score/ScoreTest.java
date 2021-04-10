package seedu.weeblingo.model.score;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

/**
 * Automated testing for seedu.weeblingo.model.Score.
 */
public class ScoreTest {

    @Test
    public void of_null_throwsRuntimeException() {
        assertThrows(RuntimeException.class, () -> Score.of(null, 1, "0:03:04"));
        assertThrows(RuntimeException.class, () -> Score.of(1, null, "0:03:04"));
        assertThrows(RuntimeException.class, () -> Score.of(null, null, "0:03:04"));
        assertThrows(RuntimeException.class, () -> Score.of(1, 1, null));
        assertThrows(RuntimeException.class, () -> Score.of(null, 1, 1,
                "03:03:04"));
    }

    @Test
    public void of_invalidValue_throwsRuntimeException() {
        assertThrows(RuntimeException.class, () -> Score
                .of(0, 1, "00:03:04"));
        assertThrows(RuntimeException.class, () -> Score
                .of(-1, 1, "00:03:04"));
        assertThrows(RuntimeException.class, () -> Score
                .of(-100000, 1, "00:03:04"));
        assertThrows(RuntimeException.class, () -> Score
                .of(1, -1, "00:03:04"));
        assertThrows(RuntimeException.class, () -> Score
                .of(1, -10000, "00:03:04"));

        // single/multiple white spaces
        assertThrows(RuntimeException.class, () -> Score.of(1, 1, ""));
        assertThrows(RuntimeException.class, () -> Score.of(1, 1, "    "));

        // not white spaces, but not in the correct format
        assertThrows(DateTimeParseException.class, () -> Score.of(1, 1,
                "0:03:04"));
        assertThrows(DateTimeParseException.class, () -> Score.of(1, 1,
                "00:61:04")); // some duration string that seems to be parsable
        assertThrows(DateTimeParseException.class, () -> Score.of(1, 1,
                "This will fail"));
        assertThrows(DateTimeParseException.class, () -> Score.of(1, 1,
                "12345"));
    }

    @Test
    public void of_questionAttemptedSmallerThanQuestionCorrect_throwsRuntimeException() {
        assertThrows(RuntimeException.class, () -> Score.of(1, 2, "00:03:04"));
        assertThrows(RuntimeException.class, () -> Score.of(100, 200, "00:03:04"));
    }

    @Test
    public void of() {
        assertDoesNotThrow(() -> Score.of(1, 1, "00:03:04"));
        assertDoesNotThrow(() -> Score.of(1, 0, "00:03:04"));
        assertDoesNotThrow(() -> Score.of(1000, 1000, "00:03:04"));
        assertDoesNotThrow(() -> Score.of(0, 0, "00:03:04"));
        assertDoesNotThrow(() -> Score.of(
                LocalDateTime.now(), 1, 1, "00:03:01"));
    }

    @Test
    public void getCorrectRationString() {
        Score test = Score.of(1, 1, "00:03:04");
        assertEquals("100.000%", test.getCorrectRatioString());
        test = Score.of(10, 9, "00:03:04");
        assertEquals("90.000%", test.getCorrectRatioString());
        test = Score.of(3, 2, "00:03:04");
        assertEquals("66.667%", test.getCorrectRatioString());
    }

    @Test
    public void compareTo_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Score.of(1, 1,
                "00:03:04").compareTo(null));
    }

    @Test
    public void compareTo() {
        try {
            Score t1 = Score.of(1, 1, "00:03:04");
            TimeUnit.SECONDS.sleep(1);
            Score t2 = Score.of(1, 1, "00:03:04");
            assertEquals(1, t1.compareTo(t2));
            assertEquals(-1, t2.compareTo(t1));
            ArrayList<Score> scores = new ArrayList<>();
            scores.add(t1);
            scores.add(t2);
            Collections.sort(scores);
            assertEquals(t2, scores.get(0));
            assertEquals(t1, scores.get(1));
        } catch (InterruptedException e) {
            assertTrue(false); // the test fails
        }
    }

    @Test
    public void isSameAttempt_createImmediatelyAfter_returnsTrue() {
        Score s1 = Score.of(10, 10, "00:03:04");
        assertTrue(s1.isSameAttempt(s1));
    }

    @Test
    public void isSameAttempt_createWithDurationOneSecond_returnsFalse() {
        try {
            Score s1 = Score.of(10, 10, "00:03:04");
            TimeUnit.SECONDS.sleep(1);
            Score s2 = Score.of(10, 10, "00:03:04");
            assertFalse(s1.isSameAttempt(s2));
            // to show symmetry
            assertFalse(s2.isSameAttempt(s1));
        } catch (InterruptedException e) {
            assertTrue(false); // the test fails
        }
    }

    @Test
    public void getNumberOfQuestionsAttempted() {
        Score s = Score.of(10, 10, "00:03:04");
        assertEquals("10", s.getNumberOfQuestionsAttempted());
    }

    @Test
    public void getNumberOfQuestionsCorrect() {
        Score s = Score.of(10, 8, "00:03:04");
        assertEquals("8", s.getNumberOfQuestionsCorrect());
    }

    @Test
    public void getCompletedTime() {
        Score s = Score.of(10, 10, "00:03:04");
        assertDoesNotThrow(() -> LocalDateTime.parse((String) s.getCompletedTime(),
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    }

    @Test
    public void getDurationTime() {
        Score s = Score.of(10, 10, "00:03:04");
        assertEquals("00:03:04", s.getTimeSpent());
    }

    @Test
    public void isSameAttempt_null_throwsNullInputException() {
        Score s = Score.of(10, 10, "00:03:04");
        assertThrows(RuntimeException.class, () -> s.isSameAttempt(null));
    }

    @Test
    public void getCorrectRatioString_zeroAttempts_returnZeroPercentile() {
        Score s = Score.of(0, 0, "00:03:04");
        assertEquals("0.000%", s.getCorrectRatioString());
    }
}

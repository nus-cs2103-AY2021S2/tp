package seedu.weeblingo.model.score;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;


public class ScoreTest {

    @Test
    public void of_null_throwsRuntimeException() {
        assertThrows(RuntimeException.class, () -> Score.of(null, 1));
        assertThrows(RuntimeException.class, () -> Score.of(1, null));
        assertThrows(RuntimeException.class, () -> Score.of(null, null));
    }

    @Test
    public void of_invalidValue_throwsRuntimeException() {
        assertThrows(RuntimeException.class, () -> Score.of(0, 1));
        assertThrows(RuntimeException.class, () -> Score.of(-1, 1));
        assertThrows(RuntimeException.class, () -> Score.of(-100000, 1));
        assertThrows(RuntimeException.class, () -> Score.of(1, -1));
        assertThrows(RuntimeException.class, () -> Score.of(1, -10000));
    }

    @Test
    public void of_qusetionAttemptedSmallerThanQuestionCorrect_throwsRuntimeException() {
        assertThrows(RuntimeException.class, () -> Score.of(1, 2));
        assertThrows(RuntimeException.class, () -> Score.of(100, 200));
    }

    @Test
    public void of() {
        assertDoesNotThrow(() -> Score.of(1, 1));
        assertDoesNotThrow(() -> Score.of(1, 0));
        assertDoesNotThrow(() -> Score.of(1000, 1000));
    }

    @Test
    public void getCorrectRationString() {
        Score test = Score.of(1, 1);
        assertEquals("100.000%", test.getCorrectRatioString());
        test = Score.of(10, 9);
        assertEquals("90.000%", test.getCorrectRatioString());
        test = Score.of(3, 2);
        assertEquals("66.667%", test.getCorrectRatioString());
    }

    @Test
    public void toJsonObject() {
        Score test = Score.of(1, 1);
        JSONObject jsonObject = test.toJsonObject();
        assertTrue(jsonObject.containsKey("time"));
        assertTrue(jsonObject.containsKey("question_attempted"));
        assertTrue(jsonObject.containsKey("question_correct"));
        assertTrue(jsonObject.containsKey("ratio"));
        assertEquals("100.000%", jsonObject.get("ratio"));
        assertEquals("1", jsonObject.get("question_attempted"));
        assertEquals("1", jsonObject.get("question_correct"));
        assertDoesNotThrow(() -> LocalDateTime.parse((String) jsonObject.get("time"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    }

    @Test
    public void compareTo_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Score.of(1, 1).compareTo(null));
    }

    @Test
    public void compareTo() {
        try {
            Score t1 = Score.of(1, 1);
            TimeUnit.SECONDS.sleep(1);
            Score t2 = Score.of(1, 1);
            assertEquals(-1, t1.compareTo(t2));
            assertEquals(1, t2.compareTo(t1));
            ArrayList<Score> scores = new ArrayList<>();
            scores.add(t1);
            scores.add(t2);
            Collections.sort(scores);
            assertEquals(t1, scores.get(0));
            assertEquals(t2, scores.get(1));
        } catch (InterruptedException e) {
            assertTrue(false); // the test fails
        }
    }

    @Test
    public void toString_equalsToJsonString() {
        Score test = Score.of(1, 1);
        assertEquals(test.toString(), test.toJsonObject().toString());
    }

    @Test
    public void isSameAttempt_createImmediatelyAfter_returnsTrue() {
        Score s1 = Score.of(10, 10);
        Score s2 = Score.of(10, 10);
        assertTrue(s1.isSameAttempt(s2));
        // to show symmetry
        assertTrue(s2.isSameAttempt(s1));
    }

    @Test
    public void isSameAttempt_createWithDurationOneSecond_returnsFalse() {
        try {
            Score s1 = Score.of(10, 10);
            TimeUnit.SECONDS.sleep(1);
            Score s2 = Score.of(10, 10);
            assertFalse(s1.isSameAttempt(s2));
            // to show symmetry
            assertFalse(s2.isSameAttempt(s1));
        } catch (InterruptedException e) {
            assertTrue(false); // the test fails
        }
    }
}

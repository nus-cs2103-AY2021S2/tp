package seedu.flashback.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashback.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AnswerTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Answer(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new Answer(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Answer.isValidAnswer(null));

        // invalid phone numbers
        assertFalse(Answer.isValidAnswer("")); // empty string
        assertFalse(Answer.isValidAnswer(" ")); // spaces only


        // valid phone numbers
        assertTrue(Answer.isValidAnswer("Fantasy"));
        assertTrue(Answer.isValidAnswer("-")); // one character
        assertTrue(Answer.isValidAnswer("Single-level Abstraction Principle"));
    }
}

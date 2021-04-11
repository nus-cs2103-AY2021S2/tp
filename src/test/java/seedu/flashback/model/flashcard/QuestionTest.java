package seedu.flashback.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashback.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class QuestionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Question(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Question(invalidName));
    }

    @Test
    public void isValidQuestion() {
        // null question
        assertThrows(NullPointerException.class, () -> Question.isValidQuestion(null));

        // invalid name
        assertFalse(Question.isValidQuestion("")); // empty string
        assertFalse(Question.isValidQuestion(" ")); // spaces only

        // valid name
        assertTrue(Question.isValidQuestion("What is the genre of Harry Potter?")); // alphabets only
        assertTrue(Question.isValidQuestion("-")); // one character
        assertTrue(Question.isValidQuestion("Explain SLAP in one sentence")); // alphanumeric characters
    }
}

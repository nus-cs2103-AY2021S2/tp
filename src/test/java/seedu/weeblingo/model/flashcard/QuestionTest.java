package seedu.weeblingo.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.weeblingo.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class QuestionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Question(null));
    }

    @Test
    public void constructor_invalidquestion_throwsIllegalArgumentException() {
        String invalidquestion = "";
        assertThrows(IllegalArgumentException.class, () -> new Question(invalidquestion));
    }

    @Test
    public void isValidQuestion() {
        // null question
        assertThrows(NullPointerException.class, () -> Question.isValidQuestion(null));

        // blank question
        assertFalse(Question.isValidQuestion("")); // empty string
        assertFalse(Question.isValidQuestion(" ")); // spaces only

        // wrong language
        assertFalse(Question.isValidQuestion("hi")); // english word
        assertFalse(Question.isValidQuestion("hiあ")); // english mixed with japanese

        // valid questions
        assertTrue(Question.isValidQuestion("あ")); // hiragana
        assertTrue(Question.isValidQuestion("ア")); // katakana
        assertTrue(Question.isValidQuestion("あア")); // mix
        assertTrue(Question.isValidQuestion("零|れい")); // kanji with hiragana and special character
    }
}

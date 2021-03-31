package seedu.weeblingo.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
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

        //        // missing parts
        //        assertFalse(Question.isValidQuestion("@example.com")); // missing local part
        //        assertFalse(Question.isValidQuestion("peterjackexample.com")); // missing '@' symbol
        //        assertFalse(Question.isValidQuestion("peterjack@")); // missing domain name
        //
        //        // invalid parts
        //        assertFalse(Question.isValidQuestion("peterjack@-")); // invalid domain name
        //        assertFalse(Question.isValidQuestion("peterjack@exam_ple.com")); // underscore in domain name
        //        assertFalse(Question.isValidQuestion("peter jack@example.com")); // spaces in local part
        //        assertFalse(Question.isValidQuestion("peterjack@exam ple.com")); // spaces in domain name
        //        assertFalse(Question.isValidQuestion(" peterjack@example.com")); // leading space
        //        assertFalse(Question.isValidQuestion("peterjack@example.com ")); // trailing space
        //        assertFalse(Question.isValidQuestion("peterjack@@example.com")); // double '@' symbol
        //        assertFalse(Question.isValidQuestion("peter@jack@example.com")); // '@' symbol in local part
        //        assertFalse(Question.isValidQuestion("peterjack@example@com")); // '@' symbol in domain name
        //        assertFalse(Question.isValidQuestion("peterjack@.example.com")); // domain name starts with a period
        //        assertFalse(Question.isValidQuestion("peterjack@example.com.")); // domain name ends with a period
        //        assertFalse(Question.isValidQuestion("peterjack@-example.com")); // domain name starts with a hyphen
        //        assertFalse(Question.isValidQuestion("peterjack@example.com-")); // domain name ends with a hyphen
        //
        //        // valid question
        //        assertTrue(Question.isValidQuestion("PeterJack_1190@example.com"));
        //        assertTrue(Question.isValidQuestion("a@bc")); // minimal
        //        assertTrue(Question.isValidQuestion("test@localhost")); // alphabets only
        //        assertTrue(Question.isValidQuestion("!#$%&'*+/=?`{|}~^.-@example.org"));
        //        //above is special characters local part
        //        assertTrue(Question.isValidQuestion("123@145")); // numeric local part and domain name
        //        assertTrue(Question.isValidQuestion("a1+be!@example1.com"));
        //        //above is mixture of alphanumeric and special characters
        //        assertTrue(Question.isValidQuestion("peter_jack@very-very-very-long-example.com"));
        //        //above is long domain name
        //        assertTrue(Question.isValidQuestion("if.you.dream.it_you.can.do.it@example.com"));
        //        //above is long local part
    }
}

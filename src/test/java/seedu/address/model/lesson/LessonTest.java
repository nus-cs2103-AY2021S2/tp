package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LessonTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Lesson(null));
    }

    @Test
    public void constructor_invalidLesson_throwsIllegalArgumentException() {
        String invalidLesson = "";
        assertThrows(IllegalArgumentException.class, () -> new Lesson(invalidLesson));
    }

    @Test
    public void isValidLesson() {
        //null lesson
        assertThrows(NullPointerException.class, () -> Lesson.isValidLesson(null));

        // invalid lesson
        assertFalse(Lesson.isValidLesson(new String[] {"monday", "2300", "2359"})); // array contains more than 2 values
        assertFalse(Lesson.isValidLesson(new String[] {"monday"})); // array contains less than 2 values

        // valid lesson
        assertTrue(Lesson.isValidLesson(new String[] {"tuesday", "2000"})); // array contains 2 values
    }
}

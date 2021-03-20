package seedu.address.model.lesson;

import java.util.Objects;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Lesson {

    public static final String MESSAGE_CONSTRAINTS = "Lesson should only consist of two words - the lesson day, " +
            "followed by the lesson time \nExample: Monday 1500";
    public static final int INDEX_OF_DAY = 0;
    public static final int INDEX_OF_TIME = 1;
    public static final int SIZE_OF_ARRAY = 2;

    public final Day day;
    public final Time time;

    /**
     * Constructs a {@code Lesson}.
     *
     * @param lesson Lesson details consisting of day and time.
     */
    public Lesson(String lesson) {
        requireAllNonNull(lesson);
        String details[] = getDetails(lesson);
        checkArgument(isValidLesson(details), MESSAGE_CONSTRAINTS);
        this.day = new Day(details[INDEX_OF_DAY]);
        this.time = new Time(details[INDEX_OF_TIME]);
    }

    public Day getDay() {
        return day;
    }

    public String getDayInString() { return day.toString(); }

    public Time getTime() {
        return time;
    }

    /**
     * Separates the input string into individual strings for processing.
     *
     * @param input User input.
     * @return An array of strings for creation of a Lesson.
     */
    public static String[] getDetails(String input) {
        String trimmedInput = input.trim();
        String details[] = trimmedInput.split(" ");
        return details;
    }

    /**
     * Returns true if a given string is a valid lessonDetail.
     */
    public static boolean isValidLesson(String[] test) {
        if (test.length != SIZE_OF_ARRAY) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Lesson)) {
            return false;
        }

        Lesson otherLesson = (Lesson) other;
        return otherLesson.getDay().equals(getDay())
                && otherLesson.getTime().equals(getTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(day, time);
    }

    @Override
    public String toString() {
        return day.toString() + " " + time.toString();
    }


}

package seedu.weeblingo.model.score;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;

import seedu.weeblingo.MainApp;
import seedu.weeblingo.commons.core.LogsCenter;
import seedu.weeblingo.model.exceptions.EmptyStringInputException;
import seedu.weeblingo.model.exceptions.NullInputException;

/**
 * A score object represents an attempt of the quiz session; coded in a defensive manner.
 * It is uniquely identified by the time the attempt is finished.
 * How an attempt can be considered as complete, however, is not the
 * concern of this class, but should be the concern of application logic.
 */
public class Score implements Comparable<Score> {
    /** Global logger object */
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);
    /** The date and time when the score is awarded */
    private LocalDateTime datetime;
    /** The number of questions attempted */
    private Integer questionAttempted;
    /** The number of questions that were attempted correctly */
    private Integer questionCorrect;
    /** The duration string spent for the specific score */
    private String durationString;

    private Score(LocalDateTime datetime, Integer questionAttempted, Integer questionCorrect, String durationString) {
        if (questionAttempted == null || questionCorrect == null || durationString == null) {
            throw new NullInputException("Null value supplied to Score object");
        }
        if (questionAttempted < 0 || questionCorrect < 0) {
            throw new NullInputException("Non-positive value supplied to the number of questions attempted, or "
                    + "negative value supplied to number of questions attempted correctly.");
        }
        if (questionAttempted < questionCorrect) {
            throw new NullInputException("Questions attempted must be larger or equal to questions correct.");
        }
        if (durationString.matches("\\s*")) {
            throw new EmptyStringInputException();
        }

        try {
            DateTimeFormatter.ofPattern("HH:mm:ss").parse(durationString);
        } catch (DateTimeParseException e) {
            throw e;
        }

        this.datetime = datetime;
        this.questionAttempted = questionAttempted;
        this.questionCorrect = questionCorrect;
        this.durationString = durationString;
    }

    /**
     * Static method of creating a Score object.
     * Quiz attempt time is properly handled within this method.
     *
     * @param questionAttempted Number of questions attempted in the attempt. Must not be null or non-positive.
     * @param questionCorrect Number of questions attempted correctly in the attempt. Must not be null or negative.
     * @return A score object containing necessary information including the attributes supplied and
     * the time when the Score object is created.
     */
    public static Score of(Integer questionAttempted, Integer questionCorrect, String durationString) {
        Score result = new Score(LocalDateTime.now(), questionAttempted, questionCorrect, durationString);
        logger.info(String.format("Attempt record generated: %s.", result.toString()));
        return result;
    }

    /**
     * Static method of creating a Score object.
     * Quiz attempt time is properly handled within this method.
     *
     * @param datetime A LocalDatetime object equivalent to the LocalDatetime object created
     *                 when the quiz is initialized. Must not be null.
     * @param questionAttempted Number of questions attempted in the attempt. Must not be null or non-positive.
     * @param questionCorrect Number of questions attempted correctly in the attempt. Must not be null or negative.
     * @return A score object containing necessary information including the attributes supplied and
     * the time when the Score object is created.
     */
    public static Score of(LocalDateTime datetime, Integer questionAttempted, Integer questionCorrect,
                           String durationString) {
        if (datetime == null) {
            throw new NullInputException("Null value supplied to Score object");
        }
        Score result = new Score(datetime, questionAttempted, questionCorrect, durationString);
        logger.info(String.format("Attempt record stored: %s.", result.toString()));
        return result;
    }

    private Double getCorrectRatio() {
        assert questionAttempted != null;
        assert questionCorrect != null;
        assert questionCorrect >= 0;
        if (questionAttempted == 0) {
            return 0.0;
        } else {
            return questionCorrect * 1.0 / questionAttempted;
        }
    }

    /**
     * Get the correctness ratio of the Score object.
     *
     * @return The String representation of the correctness ration.
     * Example: 3/5 -> returns "60.000%"
     */
    public String getCorrectRatioString() {
        return String.format("%.3f", getCorrectRatio() * 100) + "%";
    }


    /**
     * Checks whether the two attempts encapsulated by the two Score object represents the same attempt.
     *
     * @param o The Score object to check whether it is the same as the current Score object or not.
     * @return True if both Score objects have the same LocalDateTime. If both objects are created with an
     * interval of >= 1 second, the two Score objects are considered as different Scores of attempts.
     */
    public boolean isSameAttempt(Score o) {
        if (o == null) {
            throw new NullInputException("Receiving Score object of Score::isSameAttempt cannot be null");
        }
        return this.datetime.equals(o.datetime);
    }

    /**
     * A getter that wraps the number of questions attempted fields in a string as return value.
     *
     * @return The String representation of the number of questions attempted in this Score object.
     */
    public String getNumberOfQuestionsAttempted() {
        return questionAttempted.toString();
    }

    /**
     * A getter that wraps the number of questions attempted correctly fields in a string as return value.
     *
     * @return The String representation of the number of questions attempted correctly in this Score object.
     */
    public String getNumberOfQuestionsCorrect() {
        return questionCorrect.toString();
    }

    /**
     * A getter that wraps the date and time when the score is awarded field in a string as return value.
     *
     * @return The String representation of the the date and time when the score is awarded in this Score object.
     */
    public String getCompletedTime() {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(datetime).toString();
    }

    /**
     * Overriden compareTo() method of Comparable interface.
     *
     * @param o The Score object to compare to.
     * @return 1 if the datetime of the current object is earlier
     * than datetime of the object to compare.
     */
    @Override
    public int compareTo(Score o) {
        return o.datetime.compareTo(this.datetime);
    }

    /**
     * Gets the string representing the time spent for the score representing an attempt.
     *
     * @return The string representation of the time spent for the score representing an attempt.
     */
    public String getTimeSpent() {
        return durationString;
    }
}

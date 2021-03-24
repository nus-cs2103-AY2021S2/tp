package seedu.weeblingo.model.score;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONObject;

/**
 * A score object represents an attempt of the quiz session; coded in a defensive manner.
 * It is uniquely identified by the time the attempt is finished.
 * How an attempt can be considered as complete, however, is not the
 * concern of this class, but should be the concern of application logic.
 */
public class Score implements Comparable<Score> {
    /** The date and time when the score is awarded */
    private LocalDateTime datetime;
    /** The number of questions attempted */
    private Integer questionAttempted;
    /** The number of questions that were attempted correctly */
    private Integer questionCorrect;

    private Score(LocalDateTime datetime, Integer questionAttempted, Integer questionCorrect) {
        this.datetime = datetime;
        this.questionAttempted = questionAttempted;
        this.questionCorrect = questionCorrect;
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
    public static Score of(Integer questionAttempted, Integer questionCorrect) {
        if (questionAttempted == null || questionCorrect == null) {
            throw new RuntimeException("Null value supplied to Score object");
        }
        if (questionAttempted <= 0 || questionCorrect < 0) {
            throw new RuntimeException("Non-positive value supplied to the number of questions attempted, or "
                + "negative value supplied to number of questions attempted correctly");
        }
        if (questionAttempted < questionCorrect) {
            throw new RuntimeException("Questions attempted must be larger or equal to questions correct");
        }
        return new Score(LocalDateTime.now(), questionAttempted, questionCorrect);
    }

    private Double getCorrectRatio() {
        assert questionAttempted != null;
        assert questionAttempted > 0;
        assert questionCorrect != null;
        assert questionCorrect >= 0;
        return questionCorrect * 1.0 / questionAttempted;
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
     * Transforms the Score object to a Json object to facilitate storage in the database.
     *
     * @return The json object representation of the Score object/
     */
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("time", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(datetime).toString());
        jsonObject.put("question_attempted", questionAttempted.toString());
        jsonObject.put("question_correct", questionCorrect.toString());
        jsonObject.put("ratio", getCorrectRatioString());
        return jsonObject;
    }

    /**
     * Overriden toString() method of Score object.
     *
     * @return The string representation of Score object. In Json String format.
     */
    @Override
    public String toString() {
        return toJsonObject().toString();
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
        if (o == null) {
            throw new RuntimeException("Null value supplied as score object");
        }
        return datetime.compareTo(o.datetime);
    }
}

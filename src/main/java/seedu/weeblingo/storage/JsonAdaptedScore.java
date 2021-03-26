package seedu.weeblingo.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.weeblingo.model.score.Score;

/**
 * Jackson-friendly version of {@link Score}.
 */
public class JsonAdaptedScore {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Score's %s field is missing!";

    private final String time;
    private final String questionAttempted;
    private final String questionCorrect;
    private final String ratio;

    /**
     * Constructs a {@code JsonAdaptedScore} with the given score details.
     */
    @JsonCreator
    public JsonAdaptedScore(@JsonProperty("time") String time,
                                @JsonProperty("question_attempted") String questionAttempted,
                                @JsonProperty("question_correct") String questionCorrect,
                                @JsonProperty("ratio") String ratio) {
        this.time = time;
        this.questionAttempted = questionAttempted;
        this.questionCorrect = questionCorrect;
        this.ratio = ratio;
    }

    /**
     * Converts a given {@code Score} into this class for Jackson use.
     */
    public JsonAdaptedScore(Score score) {
        this.time = score.getCompletedTime();
        this.questionAttempted = score.getNumberOfQuestionsAttempted();
        this.questionCorrect = score.getNumberOfQuestionsCorrect();
        this.ratio = score.getCorrectRatioString();
    }
}

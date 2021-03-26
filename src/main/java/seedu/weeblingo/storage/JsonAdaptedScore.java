package seedu.weeblingo.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.weeblingo.commons.exceptions.IllegalValueException;
import seedu.weeblingo.model.score.Score;

/**
 * Jackson-friendly version of {@link Score}.
 */
public class JsonAdaptedScore {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Score's %s field is missing!";
    public static final String DATETIME_FORMAT_INCORRECT = "Incorrect datetime format. "
            + "\"dd-MM-yyyy HH:mm:ss\" expected.";
    public static final String NUMBER_FORMAT_INCORRECT = "Questions attempted or questions correct"
            + "must be non-negative integers.";

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

    /**
     * Converts this Jackson-friendly adapted score object into the model's {@code Score} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted flashcard.
     */
    public Score toModelType() throws IllegalValueException {
        if (this.time == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Completed time"));
        }
        try {
            final LocalDateTime time = LocalDateTime.parse(this.time,
                    DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

            if (this.questionAttempted == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Question attempted"));
            }
            Integer questionAttempted = Integer.valueOf(this.questionAttempted);

            if (questionCorrect == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Question correct"));
            }
            Integer questionCorrect = Integer.valueOf(this.questionAttempted);

            return Score.of(time, questionAttempted, questionCorrect);
        } catch (NumberFormatException e) {
            throw new IllegalValueException(NUMBER_FORMAT_INCORRECT);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(DATETIME_FORMAT_INCORRECT);
        }
    }
}

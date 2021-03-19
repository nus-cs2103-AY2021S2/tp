package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.LocalDateTimeUtil;
import seedu.address.model.module.Exam;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Exam}.
 */
class JsonAdaptedExam {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Exam's %s field is missing!";
    private static final Logger logger = LogsCenter.getLogger(JsonAdaptedExam.class);
    public final String examDate;
    public final String tag;

    /**
     * Constructs a {@code JsonAdaptedExam} with the given {@code examDate} and {@code tag}.
     */
    @JsonCreator
    public JsonAdaptedExam(@JsonProperty("examDate") String examDate,
                           @JsonProperty("tag") String tag) {
        this.examDate = examDate;
        this.tag = tag;
    }

    /**
     * Converts a given {@code source} into this class for Jackson use.
     */
    public JsonAdaptedExam(Exam source) {
        examDate = source.examDate.format(LocalDateTimeUtil.DATETIME_FORMATTER);
        tag = source.getTag().tagName;
    }

    /**
     * Converts this Jackson-friendly adapted exam object into the model's {@code assignment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted assignment.
     */
    public Exam toModelType() throws IllegalValueException {
        if (examDate == null || !LocalDateTimeUtil.isValidDateTime(examDate)) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName()));
        }

        final LocalDateTime modelExamDate = LocalDateTime.parse(examDate,
                LocalDateTimeUtil.DATETIME_FORMATTER);

        if (tag == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Tag.class.getSimpleName()));
        }
        final Tag modelTag;
        if (!Tag.isValidTagName(tag)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        } else {
            modelTag = new Tag(tag);
        }


        return new Exam(modelExamDate, modelTag);
    }

}

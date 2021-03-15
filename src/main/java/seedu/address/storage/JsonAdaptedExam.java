package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.LocalDateTimeUtil;
import seedu.address.model.module.Exam;

/**
 * Jackson-friendly version of {@link Exam}.
 */
class JsonAdaptedExam {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Assignment's %s field is missing!";
    private static final Logger logger = LogsCenter.getLogger(JsonAdaptedExam.class);
    public final String examDate;

    /**
     * Constructs a {@code JsonAdaptedExam} with the given {@code deadline}.
     */
    @JsonCreator
    public JsonAdaptedExam(@JsonProperty("deadline") String examDate) {
        this.examDate = examDate;
    }

    /**
     * Converts a given {@code source} into this class for Jackson use.
     */
    public JsonAdaptedExam(Exam source) {
        if (source != null) {
            examDate = source.examDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        } else {
            examDate = "Empty";
        }
    }


    @JsonValue
    public String getExamDate() {
        return examDate;
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

        return new Exam(modelExamDate);
    }

}

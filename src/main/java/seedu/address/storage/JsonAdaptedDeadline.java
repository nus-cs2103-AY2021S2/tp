package seedu.address.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.DateConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.DateUtil;
import seedu.address.model.task.CompletableDeadline;
import seedu.address.model.task.deadline.Deadline;

/**
 * Jackson-friendly version of {@link CompletableDeadline}.
 */
class JsonAdaptedDeadline {
    private final String description;
    private final String by;
    private final Boolean isDone;

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given {@code description}, {@code interval},
     * {@code at} and {@code isDone}.
     */
    @JsonCreator
    public JsonAdaptedDeadline(@JsonProperty("description")String description,
                               @JsonProperty("date") String by,
                               @JsonProperty("isDone") Boolean isDone) {
        this.description = description;
        this.by = by;
        this.isDone = isDone;
    }

    /**
     * Converts a given {@code Deadline} into this class for Jackson use.
     */
    public JsonAdaptedDeadline(CompletableDeadline source) {
        description = source.getDescription();
        by = source.getStringByDate();
        isDone = source.getIsDone();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Deadline} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted event.
     */
    public CompletableDeadline toModelType() throws IllegalValueException {
        try {
            LocalDate date = DateUtil.encodeDate(by);
            return new Deadline(description, date , isDone);
        } catch (DateConversionException e) {
            throw new IllegalValueException(e.getMessage());
        }
    }

    private LocalDate parseDate(String date) throws IllegalValueException {
        try {
            return DateUtil.encodeDate(date);
        } catch (DateConversionException e) {
            // TODO: update e.getMessage with date constraints
            throw new IllegalValueException(e.getMessage());
        }
    }

}

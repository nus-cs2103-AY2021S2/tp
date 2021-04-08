package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.common.Description;
import seedu.address.model.common.Title;
import seedu.address.model.schedule.Schedule;

public class JsonAdaptedSchedule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Schedule's %s field"
            + " is missing!";
    private final String description;
    private final String timeFrom;
    private final String timeTo;
    private final String title;

    /**
     * Primary Constructor to create Json Adapted Schedule
     */
    @JsonCreator
    public JsonAdaptedSchedule(@JsonProperty("title") String title,
                               @JsonProperty("description") String description,
                               @JsonProperty("timeFrom") String timeFrom,
                               @JsonProperty("timeTo") String timeTo) {
        this.title = title;
        this.description = description;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    /**
     * Converts a given {@code Schedule} into this class for Jackson use.
     */
    public JsonAdaptedSchedule(Schedule schedule) {
        this.title = schedule.getTitle().value;
        this.description = schedule.getDescription().value;
        this.timeFrom = schedule.getTimeFrom().toStorageString();
        this.timeTo = schedule.getTimeTo().toStorageString();
    }


    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code
     * Schedule } object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Schedule toModelType() throws IllegalValueException {
        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (timeFrom == null || timeTo == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AppointmentDateTime.class.getSimpleName()));
        }

        if (!AppointmentDateTime.isValidDateTime(timeFrom)
                || !AppointmentDateTime.isValidDateTime(timeTo)) {
            throw new IllegalValueException(AppointmentDateTime.MESSAGE_CONSTRAINTS);
        }

        final AppointmentDateTime fromDateTime = new AppointmentDateTime(timeFrom);
        final AppointmentDateTime toDateTime = new AppointmentDateTime(timeTo);

        return new Schedule(modelTitle, fromDateTime, toDateTime, modelDescription);
    }
}

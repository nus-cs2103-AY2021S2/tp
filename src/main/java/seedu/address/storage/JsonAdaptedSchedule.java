package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.schedule.DateTime;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleDescription;
import seedu.address.model.tag.Tag;

public class JsonAdaptedSchedule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Schedule's %s field is missing!";

    private final String scheduleDescription;
    private final String startDateTime;
    private final String endDateTime;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedSchedule} with the given schedule details.
     */
    @JsonCreator
    public JsonAdaptedSchedule(@JsonProperty("scheduleDescription") String scheduleDescription,
                               @JsonProperty("startDateTime") String startDateTime,
                               @JsonProperty("endDateTime") String endDateTime,
                               @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.scheduleDescription = scheduleDescription;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * converts a given {@code Schedule} into this class for Jackson use
     * @param source
     */
    public JsonAdaptedSchedule(Schedule source) {
        scheduleDescription = source.getScheduleDescription().description;
        startDateTime = source.getStartDate().toString();
        endDateTime = source.getEndDate().toString();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted schedule object into the model's {@code Schedule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Schedule toModelType() throws IllegalValueException {
        final List<Tag> scheduleTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            scheduleTags.add(tag.toModelType());
        }
        if (scheduleDescription == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, ScheduleDescription.class.getSimpleName()));
        }
        if (!ScheduleDescription.isValidName(scheduleDescription)) {
            throw new IllegalValueException(ScheduleDescription.MESSAGE_CONSTRAINTS);
        }
        final ScheduleDescription modelScheduleDescription = new ScheduleDescription(scheduleDescription);

        if (startDateTime == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, DateTime.class.getSimpleName()));
        }
        final DateTime modelStartDateTime = new DateTime(startDateTime);

        if (endDateTime == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, DateTime.class.getSimpleName()));
        }
        final DateTime modelEndDateTime = new DateTime(endDateTime);

        final Set<Tag> modelTags = new HashSet<>(scheduleTags);
        return new Schedule(modelScheduleDescription, modelStartDateTime, modelEndDateTime, modelTags);
    }

}

package seedu.address.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleDescription;
import seedu.address.model.tag.Tag;

public class JsonAdaptedSchedule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Schedule's %s field is missing!";

    private final String scheduleDescription;
    private final String startDate;
    private final String endDate;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedSchedule} with the given schedule details.
     */
    @JsonCreator
    public JsonAdaptedSchedule(@JsonProperty("scheduleDescription") String scheduleDescription,
                               @JsonProperty("startDate") String startDate,
                               @JsonProperty("endDate") String endDate,
                               @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.scheduleDescription = scheduleDescription;
        this.startDate = startDate;
        this.endDate = endDate;
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
        startDate = source.getStartDate().toString();
        endDate = source.getEndDate().toString();
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

        if (startDate == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalDate.class.getSimpleName()));
        }
        final LocalDate modelStartDate = LocalDate.parse(startDate);

        if (endDate == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalDate.class.getSimpleName()));
        }
        final LocalDate modelEndDate = LocalDate.parse(endDate);

        final Set<Tag> modelTags = new HashSet<>(scheduleTags);
        return new Schedule(modelScheduleDescription, modelStartDate, modelEndDate, modelTags);
    }

}

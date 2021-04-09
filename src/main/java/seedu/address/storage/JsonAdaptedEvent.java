package seedu.address.storage;

import static seedu.address.commons.core.Messages.MESSAGE_END_DATETIME_BEFORE_START_DATETIME;
import static seedu.address.commons.core.Messages.MESSAGE_PAST_EVENT_END_DATE_TIME;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.common.Category;
import seedu.address.model.common.Date;
import seedu.address.model.common.Name;
import seedu.address.model.common.Tag;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDateTimePastPredicate;
import seedu.address.model.event.EventEndDateTimeValidPredicate;
import seedu.address.model.event.Time;


/**
 * Jackson-friendly version of {@link JsonAdaptedEvent}.
 */
class JsonAdaptedEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Event's %s field is missing!";

    private final String name;
    private final String startDate;
    private final String startTime;
    private final String endDate;
    private final String endTime;
    private final List<JsonAdaptedCategory> category = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTask} with the given event details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("name") String name, @JsonProperty("startDate") String startDate,
                           @JsonProperty("startTime") String startTime,
                           @JsonProperty("endDate") String endDate, @JsonProperty("endTime") String endTime,
                           @JsonProperty("category") List<JsonAdaptedCategory> category,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        if (category != null) {
            this.category.addAll(category);
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        name = source.getName().toString();
        startDate = source.getStartDate().toString();
        startTime = source.getStartTime().toString();
        endDate = source.getEndDate().toString();
        endTime = source.getEndTime().toString();
        category.addAll(source.getCategories().stream()
                .map(JsonAdaptedCategory::new)
                .collect(Collectors.toList()));
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted event.
     */
    public Event toModelType() throws IllegalValueException {
        final List<Category> eventCategories = new ArrayList<>();
        final List<Tag> eventTags = new ArrayList<>();

        for (JsonAdaptedTag tag : tagged) {
            eventTags.add(tag.toModelType());
        }

        for (JsonAdaptedCategory cat : category) {
            eventCategories.add(cat.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (startDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(startDate)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelStartDate = new Date(startDate);

        if (!Time.isValidTime(startTime)) {
            throw new IllegalValueException(Time.MESSAGE_CONSTRAINTS);
        }
        final Time modelStartTime = new Time(startTime);

        if (endDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(endDate)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelEndDate = new Date(endDate);

        if (!Time.isValidTime(endTime)) {
            throw new IllegalValueException(Time.MESSAGE_CONSTRAINTS);
        }
        final Time modelEndTime = new Time(endTime);

        //is end date time valid check
        if (!new EventDateTimePastPredicate().test(modelEndDate, modelEndTime)) {
            throw new IllegalValueException(MESSAGE_PAST_EVENT_END_DATE_TIME);
        }

        //is start date time before end date time check
        if (!new EventEndDateTimeValidPredicate(modelStartDate, modelStartTime)
                .test(modelEndDate, modelEndTime)) {
            throw new IllegalValueException(MESSAGE_END_DATETIME_BEFORE_START_DATETIME);
        }

        final Set<Tag> modelTags = new HashSet<>(eventTags);
        final Set<Category> modelCategories = new HashSet<>(eventCategories);

        return new Event(modelName, modelStartDate, modelStartTime,
                modelEndDate, modelEndTime, modelCategories, modelTags);
    }

}

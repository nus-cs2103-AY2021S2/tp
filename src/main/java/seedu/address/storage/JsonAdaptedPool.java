package seedu.address.storage;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.driver.Driver;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.pool.Pool;
import seedu.address.model.pool.TripDay;
import seedu.address.model.pool.TripTime;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Pool}.
 */
class JsonAdaptedPool {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Pool's %s field is missing!";


    private final String tripDayStr;
    private final String tripTimeStr;
    private final List<JsonAdaptedPassenger> passengers;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final JsonAdaptedDriver driver;

    /**
     * Constructs a {@code JsonAdaptedPool} with the given fields.
     */
    @JsonCreator
    public JsonAdaptedPool(@JsonProperty("tripDay") String tripDayStr, @JsonProperty("tripTime") String tripTimeStr,
                           @JsonProperty("passengers") List<JsonAdaptedPassenger> passengers,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                           @JsonProperty("driver") JsonAdaptedDriver driver) {
        this.tripDayStr = tripTimeStr;
        this.tripTimeStr = tripTimeStr;
        this.passengers = passengers;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.driver = driver;
    }

    /**
     * Converts a given {@code Pool} into this class for Jackson use.
     */
    public JsonAdaptedPool(Pool source) {
        this.tripDayStr = source.getTripDayAsStr();
        this.tripTimeStr = source.getTripTimeAsStr();
        this.passengers = source.getPassengers().stream()
                .map(JsonAdaptedPassenger::new)
                .collect(Collectors.toList());
        this.driver = new JsonAdaptedDriver(source.getDriver());
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Pool toModelType() throws IllegalValueException {
        final Set<Passenger> modelPassengers = new HashSet<>();
        for (JsonAdaptedPassenger passenger : passengers) {
            modelPassengers.add(passenger.toModelType());
        }

        final Set<Tag> modelTags = new HashSet<>();
        for (JsonAdaptedTag tag : tagged) {
            modelTags.add(tag.toModelType());
        }

        final Driver modelDriver = driver.toModelType();

        if (tripDayStr == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, TripDay.class.getSimpleName()));
        }
        DayOfWeek day;
        try {
            day = DayOfWeek.valueOf(tripDayStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(TripDay.MESSAGE_CONSTRAINTS);
        }
        final TripDay modelTripDay = new TripDay(day);

        if (tripTimeStr == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TripTime.class.getSimpleName()));
        }
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");
        LocalTime parsedTimeObject;
        try {
            parsedTimeObject = LocalTime.parse(tripTimeStr, timeFormat);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(TripTime.MESSAGE_CONSTRAINTS);
        }
        final TripTime modelTripTime = new TripTime(parsedTimeObject);

        return new Pool(modelDriver, modelTripDay, modelTripTime, modelPassengers, modelTags);
    }

}

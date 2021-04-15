package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.TripDay;
import seedu.address.model.TripTime;
import seedu.address.model.person.driver.Driver;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.pool.Pool;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Pool}.
 */
class JsonAdaptedPool {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Pool's %s field is missing!";
    public static final String MODEL_CLASS_NAME = "Pool";
    public static final StorageUtil MODEL_UTIL = new StorageUtil(MODEL_CLASS_NAME);


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
        this.tripDayStr = tripDayStr;
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
        this.tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        this.driver = new JsonAdaptedDriver(source.getDriver());
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Pool toModelType() throws IllegalValueException {
        final Driver modelDriver = driver.toModelType();

        final TripDay modelTripDay = MODEL_UTIL.verifyAndReturnTripDay(tripDayStr);
        final TripTime modelTripTime = MODEL_UTIL.verifyAndReturnTripTime(tripTimeStr);

        final List<Passenger> modelPassengers = MODEL_UTIL.convertAdaptedPassengersToModel(passengers);
        final Set<Tag> modelTags = MODEL_UTIL.convertAdaptedTagsToModel(tagged);

        return new Pool(modelDriver, modelTripDay, modelTripTime, modelPassengers, modelTags);
    }

}

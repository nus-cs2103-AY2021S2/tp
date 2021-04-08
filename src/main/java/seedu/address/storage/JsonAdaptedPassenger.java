package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.TripDay;
import seedu.address.model.TripTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.passenger.Address;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.person.passenger.Price;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Passenger}.
 */
class JsonAdaptedPassenger {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Passenger's %s field is missing!";
    public static final String MODEL_CLASS_NAME = "Passenger";
    public static final StorageUtil MODEL_UTIL = new StorageUtil(MODEL_CLASS_NAME);

    private final String name;
    private final String phone;
    private final String address;
    private final String tripDayStr;
    private final String tripTimeStr;
    private final String priceStr;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedPassenger} with the given passenger details.
     */
    @JsonCreator
    public JsonAdaptedPassenger(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                                @JsonProperty("address") String address, @JsonProperty("tripDay") String tripDayStr,
                                @JsonProperty("tripTime") String tripTimeStr, @JsonProperty("price") String priceStr,
                                @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.tripDayStr = tripDayStr;
        this.tripTimeStr = tripTimeStr;
        this.priceStr = priceStr;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Passenger} into this class for Jackson use.
     */
    public JsonAdaptedPassenger(Passenger source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        address = source.getAddress().value;
        tripDayStr = source.getTripDayAsStr();
        tripTimeStr = source.getTripTimeAsStr();
        priceStr = source.getPrice().map(Price::toString).orElse("");
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted passenger object into the model's {@code Passenger} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted passenger.
     */
    public Passenger toModelType() throws IllegalValueException {
        final Set<Tag> modelTags = MODEL_UTIL.convertAdaptedTagsToModel(tagged);

        final Name modelName = MODEL_UTIL.verifyAndReturnName(name);
        final Phone modelPhone = MODEL_UTIL.verifyAndReturnPhone(phone);
        final Address modelAddress = MODEL_UTIL.verifyAndReturnAddress(address);
        final TripDay modelTripDay = MODEL_UTIL.verifyAndReturnTripDay(tripDayStr);
        final TripTime modelTripTime = MODEL_UTIL.verifyAndReturnTripTime(tripTimeStr);

        final Optional<Price> modelPrice = Optional.ofNullable(MODEL_UTIL.verifyAndReturnPrice(priceStr));


        return new Passenger(modelName, modelPhone, modelAddress, modelTripDay, modelTripTime, modelPrice,
                modelTags);
    }

}

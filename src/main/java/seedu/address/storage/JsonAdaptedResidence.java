package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.booking.Booking;
import seedu.address.model.residence.BookingList;
import seedu.address.model.residence.Residence;
import seedu.address.model.residence.ResidenceAddress;
import seedu.address.model.residence.ResidenceName;
import seedu.address.model.tag.CleanStatusTag;
import seedu.address.model.tag.Tag;


public class JsonAdaptedResidence {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Residence's %s field is missing!";

    private final String residenceName;
    private final String residenceAddress;
    private final List<JsonAdaptedBooking> bookingList = new ArrayList<>();
    private final String cleanStatusTag;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedResidence} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedResidence(@JsonProperty("name") String residenceName,
                                @JsonProperty("address") String residenceAddress,
                                @JsonProperty("booking") List<JsonAdaptedBooking> bookings,
                                @JsonProperty("cleanStatusTag") String cleanStatusTag,
                                @JsonProperty("tag") List<JsonAdaptedTag> tags) {
        this.residenceName = residenceName;
        this.residenceAddress = residenceAddress;
        this.cleanStatusTag = cleanStatusTag;
        if (bookings != null) {
            this.bookingList.addAll(bookings);
        }
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code Residence} into this class for Json use.
     */
    public JsonAdaptedResidence(Residence source) {
        residenceName = source.getResidenceName().getValue();
        residenceAddress = source.getResidenceAddress().getValue();
        cleanStatusTag = source.getCleanStatusTag().getValue();
        bookingList.addAll(source.getBookingList().getValue().stream()
                .map(JsonAdaptedBooking::new)
                .collect(Collectors.toList()));
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Json-friendly adapted person object into the model's {@code Residence} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Residence toModelType() throws IllegalValueException {
        final List<Tag> residenceTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            residenceTags.add(tag.toModelType());
        }

        final List<Booking> residenceBookingList = new ArrayList<>();
        for (JsonAdaptedBooking booking : bookingList) {
            residenceBookingList.add(booking.toModelType());
        }

        if (residenceName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ResidenceName.class.getSimpleName()));
        }
        if (!ResidenceName.isValidResidenceName(residenceName)) {
            throw new IllegalValueException(ResidenceName.MESSAGE_CONSTRAINTS);
        }
        final ResidenceName modelName = new ResidenceName(residenceName);

        if (residenceAddress == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ResidenceAddress.class.getSimpleName()));
        }
        if (!ResidenceAddress.isValidResidenceAddress(residenceAddress)) {
            throw new IllegalValueException(ResidenceAddress.MESSAGE_CONSTRAINTS);
        }
        final ResidenceAddress modelAddress = new ResidenceAddress(residenceAddress);

        //might need to do valid and null check for booking details but skip first
        final BookingList modelBookingList = new BookingList(residenceBookingList);

        final CleanStatusTag modelCleanStatusTag = new CleanStatusTag(cleanStatusTag);

        final Set<Tag> modelTags = new HashSet<>(residenceTags);
        return new Residence(modelName, modelAddress, modelBookingList, modelCleanStatusTag, modelTags);
    }
}

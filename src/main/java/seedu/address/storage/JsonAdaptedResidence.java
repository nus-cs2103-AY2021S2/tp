package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.residence.BookingDetails;
import seedu.address.model.residence.Residence;
import seedu.address.model.residence.ResidenceAddress;
import seedu.address.model.residence.ResidenceName;
import seedu.address.model.tag.CleanStatusTag;
import seedu.address.model.tag.Tag;


public class JsonAdaptedResidence {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Residence's %s field is missing!";

    private final String residenceName;
    private final String residenceAddress;
    private final String bookingDetails;
    private final String cleanStatusTagged;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedResidence} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedResidence(@JsonProperty("name") String residenceName,
                                @JsonProperty("address") String residenceAddress,
                                @JsonProperty("bookingDetails") String bookingDetails,
                                @JsonProperty("cleanStatusTagged") String cleanStatusTagged,
                                @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.residenceName = residenceName;
        this.residenceAddress = residenceAddress;
        this.bookingDetails = bookingDetails;
        this.cleanStatusTagged = cleanStatusTagged;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Residence} into this class for Json use.
     */
    public JsonAdaptedResidence(Residence source) {
        residenceName = source.getResidenceName().fullName;
        residenceAddress = source.getResidenceAddress().value;
        bookingDetails = source.getBookingDetails().value;
        cleanStatusTagged = source.getCleanStatusTag().cleanStatus;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Json-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Residence toModelType() throws IllegalValueException {
        final List<CleanStatusTag> residenceCleanStatusTag = new ArrayList<>();
        final List<Tag> residenceTags = new ArrayList<>();
        for (String cleanStatusTag : cleanStatusTagged) {
            residenceCleanStatusTag.add(cleanStatusTag.toModelType());
        }
        for (JsonAdaptedTag tag : tagged) {
            residenceTags.add(tag.toModelType());
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
        final BookingDetails modelBookingDetails = new BookingDetails(bookingDetails);

        final Set<CleanStatusTag> modelCleanStatusTag = new HashSet<>(residenceCleanStatusTag);
        final Set<Tag> modelTags = new HashSet<>(residenceTags);
        return new Residence(modelName, modelAddress, modelBookingDetails, modelCleanStatusTag, modelTags);
    }





}

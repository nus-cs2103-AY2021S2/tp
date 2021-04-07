package seedu.iscam.storage.client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.iscam.commons.exceptions.IllegalValueException;
import seedu.iscam.model.client.Client;
import seedu.iscam.model.client.Email;
import seedu.iscam.model.client.Image;
import seedu.iscam.model.client.InsurancePlan;
import seedu.iscam.model.client.Phone;
import seedu.iscam.model.commons.Location;
import seedu.iscam.model.commons.Name;
import seedu.iscam.model.commons.Tag;

/**
 * Jackson-friendly version of {@link Client}.
 */
class JsonAdaptedClient {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Client's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final List<JsonAdaptedClientPlan> plans = new ArrayList<>();
    private final String location;
    private final List<JsonAdaptedClientTag> tagged = new ArrayList<>();
    private final String imageRes;

    /**
     * Constructs a {@code JsonAdaptedClient} with the given client details.
     */
    @JsonCreator
    public JsonAdaptedClient(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("location") String location,
            @JsonProperty("plan") List<JsonAdaptedClientPlan> plans,
            @JsonProperty("tagged") List<JsonAdaptedClientTag> tagged,
            @JsonProperty("image") String imageRes) {
        this.name = name;
        this.phone = phone;
        this.email = email;

        if (plans != null) {
            this.plans.addAll(plans);
        }

        this.location = location;

        if (tagged != null) {
            this.tagged.addAll(tagged);
        }

        this.imageRes = imageRes;
    }

    /**
     * Converts a given {@code Client} into this class for Jackson use.
     */
    public JsonAdaptedClient(Client source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        plans.addAll(source.getPlan().stream()
                .map(JsonAdaptedClientPlan::new)
                .collect(Collectors.toList()));
        location = source.getLocation().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedClientTag::new)
                .collect(Collectors.toList()));
        imageRes = source.getImageRes().value;
    }

    /**
     * Converts this Jackson-friendly adapted client object into the model's {@code Client} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted client.
     */
    public Client toModelType() throws IllegalValueException {
        final List<InsurancePlan> clientPlans = new ArrayList<>();
        final List<Tag> clientTags = new ArrayList<>();
        for(JsonAdaptedClientPlan plan : plans) {
            clientPlans.add(plan.toModelType());
        }
        for (JsonAdaptedClientTag tag : tagged) {
            clientTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_TYPE_CONSTRAINTS);
        }
        if (!Name.isValidLength(name)) {
            throw new IllegalValueException(Name.MESSAGE_LENGTH_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhoneLength(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_LENGTH_CONSTRAINTS);
        }
        if (!Phone.isValidNumbersOnly(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_INPUT_CONSTRAINTS);
        }
        if (!Phone.isValidPhoneNumber(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_STARTING_DIGIT_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        if (!Email.isValidLength(email)) {
            throw new IllegalValueException(Email.MESSAGE_LENGTH_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Location.class.getSimpleName()));
        }
        if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(String.format(Location.MESSAGE_CONSTRAINTS,
                    Location.class.getSimpleName()));
        }
        if (!Location.isValidLength(location)) {
            throw new IllegalValueException(Location.MESSAGE_LENGTH_CONSTRAINTS);
        }
        final Location modelLocation = new Location(location);

        final Set<InsurancePlan> modelPlans = new HashSet<>(clientPlans);

        final Set<Tag> modelTags = new HashSet<>(clientTags);

        if (imageRes == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Image.class.getSimpleName()));
        }
        if (!Image.isValidImageRes(imageRes)) {
            throw new IllegalValueException(String.format(Image.MESSAGE_CONSTRAINTS,
                    Image.class.getSimpleName()));
        }
        final Image modelImage = new Image(imageRes);

        return new Client(modelName, modelPhone, modelEmail, modelLocation, modelPlans, modelTags, modelImage);
    }

}

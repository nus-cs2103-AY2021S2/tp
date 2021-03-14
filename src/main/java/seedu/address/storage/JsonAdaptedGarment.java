package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.description.Description;
<<<<<<< HEAD:src/main/java/seedu/address/storage/JsonAdaptedGarment.java
import seedu.address.model.garment.Address;
import seedu.address.model.garment.Colour;
import seedu.address.model.garment.Garment;
import seedu.address.model.garment.Name;
import seedu.address.model.garment.Size;
=======
import seedu.address.model.person.Colour;
import seedu.address.model.person.DressCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Size;
>>>>>>> a619ff895d28ff145a1161fbe2a7e1656407e12f:src/main/java/seedu/address/storage/JsonAdaptedPerson.java

/**
 * Jackson-friendly version of {@link Garment}.
 */
class JsonAdaptedGarment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Garment's %s field is missing!";

    private final String name;
    private final String size;
    private final String colour;
    private final String dresscode;
    private final List<JsonAdaptedDescription> descriptions = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedGarment} with the given garment details.
     */
    @JsonCreator
<<<<<<< HEAD:src/main/java/seedu/address/storage/JsonAdaptedGarment.java
    public JsonAdaptedGarment(@JsonProperty("name") String name, @JsonProperty("size") String size,
            @JsonProperty("colour") String colour, @JsonProperty("address") String address,
=======
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("size") String size,
            @JsonProperty("colour") String colour, @JsonProperty("dresscode") String dresscode,
>>>>>>> a619ff895d28ff145a1161fbe2a7e1656407e12f:src/main/java/seedu/address/storage/JsonAdaptedPerson.java
            @JsonProperty("addedDescriptions") List<JsonAdaptedDescription> addedDescriptions) {
        this.name = name;
        this.size = size;
        this.colour = colour;
        this.dresscode = dresscode;
        if (addedDescriptions != null) {
            this.descriptions.addAll(addedDescriptions);
        }
    }

    /**
     * Converts a given {@code Garment} into this class for Jackson use.
     */
    public JsonAdaptedGarment(Garment source) {
        name = source.getName().fullName;
        size = source.getSize().value;
        colour = source.getColour().colour;
        dresscode = source.getDressCode().value;
        descriptions.addAll(source.getDescriptions().stream()
                .map(JsonAdaptedDescription::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted garment object into the model's {@code Garment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted garment.
     */
    public Garment toModelType() throws IllegalValueException {
        final List<Description> garmentDescriptions = new ArrayList<>();
        for (JsonAdaptedDescription description : descriptions) {
            garmentDescriptions.add(description.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (size == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Size.class.getSimpleName()));
        }
        if (!Size.isValidSize(size)) {
            throw new IllegalValueException(Size.MESSAGE_CONSTRAINTS);
        }
        final Size modelSize = new Size(size);

        if (colour == null || !Colour.isValidColour(colour)) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Colour.class.getSimpleName()));
        }
        final Colour modelColour = new Colour(colour);

        if (dresscode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DressCode.class.getSimpleName()));
        }
        if (!DressCode.isValidDressCode(dresscode)) {
            throw new IllegalValueException(DressCode.MESSAGE_CONSTRAINTS);
        }
        final DressCode modelAddress = new DressCode(dresscode);

        final Set<Description> modelDescriptions = new HashSet<>(garmentDescriptions);
        return new Garment(modelName, modelSize, modelColour, modelAddress, modelDescriptions);
    }

}

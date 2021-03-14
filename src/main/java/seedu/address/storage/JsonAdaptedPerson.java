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
import seedu.address.model.person.Colour;
import seedu.address.model.person.DressCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Size;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String size;
    private final String colour;
    private final String dresscode;
    private final List<JsonAdaptedDescription> descriptions = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("size") String size,
            @JsonProperty("colour") String colour, @JsonProperty("dresscode") String dresscode,
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
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        size = source.getSize().value;
        colour = source.getColour().colour;
        dresscode = source.getDressCode().value;
        descriptions.addAll(source.getDescriptions().stream()
                .map(JsonAdaptedDescription::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
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
        return new Person(modelName, modelSize, modelColour, modelAddress, modelDescriptions);
    }

}

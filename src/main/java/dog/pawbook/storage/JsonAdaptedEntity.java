package dog.pawbook.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import dog.pawbook.commons.exceptions.IllegalValueException;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.program.Program;
import dog.pawbook.model.managedentity.tag.Tag;
import javafx.util.Pair;

/**
 * Jackson-friendly version of {@link Entity}.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = JsonAdaptedOwner.class, name = Owner.ENTITY_WORD),
    @JsonSubTypes.Type(value = JsonAdaptedDog.class, name = Dog.ENTITY_WORD),
    @JsonSubTypes.Type(value = JsonAdaptedProgram.class, name = Program.ENTITY_WORD)
})
abstract class JsonAdaptedEntity {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Entities' %s field is missing!";

    protected final int id;
    protected final String name;
    protected final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedEntity} with the given owner details.
     */
    @JsonCreator
    public JsonAdaptedEntity(@JsonProperty("id") Integer id, @JsonProperty("name") String name,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.id = id;
        this.name = name;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Entity} into this class for Jackson use.
     */
    public JsonAdaptedEntity(Pair<Integer, ? extends Entity> idEntityPair) {
        this.id = idEntityPair.getKey();
        Entity source = idEntityPair.getValue();
        name = source.getName().fullName;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted entity object into the model's {@code Entity} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted entity.
     */
    public abstract Pair<Integer, Entity> toModelType() throws IllegalValueException;

    /**
     * A small data structure used to return multiple values to the derived classes
     * using the {@code checkAndGetCommonAttributes} method.
     */
    protected static class CommonAttributes {
        public final int id;
        public final Name name;
        public final Set<Tag> tags;

        public CommonAttributes(int id, Name name, Set<Tag> tags) {
            this.id = id;
            this.name = name;
            this.tags = tags;
        }
    }

    /**
     * Helper function to avoid boilerplate code within the {@code toModelType} method
     */
    protected CommonAttributes checkAndGetCommonAttributes() throws IllegalValueException {
        if (id < 1) {
            throw new IllegalValueException("Invalid ID given!");
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        final List<Tag> tags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            tags.add(tag.toModelType());
        }
        final Set<Tag> modelTags = new HashSet<>(tags);

        return new CommonAttributes(id, modelName, modelTags);
    }
}

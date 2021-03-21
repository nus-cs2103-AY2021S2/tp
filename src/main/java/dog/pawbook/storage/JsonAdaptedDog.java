package dog.pawbook.storage;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import dog.pawbook.commons.exceptions.IllegalValueException;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.dog.Breed;
import dog.pawbook.model.managedentity.dog.DateOfBirth;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.dog.Sex;
import dog.pawbook.model.managedentity.tag.Tag;
import javafx.util.Pair;

@JsonTypeName(Dog.ENTITY_WORD)
public class JsonAdaptedDog extends JsonAdaptedEntity {
    private final String breed;
    private final String dob;
    private final String sex;
    private final int ownerId;

    /**
     * Constructs a {@code JsonAdaptedEntity} with the given owner details.
     */
    @JsonCreator
    public JsonAdaptedDog(@JsonProperty("id") Integer id, @JsonProperty("name") String name,
            @JsonProperty("breed") String breed, @JsonProperty("dob") String dob,
            @JsonProperty("sex") String sex, @JsonProperty("ownerId") int ownerId,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        super(id, name, tagged);
        this.breed = breed;
        this.dob = dob;
        this.sex = sex;
        this.ownerId = ownerId;
    }

    /**
     * Converts a given {@code Entity} into this class for Jackson use.
     */
    public JsonAdaptedDog(Pair<Integer, Dog> idDogPair) {
        super(idDogPair);
        Dog source = idDogPair.getValue();
        breed = source.getBreed().value;
        dob = source.getDob().value;
        sex = source.getSex().value;
        ownerId = source.getOwnerId();
    }

    /**
     * Converts this Jackson-friendly adapted entity object into the model's {@code Entity} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted entity.
     */
    @Override
    public Pair<Integer, Entity> toModelType() throws IllegalValueException {
        CommonAttributes commonAttributes = checkAndGetCommonAttributes();
        final int modelID = commonAttributes.id;
        final Name modelName = commonAttributes.name;
        final Set<Tag> modelTags = commonAttributes.tags;

        if (breed == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Breed.class.getSimpleName()));
        }
        if (!Breed.isValidBreed(breed)) {
            throw new IllegalValueException(Breed.MESSAGE_CONSTRAINTS);
        }
        final Breed modelBreed = new Breed(breed);

        if (dob == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DateOfBirth.class.getSimpleName()));
        }
        if (!DateOfBirth.isValidDob(dob)) {
            throw new IllegalValueException(DateOfBirth.MESSAGE_CONSTRAINTS);
        }
        final DateOfBirth modelDob = new DateOfBirth(dob);

        if (sex == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Sex.class.getSimpleName()));
        }
        if (!Sex.isValidSex(sex)) {
            throw new IllegalValueException(Sex.MESSAGE_CONSTRAINTS);
        }
        final Sex modelSex = new Sex(sex);

        if (ownerId < 1) {
            throw new IllegalValueException("Owner's ID must be a positive integer!");
        }

        Dog model = new Dog(modelName, modelBreed, modelDob, modelSex, ownerId, modelTags);

        return new Pair<>(modelID, model);
    }
}

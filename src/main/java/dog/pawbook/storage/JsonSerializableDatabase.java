package dog.pawbook.storage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import dog.pawbook.commons.exceptions.IllegalValueException;
import dog.pawbook.model.Database;
import dog.pawbook.model.ReadOnlyDatabase;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.program.Program;
import javafx.util.Pair;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "pawbook")
class JsonSerializableDatabase {

    public static final String MESSAGE_DUPLICATE_OWNER = "Entities list contains duplicate entit(y|ies).";
    public static final String MESSAGE_INVALID_REFERENCE_IDS = "Entities refer to broken IDs";

    private final List<JsonAdaptedEntity> entities = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given owners.
     */
    @JsonCreator
    public JsonSerializableDatabase(@JsonProperty("entities") List<JsonAdaptedEntity> entities) {
        this.entities.addAll(entities);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableDatabase(ReadOnlyDatabase source) {
        entities.addAll(source.getEntityList().stream()
                            .sorted(Comparator.comparing(Pair::getKey))
                            .map(JsonSerializableDatabase::adaptEntity)
                            .collect(Collectors.toList()));
    }

    /**
     * Helper function to map each entity to corresponding JSON Adaptation.
     */
    private static JsonAdaptedEntity adaptEntity(Pair<Integer, Entity> idEntityPair) {
        if (idEntityPair.getValue() instanceof Owner) {
            Owner owner = (Owner) idEntityPair.getValue();
            return new JsonAdaptedOwner(new Pair<>(idEntityPair.getKey(), owner));
        } else if (idEntityPair.getValue() instanceof Dog) {
            Dog dog = (Dog) idEntityPair.getValue();
            return new JsonAdaptedDog(new Pair<>(idEntityPair.getKey(), dog));
        } else if (idEntityPair.getValue() instanceof Program) {
            Program program = (Program) idEntityPair.getValue();
            return new JsonAdaptedProgram(new Pair<>(idEntityPair.getKey(), program));
        }

        throw new AssertionError("Unknown derivative of Entity class!");
    }

    /**
     * Converts this database into the model's {@code Database} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Database toModelType() throws IllegalValueException {
        Database database = new Database();
        for (JsonAdaptedEntity jsonAdaptedEntity : entities) {
            Pair<Integer, ? extends Entity> idEntityPair = jsonAdaptedEntity.toModelType();

            if (database.hasEntity(idEntityPair.getValue())) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_OWNER);
            }
            database.addEntityWithId(idEntityPair.getValue(), idEntityPair.getKey());
        }

        if (!database.validateReferences()) {
            throw new IllegalValueException(MESSAGE_INVALID_REFERENCE_IDS);
        }
        return database;
    }

}

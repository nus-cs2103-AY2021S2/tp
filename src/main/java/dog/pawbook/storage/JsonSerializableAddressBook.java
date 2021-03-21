package dog.pawbook.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import dog.pawbook.commons.exceptions.IllegalValueException;
import dog.pawbook.model.AddressBook;
import dog.pawbook.model.ReadOnlyAddressBook;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;
import javafx.util.Pair;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "pawbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_OWNER = "Entities list contains duplicate entit(y|ies).";

    private final List<JsonAdaptedEntity> entities = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given owners.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("entities") List<JsonAdaptedEntity> entities) {
        this.entities.addAll(entities);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        entities.addAll(source.getEntityList().stream().map(JsonSerializableAddressBook::adaptEntity)
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
        }

        throw new AssertionError("Unknown derivative of Entity class!");
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedEntity jsonAdaptedEntity : entities) {
            Pair<Integer, ? extends Entity> idEntityPair = jsonAdaptedEntity.toModelType();

            if (addressBook.hasEntity(idEntityPair.getValue())) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_OWNER);
            }
            addressBook.addEntityWithId(idEntityPair.getValue(), idEntityPair.getKey());
        }
        return addressBook;
    }

}

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
import javafx.util.Pair;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_OWNER = "Owners list contains duplicate owner(s).";

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
        entities.addAll(source.getEntityList().stream().map(JsonAdaptedEntity::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedEntity jsonAdaptedEntity : entities) {
            Pair<Integer, Entity> idEntityPair = jsonAdaptedEntity.toModelType();

            if (addressBook.hasEntity(idEntityPair.getValue())) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_OWNER);
            }
            addressBook.addEntityWithId(idEntityPair.getValue(), idEntityPair.getKey());
        }
        return addressBook;
    }

}

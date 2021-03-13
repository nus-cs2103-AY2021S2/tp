package dog.pawbook.storage;

import dog.pawbook.commons.exceptions.IllegalValueException;
import dog.pawbook.model.managedentity.Entity;

public interface JsonAdaptedEntity<T extends Entity> {

    /**
     * Converts this Jackson-friendly adapted owner object into the model's {@code Owner} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted owner.
     */
    T toModelType() throws IllegalValueException;
}

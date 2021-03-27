package seedu.timeforwheels.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.timeforwheels.commons.exceptions.DataConversionException;
import seedu.timeforwheels.model.DeliveryList;
import seedu.timeforwheels.model.ReadOnlyDeliveryList;

/**
 * Represents a storage for {@link DeliveryList}.
 */
public interface DeliveryListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getDeliveryListFilePath();

    /**
     * Returns DeliveryList data as a {@link ReadOnlyDeliveryList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyDeliveryList> readDeliveryList() throws DataConversionException, IOException;

    /**
     * @see #getDeliveryListFilePath()
     */
    Optional<ReadOnlyDeliveryList> readDeliveryList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyDeliveryList} to the storage.
     * @param deliveryList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveDeliveryList(ReadOnlyDeliveryList deliveryList) throws IOException;

    /**
     * @see #saveDeliveryList(ReadOnlyDeliveryList)
     */
    void saveDeliveryList(ReadOnlyDeliveryList deliveryList, Path filePath) throws IOException;

}

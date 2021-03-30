package seedu.cakecollate.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.cakecollate.commons.exceptions.DataConversionException;
import seedu.cakecollate.model.OrderItems;
import seedu.cakecollate.model.ReadOnlyOrderItems;

/**
 * Represents a storage for {@link OrderItems}.
 */

public interface OrderItemsStorage {

    /**
     * returns the file path of the data file.
     */
    Path getOrderItemsFilePath();

    /**
     * Returns OrderItems data as a {@link OrderItems}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyOrderItems> readOrderItems() throws DataConversionException, IOException;

    /**
     * @see #getOrderItemsFilePath()
     */
    Optional<ReadOnlyOrderItems> readOrderItems(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyOrderItems} to the storage.
     * @param orderItems cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveOrderItems(ReadOnlyOrderItems orderItems) throws IOException;

    /**
     * @see #saveOrderItems(ReadOnlyOrderItems)
     */
    void saveOrderItems(ReadOnlyOrderItems orderItems, Path filePath) throws IOException;
}

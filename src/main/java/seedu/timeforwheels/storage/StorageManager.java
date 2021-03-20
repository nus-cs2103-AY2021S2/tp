package seedu.timeforwheels.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.timeforwheels.commons.core.LogsCenter;
import seedu.timeforwheels.commons.exceptions.DataConversionException;
import seedu.timeforwheels.model.ReadOnlyDeliveryList;
import seedu.timeforwheels.model.ReadOnlyUserPrefs;
import seedu.timeforwheels.model.UserPrefs;

/**
 * Manages storage of DeliveryList data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private DeliveryListStorage deliveryListStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code DeliveryListStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(DeliveryListStorage deliveryListStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.deliveryListStorage = deliveryListStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ DeliveryList methods ==============================

    @Override
    public Path getDeliveryListFilePath() {
        return deliveryListStorage.getDeliveryListFilePath();
    }

    @Override
    public Optional<ReadOnlyDeliveryList> readDeliveryList() throws DataConversionException, IOException {
        return readDeliveryList(deliveryListStorage.getDeliveryListFilePath());
    }

    @Override
    public Optional<ReadOnlyDeliveryList> readDeliveryList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return deliveryListStorage.readDeliveryList(filePath);
    }

    @Override
    public void saveDeliveryList(ReadOnlyDeliveryList deliveryList) throws IOException {
        saveDeliveryList(deliveryList, deliveryListStorage.getDeliveryListFilePath());
    }

    @Override
    public void saveDeliveryList(ReadOnlyDeliveryList deliveryList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        deliveryListStorage.saveDeliveryList(deliveryList, filePath);
    }

}

package seedu.cakecollate.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.cakecollate.commons.core.LogsCenter;
import seedu.cakecollate.commons.exceptions.DataConversionException;
import seedu.cakecollate.model.ReadOnlyCakeCollate;
import seedu.cakecollate.model.ReadOnlyOrderItems;
import seedu.cakecollate.model.ReadOnlyUserPrefs;
import seedu.cakecollate.model.UserPrefs;

/**
 * Manages storage of CakeCollate data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private CakeCollateStorage cakeCollateStorage;
    private UserPrefsStorage userPrefsStorage;
    private OrderItemsStorage orderItemsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code CakeCollateStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(CakeCollateStorage cakeCollateStorage, UserPrefsStorage userPrefsStorage,
                          OrderItemsStorage orderItemsStorage) {
        super();
        this.cakeCollateStorage = cakeCollateStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.orderItemsStorage = orderItemsStorage;
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


    // ================ CakeCollate methods ==============================

    @Override
    public Path getCakeCollateFilePath() {
        return cakeCollateStorage.getCakeCollateFilePath();
    }

    @Override
    public Optional<ReadOnlyCakeCollate> readCakeCollate() throws DataConversionException, IOException {
        return readCakeCollate(cakeCollateStorage.getCakeCollateFilePath());
    }

    @Override
    public Optional<ReadOnlyCakeCollate> readCakeCollate(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return cakeCollateStorage.readCakeCollate(filePath);
    }

    @Override
    public void saveCakeCollate(ReadOnlyCakeCollate cakeCollate) throws IOException {
        saveCakeCollate(cakeCollate, cakeCollateStorage.getCakeCollateFilePath());
    }

    @Override
    public void saveCakeCollate(ReadOnlyCakeCollate cakeCollate, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        cakeCollateStorage.saveCakeCollate(cakeCollate, filePath);
    }

    // ================ OrderItems methods ==============================

    @Override
    public Path getOrderItemsFilePath() {
        return orderItemsStorage.getOrderItemsFilePath();
    }

    @Override
    public Optional<ReadOnlyOrderItems> readOrderItems() throws DataConversionException, IOException {
        return readOrderItems(orderItemsStorage.getOrderItemsFilePath());
    }

    @Override
    public Optional<ReadOnlyOrderItems> readOrderItems(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return orderItemsStorage.readOrderItems(filePath);
    }

    @Override
    public void saveOrderItems(ReadOnlyOrderItems orderItems) throws IOException {
        saveOrderItems(orderItems, orderItemsStorage.getOrderItemsFilePath());
    }

    @Override
    public void saveOrderItems(ReadOnlyOrderItems orderItems, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        orderItemsStorage.saveOrderItems(orderItems, filePath);
    }

}

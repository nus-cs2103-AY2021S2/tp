package seedu.timeforwheels.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.timeforwheels.commons.exceptions.DataConversionException;
import seedu.timeforwheels.model.ReadOnlyDeliveryList;
import seedu.timeforwheels.model.ReadOnlyUserPrefs;
import seedu.timeforwheels.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends DeliveryListStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getDeliveryListFilePath();

    @Override
    Optional<ReadOnlyDeliveryList> readDeliveryList() throws DataConversionException, IOException;

    @Override
    void saveDeliveryList(ReadOnlyDeliveryList deliveryList) throws IOException;

}

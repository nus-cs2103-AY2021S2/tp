package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Doctor;

/**
 * A class to access DoctorRecords data stored as a json file on the hard disk.
 */
public class JsonDoctorRecordsStorage extends AddressBookStorage<Doctor> {

    private static final Logger LOGGER = LogsCenter.getLogger(JsonDoctorRecordsStorage.class);

    public JsonDoctorRecordsStorage(Path filePath) {
        super(filePath);
    }

    @Override
    public Optional<ReadOnlyAddressBook<Doctor>> readAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableDoctorRecords> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableDoctorRecords.class);

        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            LOGGER.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook<Doctor> addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableDoctorRecords(addressBook), filePath);
    }

}

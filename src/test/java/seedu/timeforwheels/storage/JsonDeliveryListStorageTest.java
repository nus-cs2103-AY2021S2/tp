package seedu.timeforwheels.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.timeforwheels.testutil.Assert.assertThrows;
import static seedu.timeforwheels.testutil.TypicalCustomers.ALICE;
import static seedu.timeforwheels.testutil.TypicalCustomers.HOON;
import static seedu.timeforwheels.testutil.TypicalCustomers.IDA;
import static seedu.timeforwheels.testutil.TypicalCustomers.getTypicalDeliveryList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.timeforwheels.commons.exceptions.DataConversionException;
import seedu.timeforwheels.model.DeliveryList;
import seedu.timeforwheels.model.ReadOnlyDeliveryList;

public class JsonDeliveryListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonDeliveryListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readDeliveryList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readDeliveryList(null));
    }

    private java.util.Optional<ReadOnlyDeliveryList> readDeliveryList(String filePath) throws Exception {
        return new JsonDeliveryListStorage(Paths.get(filePath)).readDeliveryList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readDeliveryList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () ->
            readDeliveryList("notJsonFormatDeliveryList.json"));
    }

    @Test
    public void readDeliveryList_invalidCustomerDeliveryList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
            readDeliveryList("invalidCustomerDeliveryList.json"));
    }

    @Test
    public void readDeliveryList_invalidAndValidCustomerDeliveryList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
            readDeliveryList("invalidAndValidCustomerDeliveryList.json"));
    }

    @Test
    public void readAndSaveDeliveryList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempDeliveryList.json");
        DeliveryList original = getTypicalDeliveryList();
        JsonDeliveryListStorage jsonDeliveryListStorage = new JsonDeliveryListStorage(filePath);

        // Save in new file and read back
        jsonDeliveryListStorage.saveDeliveryList(original, filePath);
        ReadOnlyDeliveryList readBack = jsonDeliveryListStorage.readDeliveryList(filePath).get();
        assertEquals(original, new DeliveryList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addCustomer(HOON);
        original.removeCustomer(ALICE);
        jsonDeliveryListStorage.saveDeliveryList(original, filePath);
        readBack = jsonDeliveryListStorage.readDeliveryList(filePath).get();
        assertEquals(original, new DeliveryList(readBack));

        // Save and read without specifying file path
        original.addCustomer(IDA);
        jsonDeliveryListStorage.saveDeliveryList(original); // file path not specified
        readBack = jsonDeliveryListStorage.readDeliveryList().get(); // file path not specified
        assertEquals(original, new DeliveryList(readBack));

    }

    @Test
    public void saveDeliveryList_nullDeliveryList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveDeliveryList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code deliveryList} at the specified {@code filePath}.
     */
    private void saveDeliveryList(ReadOnlyDeliveryList deliveryList, String filePath) {
        try {
            new JsonDeliveryListStorage(Paths.get(filePath))
                    .saveDeliveryList(deliveryList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveDeliveryList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveDeliveryList(new DeliveryList(), null));
    }
}

package seedu.cakecollate.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.cakecollate.testutil.Assert.assertThrows;
import static seedu.cakecollate.testutil.TypicalOrderItems.CHENDOL;
import static seedu.cakecollate.testutil.TypicalOrderItems.CHOCOLATE;
import static seedu.cakecollate.testutil.TypicalOrderItems.DURIAN;
import static seedu.cakecollate.testutil.TypicalOrderItems.getTypicalOrderItemsModel;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.cakecollate.commons.exceptions.DataConversionException;
import seedu.cakecollate.model.OrderItems;
import seedu.cakecollate.model.ReadOnlyOrderItems;

public class JsonOrderItemsStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonOrderItemsStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readOrderItems_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readOrderItems(null));
    }

    private java.util.Optional<ReadOnlyOrderItems> readOrderItems(String filePath) throws Exception {
        return new JsonOrderItemsStorage(Paths.get(filePath)).readOrderItems(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readOrderItems("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readOrderItems("notJsonFormatOrderItems.json"));
    }

    @Test
    public void readOrderItems_invalidOrderItems_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readOrderItems("invalidOrderItems.json"));
    }

    @Test
    public void readOrderItems_invalidAndValidOrderItems_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readOrderItems("invalidAndValidOrderItems.json"));
    }

    @Test
    public void readAndSaveOrderItems_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempOrderItems.json");
        OrderItems original = getTypicalOrderItemsModel();
        JsonOrderItemsStorage jsonOrderItemsStorage = new JsonOrderItemsStorage(filePath);

        // Save in new file and read back
        jsonOrderItemsStorage.saveOrderItems(original, filePath);
        ReadOnlyOrderItems readBack = jsonOrderItemsStorage.readOrderItems(filePath).get();
        assertEquals(original, new OrderItems(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addOrderItem(DURIAN);
        original.removeOrderItem(CHOCOLATE);
        jsonOrderItemsStorage.saveOrderItems(original, filePath);
        readBack = jsonOrderItemsStorage.readOrderItems(filePath).get();
        assertEquals(original, new OrderItems(readBack));

        // Save and read without specifying file path
        original.addOrderItem(CHENDOL);
        jsonOrderItemsStorage.saveOrderItems(original); // file path not specified
        readBack = jsonOrderItemsStorage.readOrderItems().get(); // file path not specified
        assertEquals(original, new OrderItems(readBack));

    }

    @Test
    public void saveOrderItems_nullOrderItems_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveOrderItems(null, "SomeFile.json"));
    }

    /**
     * Saves {@code cakeCollate} at the specified {@code filePath}.
     */
    private void saveOrderItems(ReadOnlyOrderItems orderItems, String filePath) {
        try {
            new JsonOrderItemsStorage(Paths.get(filePath))
                    .saveOrderItems(orderItems, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveOrderItems_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveOrderItems(new OrderItems(), null));
    }
}

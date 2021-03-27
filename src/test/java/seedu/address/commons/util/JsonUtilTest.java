package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.testutil.SerializableTestClass;
import seedu.address.testutil.TestUtil;

/**
 * Tests JSON Read and Write
 */
public class JsonUtilTest {

    private static final Path SERIALIZATION_FILE = TestUtil.getFilePathInSandboxFolder("serialize.json");

    @Test
    public void serializeObjectToJsonFile_noExceptionThrown() throws IOException {
        SerializableTestClass serializableTestClass = new SerializableTestClass();
        serializableTestClass.setTestValues();

        JsonUtil.serializeObjectToJsonFile(SERIALIZATION_FILE, serializableTestClass);

        assertEquals(FileUtil.readFromFile(SERIALIZATION_FILE), SerializableTestClass.JSON_STRING_REPRESENTATION);
    }

    @Test
    public void deserializeObjectFromJsonFile_noExceptionThrown() throws IOException {
        FileUtil.writeToFile(SERIALIZATION_FILE, SerializableTestClass.JSON_STRING_REPRESENTATION);

        SerializableTestClass serializableTestClass = JsonUtil
                .deserializeObjectFromJsonFile(SERIALIZATION_FILE, SerializableTestClass.class);

        assertEquals(serializableTestClass.getName(), SerializableTestClass.getNameTestValue());
        assertEquals(serializableTestClass.getListOfLocalDateTimes(), SerializableTestClass.getListTestValues());
        assertEquals(serializableTestClass.getMapOfIntegerToString(), SerializableTestClass.getHashMapTestValues());
    }

    @Test
    public void jsonUtil_readJsonStringToObjectInstance_correctObject() throws DataConversionException {
        Optional<SerializableTestClass> readJsonFile = JsonUtil.readJsonFile(
                SERIALIZATION_FILE, SerializableTestClass.class);
        assertFalse(readJsonFile.isEmpty());

        SerializableTestClass serializableTestClass = readJsonFile.get();

        assertEquals(serializableTestClass.getName(), SerializableTestClass.getNameTestValue());
        assertEquals(serializableTestClass.getListOfLocalDateTimes(), SerializableTestClass.getListTestValues());
        assertEquals(serializableTestClass.getMapOfIntegerToString(), SerializableTestClass.getHashMapTestValues());
    }

    @Test
    public void jsonUtil_writeThenReadObjectToJson_correctObject() throws IOException, DataConversionException {
        SerializableTestClass serializableTestClass = new SerializableTestClass();
        serializableTestClass.setTestValues();

        //Write
        JsonUtil.saveJsonFile(serializableTestClass, SERIALIZATION_FILE);

        //Read
        SerializableTestClass readSerializableTestClass = JsonUtil.readJsonFile(
                SERIALIZATION_FILE, SerializableTestClass.class).get();

        assertEquals(serializableTestClass.getName(), readSerializableTestClass.getName());
        assertEquals(serializableTestClass.getListOfLocalDateTimes(),
                readSerializableTestClass.getListOfLocalDateTimes());
        assertEquals(serializableTestClass.getMapOfIntegerToString(),
                readSerializableTestClass.getMapOfIntegerToString());
    }
}

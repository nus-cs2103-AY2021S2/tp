package dog.pawbook.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import dog.pawbook.commons.exceptions.DataConversionException;
import dog.pawbook.testutil.SerializableTestClass;
import dog.pawbook.testutil.TestUtil;

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
    public void jsonUtil_readJsonStringToObjectInstance_correctObject() throws IOException {
        SerializableTestClass serializableTestClass = new SerializableTestClass();
        serializableTestClass.setTestValues();

        SerializableTestClass object =
                JsonUtil.fromJsonString(SerializableTestClass.JSON_STRING_REPRESENTATION, SerializableTestClass.class);

        assertEquals(object, serializableTestClass);
    }

    @Test
    public void jsonUtil_writeThenReadObjectToJson_correctObject() throws IOException, DataConversionException {
        SerializableTestClass writtenSerializableTestObject = new SerializableTestClass();
        writtenSerializableTestObject.setTestValues();

        JsonUtil.saveJsonFile(writtenSerializableTestObject, SERIALIZATION_FILE);

        Optional<SerializableTestClass> optionalObjectFromFile =
                JsonUtil.readJsonFile(SERIALIZATION_FILE, SerializableTestClass.class);

        assertTrue(optionalObjectFromFile.isPresent());

        assertEquals(optionalObjectFromFile.get(), writtenSerializableTestObject);
    }
}

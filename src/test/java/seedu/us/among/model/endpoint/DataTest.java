package seedu.us.among.model.endpoint;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DataTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Data(null));
    }

    @Test
    public void constructor_invalidData_throwsIllegalArgumentException() {
        String invalidData = "";
        assertThrows(IllegalArgumentException.class, () -> new Data(invalidData));
    }

    @Test
    public void isValidData() {
        // null data
        assertThrows(NullPointerException.class, () -> Data.isValidData(null));

        // invalid data
        assertFalse(Data.isValidData("")); // empty string
        assertFalse(Data.isValidData(" ")); // spaces only
        //to-do fix this test case after this is not allowed
        //assertTrue(Data.isValidData("{What: lol}"));

        // valid data
        //to-do fix this test case after {} is allowed
        //assertTrue(Data.isValidData("{}"));
        assertTrue(Data.isValidData("{\"key\": \"value\"}"));
        assertTrue(Data.isValidData("{\"key1\": \"value1\", \"key1\": \"value2\"}"));
        assertTrue(Data.isValidData("{\"Response\": { \"responseTime\": \"0.01\", \"statusCode\": \"200\"}}"));
    }
}

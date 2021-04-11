package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_NO_PREFIX;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_NO_PREFIX;
import static seedu.address.storage.JsonAdaptedDriver.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDrivers.DRIVER_BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.driver.Driver;
import seedu.address.testutil.DriverBuilder;

public class JsonAdaptedDriverTest {
    private static final String VALID_NAME = DRIVER_BENSON.getName().toString();
    private static final String VALID_PHONE = DRIVER_BENSON.getPhone().toString();

    @Test
    public void toModelType_validDriver_returnsDriver() throws Exception {
        JsonAdaptedDriver driver = new JsonAdaptedDriver(DRIVER_BENSON);
        assertEquals(DRIVER_BENSON, driver.toModelType());
    }

    @Test
    public void toModelType_validNameAndPhone_returnsDriver() throws Exception {
        Driver driver = new DriverBuilder(DRIVER_BENSON).build();
        JsonAdaptedDriver jsonAdaptedDriver = new JsonAdaptedDriver(VALID_NAME, VALID_PHONE);
        assertEquals(driver, jsonAdaptedDriver.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedDriver driver =
                new JsonAdaptedDriver(INVALID_NAME_NO_PREFIX, VALID_PHONE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, driver::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedDriver driver = new JsonAdaptedDriver(null, VALID_PHONE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, driver::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedDriver driver =
                new JsonAdaptedDriver(VALID_NAME, INVALID_PHONE_NO_PREFIX);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, driver::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedDriver driver = new JsonAdaptedDriver(VALID_NAME, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, driver::toModelType);
    }
}

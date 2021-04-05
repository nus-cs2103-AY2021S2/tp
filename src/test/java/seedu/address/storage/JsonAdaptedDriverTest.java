package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";

    private static final String VALID_NAME = DRIVER_BENSON.getName().toString();
    private static final String VALID_PHONE = DRIVER_BENSON.getPhone().toString();

    @Test
    public void toModelType_validPassengerDetails_returnsPassenger() throws Exception {
        JsonAdaptedDriver driver = new JsonAdaptedDriver(DRIVER_BENSON);
        assertEquals(DRIVER_BENSON, driver.toModelType());
    }

    @Test
    public void toModelType_validPersonDetailsWithDriver_returnsPerson() throws Exception {
        Driver driver = new DriverBuilder(DRIVER_BENSON).build();
        JsonAdaptedDriver jsonAdaptedDriver = new JsonAdaptedDriver(driver);
        assertEquals(driver, jsonAdaptedDriver.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedDriver driver =
                new JsonAdaptedDriver(INVALID_NAME, VALID_PHONE);
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
                new JsonAdaptedDriver(VALID_NAME, INVALID_PHONE);
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

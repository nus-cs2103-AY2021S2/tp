package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPool.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPools.HOMEPOOL;
import static seedu.address.testutil.TypicalPools.OFFICEPOOL;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.TripDay;
import seedu.address.model.TripTime;

class JsonAdaptedPoolTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_TRIPDAY = "funday";
    private static final String INVALID_TRIPTIME = "21032103";
    private static final String INVALID_PRICE = "1.42069";

    private static final String VALID_TRIPDAY = OFFICEPOOL.getTripDay().toString();
    private static final String VALID_TRIPTIME = OFFICEPOOL.getTripTime().toString();
    private static final List<JsonAdaptedPassenger> VALID_PASSENGERS = OFFICEPOOL.getPassengers().stream()
            .map(JsonAdaptedPassenger::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_TAGS = OFFICEPOOL.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final JsonAdaptedDriver VALID_DRIVER = new JsonAdaptedDriver(OFFICEPOOL.getDriver());

    @Test
    public void toModelType_validPoolDetails_returnsPool() throws Exception {
        JsonAdaptedPool pool = new JsonAdaptedPool(HOMEPOOL);
        assertEquals(HOMEPOOL, pool.toModelType());
    }

    @Test
    public void toModelType_invalidTripDay_throwsIllegalValueException() {
        JsonAdaptedPool pool =
                new JsonAdaptedPool(INVALID_TRIPDAY, VALID_TRIPTIME, VALID_PASSENGERS, VALID_TAGS, VALID_DRIVER);
        String expectedMessage = TripDay.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, pool::toModelType);
    }


    @Test
    public void toModelType_nullTripDay_throwsIllegalValueException() {
        JsonAdaptedPool pool =
                new JsonAdaptedPool(null, VALID_TRIPTIME, VALID_PASSENGERS, VALID_TAGS, VALID_DRIVER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TripDay.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, pool::toModelType);
    }

    @Test
    public void toModelType_invalidTripTime_throwsIllegalValueException() {
        JsonAdaptedPool pool =
                new JsonAdaptedPool(VALID_TRIPDAY, INVALID_TRIPTIME, VALID_PASSENGERS, VALID_TAGS, VALID_DRIVER);
        String expectedMessage = TripTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, pool::toModelType);
    }

    @Test
    public void toModelType_nullTripTime_throwsIllegalValueException() {
        JsonAdaptedPool pool =
                new JsonAdaptedPool(VALID_TRIPDAY, null, VALID_PASSENGERS, VALID_TAGS, VALID_DRIVER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TripTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, pool::toModelType);
    }


    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPool pool =
                new JsonAdaptedPool(VALID_TRIPDAY, VALID_TRIPTIME, VALID_PASSENGERS, invalidTags, VALID_DRIVER);
        assertThrows(IllegalValueException.class, pool::toModelType);
    }

}

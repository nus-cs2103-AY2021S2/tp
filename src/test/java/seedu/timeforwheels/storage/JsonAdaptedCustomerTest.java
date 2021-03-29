package seedu.timeforwheels.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.timeforwheels.storage.JsonAdaptedCustomer.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.timeforwheels.testutil.Assert.assertThrows;
import static seedu.timeforwheels.testutil.TypicalCustomers.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.timeforwheels.commons.exceptions.IllegalValueException;
import seedu.timeforwheels.model.customer.Address;
import seedu.timeforwheels.model.customer.Email;
import seedu.timeforwheels.model.customer.Name;
import seedu.timeforwheels.model.customer.Phone;

public class JsonAdaptedCustomerTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_REMARK = BENSON.getRemark().toString();
    private static final String VALID_DONE = BENSON.getDone().toString();
    private static final String VALID_DATE = BENSON.getDate().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validCustomerDetails_returnsCustomer() throws Exception {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(BENSON);
        assertEquals(BENSON, customer.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedCustomer customer =
                new JsonAdaptedCustomer(INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_REMARK, VALID_DATE, VALID_TAGS, VALID_DONE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_REMARK, VALID_DATE, VALID_TAGS, VALID_DONE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedCustomer customer =
                new JsonAdaptedCustomer(VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_REMARK, VALID_DATE, VALID_TAGS, VALID_DONE);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_NAME, null, VALID_EMAIL,
                VALID_ADDRESS, VALID_REMARK, VALID_DATE, VALID_TAGS, VALID_DONE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedCustomer customer =
                new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                        VALID_REMARK, VALID_DATE, VALID_TAGS, VALID_DONE);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, null,
                VALID_ADDRESS, VALID_REMARK, VALID_DATE, VALID_TAGS, VALID_DONE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                INVALID_ADDRESS, VALID_REMARK, VALID_DATE, VALID_TAGS, VALID_DONE);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class,
                expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                null, VALID_REMARK, VALID_DATE, VALID_TAGS, VALID_DONE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedCustomer customer =
                new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_REMARK, VALID_DATE, invalidTags, VALID_DONE);
        assertThrows(IllegalValueException.class, customer::toModelType);
    }

}

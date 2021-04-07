package seedu.iscam.storage.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.iscam.storage.client.JsonAdaptedClient.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.iscam.testutil.Assert.assertThrows;
import static seedu.iscam.testutil.TypicalClients.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.iscam.commons.exceptions.IllegalValueException;
import seedu.iscam.model.client.Email;
import seedu.iscam.model.client.InsurancePlan;
import seedu.iscam.model.client.Phone;
import seedu.iscam.model.commons.Location;
import seedu.iscam.model.commons.Name;

public class JsonAdaptedClientTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_LOCATION = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_PLAN = "Med1care";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_IMAGE = "w ef.jaypeg";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_LOCATION = BENSON.getLocation().toString();
    private static final List<JsonAdaptedClientPlan> VALID_PLAN = BENSON.getPlans().stream()
            .map(JsonAdaptedClientPlan::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedClientTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedClientTag::new)
            .collect(Collectors.toList());
    private static final String VALID_IMAGE = "default.png";

    @Test
    public void toModelType_validClientDetails_returnsClient() throws Exception {
        JsonAdaptedClient client = new JsonAdaptedClient(BENSON);
        assertEquals(BENSON, client.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_LOCATION, VALID_PLAN, VALID_TAGS,
                        VALID_IMAGE);
        String expectedMessage = Name.MESSAGE_TYPE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(
                null, VALID_PHONE, VALID_EMAIL, VALID_LOCATION, VALID_PLAN, VALID_TAGS, VALID_IMAGE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_LOCATION, VALID_PLAN, VALID_TAGS,
                        VALID_IMAGE);
        String expectedMessage = Phone.MESSAGE_LENGTH_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(
                VALID_NAME, null, VALID_EMAIL, VALID_LOCATION, VALID_PLAN, VALID_TAGS, VALID_IMAGE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_LOCATION, VALID_PLAN, VALID_TAGS,
                        VALID_IMAGE);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(
                VALID_NAME, VALID_PHONE, null, VALID_LOCATION, VALID_PLAN, VALID_TAGS, VALID_IMAGE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidLocation_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_LOCATION, VALID_PLAN, VALID_TAGS,
                        VALID_IMAGE);
        String expectedMessage = Location.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_PLAN, VALID_TAGS, VALID_IMAGE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidPlan_throwsIllegalValueException() {
        List<JsonAdaptedClientPlan> invalidPlans = new ArrayList<>(VALID_PLAN);
        invalidPlans.add(new JsonAdaptedClientPlan(INVALID_PLAN));
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_LOCATION, invalidPlans, VALID_TAGS,
                        VALID_IMAGE);
        String expectedMessage = InsurancePlan.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullPlan_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_LOCATION, null, VALID_TAGS, VALID_IMAGE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, InsurancePlan.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedClientTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedClientTag(INVALID_TAG));
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_LOCATION, VALID_PLAN, invalidTags,
                        VALID_IMAGE);
        assertThrows(IllegalValueException.class, client::toModelType);
    }

    @Test
    public void toModelType_invalidImage_throwsIllegalValueException() {
        List<JsonAdaptedClientPlan> invalidPlans = new ArrayList<>(VALID_PLAN);
        invalidPlans.add(new JsonAdaptedClientPlan(INVALID_PLAN));
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_LOCATION, invalidPlans, VALID_TAGS,
                        INVALID_IMAGE);
        String expectedMessage = InsurancePlan.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullImage_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_LOCATION, null, VALID_TAGS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, InsurancePlan.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

}

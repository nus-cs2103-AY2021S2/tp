package dog.pawbook.storage;

import static dog.pawbook.storage.JsonAdaptedEntity.MISSING_FIELD_MESSAGE_FORMAT;
import static dog.pawbook.testutil.Assert.assertThrows;
import static dog.pawbook.testutil.TypicalOwners.BENSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dog.pawbook.commons.exceptions.IllegalValueException;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.owner.Address;
import dog.pawbook.model.managedentity.owner.Email;
import dog.pawbook.model.managedentity.owner.Phone;

public class JsonAdaptedEntityOwnerTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final Map<String, String> otherPropertiesDict = new HashMap<>();

    @BeforeEach
    private void init() {
        otherPropertiesDict.put("type", "owner");
        otherPropertiesDict.put(Phone.class.getSimpleName(), VALID_PHONE);
        otherPropertiesDict.put(Email.class.getSimpleName(), VALID_EMAIL);
        otherPropertiesDict.put(Address.class.getSimpleName(), VALID_ADDRESS);
    }

    @Test
    public void toModelType_validOwnerDetails_returnsOwner() throws Exception {
        JsonAdaptedEntity owner = new JsonAdaptedEntity(BENSON);
        assertEquals(BENSON, owner.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedEntity owner = new JsonAdaptedEntity(INVALID_NAME, VALID_TAGS, otherPropertiesDict);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, owner::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedEntity owner = new JsonAdaptedEntity(null, VALID_TAGS, otherPropertiesDict);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, owner::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        otherPropertiesDict.put(Phone.class.getSimpleName(), INVALID_PHONE);
        JsonAdaptedEntity owner = new JsonAdaptedEntity(VALID_NAME, VALID_TAGS, otherPropertiesDict);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, owner::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        otherPropertiesDict.put(Phone.class.getSimpleName(), null);
        JsonAdaptedEntity owner = new JsonAdaptedEntity(VALID_NAME, VALID_TAGS, otherPropertiesDict);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, owner::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        otherPropertiesDict.put(Email.class.getSimpleName(), INVALID_EMAIL);
        JsonAdaptedEntity owner = new JsonAdaptedEntity(VALID_NAME, VALID_TAGS, otherPropertiesDict);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, owner::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        otherPropertiesDict.put(Email.class.getSimpleName(), null);
        JsonAdaptedEntity owner = new JsonAdaptedEntity(VALID_NAME, VALID_TAGS, otherPropertiesDict);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, owner::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        otherPropertiesDict.put(Address.class.getSimpleName(), INVALID_ADDRESS);
        JsonAdaptedEntity owner = new JsonAdaptedEntity(VALID_NAME, VALID_TAGS, otherPropertiesDict);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, owner::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        otherPropertiesDict.put(Address.class.getSimpleName(), null);
        JsonAdaptedEntity owner = new JsonAdaptedEntity(VALID_NAME, VALID_TAGS, otherPropertiesDict);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, owner::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedEntity owner = new JsonAdaptedEntity(VALID_NAME, invalidTags, otherPropertiesDict);
        assertThrows(IllegalValueException.class, owner::toModelType);
    }

}

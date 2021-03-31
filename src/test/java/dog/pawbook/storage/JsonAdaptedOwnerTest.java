package dog.pawbook.storage;

import static dog.pawbook.storage.JsonAdaptedOwner.MISSING_FIELD_MESSAGE_FORMAT;
import static dog.pawbook.testutil.Assert.assertThrows;
import static dog.pawbook.testutil.TypicalOwners.BENSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import dog.pawbook.commons.exceptions.IllegalValueException;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.owner.Address;
import dog.pawbook.model.managedentity.owner.Email;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.owner.Phone;
import javafx.util.Pair;

public class JsonAdaptedOwnerTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final Integer BENSON_ID = 2;
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<Integer> VALID_DOG_IDS = new ArrayList<>(BENSON.getDogIdSet());

    @Test
    public void toModelType_validOwnerDetails_returnsOwner() throws Exception {
        Pair<Integer, Owner> original = new Pair<>(BENSON_ID, BENSON);
        JsonAdaptedOwner owner = new JsonAdaptedOwner(original);
        assertEquals(original, owner.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedOwner owner = new JsonAdaptedOwner(BENSON_ID, INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_TAGS, VALID_DOG_IDS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, owner::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedOwner owner = new JsonAdaptedOwner(BENSON_ID, null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_TAGS, VALID_DOG_IDS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, owner::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedOwner owner = new JsonAdaptedOwner(BENSON_ID, VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_TAGS, VALID_DOG_IDS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, owner::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedOwner owner = new JsonAdaptedOwner(BENSON_ID, VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_TAGS, VALID_DOG_IDS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, owner::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedOwner owner = new JsonAdaptedOwner(BENSON_ID, VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                VALID_TAGS, VALID_DOG_IDS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, owner::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedOwner owner = new JsonAdaptedOwner(BENSON_ID, VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_TAGS, VALID_DOG_IDS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, owner::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedOwner owner = new JsonAdaptedOwner(BENSON_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                VALID_TAGS, VALID_DOG_IDS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, owner::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedOwner owner = new JsonAdaptedOwner(BENSON_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_TAGS, VALID_DOG_IDS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, owner::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedOwner owner = new JsonAdaptedOwner(BENSON_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                invalidTags, VALID_DOG_IDS);
        assertThrows(IllegalValueException.class, owner::toModelType);
    }

}

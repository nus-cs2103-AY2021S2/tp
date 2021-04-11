package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTeachingAssistant.BEN;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.contact.ContactEmail;
import seedu.address.model.contact.ContactName;
import seedu.address.model.contact.ContactPhone;

public class JsonAdaptedContactTest {

    public static final String VALID_CONTACT_NAME = BEN.getName().fullName;
    public static final String VALID_CONTACT_PHONE = BEN.getPhone().value;
    public static final String VALID_CONTACT_EMAIL = BEN.getEmail().value;
    public static final List<JsonAdaptedTag> VALID_CONTACT_TAGS = BEN.getTags().stream()
            .map(JsonAdaptedTag::new).collect(Collectors.toList());

    public static final String INVALID_CONTACT_NAME = "@@ron";
    public static final String INVALID_CONTACT_PHONE = "8888";
    public static final String INVALID_CONTACT_EMAIL = "carls email";
    public static final String INVALID_CONTACT_TAG = "%%";

    @Test
    public void toModelType_validContactDetails_returnContact() throws Exception {
        JsonAdaptedContact contact = new JsonAdaptedContact(BEN);
        assertEquals(BEN, contact.toModelType());
    }

    @Test
    public void toModelType_invalidContactName_throwsIllegalValueException() {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(INVALID_CONTACT_NAME, VALID_CONTACT_PHONE,
                        VALID_CONTACT_EMAIL, VALID_CONTACT_TAGS);
        String expectedMessage = ContactName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_invalidContactPhone_throwsIllegalValueException() {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(VALID_CONTACT_NAME, INVALID_CONTACT_PHONE,
                        VALID_CONTACT_EMAIL, VALID_CONTACT_TAGS);
        String expectedMessage = ContactPhone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_invalidContactEmail_throwsIllegalValueException() {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(VALID_CONTACT_NAME, VALID_CONTACT_PHONE,
                        INVALID_CONTACT_EMAIL, VALID_CONTACT_TAGS);
        String expectedMessage = ContactEmail.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_CONTACT_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_CONTACT_TAG));
        JsonAdaptedContact contact =
                new JsonAdaptedContact(VALID_CONTACT_NAME, VALID_CONTACT_PHONE, VALID_CONTACT_EMAIL, invalidTags);
        assertThrows(IllegalValueException.class, contact::toModelType);
    }
}

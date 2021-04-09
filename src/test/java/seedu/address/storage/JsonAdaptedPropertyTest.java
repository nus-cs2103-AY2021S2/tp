package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.storage.JsonAdaptedProperty.INCORRECT_CLIENT_FIELD_MESSAGE;
import static seedu.address.testutil.TypicalClients.CLIENT_EVE;
import static seedu.address.testutil.TypicalProperties.JURONG;
import static seedu.address.testutil.TypicalProperties.WOODLANDS_CRESCENT;

import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.name.Name;
import seedu.address.model.property.Address;
import seedu.address.model.property.Deadline;
import seedu.address.model.property.PostalCode;
import seedu.address.model.property.Type;
import seedu.address.model.remark.Remark;
import seedu.address.testutil.PropertyBuilder;

public class JsonAdaptedPropertyTest {
    @Test
    public void validNoClientPropertyCreateTest() throws IllegalValueException {
        JsonAdaptedProperty adaptedProperty = new JsonAdaptedProperty(WOODLANDS_CRESCENT);
        assertEquals(adaptedProperty.toModelType(), WOODLANDS_CRESCENT);
        // assertEquals(adaptedProperty, expectedAdaptedProperty);
    }

    @Test
    public void validWithClientPropertyCreateTest() throws IllegalValueException {
        JsonAdaptedProperty adaptedProperty = new JsonAdaptedProperty(JURONG);
        assertEquals(adaptedProperty.toModelType(), JURONG);
        // assertEquals(adaptedProperty, expectedAdaptedPropertyWithClient);
    }

    @Test
    public void invalidNoNameCreateTest() {
        JsonAdaptedProperty adaptedPropertyNoName =
                new JsonAdaptedProperty(new PropertyBuilder().withName(null).build());
        IllegalValueException thrown = assertThrows(IllegalValueException.class, adaptedPropertyNoName::toModelType);
        assertEquals(thrown.getMessage(),
                String.format(JsonAdaptedProperty.MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
    }

    @Test
    public void invalidBlankNameCreateTest() {
        JsonAdaptedProperty adaptedPropertyNoName =
                new JsonAdaptedProperty("  ",
                        WOODLANDS_CRESCENT.getPropertyType().toString(),
                        WOODLANDS_CRESCENT.getAddress().toString(),
                        "",
                        WOODLANDS_CRESCENT.getPostalCode().toString(),
                        WOODLANDS_CRESCENT.getDeadline().toString(),
                        null,
                        WOODLANDS_CRESCENT.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                        null);
        IllegalValueException thrown = assertThrows(IllegalValueException.class, adaptedPropertyNoName::toModelType);
        assertEquals(thrown.getMessage(), Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void invalidNoTypeCreateTest() {
        JsonAdaptedProperty adaptedPropertyNoName =
                new JsonAdaptedProperty(WOODLANDS_CRESCENT.getName().toString(),
                        null,
                        WOODLANDS_CRESCENT.getAddress().toString(),
                        "",
                        WOODLANDS_CRESCENT.getPostalCode().toString(),
                        WOODLANDS_CRESCENT.getDeadline().toString(),
                        null,
                        WOODLANDS_CRESCENT.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                        null);
        IllegalValueException thrown = assertThrows(IllegalValueException.class, adaptedPropertyNoName::toModelType);
        assertEquals(thrown.getMessage(),
                String.format(JsonAdaptedProperty.MISSING_FIELD_MESSAGE_FORMAT, Type.class.getSimpleName()));
    }

    @Test
    public void invalidBlankTypeCreateTest() {
        JsonAdaptedProperty adaptedPropertyNoName =
                new JsonAdaptedProperty(WOODLANDS_CRESCENT.getName().toString(),
                        " ",
                        WOODLANDS_CRESCENT.getAddress().toString(),
                        "",
                        WOODLANDS_CRESCENT.getPostalCode().toString(),
                        WOODLANDS_CRESCENT.getDeadline().toString(),
                        null,
                        WOODLANDS_CRESCENT.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                        null);
        IllegalValueException thrown = assertThrows(IllegalValueException.class, adaptedPropertyNoName::toModelType);
        assertEquals(thrown.getMessage(), Type.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void invalidNoAddressCreateTest() {
        JsonAdaptedProperty adaptedPropertyNoName =
                new JsonAdaptedProperty(WOODLANDS_CRESCENT.getName().toString(),
                        WOODLANDS_CRESCENT.getPropertyType().toString(),
                        null,
                        "",
                        WOODLANDS_CRESCENT.getPostalCode().toString(),
                        WOODLANDS_CRESCENT.getDeadline().toString(),
                        null,
                        WOODLANDS_CRESCENT.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                        null);
        IllegalValueException thrown = assertThrows(IllegalValueException.class, adaptedPropertyNoName::toModelType);
        assertEquals(thrown.getMessage(),
                String.format(JsonAdaptedProperty.MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
    }

    @Test
    public void invalidBlankAddressCreateTest() {
        JsonAdaptedProperty adaptedPropertyNoName =
                new JsonAdaptedProperty(WOODLANDS_CRESCENT.getName().toString(),
                        WOODLANDS_CRESCENT.getPropertyType().toString(),
                        " ",
                        "",
                        WOODLANDS_CRESCENT.getPostalCode().toString(),
                        WOODLANDS_CRESCENT.getDeadline().toString(),
                        null,
                        WOODLANDS_CRESCENT.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                        null);
        IllegalValueException thrown = assertThrows(IllegalValueException.class, adaptedPropertyNoName::toModelType);
        assertEquals(thrown.getMessage(), Address.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void invalidNoPostalCreateTest() {
        JsonAdaptedProperty adaptedPropertyNoName =
                new JsonAdaptedProperty(WOODLANDS_CRESCENT.getName().toString(),
                        WOODLANDS_CRESCENT.getPropertyType().toString(),
                        WOODLANDS_CRESCENT.getAddress().toString(),
                        "",
                        null,
                        WOODLANDS_CRESCENT.getDeadline().toString(),
                        null,
                        WOODLANDS_CRESCENT.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                        null);
        IllegalValueException thrown = assertThrows(IllegalValueException.class, adaptedPropertyNoName::toModelType);
        assertEquals(thrown.getMessage(),
                String.format(JsonAdaptedProperty.MISSING_FIELD_MESSAGE_FORMAT, PostalCode.class.getSimpleName()));
    }

    @Test
    public void invalidBlankPostalCreateTest() {
        JsonAdaptedProperty adaptedPropertyNoName =
                new JsonAdaptedProperty(WOODLANDS_CRESCENT.getName().toString(),
                        WOODLANDS_CRESCENT.getPropertyType().toString(),
                        WOODLANDS_CRESCENT.getAddress().toString(),
                        "",
                        " ",
                        WOODLANDS_CRESCENT.getDeadline().toString(),
                        null,
                        WOODLANDS_CRESCENT.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                        null);
        IllegalValueException thrown = assertThrows(IllegalValueException.class, adaptedPropertyNoName::toModelType);
        assertEquals(thrown.getMessage(), PostalCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void invalidNoDeadlineCreateTest() {
        JsonAdaptedProperty adaptedPropertyNoName =
                new JsonAdaptedProperty(WOODLANDS_CRESCENT.getName().toString(),
                        WOODLANDS_CRESCENT.getPropertyType().toString(),
                        WOODLANDS_CRESCENT.getAddress().toString(),
                        "",
                        WOODLANDS_CRESCENT.getPostalCode().toString(),
                        null,
                        null,
                        WOODLANDS_CRESCENT.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                        null);
        IllegalValueException thrown = assertThrows(IllegalValueException.class, adaptedPropertyNoName::toModelType);
        assertEquals(thrown.getMessage(),
                String.format(JsonAdaptedProperty.MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName()));
    }

    @Test
    public void parseStringToClientTest() throws IllegalValueException {
        assertEquals(JsonAdaptedProperty.fromStringToClient(CLIENT_EVE.toString()),
            CLIENT_EVE);
        IllegalValueException thrown = assertThrows(IllegalValueException.class, () ->
            JsonAdaptedProperty.fromStringToClient("This is no client"));
        assertEquals(thrown.getMessage(), INCORRECT_CLIENT_FIELD_MESSAGE);
    }

    @Test
    public void parseRemarkTest() throws IllegalValueException {
        assertEquals(JsonAdaptedProperty.parseToRemark(new Remark("Urgent to sell").toString()),
                new Remark("Urgent to sell"));
        IllegalValueException thrown = assertThrows(IllegalValueException.class, () ->
            JsonAdaptedProperty.parseToRemark(" "));
        assertEquals(thrown.getMessage(), Remark.MESSAGE_CONSTRAINTS);
    }
}

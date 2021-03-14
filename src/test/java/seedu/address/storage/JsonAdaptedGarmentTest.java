package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedGarment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGarments.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
<<<<<<< HEAD:src/test/java/seedu/address/storage/JsonAdaptedGarmentTest.java
import seedu.address.model.garment.Address;
import seedu.address.model.garment.Colour;
import seedu.address.model.garment.Name;
import seedu.address.model.garment.Size;
=======
import seedu.address.model.person.Colour;
import seedu.address.model.person.DressCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Size;
>>>>>>> a619ff895d28ff145a1161fbe2a7e1656407e12f:src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java

public class JsonAdaptedGarmentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_SIZE = "+22";
    private static final String INVALID_DRESSCODE = " ";
    private static final String INVALID_COLOUR = " ";
    private static final String INVALID_DESCRIPTION = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_SIZE = BENSON.getSize().toString();
    private static final String VALID_COLOUR = BENSON.getColour().toString();
    private static final String VALID_DRESSCODE = BENSON.getDressCode().toString();
    private static final List<JsonAdaptedDescription> VALID_DESCRIPTIONS = BENSON.getDescriptions().stream()
            .map(JsonAdaptedDescription::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validGarmentDetails_returnsGarment() throws Exception {
        JsonAdaptedGarment garment = new JsonAdaptedGarment(BENSON);
        assertEquals(BENSON, garment.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
<<<<<<< HEAD:src/test/java/seedu/address/storage/JsonAdaptedGarmentTest.java
        JsonAdaptedGarment garment =
                new JsonAdaptedGarment(INVALID_NAME, VALID_SIZE, VALID_COLOUR, VALID_ADDRESS, VALID_DESCRIPTIONS);
=======
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_SIZE, VALID_COLOUR, VALID_DRESSCODE, VALID_DESCRIPTIONS);
>>>>>>> a619ff895d28ff145a1161fbe2a7e1656407e12f:src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, garment::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
<<<<<<< HEAD:src/test/java/seedu/address/storage/JsonAdaptedGarmentTest.java
        JsonAdaptedGarment garment = new JsonAdaptedGarment(null, VALID_SIZE, VALID_COLOUR, VALID_ADDRESS,
=======
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_SIZE, VALID_COLOUR, VALID_DRESSCODE,
>>>>>>> a619ff895d28ff145a1161fbe2a7e1656407e12f:src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java
                VALID_DESCRIPTIONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, garment::toModelType);
    }

    @Test
    public void toModelType_invalidSize_throwsIllegalValueException() {
<<<<<<< HEAD:src/test/java/seedu/address/storage/JsonAdaptedGarmentTest.java
        JsonAdaptedGarment garment =
                new JsonAdaptedGarment(VALID_NAME, INVALID_SIZE, VALID_COLOUR, VALID_ADDRESS, VALID_DESCRIPTIONS);
=======
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_SIZE, VALID_COLOUR, VALID_DRESSCODE, VALID_DESCRIPTIONS);
>>>>>>> a619ff895d28ff145a1161fbe2a7e1656407e12f:src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java
        String expectedMessage = Size.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, garment::toModelType);
    }

    @Test
    public void toModelType_nullSize_throwsIllegalValueException() {
<<<<<<< HEAD:src/test/java/seedu/address/storage/JsonAdaptedGarmentTest.java
        JsonAdaptedGarment garment = new JsonAdaptedGarment(VALID_NAME, null, VALID_COLOUR, VALID_ADDRESS,
=======
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_COLOUR, VALID_DRESSCODE,
>>>>>>> a619ff895d28ff145a1161fbe2a7e1656407e12f:src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java
                VALID_DESCRIPTIONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Size.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, garment::toModelType);
    }

    @Test
    public void toModelType_invalidColour_throwsIllegalValueException() {
<<<<<<< HEAD:src/test/java/seedu/address/storage/JsonAdaptedGarmentTest.java
        JsonAdaptedGarment garment =
                new JsonAdaptedGarment(VALID_NAME, VALID_SIZE, INVALID_COLOUR, VALID_ADDRESS, VALID_DESCRIPTIONS);
=======
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_SIZE, INVALID_COLOUR, VALID_DRESSCODE, VALID_DESCRIPTIONS);
>>>>>>> a619ff895d28ff145a1161fbe2a7e1656407e12f:src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java
        String expectedMessage = Colour.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, garment::toModelType);
    }

    @Test
<<<<<<< HEAD:src/test/java/seedu/address/storage/JsonAdaptedGarmentTest.java
    public void toModelType_nullColour_throwsIllegalValueException() {
        JsonAdaptedGarment garment = new JsonAdaptedGarment(VALID_NAME, VALID_SIZE, null, VALID_ADDRESS,
=======
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_SIZE, null, VALID_DRESSCODE,
>>>>>>> a619ff895d28ff145a1161fbe2a7e1656407e12f:src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java
                VALID_DESCRIPTIONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Colour.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, garment::toModelType);
    }

    @Test
<<<<<<< HEAD:src/test/java/seedu/address/storage/JsonAdaptedGarmentTest.java
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedGarment garment =
                new JsonAdaptedGarment(VALID_NAME, VALID_SIZE, VALID_COLOUR, INVALID_ADDRESS, VALID_DESCRIPTIONS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, garment::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedGarment garment = new JsonAdaptedGarment(VALID_NAME, VALID_SIZE, VALID_COLOUR, null,
                VALID_DESCRIPTIONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, garment::toModelType);
=======
    public void toModelType_invalidDressCode_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_SIZE, VALID_COLOUR, INVALID_DRESSCODE, VALID_DESCRIPTIONS);
        String expectedMessage = DressCode.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDressCode_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_SIZE, VALID_COLOUR, null,
                VALID_DESCRIPTIONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DressCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
>>>>>>> a619ff895d28ff145a1161fbe2a7e1656407e12f:src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java
    }

    @Test
    public void toModelType_invalidDescriptions_throwsIllegalValueException() {
        List<JsonAdaptedDescription> invalidDescriptions = new ArrayList<>(VALID_DESCRIPTIONS);
        invalidDescriptions.add(new JsonAdaptedDescription(INVALID_DESCRIPTION));
<<<<<<< HEAD:src/test/java/seedu/address/storage/JsonAdaptedGarmentTest.java
        JsonAdaptedGarment garment =
                new JsonAdaptedGarment(VALID_NAME, VALID_SIZE, VALID_COLOUR, VALID_ADDRESS,
=======
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_SIZE, VALID_COLOUR, VALID_DRESSCODE,
>>>>>>> a619ff895d28ff145a1161fbe2a7e1656407e12f:src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java
                        invalidDescriptions);
        assertThrows(IllegalValueException.class, garment::toModelType);
    }

}

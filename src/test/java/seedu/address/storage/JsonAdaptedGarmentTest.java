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
import seedu.address.model.garment.Colour;
import seedu.address.model.garment.DressCode;
import seedu.address.model.garment.Name;
import seedu.address.model.garment.Size;

public class JsonAdaptedGarmentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_SIZE = "+22";
    private static final String INVALID_DRESSCODE = " ";
    private static final String INVALID_COLOUR = "magneto";
    private static final String INVALID_DESCRIPTION = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_SIZE = BENSON.getSize().toString();
    private static final String VALID_COLOUR = BENSON.getColour().toString();
    private static final String VALID_DRESSCODE = BENSON.getDressCode().toString();
    private static final String VALID_TYPE = BENSON.getType().toString();
    private static final List<JsonAdaptedDescription> VALID_DESCRIPTIONS = BENSON.getDescriptions().stream()
            .map(JsonAdaptedDescription::new)
            .collect(Collectors.toList());
    private static final String VALID_LASTUSE = "2021-03-22";

    @Test
    public void toModelType_validGarmentDetails_returnsGarment() throws Exception {
        JsonAdaptedGarment garment = new JsonAdaptedGarment(BENSON);
        assertEquals(BENSON, garment.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedGarment garment =
                new JsonAdaptedGarment(INVALID_NAME, VALID_SIZE, VALID_COLOUR, VALID_DRESSCODE, VALID_TYPE, VALID_DESCRIPTIONS, VALID_LASTUSE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, garment::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedGarment garment = new JsonAdaptedGarment(null, VALID_SIZE, VALID_COLOUR, VALID_DRESSCODE,
                VALID_TYPE, VALID_DESCRIPTIONS, VALID_LASTUSE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, garment::toModelType);
    }

    @Test
    public void toModelType_invalidSize_throwsIllegalValueException() {
        JsonAdaptedGarment garment =
                new JsonAdaptedGarment(VALID_NAME, INVALID_SIZE, VALID_COLOUR, "",
                        VALID_TYPE, VALID_DESCRIPTIONS, VALID_LASTUSE);
        String expectedMessage = Size.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, garment::toModelType);
    }

    @Test
    public void toModelType_nullSize_throwsIllegalValueException() {
        JsonAdaptedGarment garment = new JsonAdaptedGarment(VALID_NAME, null, VALID_COLOUR, VALID_DRESSCODE,
                VALID_TYPE, VALID_DESCRIPTIONS, VALID_LASTUSE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Size.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, garment::toModelType);
    }

    @Test
    public void toModelType_invalidColour_throwsIllegalValueException() {
        JsonAdaptedGarment garment =
                new JsonAdaptedGarment(VALID_NAME, VALID_SIZE, INVALID_COLOUR, VALID_DRESSCODE,
                        VALID_TYPE, VALID_DESCRIPTIONS, VALID_LASTUSE);
        String expectedMessage = Colour.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, garment::toModelType);
    }

    @Test
    public void toModelType_nullColour_throwsIllegalValueException() {
        JsonAdaptedGarment garment = new JsonAdaptedGarment(VALID_NAME, VALID_SIZE, null, VALID_DRESSCODE,
                VALID_TYPE, VALID_DESCRIPTIONS, VALID_LASTUSE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Colour.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, garment::toModelType);
    }

    @Test
    public void toModelType_invalidDressCode_throwsIllegalValueException() {
        JsonAdaptedGarment garment =
                new JsonAdaptedGarment(VALID_NAME, VALID_SIZE, VALID_COLOUR, INVALID_DRESSCODE,
                        VALID_TYPE, VALID_DESCRIPTIONS, VALID_LASTUSE);
        String expectedMessage = DressCode.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, garment::toModelType);
    }

    @Test
    public void toModelType_nullDressCode_throwsIllegalValueException() {
        JsonAdaptedGarment garment = new JsonAdaptedGarment(VALID_NAME, VALID_SIZE, VALID_COLOUR, null,
                VALID_TYPE, VALID_DESCRIPTIONS, VALID_LASTUSE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DressCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, garment::toModelType);
    }

    @Test
    public void toModelType_invalidDescriptions_throwsIllegalValueException() {
        List<JsonAdaptedDescription> invalidDescriptions = new ArrayList<>(VALID_DESCRIPTIONS);
        invalidDescriptions.add(new JsonAdaptedDescription(INVALID_DESCRIPTION));
        JsonAdaptedGarment garment =
                new JsonAdaptedGarment(VALID_NAME, VALID_SIZE, VALID_COLOUR, VALID_DRESSCODE,
                        VALID_TYPE, invalidDescriptions, VALID_LASTUSE);
        assertThrows(IllegalValueException.class, garment::toModelType);
    }

}

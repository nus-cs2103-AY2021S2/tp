package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_GARMENT;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.description.Description;
import seedu.address.model.garment.Colour;
import seedu.address.model.garment.DressCode;
import seedu.address.model.garment.Name;
import seedu.address.model.garment.Size;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_SIZE = "+65";
    private static final String INVALID_DRESSCODE = " ";
    private static final String INVALID_COLOUR = " ";
    private static final String INVALID_DESCRIPTION = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_SIZE = "12";
    private static final String VALID_DRESSCODE = "FORMAL";
    private static final String VALID_COLOUR = "blue";
    private static final String VALID_DESCRIPTION_1 = "friend";
    private static final String VALID_DESCRIPTION_2 = "neighbour";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_GARMENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_GARMENT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseSize_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSize((String) null));
    }

    @Test
    public void parseSize_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSize(INVALID_SIZE));
    }

    @Test
    public void parseSize_validValueWithoutWhitespace_returnsSize() throws Exception {
        Size expectedSize = new Size(VALID_SIZE);
        assertEquals(expectedSize, ParserUtil.parseSize(VALID_SIZE));
    }

    @Test
    public void parseSize_validValueWithWhitespace_returnsTrimmedSize() throws Exception {
        String sizeWithWhitespace = WHITESPACE + VALID_SIZE + WHITESPACE;
        Size expectedSize = new Size(VALID_SIZE);
        assertEquals(expectedSize, ParserUtil.parseSize(sizeWithWhitespace));
    }

    @Test
    public void parseDressCode_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDressCode((String) null));
    }

    @Test
    public void parseDressCode_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDressCode(INVALID_DRESSCODE));
    }

    @Test
    public void parseDressCode_validValueWithoutWhitespace_returnsAddress() throws Exception {
        DressCode expectedDressCode = new DressCode(VALID_DRESSCODE);
        assertEquals(expectedDressCode, ParserUtil.parseDressCode(VALID_DRESSCODE));
    }

    @Test
    public void parseDressCode_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_DRESSCODE + WHITESPACE;
        DressCode expectedDressCode = new DressCode(VALID_DRESSCODE);
        assertEquals(expectedDressCode, ParserUtil.parseDressCode(addressWithWhitespace));
    }

    @Test
    public void parseColour_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseColour((String) null));
    }

    @Test
    public void parseColour_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseColour(INVALID_COLOUR));
    }

    @Test
    public void parseColour_validValueWithoutWhitespace_returnsColour() throws Exception {
        Colour expectedColour = new Colour(VALID_COLOUR);
        assertEquals(expectedColour, ParserUtil.parseColour(VALID_COLOUR));
    }

    @Test
    public void parseColour_validValueWithWhitespace_returnsTrimmedColour() throws Exception {
        String colourWithWhitespace = WHITESPACE + VALID_COLOUR + WHITESPACE;
        Colour expectedColour = new Colour(VALID_COLOUR);
        assertEquals(expectedColour, ParserUtil.parseColour(colourWithWhitespace));
    }

    @Test
    public void parseDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDescription(null));
    }

    @Test
    public void parseDescription_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDescription(INVALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validValueWithoutWhitespace_returnsDescription() throws Exception {
        Description expectedDescription = new Description(VALID_DESCRIPTION_1);
        assertEquals(expectedDescription, ParserUtil.parseDescription(VALID_DESCRIPTION_1));
    }

    @Test
    public void parseDescription_validValueWithWhitespace_returnsTrimmedDescription() throws Exception {
        String descriptionWithWhitespace = WHITESPACE + VALID_DESCRIPTION_1 + WHITESPACE;
        Description expectedDescription = new Description(VALID_DESCRIPTION_1);
        assertEquals(expectedDescription, ParserUtil.parseDescription(descriptionWithWhitespace));
    }

    @Test
    public void parseDescriptions_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDescriptions(null));
    }

    @Test
    public void parseDescriptions_collectionWithInvalidDescriptions_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil
                .parseDescriptions(Arrays.asList(VALID_DESCRIPTION_1, INVALID_DESCRIPTION)));
    }

    @Test
    public void parseDescriptions_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseDescriptions(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseDescriptions_collectionWithValidDescriptions_returnsDescriptionSet() throws Exception {
        Set<Description> actualDescriptionSet = ParserUtil
                .parseDescriptions(Arrays.asList(VALID_DESCRIPTION_1, VALID_DESCRIPTION_2));
        Set<Description> expectedDescriptionSet = new HashSet<Description>(Arrays.asList(
                new Description(VALID_DESCRIPTION_1),
                new Description(VALID_DESCRIPTION_2)));

        assertEquals(expectedDescriptionSet, actualDescriptionSet);
    }
}

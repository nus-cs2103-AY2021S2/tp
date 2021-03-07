package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.name.Name;
import seedu.address.model.property.Address;
import seedu.address.model.property.Deadline;
import seedu.address.model.property.PostalCode;
import seedu.address.model.property.Type;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    public static final String INVALID_PROPERTY_NAME = "Mayfair&"; // '&' not allowed in names
    public static final String INVALID_PROPERTY_TYPE = "apartment"; // 'apartment' is not a valid type
    public static final String INVALID_PROPERTY_ADDRESS = ""; // empty string not allowed for addresses
    public static final String INVALID_PROPERTY_POSTAL = "12a"; // 'a' not allowed in postal codes
    public static final String INVALID_PROPERTY_DEADLINE = "31-04-2021"; // 31st April not valid

    public static final String VALID_PROPERTY_NAME = "Mayfair";
    public static final String VALID_PROPERTY_TYPE = "Condo";
    public static final String VALID_PROPERTY_ADDRESS = "1 Jurong East Street 32, #08-111";
    public static final String VALID_PROPERTY_POSTAL = "609477";
    public static final String VALID_PROPERTY_DEADLINE = "31-12-2021";
    public static final LocalDate VALID_PROPERTY_DEADLINE_DATE = LocalDate.parse(VALID_PROPERTY_DEADLINE,
            DateTimeFormatter.ofPattern("d-M-u").withResolverStyle(ResolverStyle.STRICT));

    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

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
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parsePropertyName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePropertyName((String) null));
    }

    @Test
    public void parsePropertyName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePropertyName(INVALID_PROPERTY_NAME));
    }

    @Test
    public void parsePropertyName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_PROPERTY_NAME);
        assertEquals(expectedName, ParserUtil.parsePropertyName(VALID_PROPERTY_NAME));
    }

    @Test
    public void parsePropertyName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_PROPERTY_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_PROPERTY_NAME);
        assertEquals(expectedName, ParserUtil.parsePropertyName(nameWithWhitespace));
    }

    @Test
    public void parsePropertyType_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePropertyType((String) null));
    }

    @Test
    public void parsePropertyType_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePropertyType(INVALID_PROPERTY_TYPE));
    }

    @Test
    public void parsePropertyType_validValueWithoutWhitespace_returnsType() throws Exception {
        Type expectedType = new Type(VALID_PROPERTY_TYPE);
        assertEquals(expectedType, ParserUtil.parsePropertyType(VALID_PROPERTY_TYPE));
    }

    @Test
    public void parsePropertyType_validValueWithWhitespace_returnsTrimmedType() throws Exception {
        String typeWithWhitespace = WHITESPACE + VALID_PROPERTY_TYPE + WHITESPACE;
        Type expectedType = new Type(VALID_PROPERTY_TYPE);
        assertEquals(expectedType, ParserUtil.parsePropertyType(typeWithWhitespace));
    }

    @Test
    public void parsePropertyAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePropertyAddress((String) null));
    }

    @Test
    public void parsePropertyAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePropertyAddress(INVALID_PROPERTY_ADDRESS));
    }

    @Test
    public void parsePropertyAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_PROPERTY_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parsePropertyAddress(VALID_PROPERTY_ADDRESS));
    }

    @Test
    public void parsePropertyAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_PROPERTY_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_PROPERTY_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parsePropertyAddress(addressWithWhitespace));
    }

    @Test
    public void parsePropertyPostal_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePropertyPostal((String) null));
    }

    @Test
    public void parsePropertyPostal_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePropertyPostal(INVALID_PROPERTY_POSTAL));
    }

    @Test
    public void parsePropertyPostal_validValueWithoutWhitespace_returnsPostal() throws Exception {
        PostalCode expectedPostal = new PostalCode(VALID_PROPERTY_POSTAL);
        assertEquals(expectedPostal, ParserUtil.parsePropertyPostal(VALID_PROPERTY_POSTAL));
    }

    @Test
    public void parsePropertyPostal_validValueWithWhitespace_returnsTrimmedPostal() throws Exception {
        String postalWithWhitespace = WHITESPACE + VALID_PROPERTY_POSTAL + WHITESPACE;
        PostalCode expectedPostal = new PostalCode(VALID_PROPERTY_POSTAL);
        assertEquals(expectedPostal, ParserUtil.parsePropertyPostal(postalWithWhitespace));
    }

    @Test
    public void parsePropertyDeadline_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePropertyDeadline((String) null));
    }

    @Test
    public void parsePropertyDeadline_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePropertyDeadline(INVALID_PROPERTY_DEADLINE));
    }

    @Test
    public void parsePropertyDeadline_validValueWithoutWhitespace_returnsDeadline() throws Exception {
        Deadline expectedDeadline = new Deadline(VALID_PROPERTY_DEADLINE_DATE);
        assertEquals(expectedDeadline, ParserUtil.parsePropertyDeadline(VALID_PROPERTY_DEADLINE));
    }

    @Test
    public void parsePropertyDeadline_validValueWithWhitespace_returnsTrimmedDeadline() throws Exception {
        String deadlineWithWhitespace = WHITESPACE + VALID_PROPERTY_DEADLINE + WHITESPACE;
        Deadline expectedDeadline = new Deadline(VALID_PROPERTY_DEADLINE_DATE);
        assertEquals(expectedDeadline, ParserUtil.parsePropertyDeadline(deadlineWithWhitespace));
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
        seedu.address.model.person.Name expectedName = new seedu.address.model.person.Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        seedu.address.model.person.Name expectedName = new seedu.address.model.person.Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        seedu.address.model.person.Phone expectedPhone = new seedu.address.model.person.Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        seedu.address.model.person.Phone expectedPhone = new seedu.address.model.person.Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        seedu.address.model.person.Address expectedAddress = new seedu.address.model.person.Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        seedu.address.model.person.Address expectedAddress = new seedu.address.model.person.Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        seedu.address.model.person.Email expectedEmail = new seedu.address.model.person.Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        seedu.address.model.person.Email expectedEmail = new seedu.address.model.person.Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }
}

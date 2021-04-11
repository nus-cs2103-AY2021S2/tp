package seedu.partyplanet.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.partyplanet.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.partyplanet.testutil.Assert.assertThrows;
import static seedu.partyplanet.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.partyplanet.logic.parser.exceptions.ParseException;
import seedu.partyplanet.model.date.Date;
import seedu.partyplanet.model.event.EventDate;
import seedu.partyplanet.model.name.Name;
import seedu.partyplanet.model.person.Address;
import seedu.partyplanet.model.person.Birthday;
import seedu.partyplanet.model.person.Email;
import seedu.partyplanet.model.person.Phone;
import seedu.partyplanet.model.remark.Remark;
import seedu.partyplanet.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_REMARK = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_REMARK = "Got some spare chicken puffs";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_DATE_ISO = "2020-05-02";
    private static final String VALID_MONTHDAY_ISO = "--05-02";

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
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
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
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseRemark() throws Exception {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRemark(null));

        // Test with whitespace
        String remarkWithWhitespace = WHITESPACE + VALID_REMARK + WHITESPACE;
        Remark expectedRemark = new Remark(VALID_REMARK);
        assertEquals(expectedRemark, ParserUtil.parseRemark(remarkWithWhitespace));

        // Test invalid value
        assertThrows(ParseException.class, () -> ParserUtil.parseRemark(INVALID_REMARK));
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
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
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

    @Test
    public void parseMonthInteger() throws Exception {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMonthInteger(null));

        // Check string months
        assertEquals(ParserUtil.parseMonthInteger("jan"), 1);
        assertEquals(ParserUtil.parseMonthInteger(WHITESPACE + "jan" + WHITESPACE), 1); // trimmed input
        assertEquals(ParserUtil.parseMonthInteger("feb"), 2);
        assertEquals(ParserUtil.parseMonthInteger("DEC"), 12);
        assertEquals(ParserUtil.parseMonthInteger("DeceMBeR"), 12);

        // Check integer months
        assertEquals(ParserUtil.parseMonthInteger("1"), 1);
        assertEquals(ParserUtil.parseMonthInteger("12"), 12);
        assertEquals(ParserUtil.parseMonthInteger(WHITESPACE + "5" + WHITESPACE), 5);

        // Check empty months
        assertEquals(ParserUtil.parseMonthInteger(""), Date.EMPTY_MONTH);
        assertEquals(ParserUtil.parseMonthInteger("0"), Date.EMPTY_MONTH);
        assertEquals(ParserUtil.parseMonthInteger(WHITESPACE), Date.EMPTY_MONTH); // trimmed input

        // Invalid input
        assertThrows(ParseException.class, () -> ParserUtil.parseMonthInteger("2.0"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMonthInteger("13"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMonthInteger("1 3"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMonthInteger("M"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMonthInteger("M AY")); // inner whitespace
    }

    @Test
    public void parseEventDate() throws Exception {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEventDate(null));

        // Check valid datetime formats
        EventDate validDate = new EventDate(VALID_DATE_ISO);
        assertEquals(validDate, ParserUtil.parseEventDate(VALID_DATE_ISO));
        assertEquals(validDate, ParserUtil.parseEventDate(WHITESPACE + VALID_DATE_ISO + WHITESPACE));

        // Check invalid datetime formats
        assertThrows(ParseException.class, () -> ParserUtil.parseEventDate("2020-5-2")); // incorrect ISO format
        assertThrows(ParseException.class, () -> ParserUtil.parseEventDate("2020-05-32")); // invalid event date
        assertThrows(ParseException.class, () -> ParserUtil.parseEventDate("--05-02")); // missing year
    }

    @Test
    public void parseBirthday() throws Exception {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseBirthday(null));

        // Check valid datetime formats
        Birthday validDate = new Birthday(VALID_DATE_ISO);
        assertEquals(validDate, ParserUtil.parseBirthday(VALID_DATE_ISO));
        assertEquals(validDate, ParserUtil.parseBirthday(WHITESPACE + VALID_DATE_ISO + WHITESPACE));

        Birthday validDateWithoutYear = new Birthday(VALID_MONTHDAY_ISO);
        assertEquals(validDateWithoutYear, ParserUtil.parseBirthday(VALID_MONTHDAY_ISO));

        // Check invalid datetime formats
        assertThrows(ParseException.class, () -> ParserUtil.parseBirthday("2020-5-2")); // incorrect ISO format
        assertThrows(ParseException.class, () -> ParserUtil.parseBirthday("3998-05-32")); // future date
    }
}

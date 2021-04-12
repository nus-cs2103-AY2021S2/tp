package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndices.INDEX_FIRST;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.ContactEmail;
import seedu.address.model.contact.ContactName;
import seedu.address.model.contact.ContactPhone;
import seedu.address.model.entry.EntryDate;
import seedu.address.model.entry.EntryName;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME_SYMBOLS = "R@chel";
    private static final String INVALID_NAME_LESS_THAN_2_CHARACTERS = "R";
    private static final String INVALID_NAME_START_WITH_NUMBER = "1Rachel";
    private static final String INVALID_NAME_END_WITH_NUMBER = "Rachel1";
    private static final String INVALID_PHONE_NOT_8_DIGITS = "123";
    private static final String INVALID_PHONE_STARTS_WITH_0 = "0123";
    private static final String INVALID_PHONE_HAS_SYMBOLS = "+123";
    private static final String INVALID_EMAIL_LOCAL_LESS_THAN_2_CHAR = "r@gmail";
    private static final String INVALID_EMAIL_LOCAL_INVALID_SYMBOLS = "[rachel@gmail";
    private static final String INVALID_EMAIL_DOMAIN_LESS_THAN_2_CHAR = "rachel@g";
    private static final String INVALID_EMAIL_DOMAIN_INVALID_SYMBOLS = "rachel@gmail+yahoo";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_ENTRY_NAME_BLANK = " ";
    private static final String INVALID_ENTRY_NAME_SYMBOLS = "$%^&*(";
    private static final String INVALID_ENTRY_DATE_BLANK = " ";
    private static final String INVALID_ENTRY_DATE_WRONG_DATE_FORMAT = "01-01-2022 12:12";
    private static final String INVALID_ENTRY_DATE_NO_TIME = "01-01-2022";
    private static final String INVALID_ENTRY_DATE_WRONG_TIME_FORMAT = "01-01-2022 12:12:12";
    private static final String INVALID_ENTRY_DATE_INVALID_TIME = "01-01-2022 12:12:12";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_NAME_WITH_HYPHEN = "Rachel Walker-Lim";
    private static final String VALID_NAME_AT_LEAST_2_CHARACTERS = "Ra";
    private static final String VALID_PHONE = "12345678";
    private static final String VALID_EMAIL = "rachel@example-gmail.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_ENTRY_NAME = "Consultation with teacher";
    private static final String VALID_ENTRY_DATE = "2022-01-01 12:12";

    //to be deleted --------------------------------
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";

    private static final String VALID_ADDRESS = "123 Main Street #0505";
    //---------------------------------------------------

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class,
                String.format("Index given: %s\n%s", Integer.MAX_VALUE + 1, MESSAGE_INVALID_INDEX), ()
                -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseContactName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseContactName((String) null));
    }

    @Test
    public void parseContactName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseContactName(INVALID_NAME_SYMBOLS));
        assertThrows(ParseException.class, () -> ParserUtil.parseContactName(INVALID_NAME_LESS_THAN_2_CHARACTERS));
        assertThrows(ParseException.class, () -> ParserUtil.parseContactName(INVALID_NAME_START_WITH_NUMBER));
        assertThrows(ParseException.class, () -> ParserUtil.parseContactName(INVALID_NAME_END_WITH_NUMBER));
    }

    @Test
    public void parseContactName_validValueWithoutWhitespace_returnsName() throws Exception {
        ContactName expectedName = new ContactName(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseContactName(VALID_NAME));

        expectedName = new ContactName(VALID_NAME_WITH_HYPHEN);
        assertEquals(expectedName, ParserUtil.parseContactName(VALID_NAME_WITH_HYPHEN));

        expectedName = new ContactName(VALID_NAME_AT_LEAST_2_CHARACTERS);
        assertEquals(expectedName, ParserUtil.parseContactName(VALID_NAME_AT_LEAST_2_CHARACTERS));
    }

    @Test
    public void parseContactName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        ContactName expectedName = new ContactName(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseContactName(nameWithWhitespace));
    }

    @Test
    public void parseContactPhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseContactPhone((String) null));
    }

    @Test
    public void parseContactPhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseContactPhone(INVALID_PHONE_HAS_SYMBOLS));
        assertThrows(ParseException.class, () -> ParserUtil.parseContactPhone(INVALID_PHONE_NOT_8_DIGITS));
        assertThrows(ParseException.class, () -> ParserUtil.parseContactPhone(INVALID_PHONE_STARTS_WITH_0));
    }

    @Test
    public void parseContactPhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        ContactPhone expectedPhone = new ContactPhone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parseContactPhone(VALID_PHONE));
    }

    @Test
    public void parseContactPhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        ContactPhone expectedPhone = new ContactPhone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parseContactPhone(phoneWithWhitespace));
    }

    @Test
    public void parseContactEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseContactEmail((String) null));
    }

    @Test
    public void parseContactEmail_invalidLocalPart_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseContactEmail(INVALID_EMAIL_LOCAL_LESS_THAN_2_CHAR));
        assertThrows(ParseException.class, () -> ParserUtil.parseContactEmail(INVALID_EMAIL_LOCAL_INVALID_SYMBOLS));
    }

    @Test
    public void parseContactEmail_invalidDomainPart_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseContactEmail(INVALID_EMAIL_DOMAIN_LESS_THAN_2_CHAR));
        assertThrows(ParseException.class, () -> ParserUtil.parseContactEmail(INVALID_EMAIL_DOMAIN_INVALID_SYMBOLS));
    }

    @Test
    public void parseContactEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        ContactEmail expectedEmail = new ContactEmail(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseContactEmail(VALID_EMAIL));
    }

    @Test
    public void parseContactEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        ContactEmail expectedEmail = new ContactEmail(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseContactEmail(emailWithWhitespace));
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
    public void parseEntryName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEntryName(null));
    }

    @Test
    public void parseEntryName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEntryName(INVALID_ENTRY_NAME_BLANK));
        assertThrows(ParseException.class, () -> ParserUtil.parseEntryName(INVALID_ENTRY_NAME_SYMBOLS));
    }

    @Test
    public void parseEntryName_validValueWithoutWhitespace_returnsEntryName() throws Exception {
        EntryName expectedEntryName = new EntryName(VALID_ENTRY_NAME);
        assertEquals(expectedEntryName, ParserUtil.parseEntryName(VALID_ENTRY_NAME));
    }

    @Test
    public void parseEntryDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEntryDate(null));
    }

    @Test
    public void parseEntryDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEntryDate(INVALID_ENTRY_DATE_BLANK));
        assertThrows(ParseException.class, () -> ParserUtil.parseEntryDate(INVALID_ENTRY_DATE_WRONG_DATE_FORMAT));
        assertThrows(ParseException.class, () -> ParserUtil.parseEntryDate(INVALID_ENTRY_DATE_NO_TIME));
        assertThrows(ParseException.class, () -> ParserUtil.parseEntryDate(INVALID_ENTRY_DATE_WRONG_TIME_FORMAT));
        assertThrows(ParseException.class, () -> ParserUtil.parseEntryDate(INVALID_ENTRY_DATE_INVALID_TIME));
    }

    @Test
    public void parseEntryDate_validValueWithoutWhitespace_returnsEntryDate() throws Exception {
        EntryDate expectedEntryDate = new EntryDate(VALID_ENTRY_DATE);
        assertEquals(expectedEntryDate, ParserUtil.parseEntryDate(VALID_ENTRY_DATE));
    }
}

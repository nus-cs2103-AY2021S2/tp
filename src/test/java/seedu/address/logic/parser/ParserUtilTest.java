package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Date;
import seedu.address.model.appointment.Time;
import seedu.address.model.name.Name;
import seedu.address.model.property.Address;
import seedu.address.model.property.Deadline;
import seedu.address.model.property.PostalCode;
import seedu.address.model.property.Type;
import seedu.address.model.property.client.AskingPrice;
import seedu.address.model.property.client.Contact;
import seedu.address.model.property.client.Email;
import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    public static final String INVALID_PROPERTY_NAME = "Mayfair&"; // '&' not allowed in names
    public static final String INVALID_PROPERTY_TYPE = "apartment"; // 'apartment' is not a valid type
    public static final String INVALID_PROPERTY_ADDRESS = ""; // empty string not allowed for addresses
    public static final String INVALID_PROPERTY_POSTAL = "12a"; // 'a' not allowed in postal codes
    public static final String INVALID_PROPERTY_DEADLINE = "31-04-2021"; // 31st April not valid
    public static final String INVALID_PROPERTY_REMARK = ""; // empty string not allowed for remarks
    public static final String INVALID_PROPERTY_TAG = "#Freehold"; // '#' not allowed in tags

    public static final String VALID_PROPERTY_NAME = "Mayfair";
    public static final String VALID_PROPERTY_TYPE = "Condo";
    public static final String VALID_PROPERTY_ADDRESS = "1 Jurong East Street 32, #08-111";
    public static final String VALID_PROPERTY_POSTAL = "609477";
    public static final String VALID_PROPERTY_DEADLINE = "31-12-2021";
    public static final LocalDate VALID_PROPERTY_DEADLINE_LOCALDATE = LocalDate.parse(VALID_PROPERTY_DEADLINE,
            DateTimeFormatter.ofPattern("d-M-u").withResolverStyle(ResolverStyle.STRICT));
    public static final String VALID_PROPERTY_REMARK = "Urgent to sell!";
    public static final String VALID_PROPERTY_TAG_1 = "Freehold";
    public static final String VALID_PROPERTY_TAG_2 = "5 bedrooms";

    public static final String INVALID_APPOINTMENT_NAME = "Meet Alex&"; // '&' not allowed in names
    public static final String INVALID_APPOINTMENT_REMARK = ""; // empty string not allowed for remarks
    public static final String INVALID_APPOINTMENT_DATE = "31-04-2021"; // 31st April not valid
    public static final String INVALID_APPOINTMENT_TIME = "1860"; // 1260 not valid

    public static final String VALID_APPOINTMENT_NAME = "Meet Alex";
    public static final String VALID_APPOINTMENT_REMARK = "At M Hotel";
    public static final String VALID_APPOINTMENT_DATE = "25-12-2021";
    public static final LocalDate VALID_APPOINTMENT_DATE_LOCALDATE = LocalDate.parse(VALID_APPOINTMENT_DATE,
            DateTimeFormatter.ofPattern("d-M-u").withResolverStyle(ResolverStyle.STRICT));
    public static final String VALID_APPOINTMENT_TIME = "1900";
    public static final LocalTime VALID_APPOINTMENT_TIME_LOCALTIME = LocalTime.parse(VALID_APPOINTMENT_TIME,
            DateTimeFormatter.ofPattern("HHmm"));

    public static final String INVALID_CLIENT_NAME = "Alice&"; // '&' not allowed in names
    public static final String INVALID_CLIENT_CONTACT = "+91234567"; // + not allowed
    public static final String INVALID_CLIENT_EMAIL = "alice.example.com"; // missing @
    public static final String INVALID_CLIENT_ASKING_PRICE = "$00800000"; // leading zeros not allowed

    public static final String VALID_CLIENT_NAME = "Alice";
    public static final String VALID_CLIENT_CONTACT = "91234567";
    public static final String VALID_CLIENT_EMAIL = "alice@gmail.com";
    public static final String VALID_CLIENT_ASKING_PRICE = "$800,000";

    private static final String WHITESPACE = " \t\r\n";

    // ===== Tests for general shared parser methods =============================================================

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
    public void parseName_null_returnsNull() throws Exception {
        assertNull(ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_PROPERTY_NAME));
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_APPOINTMENT_NAME));
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_CLIENT_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_PROPERTY_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_PROPERTY_NAME));

        expectedName = new Name(VALID_APPOINTMENT_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_APPOINTMENT_NAME));

        expectedName = new Name(VALID_CLIENT_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_CLIENT_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_PROPERTY_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_PROPERTY_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));

        nameWithWhitespace = WHITESPACE + VALID_APPOINTMENT_NAME + WHITESPACE;
        expectedName = new Name(VALID_APPOINTMENT_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));

        nameWithWhitespace = WHITESPACE + VALID_CLIENT_NAME + WHITESPACE;
        expectedName = new Name(VALID_CLIENT_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseRemark_null_returnsNull() throws Exception {
        assertNull(ParserUtil.parseRemark((String) null));
    }

    @Test
    public void parseRemark_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRemark(INVALID_PROPERTY_REMARK));
        assertThrows(ParseException.class, () -> ParserUtil.parseRemark(INVALID_APPOINTMENT_REMARK));
    }

    @Test
    public void parseRemark_validValueWithoutWhitespace_returnsRemark() throws Exception {
        Remark expectedRemark = new Remark(VALID_PROPERTY_REMARK);
        assertEquals(expectedRemark, ParserUtil.parseRemark(VALID_PROPERTY_REMARK));

        expectedRemark = new Remark(VALID_APPOINTMENT_REMARK);
        assertEquals(expectedRemark, ParserUtil.parseRemark(VALID_APPOINTMENT_REMARK));
    }

    @Test
    public void parseRemark_validValueWithWhitespace_returnsTrimmedRemark() throws Exception {
        String remarkWithWhitespace = WHITESPACE + VALID_PROPERTY_REMARK + WHITESPACE;
        Remark expectedRemark = new Remark(VALID_PROPERTY_REMARK);
        assertEquals(expectedRemark, ParserUtil.parseRemark(remarkWithWhitespace));

        remarkWithWhitespace = WHITESPACE + VALID_APPOINTMENT_REMARK + WHITESPACE;
        expectedRemark = new Remark(VALID_APPOINTMENT_REMARK);
        assertEquals(expectedRemark, ParserUtil.parseRemark(remarkWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_PROPERTY_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_PROPERTY_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_PROPERTY_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_PROPERTY_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_PROPERTY_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(null).isEmpty());
    }

    @Test
    public void parseTags_stringWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(VALID_PROPERTY_TAG_1 + ", "
                + INVALID_PROPERTY_TAG));
    }

    @Test
    public void parseTags_emptyString_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags("").isEmpty());
    }

    @Test
    public void parseTags_whitespacesString_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags("     ").isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(VALID_PROPERTY_TAG_1 + ", " + VALID_PROPERTY_TAG_2);
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_PROPERTY_TAG_1),
                new Tag(VALID_PROPERTY_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    // ===== Tests for property parser methods ===================================================================

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
        Deadline expectedDeadline = new Deadline(VALID_PROPERTY_DEADLINE_LOCALDATE);
        assertEquals(expectedDeadline, ParserUtil.parsePropertyDeadline(VALID_PROPERTY_DEADLINE));
    }

    @Test
    public void parsePropertyDeadline_validValueWithWhitespace_returnsTrimmedDeadline() throws Exception {
        String deadlineWithWhitespace = WHITESPACE + VALID_PROPERTY_DEADLINE + WHITESPACE;
        Deadline expectedDeadline = new Deadline(VALID_PROPERTY_DEADLINE_LOCALDATE);
        assertEquals(expectedDeadline, ParserUtil.parsePropertyDeadline(deadlineWithWhitespace));
    }

    // ===== Tests for client parser methods =====================================================================

    @Test
    public void parseClientContact_null_returnsNull() throws Exception {
        assertNull(ParserUtil.parseClientContact((String) null));
    }

    @Test
    public void parseClientContact_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseClientContact(INVALID_CLIENT_CONTACT));
    }

    @Test
    public void parseClientContact_validValueWithoutWhitespace_returnsContact() throws Exception {
        Contact expectedContact = new Contact(VALID_CLIENT_CONTACT);
        assertEquals(expectedContact, ParserUtil.parseClientContact(VALID_CLIENT_CONTACT));
    }

    @Test
    public void parseClientContact_validValueWithWhitespace_returnsTrimmedContact() throws Exception {
        String contactWithWhitespace = WHITESPACE + VALID_CLIENT_CONTACT + WHITESPACE;
        Contact expectedContact = new Contact(VALID_CLIENT_CONTACT);
        assertEquals(expectedContact, ParserUtil.parseClientContact(contactWithWhitespace));
    }
    @Test
    public void parseClientEmail_null_returnsNull() throws Exception {
        assertNull(ParserUtil.parseClientEmail((String) null));
    }

    @Test
    public void parseClientEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseClientEmail(INVALID_CLIENT_EMAIL));
    }

    @Test
    public void parseClientEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_CLIENT_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseClientEmail(VALID_CLIENT_EMAIL));
    }

    @Test
    public void parseClientEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_CLIENT_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_CLIENT_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseClientEmail(emailWithWhitespace));
    }
    @Test
    public void parseClientAskingPrice_null_returnsNull() throws Exception {
        assertNull(ParserUtil.parseClientAskingPrice((String) null));
    }

    @Test
    public void parseClientAskingPrice_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseClientAskingPrice(INVALID_CLIENT_ASKING_PRICE));
    }

    @Test
    public void parseClientAskingPrice_validValueWithoutWhitespace_returnsAskingPrice() throws Exception {
        AskingPrice expectedAskingPrice = new AskingPrice(VALID_CLIENT_ASKING_PRICE);
        assertEquals(expectedAskingPrice, ParserUtil.parseClientAskingPrice(VALID_CLIENT_ASKING_PRICE));
    }

    @Test
    public void parseClientAskingPrice_validValueWithWhitespace_returnsTrimmedAskingPrice() throws Exception {
        String askingPriceWithWhitespace = WHITESPACE + VALID_CLIENT_ASKING_PRICE + WHITESPACE;
        AskingPrice expectedAskingPrice = new AskingPrice(VALID_CLIENT_ASKING_PRICE);
        assertEquals(expectedAskingPrice, ParserUtil.parseClientAskingPrice(askingPriceWithWhitespace));
    }
    // ===== Tests for appointment parser methods ================================================================

    @Test
    public void parseAppointmentDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAppointmentDate((String) null));
    }

    @Test
    public void parseAppointmentDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointmentDate(INVALID_APPOINTMENT_DATE));
    }

    @Test
    public void parseAppointmentDate_validValueWithoutWhitespace_returnsDate() throws Exception {
        Date expectedDate = new Date(VALID_APPOINTMENT_DATE_LOCALDATE);
        assertEquals(expectedDate, ParserUtil.parseAppointmentDate(VALID_APPOINTMENT_DATE));
    }

    @Test
    public void parseAppointmentDate_validValueWithWhitespace_returnsTrimmedDate() throws Exception {
        String dateWithWhitespace = WHITESPACE + VALID_APPOINTMENT_DATE + WHITESPACE;
        Date expectedDate = new Date(VALID_APPOINTMENT_DATE_LOCALDATE);
        assertEquals(expectedDate, ParserUtil.parseAppointmentDate(dateWithWhitespace));
    }

    @Test
    public void parseAppointmentTime_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAppointmentTime((String) null));
    }

    @Test
    public void parseAppointmentTime_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointmentTime(INVALID_APPOINTMENT_TIME));
    }

    @Test
    public void parseAppointmentTime_validValueWithoutWhitespace_returnsTime() throws Exception {
        Time expectedTime = new Time(VALID_APPOINTMENT_TIME_LOCALTIME);
        assertEquals(expectedTime, ParserUtil.parseAppointmentTime(VALID_APPOINTMENT_TIME));
    }

    @Test
    public void parseAppointmentTime_validValueWithWhitespace_returnsTrimmedTime() throws Exception {
        String timeWithWhitespace = WHITESPACE + VALID_APPOINTMENT_TIME + WHITESPACE;
        Time expectedTime = new Time(VALID_APPOINTMENT_TIME_LOCALTIME);
        assertEquals(expectedTime, ParserUtil.parseAppointmentTime(timeWithWhitespace));
    }

}

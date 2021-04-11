package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDate;
import java.time.LocalTime;
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
import seedu.address.model.sort.descriptor.AppointmentSortingKey;
import seedu.address.model.sort.descriptor.PropertySortingKey;
import seedu.address.model.sort.descriptor.SortingOrder;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.DateTimeFormat;

public class ParserUtilTest {

    // for testing property parser methods
    public static final String INVALID_PROPERTY_NAME = "Mayfair&"; // '&' not allowed in names
    public static final String INVALID_PROPERTY_TYPE = "apartment"; // 'apartment' is not a valid type
    public static final String INVALID_PROPERTY_ADDRESS = ""; // empty string not allowed for addresses
    public static final String INVALID_PROPERTY_POSTAL = "12345a"; // 'a' not allowed in postal codes
    public static final String INVALID_PROPERTY_DEADLINE_INCORRECT_FORMAT = "30-4-2021"; // 1 digit in month part
    public static final String INVALID_PROPERTY_DEADLINE_CORRECT_FORMAT = "31-04-2021"; // 31st April not valid
    public static final String INVALID_PROPERTY_REMARK = ""; // empty string not allowed for remarks
    public static final String INVALID_PROPERTY_TAG = "#Freehold"; // '#' not allowed in tags

    public static final String VALID_PROPERTY_NAME = "Mayfair";
    public static final String VALID_PROPERTY_TYPE = "Condo";
    public static final String VALID_PROPERTY_ADDRESS = "1 Jurong East Street 32, #08-111";
    public static final String VALID_PROPERTY_POSTAL = "609477";
    public static final String VALID_PROPERTY_DEADLINE = "31-12-2021";
    public static final LocalDate VALID_PROPERTY_DEADLINE_LOCALDATE = LocalDate.parse(VALID_PROPERTY_DEADLINE,
            DateTimeFormat.INPUT_DATE_FORMAT);
    public static final String VALID_PROPERTY_REMARK = "Urgent to sell!";
    public static final String VALID_PROPERTY_TAG_1 = "Freehold";
    public static final String VALID_PROPERTY_TAG_2 = "5 bedrooms";
    public static final String VALID_PROPERTY_TAGS = "Freehold, 5 bedrooms";

    // for testing appointment parser methods
    public static final String INVALID_APPOINTMENT_NAME = "Meet Alex&"; // '&' not allowed in names
    public static final String INVALID_APPOINTMENT_REMARK = ""; // empty string not allowed for remarks
    public static final String INVALID_APPOINTMENT_DATE_INCORRECT_FORMAT = "30-4-2021"; // 1 digit in month part
    public static final String INVALID_APPOINTMENT_DATE_CORRECT_FORMAT = "31-04-2021"; // 31st April not valid
    public static final String INVALID_APPOINTMENT_TIME_INCORRECT_FORMAT = "930"; // 3 digits
    public static final String INVALID_APPOINTMENT_TIME_CORRECT_FORMAT = "1860"; // 1260 not valid

    public static final String VALID_APPOINTMENT_NAME = "Meet Alex";
    public static final String VALID_APPOINTMENT_REMARK = "At M Hotel";
    public static final String VALID_APPOINTMENT_DATE = "25-12-2021";
    public static final LocalDate VALID_APPOINTMENT_DATE_LOCALDATE = LocalDate.parse(VALID_APPOINTMENT_DATE,
            DateTimeFormat.INPUT_DATE_FORMAT);
    public static final String VALID_APPOINTMENT_TIME = "1900";
    public static final LocalTime VALID_APPOINTMENT_TIME_LOCALTIME = LocalTime.parse(VALID_APPOINTMENT_TIME,
            DateTimeFormat.INPUT_TIME_FORMAT);

    // for testing client parser methods
    public static final String INVALID_CLIENT_NAME = "Alice&"; // '&' not allowed in names
    public static final String INVALID_CLIENT_CONTACT = "+91234"; // Shorter than  7 digits
    public static final String INVALID_CLIENT_EMAIL = "alice.example.com"; // missing @
    public static final String INVALID_CLIENT_ASKING_PRICE = "$001000000"; // leading zeros not allowed

    public static final String VALID_CLIENT_NAME = "Alice";
    public static final String VALID_CLIENT_CONTACT = "91234567";
    public static final String VALID_CLIENT_EMAIL = "alice@gmail.com";
    public static final String VALID_CLIENT_ASKING_PRICE = "$1,000,000";

    // for testing sorting parser methods
    public static final String INVALID_APPOINTMENT_SORTING_KEY = "date"; // date is not an appointment sorting key
    public static final String INVALID_PROPERTY_SORTING_KEY = "datetime"; // datetime is not a property sorting key
    public static final String INVALID_SORTING_ORDER = "increasing"; // increasing is not a valid sorting order

    public static final String VALID_APPOINTMENT_SORTING_KEY = "datetime";
    public static final String VALID_PROPERTY_SORTING_KEY = "price";
    public static final String VALID_SORTING_ORDER = "asc";

    private static final String WHITESPACE = " \t\r\n";

    // ===== Tests for general shared parser methods =============================================================

    @Test
    public void parseIndex_invalidInput_throwsNumberFormatException() {
        assertThrows(NumberFormatException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsNumberFormatException() {
        assertThrows(NumberFormatException.class, null, ()
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
        Set<Tag> actualTagSet = ParserUtil.parseTags(VALID_PROPERTY_TAGS);
        Set<Tag> expectedTagSet = new HashSet<>(Arrays.asList(new Tag(VALID_PROPERTY_TAG_1),
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
        assertThrows(ParseException.class, () ->
                ParserUtil.parsePropertyDeadline(INVALID_PROPERTY_DEADLINE_INCORRECT_FORMAT));
        assertThrows(ParseException.class, () ->
                ParserUtil.parsePropertyDeadline(INVALID_PROPERTY_DEADLINE_CORRECT_FORMAT));
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
        Long askingPrice = Long.parseLong(VALID_CLIENT_ASKING_PRICE
                .replace("$", "")
                .replace(",", ""));
        AskingPrice expectedAskingPrice = new AskingPrice(askingPrice);
        assertEquals(expectedAskingPrice, ParserUtil.parseClientAskingPrice(VALID_CLIENT_ASKING_PRICE));
    }

    @Test
    public void parseClientAskingPrice_validValueWithWhitespace_returnsTrimmedAskingPrice() throws Exception {
        Long askingPrice = Long.parseLong(VALID_CLIENT_ASKING_PRICE
                .replace("$", "")
                .replace(",", ""));
        String askingPriceWithWhitespace = WHITESPACE + VALID_CLIENT_ASKING_PRICE + WHITESPACE;
        AskingPrice expectedAskingPrice = new AskingPrice(askingPrice);
        assertEquals(expectedAskingPrice, ParserUtil.parseClientAskingPrice(askingPriceWithWhitespace));
    }

    // ===== Tests for appointment parser methods ================================================================

    @Test
    public void parseAppointmentDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAppointmentDate((String) null));
    }

    @Test
    public void parseAppointmentDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () ->
                ParserUtil.parseAppointmentDate(INVALID_APPOINTMENT_DATE_INCORRECT_FORMAT));
        assertThrows(ParseException.class, () ->
                ParserUtil.parseAppointmentDate(INVALID_APPOINTMENT_DATE_CORRECT_FORMAT));
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
        assertThrows(ParseException.class, () ->
                ParserUtil.parseAppointmentTime(INVALID_APPOINTMENT_TIME_INCORRECT_FORMAT));
        assertThrows(ParseException.class, () ->
                ParserUtil.parseAppointmentTime(INVALID_APPOINTMENT_TIME_CORRECT_FORMAT));
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

    // ===== Tests for sorting parser methods ================================================================

    @Test
    public void parseAppointmentSortingKey_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAppointmentSortingKey((String) null));
    }

    @Test
    public void parseAppointmentSortingKey_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () ->
                ParserUtil.parseAppointmentSortingKey(INVALID_APPOINTMENT_SORTING_KEY));
    }

    @Test
    public void parseAppointmentSortingKey_validValueWithoutWhitespace_returnsAppointmentSortingKey()
            throws Exception {
        AppointmentSortingKey expectedAppointmentSortingKey =
                new AppointmentSortingKey(VALID_APPOINTMENT_SORTING_KEY);
        assertEquals(expectedAppointmentSortingKey,
                ParserUtil.parseAppointmentSortingKey(VALID_APPOINTMENT_SORTING_KEY));
    }

    @Test
    public void parseAppointmentSortingKey_validValueWithWhitespace_returnsTrimmedAppointmentSortingKey()
            throws Exception {
        String appointmentSortingKeyWithWhitespace = WHITESPACE + VALID_APPOINTMENT_SORTING_KEY + WHITESPACE;
        AppointmentSortingKey expectedAppointmentSortingKey =
                new AppointmentSortingKey(VALID_APPOINTMENT_SORTING_KEY);
        assertEquals(expectedAppointmentSortingKey,
                ParserUtil.parseAppointmentSortingKey(appointmentSortingKeyWithWhitespace));
    }

    @Test
    public void parsePropertySortingKey_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePropertySortingKey((String) null));
    }

    @Test
    public void parsePropertySortingKey_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () ->
                ParserUtil.parsePropertySortingKey(INVALID_PROPERTY_SORTING_KEY));
    }

    @Test
    public void parsePropertySortingKey_validValueWithoutWhitespace_returnsPropertySortingKey() throws Exception {
        PropertySortingKey expectedPropertySortingKey =
                new PropertySortingKey(VALID_PROPERTY_SORTING_KEY);
        assertEquals(expectedPropertySortingKey,
                ParserUtil.parsePropertySortingKey(VALID_PROPERTY_SORTING_KEY));
    }

    @Test
    public void parsePropertySortingKey_validValueWithWhitespace_returnsTrimmedPropertySortingKey() throws Exception {
        String propertySortingKeyWithWhitespace = WHITESPACE + VALID_PROPERTY_SORTING_KEY + WHITESPACE;
        PropertySortingKey expectedPropertySortingKey =
                new PropertySortingKey(VALID_PROPERTY_SORTING_KEY);
        assertEquals(expectedPropertySortingKey,
                ParserUtil.parsePropertySortingKey(propertySortingKeyWithWhitespace));
    }

    @Test
    public void parseSortingOrder_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSortingOrder((String) null));
    }

    @Test
    public void parseSortingOrder_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSortingOrder(INVALID_SORTING_ORDER));
    }

    @Test
    public void parseSortingOrder_validValueWithoutWhitespace_returnsSortingOrder() throws Exception {
        SortingOrder expectedSortingOrder = new SortingOrder(VALID_SORTING_ORDER);
        assertEquals(expectedSortingOrder, ParserUtil.parseSortingOrder(VALID_SORTING_ORDER));
    }

    @Test
    public void parseSortingOrder_validValueWithWhitespace_returnsTrimmedSortingOrder() throws Exception {
        String sortingOrderWithWhitespace = WHITESPACE + VALID_SORTING_ORDER + WHITESPACE;
        SortingOrder expectedSortingOrder = new SortingOrder(VALID_SORTING_ORDER);
        assertEquals(expectedSortingOrder, ParserUtil.parseSortingOrder(sortingOrderWithWhitespace));
    }

}

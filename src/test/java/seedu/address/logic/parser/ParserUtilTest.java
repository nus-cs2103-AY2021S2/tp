package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CUSTOMER;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.cheese.CheeseType;
import seedu.address.model.cheese.ExpiryDate;
import seedu.address.model.cheese.ManufactureDate;
import seedu.address.model.cheese.MaturityDate;
import seedu.address.model.customer.Address;
import seedu.address.model.customer.Email;
import seedu.address.model.customer.Name;
import seedu.address.model.customer.Phone;
import seedu.address.model.order.OrderDate;
import seedu.address.model.order.Quantity;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_CHEESE_TYPE = "123%@can";
    private static final String INVALID_QUANTITY = "-1";
    private static final String INVALID_ORDER_DATE = "03-20-2020";
    private static final String INVALID_MANUFACTURE_DATE = "2020/02/12";
    private static final String INVALID_MATURITY_DATE = "02-12-2020";
    private static final String INVALID_EXPIRY_DATE = "2020-30-12";
    private static final String INVALID_INTEGER = "-4";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_CHEESE_TYPE = "Parmesan";
    private static final String VALID_QUANTITY = "8";
    private static final String VALID_ORDER_DATE = "2020-02-12";
    private static final String VALID_MANUFACTURE_DATE = "2021-12-30";
    private static final String VALID_MATURITY_DATE = "2021-05-30";
    private static final String VALID_EXPIRY_DATE = "2021-08-31";
    private static final String VALID_INTEGER = "3";

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
        assertEquals(INDEX_FIRST_CUSTOMER, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_CUSTOMER, ParserUtil.parseIndex("  1  "));
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
    public void parseCheeseType_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCheeseType(null));
    }

    @Test
    public void parseCheeseType_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCheeseType(INVALID_CHEESE_TYPE));
    }

    @Test
    public void parseCheeseType_validValueWithoutWhitespace_returnsCheeseType() throws Exception {
        CheeseType expectedCheeseType = CheeseType.getCheeseType(VALID_CHEESE_TYPE);
        assertEquals(expectedCheeseType, ParserUtil.parseCheeseType(VALID_CHEESE_TYPE));
    }

    @Test
    public void parseCheeseType_validValueWithWhitespace_returnsTrimmedCheeseType() throws Exception {
        String cheeseTypeWithWhitespace = WHITESPACE + VALID_CHEESE_TYPE + WHITESPACE;
        CheeseType expectedCheeseType = CheeseType.getCheeseType(VALID_CHEESE_TYPE);
        assertEquals(expectedCheeseType, ParserUtil.parseCheeseType(cheeseTypeWithWhitespace));
    }

    @Test
    public void parseQuantity_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseQuantity(null));
    }

    @Test
    public void parseQuantity_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseQuantity(INVALID_QUANTITY));
    }

    @Test
    public void parseQuantity_validValueWithoutWhitespace_returnsQuantity() throws Exception {
        Quantity expectedQuantity = new Quantity(Integer.parseInt(VALID_QUANTITY));
        assertEquals(expectedQuantity, ParserUtil.parseQuantity(VALID_QUANTITY));
    }

    @Test
    public void parseQuantity_validValueWithWhitespace_returnsTrimmedQuantity() throws Exception {
        String quantityWithWhitespace = WHITESPACE + VALID_QUANTITY + WHITESPACE;
        Quantity expectedQuantity = new Quantity(Integer.parseInt(VALID_QUANTITY));
        assertEquals(expectedQuantity, ParserUtil.parseQuantity(quantityWithWhitespace));
    }

    @Test
    public void parseOrderDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseOrderDate(null));
    }

    @Test
    public void parseOrderDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseOrderDate(INVALID_ORDER_DATE));
    }

    @Test
    public void parseOrderDate_validValueWithoutWhitespace_returnsOrderDate() throws Exception {
        OrderDate expectedOrderDate = new OrderDate(VALID_ORDER_DATE);
        assertEquals(expectedOrderDate, ParserUtil.parseOrderDate(VALID_ORDER_DATE));
    }

    @Test
    public void parseOrderDate_validValueWithWhitespace_returnsTrimmedOrderDate() throws Exception {
        String orderDateWithWhitespace = WHITESPACE + VALID_ORDER_DATE + WHITESPACE;
        OrderDate expectedOrderDate = new OrderDate(VALID_ORDER_DATE);
        assertEquals(expectedOrderDate, ParserUtil.parseOrderDate(orderDateWithWhitespace));
    }


    @Test
    public void parseInteger_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseInteger(null));
    }

    @Test
    public void parseInteger_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseInteger(INVALID_INTEGER));
    }

    @Test
    public void parseInteger_validValueWithoutWhitespace_returnsInteger() throws Exception {
        int expectedInteger = Integer.parseInt(VALID_INTEGER);
        assertEquals(expectedInteger, ParserUtil.parseInteger(VALID_INTEGER));
    }

    @Test
    public void parseInteger_validValueWithWhitespace_returnsTrimmedInteger() throws Exception {
        String integerWithWhitespace = WHITESPACE + VALID_INTEGER + WHITESPACE;
        int expectedInteger = Integer.parseInt(VALID_INTEGER);
        assertEquals(expectedInteger, ParserUtil.parseInteger(integerWithWhitespace));
    }

    @Test
    public void parseManufactureDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseManufactureDate(null));
    }

    @Test
    public void parseManufactureDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseManufactureDate(INVALID_MANUFACTURE_DATE));
    }

    @Test
    public void parseManufactureDate_validValueWithoutWhitespace_returnsManufactureDate() throws Exception {
        ManufactureDate expectedManufactureDate = new ManufactureDate(VALID_MANUFACTURE_DATE);
        assertEquals(expectedManufactureDate, ParserUtil.parseManufactureDate(VALID_MANUFACTURE_DATE));
    }

    @Test
    public void parseManufactureDate_validValueWithWhitespace_returnsTrimmedManufactureDate() throws Exception {
        String manufactureDateWithWhitespace = WHITESPACE + VALID_MANUFACTURE_DATE + WHITESPACE;
        ManufactureDate expectedManufactureDate = new ManufactureDate(VALID_MANUFACTURE_DATE);
        assertEquals(expectedManufactureDate, ParserUtil.parseManufactureDate(manufactureDateWithWhitespace));
    }

    @Test
    public void parseMaturityDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMaturityDate(null));
    }

    @Test
    public void parseMaturityDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMaturityDate(INVALID_MATURITY_DATE));
    }

    @Test
    public void parseMaturityDate_validValueWithoutWhitespace_returnsMaturityDate() throws Exception {
        MaturityDate expectedMaturityDate = new MaturityDate(VALID_MATURITY_DATE);
        assertEquals(expectedMaturityDate, ParserUtil.parseMaturityDate(VALID_MATURITY_DATE));
    }

    @Test
    public void parseMaturityDate_validValueWithWhitespace_returnsTrimmedMaturityDate() throws Exception {
        String maturityDateWithWhitespace = WHITESPACE + VALID_MATURITY_DATE + WHITESPACE;
        MaturityDate expectedMaturityDate = new MaturityDate(VALID_MATURITY_DATE);
        assertEquals(expectedMaturityDate, ParserUtil.parseMaturityDate(maturityDateWithWhitespace));
    }

    @Test
    public void parseExpiryDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseExpiryDate(null));
    }

    @Test
    public void parseExpiryDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseExpiryDate(INVALID_EXPIRY_DATE));
    }

    @Test
    public void parseExpiryDate_validValueWithoutWhitespace_returnsExpiryDate() throws Exception {
        ExpiryDate expectedExpiryDate = new ExpiryDate(VALID_EXPIRY_DATE);
        assertEquals(expectedExpiryDate, ParserUtil.parseExpiryDate(VALID_EXPIRY_DATE));
    }

    @Test
    public void parseExpiryDate_validValueWithWhitespace_returnsTrimmedExpiryDate() throws Exception {
        String expiryDateWithWhitespace = WHITESPACE + VALID_EXPIRY_DATE + WHITESPACE;
        ExpiryDate expectedExpiryDate = new ExpiryDate(VALID_EXPIRY_DATE);
        assertEquals(expectedExpiryDate, ParserUtil.parseExpiryDate(expiryDateWithWhitespace));
    }
}

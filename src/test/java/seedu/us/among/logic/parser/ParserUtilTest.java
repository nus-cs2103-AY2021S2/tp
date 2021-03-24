package seedu.us.among.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.us.among.testutil.Assert.assertThrows;
import static seedu.us.among.testutil.TypicalIndexes.INDEX_FIRST_ENDPOINT;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.us.among.logic.parser.exceptions.ParseException;
import seedu.us.among.model.endpoint.Address;
import seedu.us.among.model.endpoint.Data;
import seedu.us.among.model.endpoint.Method;
import seedu.us.among.model.header.Header;
import seedu.us.among.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_DATA = "invaliddata";
    private static final String INVALID_HEADER = "invalidheader";

    private static final String VALID_NAME = "GET";
    private static final String VALID_ADDRESS = "https://address.get";
    private static final String VALID_ADDRESS2 = "address.get";
    private static final String VALID_TAG_1 = "cat";
    private static final String VALID_TAG_2 = "cool";
    private static final String VALID_DATA = "{\"valid\":\"data\"}";
    private static final String VALID_HEADER_1 = "\"valid: header\"";
    private static final String VALID_HEADER_2 = "\"valid2: header2\"";


    private static final String WHITESPACE = " ";

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
        Assertions.assertEquals(INDEX_FIRST_ENDPOINT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        Assertions.assertEquals(INDEX_FIRST_ENDPOINT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMethod((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMethod(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Method expectedName = new Method(VALID_NAME);
        Assertions.assertEquals(expectedName, ParserUtil.parseMethod(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Method expectedName = new Method(VALID_NAME);
        Assertions.assertEquals(expectedName, ParserUtil.parseMethod(nameWithWhitespace));
    }

    @Test
    public void parseData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseData((String) null));
    }

    @Test
    public void parseData_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseData(INVALID_DATA));
    }

    @Test
    public void parseData_validValueWithoutWhitespace_returnsData() throws Exception {
        Data expectedData = new Data(VALID_DATA);
        Assertions.assertEquals(expectedData, ParserUtil.parseData(VALID_DATA));
    }

    @Test
    public void parseData_validValueWithWhitespace_returnsTrimmedData() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_DATA + WHITESPACE;
        Data expectedPhone = new Data(VALID_DATA);
        Assertions.assertEquals(expectedPhone, ParserUtil.parseData(phoneWithWhitespace));
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
    public void parseAddress2_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address("http://" + VALID_ADDRESS2);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS2));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
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
        Assertions.assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        Assertions.assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
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
    public void parseHeader_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseHeader(null));
    }

    @Test
    public void parseHeader_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseHeader(INVALID_HEADER));
    }

    @Test
    public void parseHeader_validValueWithoutWhitespace_returnsTag() throws Exception {
        Header expectedHeader = new Header(VALID_HEADER_1);
        Assertions.assertEquals(expectedHeader, ParserUtil.parseHeader(VALID_HEADER_1));
    }

    @Test
    public void parseHeader_validValueWithWhitespace_returnsTrimmedHeader() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_HEADER_1 + WHITESPACE;
        Header expectedHeader = new Header(VALID_HEADER_1);
        Assertions.assertEquals(expectedHeader, ParserUtil.parseHeader(tagWithWhitespace));
    }

    @Test
    public void parseHeaders_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseHeaders(null));
    }

    @Test
    public void parsHeaders_collectionWithInvalidHeader_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseHeaders(
                Arrays.asList(VALID_HEADER_1, INVALID_HEADER)));
    }

    @Test
    public void parseHeaders_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseHeaders(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseHeaders_collectionWithValidHeaders_returnsHeadersSet() throws Exception {
        Set<Header> actualHeaderSet = ParserUtil.parseHeaders(Arrays.asList(VALID_HEADER_1, VALID_HEADER_2));
        Set<Header> expectedHeaderSet = new HashSet<Header>(
                Arrays.asList(new Header(VALID_HEADER_1), new Header(VALID_HEADER_2)));

        assertEquals(expectedHeaderSet, actualHeaderSet);
    }
}

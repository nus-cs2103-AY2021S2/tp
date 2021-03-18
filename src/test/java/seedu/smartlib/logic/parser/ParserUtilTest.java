package seedu.smartlib.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.smartlib.testutil.Assert.assertThrows;
import static seedu.smartlib.testutil.TypicalIndexes.INDEX_FIRST_READER;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.logic.parser.exceptions.ParseException;
import seedu.smartlib.model.book.Author;
import seedu.smartlib.model.book.Isbn;
import seedu.smartlib.model.book.Publisher;
import seedu.smartlib.model.reader.Address;
import seedu.smartlib.model.reader.Email;
import seedu.smartlib.model.reader.Phone;
import seedu.smartlib.model.tag.Tag;

public class ParserUtilTest {

    //=========== Books ==================================================================================
    private static final String INVALID_BOOK_NAME = "Sorcerer's Stone";
    private static final String INVALID_BOOK_AUTHOR = "J.K. Rowling";
    private static final String INVALID_BOOK_PUBLISHER = " ";
    private static final String INVALID_BOOK_ISBN = "1234567";

    private static final String VALID_BOOK_NAME = "A Promise Lane";
    private static final String VALID_BOOK_AUTHOR = "Barack Obama";
    private static final String VALID_BOOK_PUBLISHER = "Crown Publishing Group";
    private static final String VALID_BOOK_ISBN = "9781524763169";
    //=========== Readers ==================================================================================
    private static final String INVALID_READER_NAME = "R@chel";
    private static final String INVALID_READER_PHONE = "+651234";
    private static final String INVALID_READER_ADDRESS = " ";
    private static final String INVALID_READER_EMAIL = "example.com";
    private static final String INVALID_READER_TAG = "#friend";

    private static final String VALID_READER_NAME = "Rachel Walker";
    private static final String VALID_READER_PHONE = "123456";
    private static final String VALID_READER_ADDRESS = "123 Main Street #0505";
    private static final String VALID_READER_EMAIL = "rachel@example.com";
    private static final String VALID_READER_TAG_1 = "friend";
    private static final String VALID_READER_TAG_2 = "neighbour";

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
        assertEquals(INDEX_FIRST_READER, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_READER, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_READER_NAME));
    }

    @Test
    public void parseName_invalidBookValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_BOOK_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_READER_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_READER_NAME));
    }

    @Test
    public void parseName_validBookValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_BOOK_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_BOOK_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_READER_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_READER_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseName_validBookValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_BOOK_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_BOOK_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_READER_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_READER_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_READER_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_READER_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_READER_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_READER_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_READER_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_READER_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_READER_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_READER_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_READER_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_READER_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_READER_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_READER_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_READER_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_READER_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_READER_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_READER_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_READER_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_READER_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_READER_TAG_1,
                INVALID_READER_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_READER_TAG_1, VALID_READER_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_READER_TAG_1),
                new Tag(VALID_READER_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseAuthor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAuthor((String) null));
    }

    @Test
    public void parseAuthor_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAuthor(INVALID_BOOK_AUTHOR));
    }

    @Test
    public void parseAuthor_validValueWithoutWhitespace_returnsAuthor() throws Exception {
        Author expectedAuthor = new Author(new Name(VALID_BOOK_AUTHOR));
        assertEquals(expectedAuthor, ParserUtil.parseAuthor(VALID_BOOK_AUTHOR));
    }

    @Test
    public void parseAuthor_validValueWithWhitespace_returnsTrimmedAuthor() throws Exception {
        String authorWithWhitespace = WHITESPACE + VALID_BOOK_AUTHOR + WHITESPACE;
        Author expectedAuthor = new Author(new Name(VALID_BOOK_AUTHOR));
        assertEquals(expectedAuthor, ParserUtil.parseAuthor(authorWithWhitespace));
    }

    @Test
    public void parsePublisher_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePublisher((String) null));
    }

    @Test
    public void parsePublisher_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePublisher(INVALID_BOOK_PUBLISHER));
    }

    @Test
    public void parsePublisher_validValueWithoutWhitespace_returnsPublisher() throws Exception {
        Publisher expectedPublisher = new Publisher(new Name(VALID_BOOK_PUBLISHER));
        assertEquals(expectedPublisher, ParserUtil.parsePublisher(VALID_BOOK_PUBLISHER));
    }

    @Test
    public void parsePublisher_validValueWithWhitespace_returnsTrimmedPublisher() throws Exception {
        String publisherWithWhitespace = WHITESPACE + VALID_BOOK_PUBLISHER + WHITESPACE;
        Publisher expectedPublisher = new Publisher(new Name(VALID_BOOK_PUBLISHER));
        assertEquals(expectedPublisher, ParserUtil.parsePublisher(publisherWithWhitespace));
    }

    @Test
    public void parseIsbn_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseIsbn((String) null));
    }

    @Test
    public void parseIsbn_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIsbn(INVALID_BOOK_ISBN));
    }

    @Test
    public void parseIsbn_validValueWithoutWhitespace_returnsIsbn() throws Exception {
        Isbn expectedIsbn = new Isbn(VALID_BOOK_ISBN);
        assertEquals(expectedIsbn, ParserUtil.parseIsbn(VALID_BOOK_ISBN));
    }

    @Test
    public void parseIsbn_validValueWithWhitespace_returnsTrimmedIsbn() throws Exception {
        String isbnWithWhitespace = WHITESPACE + VALID_BOOK_ISBN + WHITESPACE;
        Isbn expectedIsbn = new Isbn(VALID_BOOK_ISBN);
        assertEquals(expectedIsbn, ParserUtil.parseIsbn(isbnWithWhitespace));
    }
}


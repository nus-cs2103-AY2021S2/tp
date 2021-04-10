package dog.pawbook.logic.parser;

import static dog.pawbook.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import dog.pawbook.logic.parser.exceptions.ParseException;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.dog.Breed;
import dog.pawbook.model.managedentity.dog.DateOfBirth;
import dog.pawbook.model.managedentity.dog.Sex;
import dog.pawbook.model.managedentity.owner.Address;
import dog.pawbook.model.managedentity.owner.Email;
import dog.pawbook.model.managedentity.owner.Phone;
import dog.pawbook.model.managedentity.program.Session;
import dog.pawbook.model.managedentity.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_BREED = "G0ldie";
    private static final String INVALID_DATE = "12-aa-2020";
    private static final String INVALID_SEX = "none";
    private static final String INVALID_ID = "-1";
    private static final String INVALID_SESSION = "12-aa-2020 22:12";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_BREED = "Golden Retriever";
    private static final String VALID_DATE = "12-12-2020";
    private static final String VALID_SEX = "Male";
    private static final String VALID_ID_1 = "1";
    private static final String VALID_ID_2 = "2";
    private static final String VALID_SESSION_1 = "12-12-2020 22:12";
    private static final String VALID_SESSION_2 = "01-12-2021 11:33";

    private static final String WHITESPACE = " \t\r\n";

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
        Set<Tag> expectedTagSet = new HashSet<>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseBreed_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseBreed((String) null));
    }

    @Test
    public void parseBreed_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBreed(INVALID_BREED));
    }

    @Test
    public void parseBreed_validValueWithoutWhitespace_returnsBreed() throws Exception {
        Breed expectedBreed = new Breed(VALID_BREED);
        assertEquals(expectedBreed, ParserUtil.parseBreed(VALID_BREED));
    }

    @Test
    public void parseBreed_validValueWithWhitespace_returnsTrimmedBreed() throws Exception {
        String breedWithWhitespace = WHITESPACE + VALID_BREED + WHITESPACE;
        Breed expectedBreed = new Breed(VALID_BREED);
        assertEquals(expectedBreed, ParserUtil.parseBreed(breedWithWhitespace));
    }

    @Test
    public void parseDateOfBirth_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDob((String) null));
    }

    @Test
    public void parseDateOfBirth_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDob(INVALID_DATE));
    }

    @Test
    public void parseDateOfBirth_validValueWithoutWhitespace_returnsDateOfBirth() throws Exception {
        DateOfBirth expectedDate = new DateOfBirth(VALID_DATE);
        assertEquals(expectedDate, ParserUtil.parseDob(VALID_DATE));
    }

    @Test
    public void parseDateOfBirth_validValueWithWhitespace_returnsTrimmedDateOfBirth() throws Exception {
        String dateWithWhitespace = WHITESPACE + VALID_DATE + WHITESPACE;
        DateOfBirth expectedDate = new DateOfBirth(VALID_DATE);
        assertEquals(expectedDate, ParserUtil.parseDob(dateWithWhitespace));
    }

    @Test
    public void parseSex_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSex((String) null));
    }

    @Test
    public void parseSex_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSex(INVALID_SEX));
    }

    @Test
    public void parseSex_validValueWithoutWhitespace_returnsSex() throws Exception {
        Sex expectedSex = new Sex(VALID_SEX);
        assertEquals(expectedSex, ParserUtil.parseSex(VALID_SEX));
    }

    @Test
    public void parseSex_validValueWithWhitespace_returnsTrimmedSex() throws Exception {
        String sexWithWhitespace = WHITESPACE + VALID_SEX + WHITESPACE;
        Sex expectedSex = new Sex(VALID_SEX);
        assertEquals(expectedSex, ParserUtil.parseSex(sexWithWhitespace));
    }

    @Test
    public void parseId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseId((String) null));
    }

    @Test
    public void parseId_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseId(INVALID_ID));
    }

    @Test
    public void parseId_validValueWithoutWhitespace_returnsId() throws Exception {
        int expectedId = 1;
        assertEquals(expectedId, ParserUtil.parseId(VALID_ID_1));
    }

    @Test
    public void parseId_validValueWithWhitespace_returnsTrimmedId() throws Exception {
        String idWithWhitespace = WHITESPACE + VALID_ID_1 + WHITESPACE;
        int expectedId = 1;
        assertEquals(expectedId, ParserUtil.parseId(idWithWhitespace));
    }

    @Test
    public void parseIds_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseIds(null));
    }

    @Test
    public void parseIds_collectionWithInvalidIds_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIds(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseIds_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseIds(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseIds_collectionWithValidIds_returnsIdSet() throws Exception {
        Set<Integer> actualIdSet = ParserUtil.parseIds(Arrays.asList(VALID_ID_1, VALID_ID_2));
        Set<Integer> expectedIdSet = new HashSet<>(Arrays.asList(1, 2));

        assertEquals(expectedIdSet, actualIdSet);
    }

    @Test
    public void parseSession_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSession((String) null));
    }

    @Test
    public void parseSession_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSession(INVALID_SESSION));
    }

    @Test
    public void parseSession_validValueWithoutWhitespace_returnsSession() throws Exception {
        Session expectedSession = new Session(VALID_SESSION_1);
        assertEquals(expectedSession, ParserUtil.parseSession(VALID_SESSION_1));
    }

    @Test
    public void parseSession_validValueWithWhitespace_returnsTrimmedSession() throws Exception {
        String sessionWithWhitespace = WHITESPACE + VALID_SESSION_1 + WHITESPACE;
        Session expectedSession = new Session(VALID_SESSION_1);
        assertEquals(expectedSession, ParserUtil.parseSession(sessionWithWhitespace));
    }

    @Test
    public void parseSessions_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSessions(null));
    }

    @Test
    public void parseSessions_collectionWithInvalidSessions_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSessions(
                Arrays.asList(VALID_SESSION_1, INVALID_SESSION)));
    }

    @Test
    public void parseSessions_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseSessions(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseSessions_collectionWithValidSessions_returnsSessionset() throws Exception {
        Set<Session> actualSessionSet = ParserUtil.parseSessions(Arrays.asList(VALID_SESSION_1, VALID_SESSION_2));
        Set<Session> expectedSessionSet = new HashSet<>(
                Arrays.asList(new Session(VALID_SESSION_1), new Session(VALID_SESSION_2)));

        assertEquals(expectedSessionSet, actualSessionSet);
    }
}

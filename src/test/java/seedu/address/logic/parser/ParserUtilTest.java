package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INDEX_IS_WORD;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_BATCH_INDICES;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.insurancepolicy.InsurancePolicy;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_POLICYID_NO_URL = "Policy>123";
    private static final String INVALID_POLICYID_VALID_URL = "Policy>12345>www.google.com";
    private static final String INVALID_POLICYID_INVALID_URL = "Policy>12345>!@#$%^&*()";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_POLICYID_1 = "Policy_1234";
    private static final String VALID_POLICYID_2 = "Policy_1235";
    private static final String VALID_POLICY_URL = "www.google.com";
    private static final String VALID_POLICYID_NO_URL = "Policy_1234";
    private static final String VALID_POLICYID_INVALID_URL = "Policy_1235>!@#$%^&*()";
    private static final String VALID_POLICYID_VALID_URL = "Policy_1235>www.google.com";

    private static final String WHITESPACE = " \t\r\n";

    private static final String INVALID_INPUT_INDICES_ZERO = "0, 1, 2";
    private static final String INVALID_INPUT_INDICES_HUGE = "1, 2, 10000000000000000000000000000000000000";
    private static final String INVALID_INPUT_INDICES_ALL_NUMBERS_NO_COMMA = "1 2 3";
    private static final String INVALID_INPUT_INDICES_WITH_WORD_NO_COMMA = "1 2 lol";
    private static final String INVALID_INPUT_INDICES_WITH_WORD_WITH_COMMA = "1, 2, lol";
    private static final String INVALID_INPUT_INDICES_REPEATED_INDICES = "1 1 1";

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
    public void parseIndices_invalidInput_throwsParseException() {
        // Input indices contain some indices which are less than 1
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndices(INVALID_INPUT_INDICES_ZERO));

        // Input indices contain some indices which are huge
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndices(INVALID_INPUT_INDICES_HUGE));

        // Input all numbers but not separated by commas
        assertThrows(ParseException.class, MESSAGE_INVALID_BATCH_INDICES, ()
            -> ParserUtil.parseIndices(INVALID_INPUT_INDICES_ALL_NUMBERS_NO_COMMA));

        // Input has word and not separated by commas
        assertThrows(ParseException.class, MESSAGE_INDEX_IS_WORD, ()
            -> ParserUtil.parseIndices(INVALID_INPUT_INDICES_WITH_WORD_NO_COMMA));

        // Input has word but separated by commas
        assertThrows(ParseException.class, MESSAGE_INDEX_IS_WORD, ()
            -> ParserUtil.parseIndices(INVALID_INPUT_INDICES_WITH_WORD_WITH_COMMA));

        // Repeated indices in input
        assertThrows(ParseException.class, MESSAGE_INVALID_BATCH_INDICES, ()
            -> ParserUtil.parseIndices(INVALID_INPUT_INDICES_REPEATED_INDICES));

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
    public void parsePolicies_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePolicies(null));
    }

    @Test
    public void parsePolicies_collectionWithInvalidPolicyId_throwsParseException() {
        // Tests invalid policy ID with no URL
        assertThrows(ParseException.class, InsurancePolicy.MESSAGE_CONSTRAINTS, ()
            -> ParserUtil.parsePolicies(Arrays.asList(INVALID_POLICYID_NO_URL)));

        // Tests invalid policy ID with valid URL
        assertThrows(ParseException.class, InsurancePolicy.MESSAGE_CONSTRAINTS, ()
            -> ParserUtil.parsePolicies(Arrays.asList(VALID_POLICYID_NO_URL, INVALID_POLICYID_VALID_URL)));

        // Tests invalid policy ID with invalid URL
        assertThrows(ParseException.class, InsurancePolicy.MESSAGE_CONSTRAINTS, ()
            -> ParserUtil.parsePolicies(Arrays.asList(VALID_POLICYID_NO_URL, INVALID_POLICYID_INVALID_URL)));
    }

    @Test
    public void parsePolicies_collectionWithInvalidPolicyUrl_throwsParseException() {
        // Tests invalid policy URL with valid policy ID
        assertThrows(ParseException.class, InsurancePolicy.MESSAGE_CONSTRAINTS, ()
            -> ParserUtil.parsePolicies(Arrays.asList(VALID_POLICYID_NO_URL, VALID_POLICYID_INVALID_URL)));
    }

    @Test
    public void parsePolicies_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parsePolicies(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parsePolicies_collectionWithValidPolicies_returnsPoliciesList() throws Exception {
        Set<InsurancePolicy> actualPolicySet = ParserUtil.parsePolicies(Arrays.asList(VALID_POLICYID_NO_URL,
            VALID_POLICYID_VALID_URL));
        Set<InsurancePolicy> expectedPolicyList = new HashSet<>(Arrays.asList(new InsurancePolicy(VALID_POLICYID_1),
                new InsurancePolicy(VALID_POLICYID_2, VALID_POLICY_URL)));

        assertEquals(expectedPolicyList, actualPolicySet);
    }
}

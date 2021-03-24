package seedu.module.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.module.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.module.testutil.Assert.assertThrows;
import static seedu.module.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.module.logic.parser.exceptions.ParseException;
import seedu.module.model.tag.Tag;
import seedu.module.model.task.Description;
import seedu.module.model.task.Module;
import seedu.module.model.task.Name;
import seedu.module.model.task.Time;
import seedu.module.model.task.Workload;


public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_DEADLINE = "+651234";
    private static final String INVALID_DESCRIPTION = " ";
    private static final String INVALID_MODULE = "example.com";
    private static final String INVALID_WORKLOAD = "1 a";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Midterm";
    private static final String VALID_DEADLINE = "2021-03-06 12:00";
    private static final String VALID_DESCRIPTION = "It may be not easy.";
    private static final String VALID_MODULE = "CS3230";
    private static final String VALID_WORKLOAD = "1";
    private static final String VALID_TAG_1 = "priorityHigh";
    private static final String VALID_TAG_2 = "workloadMedian";

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
        assertEquals(INDEX_FIRST_TASK, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_TASK, ParserUtil.parseIndex("  1  "));
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
    public void parseDeadline_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTime((String) null));
    }

    @Test
    public void parseDeadline_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTime(INVALID_DEADLINE));
    }

    @Test
    public void parseDeadline_validValueWithoutWhitespace_returnsDeadline() throws Exception {
        Time expectedDeadline = new Time(VALID_DEADLINE);
        assertEquals(expectedDeadline, ParserUtil.parseTime(VALID_DEADLINE));
    }

    @Test
    public void parseDeadline_validValueWithWhitespace_returnsTrimmedDeadline() throws Exception {
        String deadlineWithWhitespace = WHITESPACE + VALID_DEADLINE + WHITESPACE;
        Time expectedDeadline = new Time(VALID_DEADLINE);
        assertEquals(expectedDeadline, ParserUtil.parseTime(deadlineWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDescription((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDescription(INVALID_DESCRIPTION));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Description expectedDescription = new Description(VALID_DESCRIPTION);
        assertEquals(expectedDescription, ParserUtil.parseDescription(VALID_DESCRIPTION));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String descriptionWithWhitespace = WHITESPACE + VALID_DESCRIPTION + WHITESPACE;
        Description expectedDescription = new Description(VALID_DESCRIPTION);
        assertEquals(expectedDescription, ParserUtil.parseDescription(descriptionWithWhitespace));
    }

    @Test
    public void parseModule_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseModule((String) null));
    }

    @Test
    public void parseModule_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseModule(INVALID_MODULE));
    }

    @Test
    public void parseModule_validValueWithoutWhitespace_returnsModule() throws Exception {
        Module expectedModule = new Module(VALID_MODULE);
        assertEquals(expectedModule, ParserUtil.parseModule(VALID_MODULE));
    }

    @Test
    public void parseModule_validValueWithWhitespace_returnsTrimmedModule() throws Exception {
        String moduleWithWhitespace = WHITESPACE + VALID_MODULE + WHITESPACE;
        Module expectedModule = new Module(VALID_MODULE);
        assertEquals(expectedModule, ParserUtil.parseModule(moduleWithWhitespace));
    }

    @Test
    public void parseWorkload_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseWorkload(null));
    }

    @Test
    public void parseWorkload_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_WORKLOAD));
    }

    @Test
    public void parseWorkload_validValueWithoutWhitespace_returnsWorkload() throws Exception {
        Workload expectedWorkload = new Workload(VALID_WORKLOAD);
        assertEquals(expectedWorkload, ParserUtil.parseWorkload(VALID_WORKLOAD));
    }

    @Test
    public void parseWorkload_validValueWithWhitespace_returnsTrimmedWorkload() throws Exception {
        String workloadWithWhitespace = WHITESPACE + VALID_WORKLOAD + WHITESPACE;
        Workload expectedWorkload = new Workload(VALID_WORKLOAD);
        assertEquals(expectedWorkload, ParserUtil.parseWorkload(workloadWithWhitespace));
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

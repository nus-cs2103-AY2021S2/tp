package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.DeadlineDate;
import seedu.address.model.person.DeadlineTime;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.TaskName;
import seedu.address.model.person.Weightage;
import seedu.address.model.tag.PriorityTag;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_CODE = "SC7666";
    private static final String INVALID_DATE = "32-13-2000";
    private static final String INVALID_TIME = "24:00";
    private static final String INVALID_WEIGHTAGE = "101%";
    private static final String INVALID_PRIORITY = "EASY";


    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_CODE = "CS1236";
    private static final String VALID_DATE = "31-01-2030";
    private static final String VALID_TIME = "23:00";
    private static final String VALID_WEIGHTAGE = "11%";
    private static final String VALID_PRIORITY = "LOW";


    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("a 10"));
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("3 2 1"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Long.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_TASK, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_TASK, ParserUtil.parseIndex("  1  "));
    }

    // TaskName
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
        TaskName expectedModuleName = new TaskName(VALID_NAME);
        assertEquals(expectedModuleName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        TaskName expectedModuleName = new TaskName(VALID_NAME);
        assertEquals(expectedModuleName, ParserUtil.parseName(nameWithWhitespace));
    }

    // ModuleCode
    @Test
    public void parseCode_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCode((String) null));
    }

    @Test
    public void parseCode_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCode(INVALID_CODE));
    }

    @Test
    public void parseCode_validValueWithoutWhitespace_returnsCode() throws Exception {
        ModuleCode expectedModuleCode = new ModuleCode(VALID_CODE);
        assertEquals(expectedModuleCode, ParserUtil.parseCode(VALID_CODE));
    }

    @Test
    public void parseCode_validValueWithWhitespace_returnsTrimmedCode() throws Exception {
        String codeWithWhitespace = WHITESPACE + VALID_CODE + WHITESPACE;
        ModuleCode expectedModuleCode = new ModuleCode(VALID_CODE);
        assertEquals(expectedModuleCode, ParserUtil.parseCode(codeWithWhitespace));
    }

    // DeadlineDate
    @Test
    public void parseDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDeadlineDate((String) null));
    }

    @Test
    public void parseDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadlineDate(INVALID_DATE));
    }

    @Test
    public void parseDate_validValueWithoutWhitespace_returnsDate() throws Exception {
        DeadlineDate expectedDeadlineDate = new DeadlineDate(VALID_DATE);
        assertEquals(expectedDeadlineDate, ParserUtil.parseDeadlineDate(VALID_DATE));
    }

    @Test
    public void parseDate_validValueWithWhitespace_returnsTrimmedDate() throws Exception {
        String dateWithWhitespace = WHITESPACE + VALID_DATE + WHITESPACE;
        DeadlineDate expectedModuleDate = new DeadlineDate(VALID_DATE);
        assertEquals(expectedModuleDate, ParserUtil.parseDeadlineDate(dateWithWhitespace));
    }

    // DeadlineTime
    @Test
    public void parseTime_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDeadlineTime((String) null));
    }

    @Test
    public void parseTime_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadlineTime(INVALID_TIME));
    }

    @Test
    public void parseTime_validValueWithoutWhitespace_returnsTime() throws Exception {
        DeadlineTime expectedDeadlineTime = new DeadlineTime(VALID_TIME);
        assertEquals(expectedDeadlineTime, ParserUtil.parseDeadlineTime(VALID_TIME));
    }

    @Test
    public void parseTime_validValueWithWhitespace_returnsTrimmedTime() throws Exception {
        String timeWithWhitespace = WHITESPACE + VALID_TIME + WHITESPACE;
        DeadlineTime expectedDeadlineTime = new DeadlineTime(VALID_TIME);
        assertEquals(expectedDeadlineTime, ParserUtil.parseDeadlineTime(timeWithWhitespace));
    }

    // Weightage
    @Test
    public void parseWeightage_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseWeightage((String) null));
    }

    @Test
    public void parseWeightage_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseWeightage(INVALID_WEIGHTAGE));
    }

    @Test
    public void parseWeightage_validValueWithoutWhitespace_returnsWeightage() throws Exception {
        Weightage expectedWeightage = new Weightage(11);
        assertEquals(expectedWeightage, ParserUtil.parseWeightage(VALID_WEIGHTAGE));
    }

    @Test
    public void parseWeightage_validValueWithWhitespace_returnsTrimmedWeightage() throws Exception {
        String weightageWithWhitespace = WHITESPACE + VALID_WEIGHTAGE + WHITESPACE;
        Weightage expectedWeightage = new Weightage(11);
        assertEquals(expectedWeightage, ParserUtil.parseWeightage(weightageWithWhitespace));
    }

    // Priority tag
    @Test
    public void parsePriorityTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePriorityTag((String) null));
    }

    @Test
    public void parsePriorityTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePriorityTag(INVALID_PRIORITY));
    }

    @Test
    public void parsePriorityTag_validValueWithoutWhitespace_returnsPriorityTag() throws Exception {
        PriorityTag expectedPriority = new PriorityTag(VALID_PRIORITY);
        assertEquals(expectedPriority, ParserUtil.parsePriorityTag(VALID_PRIORITY));
    }

    @Test
    public void parsePriorityTag_validValueWithWhitespace_returnsTrimmedPriorityTag() throws Exception {
        String priorityWithWhitespace = WHITESPACE + VALID_PRIORITY + WHITESPACE;
        PriorityTag expectedPriority = new PriorityTag(VALID_PRIORITY);
        assertEquals(expectedPriority, ParserUtil.parsePriorityTag(priorityWithWhitespace));
    }

    // Single tag
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

    // Multiple tags
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

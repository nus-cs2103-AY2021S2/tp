package seedu.weeblingo.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.weeblingo.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.weeblingo.testutil.Assert.assertThrows;
import static seedu.weeblingo.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.weeblingo.logic.parser.exceptions.ParseException;
import seedu.weeblingo.model.flashcard.Answer;
import seedu.weeblingo.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_ANSWER = " ";
    private static final String INVALID_QUESTION = "how do you spell elephant?";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_ANSWER = "a";
    private static final String VALID_QUESTION = "あ";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

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
        assertEquals(INDEX_FIRST_FLASHCARD, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_FLASHCARD, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseAnswer_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAnswer((String) null));
    }

    @Test
    public void parseAnswer_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAnswer(INVALID_ANSWER));
    }

    @Test
    public void parseAnswer_validValueWithoutWhitespace_returnsanswer() throws Exception {
        Answer expectedAnswer = new Answer(VALID_ANSWER);
        assertEquals(expectedAnswer, ParserUtil.parseAnswer(VALID_ANSWER));
    }

    @Test
    public void parseAnswer_validValueWithWhitespace_returnsTrimmedanswer() throws Exception {
        String answerWithWhitespace = WHITESPACE + VALID_ANSWER + WHITESPACE;
        Answer expectedanswer = new Answer(VALID_ANSWER);
        assertEquals(expectedanswer, ParserUtil.parseAnswer(answerWithWhitespace));
    }

    @Test
    public void parseQuestion_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseQuestion((String) null));
    }

    @Test
    public void parseQuestion_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseQuestion(INVALID_QUESTION));
    }

    //    @Test
    //    public void parseQuestion_validValueWithoutWhitespace_returnsquestion() throws Exception {
    //        Question expectedquestion = new Question(VALID_QUESTION);
    //        assertEquals(expectedquestion, ParserUtil.parseQuestion(VALID_QUESTION));
    //    }

    //    @Test
    //    public void parseQuestion_validValueWithWhitespace_returnsTrimmedquestion() throws Exception {
    //        String questionWithWhitespace = WHITESPACE + VALID_QUESTION + WHITESPACE;
    //        Question expectedquestion = new Question(VALID_QUESTION);
    //        assertEquals(expectedquestion, ParserUtil.parseQuestion(questionWithWhitespace));
    //    }

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

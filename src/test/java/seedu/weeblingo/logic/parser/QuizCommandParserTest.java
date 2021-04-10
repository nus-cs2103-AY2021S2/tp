package seedu.weeblingo.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.weeblingo.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_NONEXISTENT_TAGS_SET;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_NONEXISTENT_TAGS_SET_DESC;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAGS_SET_COMBINATION;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAGS_SET_GENERIC;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAGS_SET_GOJUON;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAGS_SET_HIRAGANA;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAG_COMBINATION_DESC;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAG_GOJUON_DESC;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAG_HIRAGANA_DESC;
import static seedu.weeblingo.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.weeblingo.logic.commands.QuizCommand;
import seedu.weeblingo.logic.parser.exceptions.ParseException;
import seedu.weeblingo.model.tag.Tag;

public class QuizCommandParserTest {

    private static final QuizCommand QUIZ_COMMAND_GENERIC = new QuizCommand(VALID_TAGS_SET_GENERIC);
    private static final QuizCommand QUIZ_COMMAND_TAG_HIRAGANA = new QuizCommand(VALID_TAGS_SET_HIRAGANA);
    private static final QuizCommand QUIZ_COMMAND_TAG_GOJUON = new QuizCommand(VALID_TAGS_SET_GOJUON);
    private static final QuizCommand QUIZ_COMMAND_TAG_COMBINATION = new QuizCommand(VALID_TAGS_SET_COMBINATION);
    private static final QuizCommand QUIZ_COMMAND_TAG_NONEXISTENT = new QuizCommand(VALID_NONEXISTENT_TAGS_SET);

    private final QuizCommandParser parser = new QuizCommandParser();

    @Test
    public void parse_emptyArgument_success() throws ParseException {
        assertEquals(parser.parse(""), QUIZ_COMMAND_GENERIC);
    }

    @Test
    public void parse_validTagHiragana_success() throws ParseException {
        assertEquals(parser.parse(VALID_TAG_HIRAGANA_DESC), QUIZ_COMMAND_TAG_HIRAGANA);
    }

    @Test
    public void parse_validTagGojuon_success() throws ParseException {
        assertEquals(parser.parse(VALID_TAG_GOJUON_DESC), QUIZ_COMMAND_TAG_GOJUON);
    }

    @Test
    public void parse_validTagCombination_success() throws ParseException {
        assertEquals(parser.parse(VALID_TAG_COMBINATION_DESC), QUIZ_COMMAND_TAG_COMBINATION);
    }

    @Test
    public void parse_validNonexistentTags_success() throws ParseException {
        assertEquals(parser.parse(VALID_NONEXISTENT_TAGS_SET_DESC), QUIZ_COMMAND_TAG_NONEXISTENT);
    }

    @Test
    public void parse_invalidTag_failure() {
        assertThrows(ParseException.class, Tag.MESSAGE_CONSTRAINTS, () -> parser.parse(INVALID_TAG_DESC));
    }
}

package seedu.weeblingo.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.weeblingo.logic.commands.CommandTestUtil.INVALID_START_INTEGER_MAX_DESC;
import static seedu.weeblingo.logic.commands.CommandTestUtil.INVALID_START_INTEGER_MIDDLE_DESC;
import static seedu.weeblingo.logic.commands.CommandTestUtil.INVALID_START_INTEGER_MIN_DESC;
import static seedu.weeblingo.logic.commands.CommandTestUtil.INVALID_START_TAG_DESC;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_START_INTEGER_GENERIC;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_START_INTEGER_MAX;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_START_INTEGER_MAX_DESC;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_START_INTEGER_MIDDLE;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_START_INTEGER_MIDDLE_DESC;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_START_INTEGER_MIN;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_START_INTEGER_MIN_DESC;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_START_TAGS_SET_GENERIC;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_START_TAG_COMBINATION_DESC;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_START_TAG_GOJUON;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_START_TAG_GOJUON_DESC;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_START_TAG_HIRAGANA;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_START_TAG_HIRAGANA_DESC;
import static seedu.weeblingo.testutil.Assert.assertThrows;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.weeblingo.logic.commands.StartCommand;
import seedu.weeblingo.logic.parser.exceptions.ParseException;
import seedu.weeblingo.model.tag.Tag;

public class StartCommandParserTest {

    private static final StartCommand STARTCOMMAND_GENERIC =
        new StartCommand(VALID_START_INTEGER_GENERIC, VALID_START_TAGS_SET_GENERIC);
    private static final StartCommand STARTCOMMAND_VALID_INTEGER_MIN =
        new StartCommand(Integer.parseInt(VALID_START_INTEGER_MIN), VALID_START_TAGS_SET_GENERIC);
    private static final StartCommand STARTCOMMAND_VALID_INTEGER_MIDDLE =
        new StartCommand(Integer.parseInt(VALID_START_INTEGER_MIDDLE), VALID_START_TAGS_SET_GENERIC);
    private static final StartCommand STARTCOMMAND_VALID_INTEGER_MAX =
        new StartCommand(Integer.parseInt(VALID_START_INTEGER_MAX), VALID_START_TAGS_SET_GENERIC);
    private static final StartCommand STARTCOMMAND_VALID_TAG_HIRAGANA =
        new StartCommand(VALID_START_INTEGER_GENERIC, new HashSet<>(List.of(new Tag(VALID_START_TAG_HIRAGANA))));
    private static final StartCommand STARTCOMMAND_VALID_TAG_GOJUON =
        new StartCommand(VALID_START_INTEGER_GENERIC, new HashSet<>(List.of(new Tag(VALID_START_TAG_GOJUON))));
    private static final StartCommand STARTCOMMAND_VALID_TAG_COMBINATION =
        new StartCommand(VALID_START_INTEGER_GENERIC,
            new HashSet<>(List.of(new Tag(VALID_START_TAG_GOJUON), new Tag(VALID_START_TAG_HIRAGANA))));

    private final StartCommandParser parser = new StartCommandParser();

    @Test
    public void parse_emptyArgument_success() throws ParseException {
        assertEquals(parser.parse(""), STARTCOMMAND_GENERIC);
    }

    @Test
    public void parse_validIntegerMin_success() throws ParseException {
        assertEquals(parser.parse(VALID_START_INTEGER_MIN_DESC), STARTCOMMAND_VALID_INTEGER_MIN);
    }

    @Test
    public void parse_validIntegerMiddle_success() throws ParseException {
        assertEquals(parser.parse(VALID_START_INTEGER_MIDDLE_DESC), STARTCOMMAND_VALID_INTEGER_MIDDLE);
    }

    @Test
    public void parse_validIntegerMax_success() throws ParseException {
        assertEquals(parser.parse(VALID_START_INTEGER_MAX_DESC), STARTCOMMAND_VALID_INTEGER_MAX);
    }

    @Test
    public void parse_validTagHiragana_success() throws ParseException {
        assertEquals(parser.parse(VALID_START_TAG_HIRAGANA_DESC), STARTCOMMAND_VALID_TAG_HIRAGANA);
    }

    @Test
    public void parse_validTagGojuon_success() throws ParseException {
        assertEquals(parser.parse(VALID_START_TAG_GOJUON_DESC), STARTCOMMAND_VALID_TAG_GOJUON);
    }

    @Test
    public void parse_validTagCombination_success() throws ParseException {
        assertEquals(parser.parse(VALID_START_TAG_COMBINATION_DESC), STARTCOMMAND_VALID_TAG_COMBINATION);
    }

    @Test
    public void parse_invalidIntegerMax_failure() {
        assertThrows(ParseException.class, StartCommand.MESSAGE_INVALID_NUMBER_OF_QUESTIONS, () -> parser.parse(
                INVALID_START_INTEGER_MAX_DESC));
    }

    @Test
    public void parse_invalidIntegerMiddle_failure() {
        assertThrows(ParseException.class, StartCommand.MESSAGE_INVALID_NUMBER_OF_QUESTIONS, () -> parser.parse(
                INVALID_START_INTEGER_MIDDLE_DESC));
    }

    @Test
    public void parse_invalidIntegerMin_failure() {
        assertThrows(ParseException.class, StartCommand.MESSAGE_INVALID_NUMBER_OF_QUESTIONS, () -> parser.parse(
                INVALID_START_INTEGER_MIN_DESC));
    }

    @Test
    public void parse_invalidTag_failure() {
        assertThrows(ParseException.class,
            Tag.MESSAGE_CONSTRAINTS, () -> parser.parse(INVALID_START_TAG_DESC));
    }
}

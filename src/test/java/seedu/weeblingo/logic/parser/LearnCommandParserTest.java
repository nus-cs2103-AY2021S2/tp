package seedu.weeblingo.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_START_TAGS_SET_GENERIC;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_START_TAG_COMBINATION_DESC;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_START_TAG_GOJUON_DESC;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_START_TAG_HIRAGANA_DESC;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAGS_SET_COMBINATION;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAGS_SET_GOJUON;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAGS_SET_HIRAGANA;

import org.junit.jupiter.api.Test;

import seedu.weeblingo.logic.commands.LearnCommand;
import seedu.weeblingo.logic.parser.exceptions.ParseException;

public class LearnCommandParserTest {

    private static final LearnCommand LEARN_COMMAND_GENERIC = new LearnCommand(VALID_START_TAGS_SET_GENERIC);
    private static final LearnCommand LEARN_COMMAND_TAG_HIRAGANA = new LearnCommand(VALID_TAGS_SET_HIRAGANA);
    private static final LearnCommand LEARN_COMMAND_TAG_GOJUON = new LearnCommand(VALID_TAGS_SET_GOJUON);
    private static final LearnCommand LEARN_COMMAND_TAG_COMBINATION = new LearnCommand(VALID_TAGS_SET_COMBINATION);

    private final LearnCommandParser parser = new LearnCommandParser();

    @Test
    public void parse_emptyArgument_success() throws ParseException {
        assertEquals(parser.parse(""), LEARN_COMMAND_GENERIC);
    }

    @Test
    public void parse_validTagHiragana_success() throws ParseException {
        assertEquals(parser.parse(VALID_START_TAG_HIRAGANA_DESC), LEARN_COMMAND_TAG_HIRAGANA);
    }

    @Test
    public void parse_validTagGojuon_success() throws ParseException {
        assertEquals(parser.parse(VALID_START_TAG_GOJUON_DESC), LEARN_COMMAND_TAG_GOJUON);
    }

    @Test
    public void parse_validTagCombination_success() throws ParseException {
        assertEquals(parser.parse(VALID_START_TAG_COMBINATION_DESC), LEARN_COMMAND_TAG_COMBINATION);
    }

}

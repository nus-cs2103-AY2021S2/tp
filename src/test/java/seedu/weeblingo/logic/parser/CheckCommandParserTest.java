package seedu.weeblingo.logic.parser;

import static seedu.weeblingo.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.weeblingo.logic.commands.CommandTestUtil.INVALID_ANSWER_EMPTY;
import static seedu.weeblingo.logic.commands.CommandTestUtil.INVALID_ANSWER_SYMBOL;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_ANSWER_A;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_ANSWER_CAPS;
import static seedu.weeblingo.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.weeblingo.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;
import seedu.weeblingo.logic.commands.CheckCommand;
import seedu.weeblingo.model.flashcard.Answer;


public class CheckCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT = String.format(
            MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE);

    private CheckCommandParser parser = new CheckCommandParser();

    @Test
    public void parse_missingParts_failure() {
        assertParseFailure(parser, INVALID_ANSWER_EMPTY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidAnswer_failure() {
        assertParseFailure(parser, INVALID_ANSWER_SYMBOL, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validArgs_returnCheckCommand() {
        Answer validAnswer = new Answer(VALID_ANSWER_A);
        Answer validAnswerCaps = new Answer(VALID_ANSWER_CAPS);
        assertParseSuccess(parser, VALID_ANSWER_A, new CheckCommand(validAnswer));
        assertParseSuccess(parser, VALID_ANSWER_CAPS, new CheckCommand(validAnswerCaps));

    }
}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.commandhistory.ViewHistoryCommand;
import seedu.address.logic.parser.commandhistory.ViewHistoryCommandParser;

/**
 * Contains unit tests for {@code ViewHistoryCommandParser}.
 */
public class ViewHistoryCommandParserTest {
    private static final String VALID_ARG_FIVE = "5";
    private static final String VALID_ARG_TWENTY_TWO = "22";
    private static final String VALID_ARG_NEGATIVE = "-4";
    private static final String VALID_ARG_ZERO = "0";
    private static final String NO_ARG = "";
    private static final String INVALID_ARG_A = "A";
    private static final String INVALID_ARG_SYMBOLS = "!*#";

    private final ViewHistoryCommandParser parser = new ViewHistoryCommandParser();

    @Test
    public void parse_nonIntegerCount_failure() {
        // invalid counts: non-integers
        assertParseFailure(parser, INVALID_ARG_A,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewHistoryCommand.MESSAGE_USAGE));
        assertParseFailure(parser, INVALID_ARG_SYMBOLS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewHistoryCommand.MESSAGE_USAGE));
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + VALID_ARG_FIVE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewHistoryCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_optionalCountMissing_success() {
        assertParseSuccess(parser, NO_ARG, new ViewHistoryCommand());
    }

    @Test
    public void parse_withIntegerCount_success() {
        // EP: positive integers
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_ARG_FIVE,
                new ViewHistoryCommand(Integer.parseInt(VALID_ARG_FIVE)));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_ARG_TWENTY_TWO,
                new ViewHistoryCommand(Integer.parseInt(VALID_ARG_TWENTY_TWO)));

        // EP: non-positive integers
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_ARG_ZERO,
                new ViewHistoryCommand(Integer.parseInt(VALID_ARG_ZERO)));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_ARG_NEGATIVE,
                new ViewHistoryCommand(Integer.parseInt(VALID_ARG_NEGATIVE)));
    }
}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.commandhistory.ViewHistoryCommand;
import seedu.address.logic.parser.commandhistory.ViewHistoryCommandParser;

public class ViewHistoryCommandParserTest {
    private static final String VALID_ARG_FIVE = "5";
    private static final String VALID_ARG_TWENTY_TWO = "22";
    private static final String NO_ARG = "";
    private static final String INVALID_ARG_A = "A";
    private static final String INVALID_ARG_NEGATIVE = "-4";
    private static final String INVALID_ARG_ZERO = "0";
    private final ViewHistoryCommandParser parser = new ViewHistoryCommandParser();

    @Test
    public void parse_invalidValue_failure() {
        // invalid count: non-integer
        assertParseFailure(parser, INVALID_ARG_A,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewHistoryCommand.MESSAGE_USAGE));

        // invalid count: zero
        assertParseFailure(parser, INVALID_ARG_ZERO,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewHistoryCommand.MESSAGE_USAGE));

        // invalid count: negative
        assertParseFailure(parser, INVALID_ARG_NEGATIVE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewHistoryCommand.MESSAGE_USAGE));

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewHistoryCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_optionalCountMissing_success() {
        assertParseSuccess(parser, NO_ARG, new ViewHistoryCommand());
    }

    @Test
    public void parse_withValidCount_success() {
        // valid count 1
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_ARG_FIVE,
                new ViewHistoryCommand(Integer.parseInt(VALID_ARG_FIVE)));

        // valid count 2
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_ARG_TWENTY_TWO,
                new ViewHistoryCommand(Integer.parseInt(VALID_ARG_TWENTY_TWO)));
    }
}

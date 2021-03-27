package seedu.storemando.logic.parser;

import static seedu.storemando.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.storemando.logic.commands.SortCommand;
import seedu.storemando.logic.commands.SortExpiryDateCommand;
import seedu.storemando.logic.commands.SortQuantityCommand;

class SortCommandParserTest {
    private final SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "     ", expectedMessage);
    }

    @Test
    public void parse_invalidArgument_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "invalid", expectedMessage);
    }

    @Test
    public void parse_validArgument_success() {
        SortExpiryDateCommand expectedExpiryDateCommand = new SortExpiryDateCommand();
        SortQuantityCommand expectedQuantityCommand = new SortQuantityCommand(true);

        //check for valid increasing quantity user inputs
        assertParseSuccess(parser, "quantity asc", expectedQuantityCommand);
        assertParseSuccess(parser, "QuAnTiTy Asc", expectedQuantityCommand);
        assertParseSuccess(parser, "\n      QuAnTiTy Asc", expectedQuantityCommand);

        //check for valid increasing quantity user inputs
        assertParseSuccess(parser, "quantity desc", expectedQuantityCommand);
        assertParseSuccess(parser, "QuAnTiTy DeSc", expectedQuantityCommand);
        assertParseSuccess(parser, "\n      QuAnTiTy DeSc", expectedQuantityCommand);

        //check for valid expiryDate user inputs
        assertParseSuccess(parser, "expirydate", expectedExpiryDateCommand);
        assertParseSuccess(parser, "eXpIrYdAtE", expectedExpiryDateCommand);
        assertParseSuccess(parser, "\n      eXpIrYdAtE", expectedExpiryDateCommand);
    }
}

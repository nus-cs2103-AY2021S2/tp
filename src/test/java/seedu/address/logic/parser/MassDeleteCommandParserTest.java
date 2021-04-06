package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SEVENTH_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MassDeleteCommand;

public class MassDeleteCommandParserTest {

    private static final String VALID_INPUT = INDEX_FIRST_PERSON.getOneBased() + "-"
            + INDEX_SEVENTH_PERSON.getOneBased();
    private static final String INVALID_INPUT = "-";

    private MassDeleteCommandParser parser = new MassDeleteCommandParser();


    @Test
    public void parse_validArgs_returnsMassDeleteCommand() {
        assertParseSuccess(parser, VALID_INPUT, new MassDeleteCommand(INDEX_FIRST_PERSON,
                INDEX_SEVENTH_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, INVALID_INPUT, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MassDeleteCommand.MESSAGE_USAGE));
    }
}

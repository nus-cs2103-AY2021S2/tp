package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.MASSBLACKLIST_BLACKLIST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MASSBLACKLIST_KEYWORD_BLACKLIST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLACKLIST;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SEVENTH_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MassBlacklistCommand;

public class MassBlacklistCommandParserTest {

    private static final String VALID_INPUT = INDEX_FIRST_PERSON.getOneBased() + "-"
            + INDEX_SEVENTH_PERSON.getOneBased() + " " + PREFIX_BLACKLIST + VALID_MASSBLACKLIST_KEYWORD_BLACKLIST;

    private static final String INVALID_INPUT = "lame";

    private MassBlacklistCommandParser parser = new MassBlacklistCommandParser();


    @Test
    public void parse_validArgs_returnsMassBlacklistCommand() {
        assertParseSuccess(parser, VALID_INPUT, new MassBlacklistCommand(INDEX_FIRST_PERSON,
                INDEX_SEVENTH_PERSON, MASSBLACKLIST_BLACKLIST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, INVALID_INPUT, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MassBlacklistCommand.MESSAGE_USAGE));
    }
}

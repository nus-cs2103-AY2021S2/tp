package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ALL_PREDICATE;
import static seedu.address.logic.commands.CommandTestUtil.DAY_PREDICATE;
import static seedu.address.logic.commands.CommandTestUtil.WEEK_PREDICATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListEntryCommand;

/**
 * Contains tests to make sure the parser instantiates the correct {@code ListEntryCommand}.
 */
public class ListEntryCommandParserTest {

    private final ListEntryCommandParser parser = new ListEntryCommandParser();

    @Test
    public void parser_emptyInput_success() {
        assertParseSuccess(parser, "", new ListEntryCommand(ALL_PREDICATE));
    }

    @Test
    public void parse_dayInput_success() {
        assertParseSuccess(parser, "day", new ListEntryCommand(DAY_PREDICATE));
    }

    @Test
    public void parser_weekInput_success() {
        assertParseSuccess(parser, "week", new ListEntryCommand(WEEK_PREDICATE));
    }

    @Test
    public void parser_invalidInput_throwsParseException() {
        assertParseFailure(parser, "lol",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListEntryCommand.MESSAGE_USAGE));
    }
}

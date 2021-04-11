package seedu.flashback.logic.parser;

import static seedu.flashback.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashback.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.flashback.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.flashback.logic.commands.SortCommand;
import seedu.flashback.model.flashcard.SortOptions;

public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();
    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "category -c",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "question -t",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSortCommand() {
        SortCommand expectedSortCommand = new SortCommand(SortOptions.QUESTION_DESCENDING);
        assertParseSuccess(parser, "question -d", expectedSortCommand);
        assertParseSuccess(parser, " question -d  ", expectedSortCommand); // with leading and trailing spaces
    }
}

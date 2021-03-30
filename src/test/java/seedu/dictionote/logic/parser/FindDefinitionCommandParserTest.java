package seedu.dictionote.logic.parser;

import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dictionote.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.dictionote.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.dictionote.logic.commands.FindDefinitionCommand;
import seedu.dictionote.model.dictionary.DefinitionContainsKeywordsPredicate;

public class FindDefinitionCommandParserTest {

    private FindDefinitionCommandParser parser = new FindDefinitionCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindDefinitionCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindDefinitionCommand() {
        // no leading and trailing whitespaces
        FindDefinitionCommand expectedFindDefinitionCommand =
                new FindDefinitionCommand(
                        new DefinitionContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindDefinitionCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob ", expectedFindDefinitionCommand);
    }

}

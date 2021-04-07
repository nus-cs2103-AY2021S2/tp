package seedu.taskify.logic.parser;

import static seedu.taskify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taskify.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.taskify.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.taskify.logic.commands.TagSearchCommand;
import seedu.taskify.model.task.predicates.TagContainsKeywordsPredicate;

public class TagSearchCommandParserTest {

    private TagSearchCommandParser parser = new TagSearchCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                           TagSearchCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsTagSearchCommand() {
        // no leading and trailing whitespaces
        TagSearchCommand expectedTagSearchCommand =
                new TagSearchCommand(new TagContainsKeywordsPredicate(Arrays.asList("Module", "Tutorial")));
        assertParseSuccess(parser, "Module Tutorial", expectedTagSearchCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Module \n \t Tutorial  \t", expectedTagSearchCommand);
    }

}

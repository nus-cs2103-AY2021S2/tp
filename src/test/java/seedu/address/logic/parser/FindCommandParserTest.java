package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCategoryCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindQuestionCommand;
import seedu.address.model.flashcard.CategoryContainsKeywordsPredicate;
import seedu.address.model.flashcard.QuestionContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindQuestionCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindQuestionCommand(new QuestionContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, PREFIX_QUESTION + " Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, PREFIX_QUESTION + "  \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindCategoryCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCategoryCommand(new CategoryContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, PREFIX_CATEGORY + " Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, PREFIX_CATEGORY + "  \n Alice \n \t Bob  \t", expectedFindCommand);
    }

}

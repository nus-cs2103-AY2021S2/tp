package seedu.address.logic.parser.editcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CS2101;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.editcommand.EditModuleCommand;
import seedu.address.model.module.Title;

public class EditModuleCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditModuleCommand.MESSAGE_USAGE);

    private EditModuleCommandParser parser = new EditModuleCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        String userInput1 = PREFIX_MODULE.getPrefix() + VALID_TITLE_CS2101;
        assertParseFailure(parser, userInput1, MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        String userInput2 = "";
        assertParseFailure(parser, userInput2,
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditModuleCommand.MESSAGE_USAGE));

        // no field specified
        String userInput3 = "1 " + PREFIX_MODULE.getPrefix();
        assertParseFailure(parser, userInput3,
                String.format(Title.MESSAGE_CONSTRAINTS, "Modules")); //empty description
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        String userInput1 = "-5 " + PREFIX_MODULE.getPrefix() + VALID_TITLE_CS2101;
        assertParseFailure(parser, userInput1,
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditModuleCommand.MESSAGE_USAGE));

        // zero index
        String userInput2 = "0 " + PREFIX_MODULE.getPrefix() + VALID_TITLE_CS2101;
        assertParseFailure(parser, userInput2,
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditModuleCommand.MESSAGE_USAGE));

        // invalid arguments being parsed as preamble
        String userInput3 = "words " + PREFIX_MODULE.getPrefix() + VALID_TITLE_CS2101;
        assertParseFailure(parser, userInput3 ,
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditModuleCommand.MESSAGE_USAGE));

        // invalid prefix being parsed as preamble
        String userInput4 = "i/ " + PREFIX_MODULE.getPrefix() + VALID_TITLE_CS2101;
        assertParseFailure(parser, userInput4 ,
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditModuleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {
        String userInput = "1 " + PREFIX_MODULE.getPrefix() + INVALID_TITLE;
        assertParseFailure(parser, userInput,
                String.format(Title.MESSAGE_CONSTRAINTS, "Modules")); // invalid date format
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        Index targetIndex = INDEX_FIRST_MODULE;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_MODULE + VALID_TITLE_CS2101;

        Title edited = new Title(VALID_TITLE_CS2101);
        EditModuleCommand expectedCommand = new EditModuleCommand(targetIndex.getOneBased(), edited);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

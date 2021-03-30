package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEXES;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.model.person.Name;

public class AddGroupCommandParserTest {
    private AddGroupCommandParser parser = new AddGroupCommandParser();

    @Test
    public void parse_fieldsMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGroupCommand.MESSAGE_USAGE);

        //missing persons
        String invalidCommand = " n/ Close Friends";
        assertParseFailure(parser, invalidCommand, expectedMessage);

        //missing name
        invalidCommand = " p/ 1 2 3 4";
        assertParseFailure(parser, invalidCommand, expectedMessage);
    }

    @Test
    public void parse_indexesFieldEmpty_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_INDEXES, AddGroupCommand.MESSAGE_USAGE);

        String invalidCommand = " n/ Close Friends p/ ";
        assertParseFailure(parser, invalidCommand, expectedMessage);

    }

    @Test
    public void parse_allFieldsPresent_success() {

        List<Index> expectedIndexes = new ArrayList<Index>();
        expectedIndexes.add(Index.fromOneBased(1));
        expectedIndexes.add(Index.fromOneBased(2));
        expectedIndexes.add(Index.fromOneBased(3));
        Name expectedName = new Name("Close Friends");
        String validCommand = " n/ Close Friends p/ 1 2 3";
        assertParseSuccess(parser, validCommand, new AddGroupCommand(expectedIndexes, expectedName));

    }
}

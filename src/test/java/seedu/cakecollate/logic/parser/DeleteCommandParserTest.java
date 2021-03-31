package seedu.cakecollate.logic.parser;

import static seedu.cakecollate.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cakecollate.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.cakecollate.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.cakecollate.testutil.TypicalIndexes.INDEX_FIRST_ORDER;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.cakecollate.commons.core.index.Index;
import seedu.cakecollate.commons.core.index.IndexList;
import seedu.cakecollate.logic.commands.DeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        ArrayList<Index> arrayFirstOrder = new ArrayList<Index>();
        arrayFirstOrder.add(INDEX_FIRST_ORDER);
        IndexList indexList = new IndexList(arrayFirstOrder);
        assertParseSuccess(parser, "1", new DeleteCommand(indexList));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}

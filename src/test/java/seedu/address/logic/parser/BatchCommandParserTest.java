package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_ID;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_ID_WITH_URL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.BatchCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class BatchCommandParserTest {

    private BatchCommandParser batchCommandParser = new BatchCommandParser();
    private EditCommandParser editCommandParser = new EditCommandParser();
    private DeleteCommandParser deleteCommandParser = new DeleteCommandParser();
    private List<Index> listOfIndices = Arrays.asList(Index.fromOneBased(3), Index.fromOneBased(2),
            Index.fromOneBased(1));
    private String argsForEdit = "t/" + VALID_TAG_HUSBAND + " i/" + VALID_POLICY_ID + " i/" + VALID_POLICY_ID_WITH_URL;

    @Test
    public void parse_validArgsForBatchEdit_returnsBatchEditCommand() {
        try {
            List<EditCommand> listOfEditCommands = new ArrayList<>();
            for (Index index : listOfIndices) {
                String newCommandInput = index.getOneBased() + " " + argsForEdit;
                EditCommand editCommand = editCommandParser.parse(newCommandInput);
                listOfEditCommands.add(editCommand);
            }

            assertParseSuccess(batchCommandParser, "edit 1, 2,3 t/husband i/P#1245 i/POL#6789>www.youtube.com",
                    new BatchCommand<>(listOfEditCommands));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_validArgsForBatchDelete_returnsBatchDeleteCommand() {
        try {
            List<DeleteCommand> listOfDeleteCommands = new ArrayList<>();
            for (Index index : listOfIndices) {
                String newCommandInput = String.valueOf(index.getOneBased());
                DeleteCommand deleteCommand = deleteCommandParser.parse(newCommandInput);
                listOfDeleteCommands.add(deleteCommand);
            }

            assertParseSuccess(batchCommandParser, "delete 1, 2,3",
                    new BatchCommand<>(listOfDeleteCommands));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_invalidIndices_throwsParseException() {
        assertParseFailure(batchCommandParser, "delete 0, 1, 2",
                String.format(BatchCommand.ERROR_MESSAGE, ParserUtil.MESSAGE_INVALID_INDEX));

        assertParseFailure(batchCommandParser, "edit 0, 4, 5 t/husband i/P#1245 i/POL#6789>www.youtube.com",
                String.format(BatchCommand.ERROR_MESSAGE, ParserUtil.MESSAGE_INVALID_INDEX));
    }

    @Test
    public void parse_invalidEditArguments_throwsParseException() {
        assertParseFailure(batchCommandParser, "edit 1, 4, 5 n/Tom i/P#1245 i/POL#6789>www.youtube.com",
                String.format(BatchCommand.ERROR_MESSAGE, BatchCommandParser.INVALID_EDIT_ARGUMENTS));
    }

    @Test
    public void parse_invalidCommandForBatch_throwsParseException() {
        assertParseFailure(batchCommandParser, "add n/Tom a/Orchard p/9999 e/tom@tom.com t/tom i/POL_#tom123",
                String.format(BatchCommand.ERROR_MESSAGE, BatchCommandParser.INVALID_BATCH_COMMAND));

        assertParseFailure(batchCommandParser, "policy 1",
                String.format(BatchCommand.ERROR_MESSAGE, BatchCommandParser.INVALID_BATCH_COMMAND));

        assertParseFailure(batchCommandParser, "exit",
                String.format(BatchCommand.ERROR_MESSAGE, BatchCommandParser.INVALID_BATCH_COMMAND));

        assertParseFailure(batchCommandParser, "list",
                String.format(BatchCommand.ERROR_MESSAGE, BatchCommandParser.INVALID_BATCH_COMMAND));

        assertParseFailure(batchCommandParser, "find",
                String.format(BatchCommand.ERROR_MESSAGE, BatchCommandParser.INVALID_BATCH_COMMAND));

        assertParseFailure(batchCommandParser, "sort -n -des",
                String.format(BatchCommand.ERROR_MESSAGE, BatchCommandParser.INVALID_BATCH_COMMAND));

        assertParseFailure(batchCommandParser, "lock 123",
                String.format(BatchCommand.ERROR_MESSAGE, BatchCommandParser.INVALID_BATCH_COMMAND));
    }

}

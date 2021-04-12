package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.BatchCommandParser.INVALID_BATCH_COMMAND;
import static seedu.address.logic.parser.BatchCommandParser.INVALID_EDIT_ARGUMENTS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INDEX_IS_WORD;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.BatchCommand;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class BatchCommandParserTest {

    private static final BatchCommandParser BATCH_COMMAND_PARSER = new BatchCommandParser();
    private static final EditCommandParser EDIT_COMMAND_PARSER = new EditCommandParser();
    private static final DeleteCommandParser DELETE_COMMAND_PARSER = new DeleteCommandParser();
    private static final List<Index> LIST_OF_INDICES = Arrays.asList(Index.fromOneBased(3), Index.fromOneBased(2),
            Index.fromOneBased(1));

    @Test
    public void parse_validArgsForBatchEdit_returnsBatchEditCommand() throws ParseException {
        List<EditCommand> listOfEditCommands = new ArrayList<>();
        for (Index index : LIST_OF_INDICES) {
            String newCommandInput = index.getOneBased() + " " + CommandTestUtil.ARGS_FOR_EDIT;
            EditCommand editCommand = EDIT_COMMAND_PARSER.parse(newCommandInput);
            listOfEditCommands.add(editCommand);
        }

        assertParseSuccess(BATCH_COMMAND_PARSER, "edit 1, 2,3 t/husband i/P#1245 i/POL#6789>www.youtube.com",
                new BatchCommand<>(listOfEditCommands));
    }

    @Test
    public void parse_validArgsForBatchDelete_returnsBatchDeleteCommand() throws ParseException {
        List<DeleteCommand> listOfDeleteCommands = new ArrayList<>();
        for (Index index : LIST_OF_INDICES) {
            String newCommandInput = String.valueOf(index.getOneBased());
            DeleteCommand deleteCommand = DELETE_COMMAND_PARSER.parse(newCommandInput);
            listOfDeleteCommands.add(deleteCommand);
        }

        assertParseSuccess(BATCH_COMMAND_PARSER, "delete 1, 2,3",
                new BatchCommand<>(listOfDeleteCommands));
    }

    @Test
    public void parse_invalidIndices_throwsParseException() {
        // Input with invalid numerical index
        assertParseFailure(BATCH_COMMAND_PARSER, "delete 0, 1, 2",
                String.format(BatchCommand.ERROR_MESSAGE, MESSAGE_INVALID_INDEX));

        // Input with huge numerical index
        assertParseFailure(BATCH_COMMAND_PARSER, "delete 274890137843892748927983739483, 1, 2",
                String.format(BatchCommand.ERROR_MESSAGE, MESSAGE_INVALID_INDEX));

        // Input with invalid index as word
        assertParseFailure(BATCH_COMMAND_PARSER, "delete 1, 2, lol",
                String.format(BatchCommand.ERROR_MESSAGE, MESSAGE_INDEX_IS_WORD));

        assertParseFailure(BATCH_COMMAND_PARSER, "edit 0, 4, 5 t/husband i/P#1245 i/POL#6789>www.youtube.com",
                String.format(BatchCommand.ERROR_MESSAGE, MESSAGE_INVALID_INDEX));
    }

    @Test
    public void parse_invalidEditArguments_throwsParseException() {
        // Edits name but not allowed in batch edit
        assertParseFailure(BATCH_COMMAND_PARSER, "edit 1, 4, 5 n/Tom i/P#1245 i/POL#6789>www.youtube.com",
                String.format(BatchCommand.ERROR_MESSAGE, INVALID_EDIT_ARGUMENTS));

        // No argument for edit
        assertParseFailure(BATCH_COMMAND_PARSER, "edit ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchCommand.MESSAGE_USAGE));

        // No field for edit
        assertParseFailure(BATCH_COMMAND_PARSER, "edit 1, 2",
                String.format(BatchCommand.ERROR_MESSAGE, EditCommand.MESSAGE_NOT_EDITED));
        /*
        assertParseFailure(BATCH_COMMAND_PARSER, "edit 1 2",
                String.format(BatchCommand.ERROR_MESSAGE, EditCommand.MESSAGE_NOT_EDITED));

        assertParseFailure(BATCH_COMMAND_PARSER, "edit 1, 2 lol",
                String.format(BatchCommand.ERROR_MESSAGE, EditCommand.MESSAGE_NOT_EDITED));

        assertParseFailure(BATCH_COMMAND_PARSER, "edit 1 2 lol",
                String.format(BatchCommand.ERROR_MESSAGE, EditCommand.MESSAGE_NOT_EDITED));
         */
    }

    @Test
    public void parse_invalidDeleteArguments_throwsParseException() {
        // Word argument for delete
        assertParseFailure(BATCH_COMMAND_PARSER, "delete lol",
                String.format(BatchCommand.ERROR_MESSAGE, MESSAGE_INDEX_IS_WORD));

        // No argument for delete
        assertParseFailure(BATCH_COMMAND_PARSER, "delete ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchCommand.MESSAGE_USAGE));
        /*
        assertParseFailure(BATCH_COMMAND_PARSER, "delete 1 lol",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchCommand.MESSAGE_USAGE));

        assertParseFailure(BATCH_COMMAND_PARSER, "delete 1 2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchCommand.MESSAGE_USAGE));

         */
    }

    @Test
    public void parse_noArgsForBatch_throwsParseException() {
        assertParseFailure(BATCH_COMMAND_PARSER, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchCommand.MESSAGE_USAGE));

        assertParseFailure(BATCH_COMMAND_PARSER, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidCommandForBatch_throwsParseException() {
        assertParseFailure(BATCH_COMMAND_PARSER, "add n/Tom a/Orchard p/9999 e/tom@tom.com t/tom i/POL_#tom123",
                INVALID_BATCH_COMMAND);

        assertParseFailure(BATCH_COMMAND_PARSER, "policy 1",
                INVALID_BATCH_COMMAND);

        assertParseFailure(BATCH_COMMAND_PARSER, "exit",
                INVALID_BATCH_COMMAND);

        assertParseFailure(BATCH_COMMAND_PARSER, "list",
                INVALID_BATCH_COMMAND);

        assertParseFailure(BATCH_COMMAND_PARSER, "find",
                INVALID_BATCH_COMMAND);

        assertParseFailure(BATCH_COMMAND_PARSER, "sort -n -des",
                INVALID_BATCH_COMMAND);

        assertParseFailure(BATCH_COMMAND_PARSER, "lock 123",
                INVALID_BATCH_COMMAND);
    }

}

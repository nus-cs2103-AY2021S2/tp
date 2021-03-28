package seedu.address.logic.parser;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.BatchCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.PolicyCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

public class BatchCommandParser implements Parser<BatchCommand> {

    private final Logger logger = LogsCenter.getLogger(getClass());

    public BatchCommand parse(String args) throws ParseException {
        try {
            logger.info("in batch command parser");

            String[] splitCommandAndIndicesAndArgs = args.split("-");
            logger.info("in batch command parser 1 " + Arrays.toString(splitCommandAndIndicesAndArgs));
            String inputCommand = splitCommandAndIndicesAndArgs[1].trim();
            logger.info("in batch command parser 2");
            String inputIndices = splitCommandAndIndicesAndArgs[2].trim();
            logger.info("in batch command parser 3 " + inputIndices);
            List<Index> listOfParsedIndices = ParserUtil.parseIndices(inputIndices);
            logger.info("in batch command parser 4");
            String inputCommandArgs = splitCommandAndIndicesAndArgs[3].trim();
            logger.info("in batch command parser 5");

            logger.info("in batch command parser " + Arrays.toString(splitCommandAndIndicesAndArgs) + " " + inputCommandArgs);

            switch (inputCommand) {

            case EditCommand.COMMAND_WORD:
                EditCommandParser editCommandParser = new EditCommandParser();
                return new BatchCommand<>(inputCommand, editCommandParser, listOfParsedIndices, inputCommandArgs);

            case DeleteCommand.COMMAND_WORD:
                DeleteCommandParser deleteCommandParser = new DeleteCommandParser();
                return new BatchCommand<>(inputCommand, deleteCommandParser, listOfParsedIndices, inputCommandArgs);

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        }
        catch (ParseException pe) {
            logger.info("caught a parse exception");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchCommand.MESSAGE_USAGE), pe);
        }
    }
}

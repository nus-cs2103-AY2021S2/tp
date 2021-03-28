package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.BatchCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class BatchCommandParser implements Parser<BatchCommand> {
    private static final String INVALID_BATCH_COMMAND = "Invalid batch operation! Only edit and delete operations "
            + "are supported.";

    /**
     * Parses input to prepare for a {@code BatchCommand}. Splits user input arguments for the {@BatchCommand} into
     * the command to be executed in batch, as well as indices and arguments (if any) for this command.
     *
     * @param args arguments of the {@BatchCommand} passed in by the user
     * @return a {@code BatchCommand} with the corresponding command parser and arguments for the command.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public BatchCommand parse(String args) throws ParseException {
        try {
            String[] splitCommandAndIndicesAndArgs = args.split("-");
            String inputCommand = splitCommandAndIndicesAndArgs[1].trim();
            String inputIndices = splitCommandAndIndicesAndArgs[2].trim();
            String inputCommandArgs = null;

            switch (inputCommand) {

            case EditCommand.COMMAND_WORD:
                inputCommandArgs = splitCommandAndIndicesAndArgs[3].trim();
                EditCommandParser editCommandParser = new EditCommandParser();
                return new BatchCommand<>(editCommandParser, inputIndices, inputCommandArgs);

            case DeleteCommand.COMMAND_WORD:
                DeleteCommandParser deleteCommandParser = new DeleteCommandParser();
                return new BatchCommand<>(deleteCommandParser, inputIndices, inputCommandArgs);

            default:
                throw new ParseException(INVALID_BATCH_COMMAND);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchCommand.MESSAGE_USAGE));
        }
    }
}

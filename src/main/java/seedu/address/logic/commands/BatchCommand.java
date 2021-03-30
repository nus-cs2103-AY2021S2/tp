package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.DeleteCommandParser;
import seedu.address.logic.parser.EditCommandParser;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class BatchCommand<T extends Command> extends Command {

    public static final String COMMAND_WORD = "batch";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Batch operation for all the listed clients.\n"
            + "Parameters: -COMMAND (only edit or delete command are supported) "
            + "-ARGUMENTS (for the chosen command)\n"
            + "Example: " + COMMAND_WORD + " edit 1, 2 t/colleagues";
    public static final String SUCCESS_MESSAGE = "Batch operation successful!";
    public static final String ERROR_MESSAGE = "Batch operation halted. Error message from batch command: \n%s";

    private final Logger logger = LogsCenter.getLogger(getClass());
    private final Parser<T> commandParser;
    private final List<Index> listOfIndices;
    private final String inputCommandArgs;

    /**
     * Creates a {@code BatchCommand} which has access to the relevant {@code Parser} for the {@code Command} to be
     * executed in batch.
     *
     * @param commandParser {@Parser} for the {@Command}
     * @param listOfIndices user input indices for the chosen command
     * @param inputCommandArgs user input arguments for the chosen command
     */
    public BatchCommand(Parser<T> commandParser, List<Index> listOfIndices, String inputCommandArgs) {
        this.commandParser = commandParser;
        this.listOfIndices = listOfIndices;
        this.inputCommandArgs = inputCommandArgs;
    }

    /**
     * Creates a {@code BatchCommand} which has access to the relevant {@code Parser} for the {@code Command} to be
     * executed in batch. This constructor is used when there are no arguments for the {@code Command}
     *
     * @param commandParser {@Parser} for the {@Command}
     * @param listOfIndices user input indices for the chosen command
     */
    public BatchCommand(Parser<T> commandParser, List<Index> listOfIndices) {
        this.commandParser = commandParser;
        this.listOfIndices = listOfIndices;
        this.inputCommandArgs = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            // Execute on the copy first to check for errors
            Model copy = new ModelManager(model.getAddressBook(), model.getUserPrefs());

            Collections.sort(listOfIndices);
            Collections.reverse(listOfIndices);

            boolean isReal = false;

            assert commandParser instanceof EditCommandParser || commandParser instanceof DeleteCommandParser;

            if (commandParser instanceof EditCommandParser) {
                executeBatchEdit(commandParser, copy, listOfIndices, isReal);
            } else if (commandParser instanceof DeleteCommandParser) {
                executeBatchDelete(commandParser, copy, listOfIndices, isReal);
            } else {
                assert false;
            }

            // If successfully executed on copy, we can now execute on model without worrying about exceptions.
            // Avoids having to maintain state/undo/redo functionality.

            isReal = true;

            if (commandParser instanceof EditCommandParser) {
                executeBatchEdit(commandParser, model, listOfIndices, isReal);
            } else {
                executeBatchDelete(commandParser, model, listOfIndices, isReal);
            }

            return new CommandResult(SUCCESS_MESSAGE, false, false, false);
        } catch (ParseException | CommandException e) {
            throw new CommandException(String.format(ERROR_MESSAGE, e.getLocalizedMessage()));
        }
    }

    private void executeBatchEdit(Parser<T> commandParser, Model model, List<Index> listOfParsedIndices, boolean isReal)
            throws ParseException, CommandException {
        for (Index i : listOfParsedIndices) {
            String newCommandInput = i.getOneBased() + " " + inputCommandArgs;
            CommandResult commandResult = ((EditCommandParser) commandParser)
                    .parse(newCommandInput)
                    .execute(model);
            logResultIfReal(isReal, commandResult);
        }
    }

    private void executeBatchDelete(Parser<T> commandParser, Model model, List<Index> listOfParsedIndices,
                                             boolean isReal) throws ParseException, CommandException {
        for (Index i : listOfParsedIndices) {
            String index = String.valueOf(i.getOneBased());
            CommandResult commandResult = commandParser.parse(index).execute(model);
            logResultIfReal(isReal, commandResult);
        }
    }

    private void logResultIfReal(boolean isReal, CommandResult commandResult) {
        if (isReal) {
            logger.info("Result of batch command: " + commandResult.getFeedbackToUser());
        }
    }

    @Override
    public boolean equals(Object other) {
        assert commandParser instanceof EditCommandParser || commandParser instanceof DeleteCommandParser;

        if (other == this) {
            return true;
        }

        if (other instanceof BatchCommand) {
            BatchCommand otherBatchCommand = (BatchCommand) other;

            if (commandParser instanceof EditCommandParser
                    && otherBatchCommand.commandParser instanceof EditCommandParser) {

                return listOfIndices.equals(otherBatchCommand.listOfIndices)
                        && inputCommandArgs.equals(otherBatchCommand.inputCommandArgs);

            } else if (commandParser instanceof DeleteCommandParser
                    && otherBatchCommand.commandParser instanceof DeleteCommandParser) {

                return listOfIndices.equals(otherBatchCommand.listOfIndices);

            }
        }

        return false;
    }

}

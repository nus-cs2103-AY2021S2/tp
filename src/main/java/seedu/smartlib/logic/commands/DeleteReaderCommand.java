package seedu.smartlib.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.smartlib.commons.core.Messages;
import seedu.smartlib.commons.core.index.Index;
import seedu.smartlib.logic.commands.exceptions.CommandException;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.reader.Reader;

/**
 * Deletes a reader identified using it's displayed index from SmartLib's registered reader base.
 */
public class DeleteReaderCommand extends Command {

    public static final String COMMAND_WORD = "deletereader";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the reader identified by the index number in the displayed reader list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_READER_SUCCESS = "Deleted Reader: %1$s";

    private final Index targetIndex;

    /**
     * Creates an DeleteReaderCommand to delete the specified reader.
     *
     * @param targetIndex index of the reader to be deleted from SmartLib's reader base.
     */
    public DeleteReaderCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException if an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Reader> lastShownList = model.getFilteredReaderList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_READER_DISPLAYED_INDEX);
        }

        Reader readerToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteReader(readerToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_READER_SUCCESS, readerToDelete));
    }

    /**
     * Checks if this DeleteReaderCommand is equal to another DeleteReaderCommand.
     *
     * @param other the other DeleteReaderCommand to be compared.
     * @return true if this DeleteReaderCommand is equal to the other DeleteReaderCommand, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteReaderCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteReaderCommand) other).targetIndex)); // state check
    }

}

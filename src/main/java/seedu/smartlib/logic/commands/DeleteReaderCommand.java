package seedu.smartlib.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.smartlib.commons.core.Messages;
import seedu.smartlib.commons.core.index.Index;
import seedu.smartlib.logic.commands.exceptions.CommandException;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.reader.Reader;

/**
 * Deletes a reader identified using it's displayed index from the SmarLib registered reader base.
 */
public class DeleteReaderCommand extends Command {

    public static final String COMMAND_WORD = "deletereader";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the reader identified by the index number used in the displayed reader list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_READER_SUCCESS = "Deleted Reader: %1$s";

    private final Index targetIndex;

    public DeleteReaderCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteReaderCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteReaderCommand) other).targetIndex)); // state check
    }
}

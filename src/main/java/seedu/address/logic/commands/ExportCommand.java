package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exportutils.ExportUtils;
import seedu.address.commons.exportutils.exceptions.ExportException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutor.Tutor;

/**
 * Exports a text file with details of a Tutor in the TutorBook
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + "INDEX";

    public static final String MESSAGE_SUCCESS = "Successfully exported file: %s";

    public static final String MESSAGE_FAILURE = "Unable to export file: %s";

    public static final String MESSAGE_INVALID_INDEX = "Invalid index %d";

    private final Index targetIndex;

    /**
     * Target Index of the Tutor to export
     */
    public ExportCommand (Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);

        List<Tutor> tutorList = model.getFilteredTutorList();

        if (targetIndex.getZeroBased() >= tutorList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_INDEX, targetIndex.getZeroBased()));
        }

        Tutor tutor = tutorList.get(targetIndex.getZeroBased());

        try {
            String feedback = ExportUtils.exportTutor(tutor);

            return new CommandResult(String.format(MESSAGE_SUCCESS, feedback));
        } catch (ExportException e) {
            return new CommandResult(String.format(MESSAGE_FAILURE, e.getMessage()));
        }

    }
}

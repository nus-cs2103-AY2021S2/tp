package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.uicommands.ViewProjectAndOverviewUiCommand;
import seedu.address.model.Model;
import seedu.address.model.groupmate.Groupmate;
import seedu.address.model.project.Project;

/**
 * Deletes a project identified using it's displayed index from the project list.
 */
public class DeleteGroupmateCommand extends Command {

    public static final String COMMAND_WORD = "deleteG";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the groupmate identified by GROUPMATE_INDEX from a project identified by PROJECT_INDEX.\n"
            + "Parameters: PROJECT_INDEX (must be a positive integer) "
            + PREFIX_INDEX + "GROUPMATE_INDEX \n"
            + "Sample: " + COMMAND_WORD + " 2" + PREFIX_INDEX + " 1";
    public static final String MESSAGE_DELETE_PROJECT_SUCCESS = "Deleted Groupmate %1$s from Project %2$s";

    private final Index targetProjectIndex;
    private final Index targetGroupmateIndex;

    /**
     * Constructs a new DeleteGroupmateCommand with the given indexes.
     */
    public DeleteGroupmateCommand(Index targetProjectIndex, Index targetGroupmateIndex) {
        this.targetProjectIndex = targetProjectIndex;
        this.targetGroupmateIndex = targetGroupmateIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (targetProjectIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }
        Project projectToEdit = lastShownList.get(targetProjectIndex.getZeroBased());

        if (targetGroupmateIndex.getZeroBased() >= projectToEdit.getGroupmates().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUPMATE_DISPLAYED_INDEX);
        }
        Groupmate groupmateToDelete = projectToEdit.getGroupmate(targetGroupmateIndex.getZeroBased());

        projectToEdit.deleteGroupmate(targetGroupmateIndex.getZeroBased());
        model.updateFilteredProjectList(Model.PREDICATE_SHOW_ALL_PROJECTS);

        return new CommandResult(String.format(MESSAGE_DELETE_PROJECT_SUCCESS,
                groupmateToDelete.getName(), projectToEdit.getProjectName()),
                new ViewProjectAndOverviewUiCommand(targetProjectIndex));
    }

    @Override
    public boolean equals(Object other) {
        return other == this //short circuit if same project
                || (other instanceof DeleteGroupmateCommand // instanceof handles nulls
                && targetProjectIndex.equals(((DeleteGroupmateCommand) other).targetProjectIndex)
                && targetGroupmateIndex.equals(((DeleteGroupmateCommand) other).targetGroupmateIndex));
    }
}

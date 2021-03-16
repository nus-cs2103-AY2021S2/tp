package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMOVE_TASK_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;

/**
 * Deletes a project identified using it's displayed index from the project list.
 */
public class DeleteContactFromCommand extends Command {

    public static final String COMMAND_WORD = "deleteCfrom";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the contact identified by CONTACT_INDEX from a project identified by PROJECT_INDEX.\n"
            + "Parameters: PROJECT_INDEX (must be a positive integer) "
            + PREFIX_REMOVE_TASK_INDEX + "TODO_INDEX \n"
            + "Sample: " + COMMAND_WORD + " 2" + PREFIX_REMOVE_TASK_INDEX + " 1";
    public static final String MESSAGE_DELETE_PROJECT_SUCCESS = "Deleted Contact %1$s from Project %2$s";

    private final Index targetProjectIndex;
    private final Index targetContactIndex;

    /**
     * Constructs a new DeleteContactFromCommand with the given indexes.
     */
    public DeleteContactFromCommand(Index targetProjectIndex, Index targetContactIndex) {
        this.targetProjectIndex = targetProjectIndex;
        this.targetContactIndex = targetContactIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (targetProjectIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }
        Project projectToEdit = lastShownList.get(targetProjectIndex.getZeroBased());

        if (targetContactIndex.getZeroBased() >= projectToEdit.getParticipants().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToDelete = projectToEdit.getParticipant(targetContactIndex.getZeroBased());

        projectToEdit.deleteParticipant(targetContactIndex.getZeroBased());
        model.updateFilteredProjectList(Model.PREDICATE_SHOW_ALL_PROJECTS);

        return new CommandResult(String.format(MESSAGE_DELETE_PROJECT_SUCCESS,
                personToDelete.getName(), projectToEdit.getProjectName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this //short circuit if same project
                || (other instanceof DeleteContactFromCommand // instanceof handles nulls
                && targetProjectIndex.equals(((DeleteContactFromCommand) other).targetProjectIndex)
                && targetContactIndex.equals(((DeleteContactFromCommand) other).targetContactIndex));
    }
}

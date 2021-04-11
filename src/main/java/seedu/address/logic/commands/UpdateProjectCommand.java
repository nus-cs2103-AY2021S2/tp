package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.uicommands.ViewProjectAndOverviewUiCommand;
import seedu.address.model.Model;
import seedu.address.model.project.DeadlineList;
import seedu.address.model.project.EventList;
import seedu.address.model.project.GroupmateList;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.TodoList;

/**
 * Updates the name of a project in the project list.
 */
public class UpdateProjectCommand extends Command {

    public static final String COMMAND_WORD = "updateP";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the name of the project specified "
            + "by the index number used in the side panel.\n"
            + "Parameters: PROJECT_INDEX "
            + PREFIX_NAME + "PROJECT_NAME\n"
            + "Example: "
            + COMMAND_WORD + " 1 " + PREFIX_NAME + "CS2102 Group Project";

    public static final String MESSAGE_UPDATE_PROJECT_SUCCESS = "Updated Project: %1$s";
    public static final String MESSAGE_DUPLICATE_NAME = "This project already exists in CoLAB.";

    private final Index targetIndex;
    private final ProjectName name;

    /**
     * Constructs an {@code UpdateProjectCommand} with an {@code index} and a {@code name}.
     *
     * @param targetIndex the index of the project in the filtered project list to update.
     * @param name new name for the project.
     */
    public UpdateProjectCommand(Index targetIndex, ProjectName name) {
        requireAllNonNull(targetIndex, name);

        this.targetIndex = targetIndex;
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        Project projectToEdit = lastShownList.get(targetIndex.getZeroBased());

        for (Project project : lastShownList) {
            if (project.getProjectName().equals(name) && !projectToEdit.getProjectName().equals(name)) {
                throw new CommandException(MESSAGE_DUPLICATE_NAME);
            }
        }

        Project updatedProject = createUpdatedProject(projectToEdit, name);

        model.setProject(projectToEdit, updatedProject);
        model.updateFilteredProjectList(Model.PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(String.format(MESSAGE_UPDATE_PROJECT_SUCCESS, updatedProject.getProjectName()),
                new ViewProjectAndOverviewUiCommand(targetIndex));
    }

    /**
     * Creates and returns a {@code Project} with the details of {@code projectToEdit}
     * updated with {@code name}.
     */
    public static Project createUpdatedProject(Project projectToEdit, ProjectName name) {
        assert projectToEdit != null;

        EventList currEventList = projectToEdit.getEvents();
        TodoList currTodoList = projectToEdit.getTodos();
        DeadlineList currDeadlineList = projectToEdit.getDeadlines();
        GroupmateList currGroupmateList = projectToEdit.getGroupmates();

        return new Project(name, currEventList, currTodoList, currDeadlineList, currGroupmateList);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UpdateProjectCommand // instanceof handles nulls
                && targetIndex.equals(((UpdateProjectCommand) other).targetIndex))
                && name.equals(((UpdateProjectCommand) other).name); // state check
    }

}

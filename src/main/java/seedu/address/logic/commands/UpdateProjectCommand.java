package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.DeadlineList;
import seedu.address.model.project.EventList;
import seedu.address.model.project.ParticipantList;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.TodoList;

/**
 * Updates the name of a project in the project list.
 */
public class UpdateProjectCommand extends Command {

    public static final String COMMAND_WORD = "updateP";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the name of the project specified\n"
            + "by the index number used in the displayed project list.\n"
            + "Existing value will bew overwritten by the input value\n"
            + "Parameters: INDEX (must be positive integer)\n"
            + "[" + PREFIX_NAME + "NAME]\n"
            + "Example:\n"
            + COMMAND_WORD + " 1 " + "new name";

    public static final String MESSAGE_UPDATE_PROJECT_SUCCESS = "Edited Project: %1$s";
    public static final String MESSAGE_DUPLICATE_NAME = "This project name already exists in CoLAB.";

    private final Index index;
    private final ProjectName name;

    /**
     * Constructs an {@code updateProjectCommand} with an {@code index} and a {@code name}.
     *
     * @param index of the project in the filtered project list to update.
     * @param name new name for the project
     */
    public UpdateProjectCommand(Index index, ProjectName name) {
        requireNonNull(index);
        requireNonNull(name);

        this.index = index;
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        for (Project project : lastShownList) {
            if (project.getProjectName().equals(name)) {
                throw new CommandException(MESSAGE_DUPLICATE_NAME);
            }
        }

        Project projectToEdit = lastShownList.get(index.getZeroBased());
        Project updatedProject = createUpdatedProject(projectToEdit, name);

        model.setProject(projectToEdit, updatedProject);
        model.updateFilteredProjectList(Model.PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(String.format(MESSAGE_UPDATE_PROJECT_SUCCESS, updatedProject));
    }

    /**
     * Creates and returns a {@code Project} with the details of {@code projectToEdit}
     * updated with {@code name}.
     */
    private static Project createUpdatedProject(Project projectToEdit, ProjectName name) {
        assert projectToEdit != null;

        EventList currEventList = projectToEdit.getEvents();
        TodoList currTodoList = projectToEdit.getTodos();
        DeadlineList currDeadlineList = projectToEdit.getDeadlines();
        ParticipantList currParticipantList = projectToEdit.getParticipants();

        return new Project(name, currEventList, currTodoList, currDeadlineList, currParticipantList);
    }

}

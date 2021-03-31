package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_ADD_GROUPMATE_SUCCESS;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_GROUPMATE;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.uicommands.ViewProjectAndOverviewUiCommand;
import seedu.address.model.Model;
import seedu.address.model.groupmate.Groupmate;
import seedu.address.model.project.Project;

/**
 * Adds a groupmate to a project.
 */
public class AddGroupmateCommand extends Command {

    public static final String COMMAND_WORD = "addG";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a groupmate to an existing project.\n"
            + "Parameters: PROJECT_INDEX "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_ROLE + "ROLE]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "John Doe "
            + PREFIX_ROLE + "leader "
            + PREFIX_ROLE + "frontend-developer";

    private final Index projectToAddToIndex;
    private final Groupmate groupmateToAdd;

    /**
     * Creates an AddGroupmateCommand to add the specified {@code Groupmate} to the specified {@code Project}
     */
    public AddGroupmateCommand(Index projectIndex, Groupmate groupmate) {
        requireAllNonNull(projectIndex, groupmate);
        projectToAddToIndex = projectIndex;
        groupmateToAdd = groupmate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Project> lastShownProjectList = model.getFilteredProjectList();

        if (projectToAddToIndex.getZeroBased() >= lastShownProjectList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        Project projectToAddTo = requireNonNull(lastShownProjectList.get(projectToAddToIndex.getZeroBased()));

        if (projectToAddTo.hasGroupmate(groupmateToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUPMATE);
        }

        // logic goes here
        projectToAddTo.addGroupmate(groupmateToAdd);
        model.updateFilteredProjectList(model.PREDICATE_SHOW_ALL_PROJECTS);

        return new CommandResult(
                String.format(MESSAGE_ADD_GROUPMATE_SUCCESS, groupmateToAdd.getName(), projectToAddTo.getProjectName()),
                new ViewProjectAndOverviewUiCommand(projectToAddToIndex)
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddGroupmateCommand // instanceof handles nulls
                && projectToAddToIndex.equals(((AddGroupmateCommand) other).projectToAddToIndex)
                && groupmateToAdd.equals(((AddGroupmateCommand) other).groupmateToAdd)
            );
    }
}

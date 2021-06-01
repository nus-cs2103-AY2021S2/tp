package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.uicommands.ViewProjectAndOverviewUiCommand;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.model.project.exceptions.DuplicateDeadlineException;
import seedu.address.model.task.deadline.Deadline;

/**
 * Adds an deadline to a specified project in CoLAB.
 */
public class AddDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "addD";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a deadline to a specified project.\n"
            + "Parameters: PROJECT_INDEX "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_DEADLINE_DATE + "DATE\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DESCRIPTION + "Project report "
            + PREFIX_DEADLINE_DATE + "24-04-2021";

    private final Index index;
    private final Deadline toAdd;

    /**
     * Creates an AddDeadlineCommand to add specified {@code Deadline} to {@code Project} with {@code Index}.
     *
     * @param index index of {@code Project} to add deadline in the list.
     * @param deadline {@code Deadline} to add.
     */
    public AddDeadlineCommand(Index index, Deadline deadline) {
        requireAllNonNull(index, deadline);

        this.index = index;
        this.toAdd = deadline;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        Project projectToEdit = lastShownList.get(index.getZeroBased());
        assert projectToEdit != null;

        try {
            projectToEdit.addDeadline(toAdd);
        } catch (DuplicateDeadlineException e) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_DEADLINE);
        }

        model.updateFilteredProjectList(Model.PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(String.format(Messages.MESSAGE_ADD_DEADLINE_SUCCESS, toAdd,
                projectToEdit.getProjectName()), new ViewProjectAndOverviewUiCommand(index));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDeadlineCommand // instanceof handles nulls
                && toAdd.equals(((AddDeadlineCommand) other).toAdd)
                && index.equals(((AddDeadlineCommand) other).index));
    }

}

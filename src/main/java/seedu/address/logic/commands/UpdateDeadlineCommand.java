package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UPDATE_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.model.task.CompletableDeadline;

/**
 * Updates an deadline inside a project.
 */
public class UpdateDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "updateD";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates a deadline of a project specified "
            + "by 2 index numbers: project index and target deadline index.\n"
            + "Parameters: PROJECT_INDEX "
            + PREFIX_UPDATE_INDEX + "TARGET_DEADLINE_INDEX "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_DEADLINE_DATE + "DATE\n"
            + "Example:\n" + COMMAND_WORD + " 1 "
            + PREFIX_UPDATE_INDEX + "1 "
            + PREFIX_DESCRIPTION + "Project tasks "
            + PREFIX_DEADLINE_DATE + "04-04-2021";

    public static final String MESSAGE_UPDATE_DEADLINE_SUCCESS = "Edited deadline: %1$s";
    public static final String MESSAGE_DUPLICATE_DEADLINE = "This deadline already exists in this project.";

    private final Index projectIndex;
    private final Index targetDeadlineIndex;
    private final CompletableDeadline deadline;

    /**
     * Constructs an {@code updateDeadlineCommand} with a {@code projectIndex},
     * {@code targetDeadlineIndex} and a {@code Deadline}.
     *
     * @param projectIndex index of the project in the filtered project list.
     * @param targetDeadlineIndex index of the {@code Deadline} in the {@code Deadline} to update.
     * @param deadline new deadline given for updating.
     */
    public UpdateDeadlineCommand(Index projectIndex, Index targetDeadlineIndex, CompletableDeadline deadline) {
        requireAllNonNull(projectIndex, targetDeadlineIndex, deadline);

        this.projectIndex = projectIndex;
        this.targetDeadlineIndex = targetDeadlineIndex;
        this.deadline = deadline;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (projectIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }
        Project projectRelated = lastShownList.get(projectIndex.getZeroBased());

        if (targetDeadlineIndex.getZeroBased() >= projectRelated.getDeadlines().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DEADLINE_DISPLAYED_INDEX);
        }

        if (projectRelated.getDeadlines().hasDeadline(deadline)) {
            throw new CommandException(MESSAGE_DUPLICATE_DEADLINE);
        }

        if (projectRelated.getDeadlines().checkIsDone(targetDeadlineIndex.getZeroBased())) {
            projectRelated.getDeadlines().setDeadline(targetDeadlineIndex.getZeroBased(), deadline);
            projectRelated.getDeadlines().markAsDone(targetDeadlineIndex.getZeroBased());
        } else {
            projectRelated.getDeadlines().setDeadline(targetDeadlineIndex.getZeroBased(), deadline);
        }
        model.updateFilteredProjectList(Model.PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(String.format(MESSAGE_UPDATE_DEADLINE_SUCCESS, deadline));
    }
}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.uicommands.ViewProjectAndOverviewUiCommand;
import seedu.address.model.Model;
import seedu.address.model.project.DeadlineList;
import seedu.address.model.project.Project;
import seedu.address.model.task.CompletableDeadline;
import seedu.address.model.task.deadline.Deadline;

/**
 * Updates an deadline inside a project.
 */
public class UpdateDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "updateD";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the deadline of a project "
            + "identified by 2 index numbers: project index and deadline index.\n"
            + "Parameters: PROJECT_INDEX "
            + PREFIX_INDEX + "DEADLINE_INDEX "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_DEADLINE_DATE + "DATE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_INDEX + "1 "
            + PREFIX_DESCRIPTION + "Submission deadline "
            + PREFIX_DEADLINE_DATE + "24-04-2021";

    public static final String MESSAGE_UPDATE_DEADLINE_SUCCESS = "Updated deadline: %1$s";
    public static final String MESSAGE_DUPLICATE_DEADLINE = "This deadline already exists in this project.";
    public static final String MESSAGE_UNCHANGED_DEADLINE = "This deadline already has this description and date.";

    private final Index projectIndex;
    private final Index targetDeadlineIndex;
    private final UpdateDeadlineDescriptor updateDeadlineDescriptor;

    /**
     * Constructs an {@code updateDeadlineCommand} with a {@code projectIndex},
     * {@code targetDeadlineIndex} and a {@code Deadline}.
     *
     * @param projectIndex index of the project in the filtered project list.
     * @param targetDeadlineIndex index of the {@code Deadline} in the {@code Deadline} to update.
     * @param updateDeadlineDescriptor details to update Deadline with.
     */
    public UpdateDeadlineCommand(Index projectIndex, Index targetDeadlineIndex,
                                 UpdateDeadlineDescriptor updateDeadlineDescriptor) {
        requireAllNonNull(projectIndex, targetDeadlineIndex, updateDeadlineDescriptor);

        this.projectIndex = projectIndex;
        this.targetDeadlineIndex = targetDeadlineIndex;
        this.updateDeadlineDescriptor = new UpdateDeadlineDescriptor(updateDeadlineDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (projectIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }
        Project projectToUpdate = lastShownList.get(projectIndex.getZeroBased());
        DeadlineList deadlineList = projectToUpdate.getDeadlines();

        if (targetDeadlineIndex.getZeroBased() >= deadlineList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DEADLINE_DISPLAYED_INDEX);
        }

        CompletableDeadline deadlineToUpdate = deadlineList.getDeadline(targetDeadlineIndex.getZeroBased());
        CompletableDeadline updatedDeadline = createUpdatedDeadline(deadlineToUpdate, updateDeadlineDescriptor);

        if (deadlineList.hasDeadline(updatedDeadline) && !deadlineToUpdate.equals(updatedDeadline)) {
            throw new CommandException(MESSAGE_DUPLICATE_DEADLINE);
        }

        if (deadlineList.checkIsDone(targetDeadlineIndex.getZeroBased())) {
            updatedDeadline.markAsDone();
        }

        if (deadlineToUpdate.equals(updatedDeadline)) {
            throw new CommandException(MESSAGE_UNCHANGED_DEADLINE);
        }

        deadlineList.setDeadline(targetDeadlineIndex.getZeroBased(), updatedDeadline);

        model.updateFilteredProjectList(Model.PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(String.format(MESSAGE_UPDATE_DEADLINE_SUCCESS, updatedDeadline),
                new ViewProjectAndOverviewUiCommand(projectIndex));
    }

    /**
     * Creates and returns a {@code Deadline} with the details of {@code deadlineToUpdate}
     * edited with {@code updateDeadlineDescriptor}.
     */
    private static CompletableDeadline createUpdatedDeadline(CompletableDeadline deadlineToUpdate,
                                                             UpdateDeadlineDescriptor updateDeadlineDescriptor) {
        assert deadlineToUpdate != null;

        String updatedDescription = updateDeadlineDescriptor.getDescription().orElse(deadlineToUpdate.getDescription());
        LocalDate updatedDate = updateDeadlineDescriptor.getDate().orElse(deadlineToUpdate.getBy());

        return new Deadline(updatedDescription, updatedDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UpdateDeadlineCommand // instanceof handles nulls
                && projectIndex.equals(((UpdateDeadlineCommand) other).projectIndex) // state check
                && targetDeadlineIndex.equals(((UpdateDeadlineCommand) other).targetDeadlineIndex)
                && updateDeadlineDescriptor.equals(((UpdateDeadlineCommand) other).updateDeadlineDescriptor));
    }

    /**
     * Stores the details to edit the {@code Deadline} with. Each non-empty field value will replace the
     * corresponding field value of the {@code Deadline}.
     */
    public static class UpdateDeadlineDescriptor {
        private String description;
        private LocalDate date;

        public UpdateDeadlineDescriptor() {}

        /**
         * Copy constructor.
         */
        public UpdateDeadlineDescriptor(UpdateDeadlineDescriptor toCopy) {
            setDescription(toCopy.description);
            setDate(toCopy.date);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, date);
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Optional<String> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public Optional<LocalDate> getDate() {
            return Optional.ofNullable(date);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof UpdateDeadlineDescriptor)) {
                return false;
            }

            // state check
            UpdateDeadlineDescriptor e = (UpdateDeadlineDescriptor) other;

            return getDescription().equals(e.getDescription())
                    && getDate().equals(e.getDate());
        }
    }
}

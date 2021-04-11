package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.uicommands.ViewProjectAndOverviewUiCommand;
import seedu.address.model.Model;
import seedu.address.model.groupmate.Groupmate;
import seedu.address.model.groupmate.Name;
import seedu.address.model.groupmate.Role;
import seedu.address.model.project.GroupmateList;
import seedu.address.model.project.Project;

/**
 * Updates an groupmate inside a project.
 */
public class UpdateGroupmateCommand extends Command {

    public static final String COMMAND_WORD = "updateG";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the groupmate of a project "
            + "identified by 2 index numbers: project index and groupmates index.\n"
            + "Parameters: PROJECT_INDEX "
            + PREFIX_INDEX + "GROUPMATE_INDEX "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_ROLE + "ROLE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_INDEX + "1 "
            + PREFIX_NAME + "Alice "
            + PREFIX_ROLE + "UI-designer";

    public static final String MESSAGE_UPDATE_GROUPMATE_SUCCESS = "Updated groupmate: %1$s";
    public static final String MESSAGE_DUPLICATE_GROUPMATE = "This groupmate already exists in this project.";
    public static final String MESSAGE_UNCHANGED_GROUPMATE = "This groupmate already has this name and roles.";

    private final Index projectIndex;
    private final Index targetGroupmateIndex;
    private final UpdateGroupmateDescriptor updateGroupmateDescriptor;

    /**
     * Constructs an {@code UpdateGroupmateDescriptor} with a {@code projectIndex}.
     * {@code targetGroupmateIndex} and an {@code Groupmate}.
     *
     * @param projectIndex     index of the project in the filtered project list.
     * @param targetGroupmateIndex index of the {@code Groupmate} in the {@code GroupmateList} to update.
     * @param updateGroupmateDescriptor details to edit {@code Groupmate} with.
     */
    public UpdateGroupmateCommand(Index projectIndex, Index targetGroupmateIndex,
                                  UpdateGroupmateDescriptor updateGroupmateDescriptor) {
        requireAllNonNull(projectIndex, targetGroupmateIndex, updateGroupmateDescriptor);

        this.projectIndex = projectIndex;
        this.targetGroupmateIndex = targetGroupmateIndex;
        this.updateGroupmateDescriptor = new UpdateGroupmateDescriptor(updateGroupmateDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (projectIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        Project projectToUpdate = lastShownList.get(projectIndex.getZeroBased());
        GroupmateList groupmates = projectToUpdate.getGroupmates();

        if (targetGroupmateIndex.getZeroBased() >= groupmates.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUPMATE_DISPLAYED_INDEX);
        }

        Groupmate groupmateToUpdate = groupmates.get(targetGroupmateIndex.getZeroBased());
        Groupmate updatedGroupmate = createUpdatedGroupmate(groupmateToUpdate, updateGroupmateDescriptor);

        if (groupmates.contains(updatedGroupmate) && !groupmateToUpdate.isSameGroupmate(updatedGroupmate)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUPMATE);
        }

        if (groupmateToUpdate.equals(updatedGroupmate)) {
            throw new CommandException(MESSAGE_UNCHANGED_GROUPMATE);
        }

        projectToUpdate.setGroupmate(targetGroupmateIndex.getZeroBased(), updatedGroupmate);
        model.updateFilteredProjectList(Model.PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(String.format(MESSAGE_UPDATE_GROUPMATE_SUCCESS, updatedGroupmate),
                new ViewProjectAndOverviewUiCommand(projectIndex));
    }

    /**
     * Creates and returns a {@code Groupmate} with the details of {@code groupmateToUpdate}
     * edited with {@code updatedGroupmateDescriptor}.
     */
    private static Groupmate createUpdatedGroupmate(Groupmate groupmateToUpdate,
                                                    UpdateGroupmateDescriptor updateGroupmateDescriptor) {
        assert groupmateToUpdate != null;

        Name updatedName = updateGroupmateDescriptor.getName().orElse(groupmateToUpdate.getName());
        Set<Role> updatedRoles = updateGroupmateDescriptor.getRoles().orElse(groupmateToUpdate.getRoles());

        return new Groupmate(updatedName, updatedRoles);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpdateGroupmateCommand)) {
            return false;
        }

        // state check
        UpdateGroupmateCommand command = (UpdateGroupmateCommand) other;
        return projectIndex.equals(command.projectIndex)
                && targetGroupmateIndex.equals(command.targetGroupmateIndex)
                && updateGroupmateDescriptor.equals(command.updateGroupmateDescriptor);
    }

    /**
     * Stores the details to update the {@code Groupmate}. Each non-empty field value will replace the
     * corresponding field value of the {@code Groupmate}.
     */
    public static class UpdateGroupmateDescriptor {
        private Name name;
        private Set<Role> roles;

        public UpdateGroupmateDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public UpdateGroupmateDescriptor(UpdateGroupmateDescriptor toCopy) {
            setName(toCopy.name);
            setRoles(toCopy.roles);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, roles);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setRoles(Set<Role> roles) {
            this.roles = (roles != null) ? new HashSet<>(roles) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Role>> getRoles() {
            return (roles != null) ? Optional.of(Collections.unmodifiableSet(roles)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof UpdateGroupmateDescriptor)) {
                return false;
            }

            // state check
            UpdateGroupmateDescriptor e = (UpdateGroupmateDescriptor) other;

            return getName().equals(e.getName())
                    && getRoles().equals(e.getRoles());
        }
    }
}

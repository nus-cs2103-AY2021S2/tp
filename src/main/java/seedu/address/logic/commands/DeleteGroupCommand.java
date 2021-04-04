package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_GROUP;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;

public class DeleteGroupCommand extends Command {
    public static final String COMMAND_WORD = "del-group";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the group of the specified name \n"
            + "Parameters: " + PREFIX_NAME + "GROUP_NAME (must be alphanumeric only) \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Close Friends";

    public static final String MESSAGE_DELETE_GROUP_SUCCESS = "Deleted group %1$s";

    private final Name groupName;

    /**
     * @param groupName the group Name being deleted.
     */
    public DeleteGroupCommand(Name groupName) {
        requireAllNonNull(groupName);
        this.groupName = groupName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Group group = model.getGroupMap().getOrDefault(groupName, new Group(groupName));

        if (!model.hasGroup(group)) {
            throw new CommandException(MESSAGE_UNKNOWN_GROUP);
        }

        model.deleteGroup(group);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_DELETE_GROUP_SUCCESS, groupName));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteGroupCommand)) {
            return false;
        }

        DeleteGroupCommand e = (DeleteGroupCommand) other;
        return groupName.equals(e.groupName);
    }
}

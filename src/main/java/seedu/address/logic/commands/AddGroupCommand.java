package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

public class AddGroupCommand extends Command {
    public static final String COMMAND_WORD = "add-group";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds the group of the specified name with the"
            + "specified persons indicated by the index numbers used in the last person listing. If group exists,"
            + "add people into group if they are not in it.  \n"
            + "Parameters: " + PREFIX_NAME + "GROUP_NAME (must be alphanumeric only) "
            + PREFIX_PERSONS + "[INDEX]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Close Friends" + " "
            + PREFIX_PERSONS + "1 2 3 4";

    public static final String MESSAGE_ADD_GROUP_SUCCESS = "Added/Added into group %1$s";

    private final List<Index> indexes;
    private final Name groupName;

    /**
     * @param indexes of the person in the filtered person list to add to the group
     * @param groupName the group Name being added/adding persons into
     */
    public AddGroupCommand(List<Index> indexes, Name groupName) {
        requireAllNonNull(indexes, groupName);

        this.indexes = indexes;
        this.groupName = groupName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        Group group = model.getGroupMap().getOrDefault(groupName, new Group(groupName));
        Set<Name> newPersonSet = new HashSet<>(group.getPersonNames());

        boolean isNewGroup = !model.hasGroup(group);

        if (indexes.stream().anyMatch(index->index.getZeroBased() >= lastShownList.size())) {
            throw new CommandException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        for (Index index: indexes) {
            Person person = lastShownList.get(index.getZeroBased());
            newPersonSet.add(person.getName());
        }

        group.setPersons(newPersonSet);
        if (isNewGroup) {
            model.addGroup(group);
        } else {
            model.setGroup(groupName, group);
        }
        model.updateFilteredPersonList(p -> group.getPersonNames().contains(p.getName()));
        return new CommandResult(String.format(MESSAGE_ADD_GROUP_SUCCESS, groupName));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddGroupCommand)) {
            return false;
        }

        AddGroupCommand e = (AddGroupCommand) other;
        return indexes.equals(e.indexes) && groupName.equals(e.groupName);
    }
}


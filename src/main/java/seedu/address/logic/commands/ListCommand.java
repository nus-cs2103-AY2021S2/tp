package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;
/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all persons. If a name is given,"
            + "list all the persons in the specified group name.\n"
            + "PARAMETERS: [" + PREFIX_NAME + "GROUP_NAME]\n"
            + "EXAMPLE: list " + PREFIX_NAME + "Close Friends.\n";

    public static final String MESSAGE_SUCCESS_DEFAULT = "Listed all persons";
    public static final String MESSAGE_SUCCESS_GROUP = "Listed all persons in group %1$s";

    private Optional<Name> name;

    public ListCommand(Optional<Name> name) {
        this.name = name;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (this.name.isEmpty()) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(MESSAGE_SUCCESS_DEFAULT);
        } else {
            Group group = model.getGroupMap().get(name.get());
            if (group == null) {
                throw new CommandException(MESSAGE_UNKNOWN_GROUP);
            }

            //this will cause the UI to select the group cell.
            model.setGroup(group.getName(), group);
            model.updateFilteredPersonList(p -> group.getPersonNames().contains(p.getName()));
            return new CommandResult(String.format(MESSAGE_SUCCESS_GROUP, group.getName()));
        }
    }
}

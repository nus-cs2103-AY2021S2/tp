package seedu.heymatez.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.heymatez.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.heymatez.commons.core.Messages.MESSAGE_EMPTY_PERSON_LIST;

import seedu.heymatez.logic.commands.exceptions.CommandException;
import seedu.heymatez.model.Model;

/**
 * Lists all persons in HeyMatez to the user.
 */
public class ViewMembersCommand extends Command {

    public static final String COMMAND_WORD = "viewMembers";

    public static final String MESSAGE_SUCCESS = "Listed all members in this CCA!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        if (model.isPersonListEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_PERSON_LIST);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}

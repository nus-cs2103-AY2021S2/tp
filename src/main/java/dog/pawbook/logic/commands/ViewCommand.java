package dog.pawbook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import dog.pawbook.commons.core.Messages;
import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.Model;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.RelatedEntityPredicate;

/**
 * Shows all owners in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views all related entities of one entity based on ID"
            + " and displays them as a list with index numbers.\n"
            + "Allows user to quickly search for e.g. All the owner's dogs or all the dogs in a program.\n"
            + "Parameters: KEYWORD [ID]...\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final int targetEntityId;

    public ViewCommand(int targetEntityId) {
        this.targetEntityId = targetEntityId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasEntity(targetEntityId)) {
            throw new CommandException(Messages.MESSAGE_INVALID_ENTITY_DISPLAYED_ID);
        }

        Entity targetEntity = model.getEntity(targetEntityId);

        ArrayList<Integer> targetIdList = new ArrayList<>();
        targetIdList.add(targetEntityId);
        targetIdList.addAll(targetEntity.getRelatedEntityIds());

        model.updateFilteredEntityList(new RelatedEntityPredicate(targetIdList));

        return new CommandResult(
                String.format(Messages.MESSAGE_ENTITIES_LISTED_OVERVIEW, model.getFilteredEntityList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                        && targetEntityId == (((ViewCommand) other).targetEntityId)); // state check
    }
}

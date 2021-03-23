package dog.pawbook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import dog.pawbook.commons.core.Messages;
import dog.pawbook.model.Model;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;

/**
 * Shows all owners in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views all related entities based on their IDs"
            + " and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [CLASS] [ID]...\n"
            + "Example: " + COMMAND_WORD + " owner 1";

    private final int targetEntityId;

    public ViewCommand(int targetEntityId) {
        this.targetEntityId = targetEntityId;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        // 1. Access model and fetch the target entity
        Entity targetEntity = model.getAddressBook().getEntityList().get(targetEntityId).getValue();
        // 2. Find the array list of related ids -> switch case owner, dog, program
        List<Integer> targetIdList = new ArrayList<>();
        if (targetEntity instanceof Dog) {
            Dog targetEntityDog = (Dog) targetEntity;
            targetIdList.add(targetEntityDog.getOwnerId());
        } else if (targetEntity instanceof Owner) {
            Owner targetEntityOwner = (Owner) targetEntity;
            targetIdList.addAll(targetEntityOwner.getDogIdSet());
        }
        // 3. Create a predicate using that arraylist of ids

        model.updateFilteredEntityList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ENTITIES_LISTED_OVERVIEW, model.getFilteredEntityList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && predicate.equals(((ViewCommand) other).predicate)); // state check
    }
}

//@@author ZhangAnli
package dog.pawbook.logic.commands;

import static dog.pawbook.commons.core.Messages.MESSAGE_ENTITIES_LISTED_OVERVIEW;
import static dog.pawbook.commons.core.Messages.MESSAGE_ENTITIES_LISTED_OVERVIEW_FOR_ONE;
import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_ENTITY_ID;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.Model;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.IdMatchPredicate;
import dog.pawbook.model.managedentity.ViewCommandComparator;
import dog.pawbook.model.managedentity.program.Program;
import javafx.util.Pair;

/**
 * Shows all owners in database whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views all related entities of the specified ID. \n"
            + "Parameters: [ID]...\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS_FORMAT = "Viewing!";

    private final int targetEntityId;

    /**
     * Constructs a View Command object.
     *
     * @param targetEntityId of the searched entity.
     */
    public ViewCommand(int targetEntityId) {
        // Check that targetEntityId is not invalid.
        assert (targetEntityId > 0);

        this.targetEntityId = targetEntityId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasEntity(targetEntityId)) {
            throw new CommandException(MESSAGE_INVALID_ENTITY_ID);
        }

        Entity targetEntity = model.getEntity(targetEntityId);

        ArrayList<Integer> targetIdList = new ArrayList<>();
        targetIdList.add(targetEntityId);
        targetIdList.addAll(targetEntity.getRelatedEntityIds());

        List<Integer> enrolledPrograms = model.getUnfilteredEntityList()
                .stream()
                .filter(x -> x.getValue() instanceof Program)
                .filter(x -> x.getValue().getRelatedEntityIds().contains(targetEntityId))
                .map(Pair::getKey)
                .collect(Collectors.toList());
        targetIdList.addAll(enrolledPrograms);

        assert(!targetIdList.isEmpty());

        model.updateFilteredEntityList(new IdMatchPredicate(targetIdList));
        model.sortEntities(new ViewCommandComparator(targetEntity.getClass()));

        if (model.getFilteredEntityList().size() == 1) {
            return new CommandResult(String.format(
                    MESSAGE_ENTITIES_LISTED_OVERVIEW_FOR_ONE, model.getFilteredEntityList().size()));
        } else {
            return new CommandResult(String.format(
                    MESSAGE_ENTITIES_LISTED_OVERVIEW, model.getFilteredEntityList().size()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && targetEntityId == (((ViewCommand) other).targetEntityId)); // state check
    }
}

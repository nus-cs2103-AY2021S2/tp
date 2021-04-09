package dog.pawbook.logic.commands;

import static dog.pawbook.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import dog.pawbook.model.Model;
import dog.pawbook.model.managedentity.Entity;
import javafx.util.Pair;

/**
 * Lists all owners in the database to the user.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": List a specified type of entity.\n"
            + "Parameters: ENTITY_TYPE";

    public static final String MESSAGE_SUCCESS_FORMAT = "Listed all %s(s)!";
    public static final String MESSAGE_NO_ENTITY_AVAILABLE = "No %ss available to be listed!";

    private final String entityName;

    private final Predicate<Pair<Integer, Entity>> predicate;

    /**
     * Construct a list command that lists a specified entity only.
     */
    public ListCommand(Predicate<Pair<Integer, Entity>> predicate, String entityType) {
        requireAllNonNull(predicate, entityType);

        entityName = entityType;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredEntityList(predicate);
        model.sortEntities(model.COMPARATOR_ID_ASCENDING_ORDER);

        return new CommandResult(model.getFilteredEntityList().size() != 0
                ? String.format(MESSAGE_SUCCESS_FORMAT, entityName)
                : String.format(MESSAGE_NO_ENTITY_AVAILABLE, entityName));
    }
}

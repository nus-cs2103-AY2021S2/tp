package dog.pawbook.logic.commands;

import static dog.pawbook.model.Model.PREDICATE_SHOW_ALL_ENTITIES;
import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import dog.pawbook.model.Model;
import dog.pawbook.model.managedentity.Entity;
import javafx.util.Pair;

/**
 * Lists all owners in the address book to the user.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS_FORMAT = "Listed all %s(s)";

    private final String entityName;

    private final Predicate<Pair<Integer, Entity>> predicate;

    /**
     * Construct a list command that lists all entities.
     */
    public ListCommand() {
        entityName = Entity.class.getSimpleName().toLowerCase();
        predicate = PREDICATE_SHOW_ALL_ENTITIES;
    }

    /**
     * Construct a list command that lists a specified entity only.
     */
    public ListCommand(Predicate<Pair<Integer, Entity>> predicate, String entityType) {
        entityName = entityType;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEntityList(predicate);
        return new CommandResult(String.format(MESSAGE_SUCCESS_FORMAT, entityName));
    }
}

package chim.logic.commands;

import static chim.model.Model.PREDICATE_SHOW_ALL_CHEESES;
import static java.util.Objects.requireNonNull;

import chim.model.Model;

/**
 * Lists all cheeses in the CHIM to the user.
 */
public class ListCheesesCommand extends Command {

    public static final String COMMAND_WORD = "listcheeses";
    public static final String MESSAGE_SUCCESS = "Listed all cheeses";
    public static final String SUMMARY_MESSAGE = "Listed %d cheeses (%d assigned , %d unassigned)";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCheeseList(PREDICATE_SHOW_ALL_CHEESES);
        model.setPanelToCheeseList();
        int unassignedCount = model.getFilteredCheeseListUnassignedCount();
        int totalCheese = model.getFilteredCheeseList().size();
        return new CommandResult(
                String.format(
                        SUMMARY_MESSAGE,
                        totalCheese,
                        totalCheese - unassignedCount,
                        unassignedCount
                )
        );
    }
}

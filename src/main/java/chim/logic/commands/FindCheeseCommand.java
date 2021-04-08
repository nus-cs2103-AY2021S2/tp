package chim.logic.commands;

import static chim.logic.parser.CliSyntax.PREFIX_CHEESE_ASSIGNMENT_STATUS;
import static chim.logic.parser.CliSyntax.PREFIX_CHEESE_TYPE;
import static java.util.Objects.requireNonNull;

import chim.commons.core.Messages;
import chim.model.Model;
import chim.model.cheese.Cheese;
import chim.model.util.predicate.FieldPredicate;

/**
 * Finds the cheeses in CHIM matching the input keywords; keyword matching is case insensitive.
 * Lists the matching cheeses to the user.
 */
public class FindCheeseCommand extends Command {

    public static final String COMMAND_WORD = "findcheese";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Find cheeses matching input assignment status and cheese type(s).\n"
            + "Parameters: "
            + "[" + PREFIX_CHEESE_ASSIGNMENT_STATUS + "ASSIGNMENT STATUS] "
            + "[" + PREFIX_CHEESE_TYPE + "CHEESE TYPE] \n"
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_CHEESE_ASSIGNMENT_STATUS + "assigned "
            + PREFIX_CHEESE_TYPE + "brie feta";

    public final FieldPredicate<Cheese> predicate;

    /**
     * Creates a new {@code FindCheeseCommand}
     *
     * @param predicate Predicate for filtering the cheeses list.
     */
    public FindCheeseCommand(FieldPredicate<Cheese> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCheeseList(predicate);
        model.setPanelToCheeseList();

        String message = String.format(
                Messages.MESSAGE_CHEESES_FOUND_OVERVIEW,
                model.getFilteredCheeseList().size(),
                predicate
        );

        if (model.getFilteredCheeseList().size() == 0) {
            message = String.format(Messages.MESSAGE_CHEESES_NOT_FOUND_OVERVIEW, predicate);
        }

        return new CommandResult(message);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof FindCheeseCommand
                && predicate.equals(((FindCheeseCommand) other).predicate));
    }
}

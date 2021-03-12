package seedu.address.logic.commands.menu;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.dish.DishStub;

/**
 * Adds a dish to the menu.
 */
public class MenuAddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " [Insert Usage here]";

    public static final String MESSAGE_SUCCESS = "New dish added: %1$s";
    public static final String MESSAGE_DUPLICATE_DISH = "This dish already exists in the menu";

    private final DishStub toAdd;

    /**
     * Creates an MenuAddCommand to add the specified {@code Dish}
     */
    public MenuAddCommand(DishStub dish) {
        requireNonNull(dish);
        toAdd = dish;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Check model has dish here throw MESSAGE_DUPLICATE_DISH

        // Add dish to model here
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MenuAddCommand // instanceof handles nulls
                && toAdd.equals(((MenuAddCommand) other).toAdd));
    }
}

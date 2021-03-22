package seedu.address.logic.commands.menu;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.dish.Dish;

/**
 * Adds a dish to the menu.
 */
public class MenuAddCommand extends Command {

    public static final String COMPONENT_WORD = "menu";
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMPONENT_WORD + " " + COMMAND_WORD + ": Adds a dish to the menu. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PRICE + "PRICE\n"
            + "Example: " + COMPONENT_WORD + " " + COMMAND_WORD + " "
            + PREFIX_NAME + "Fries "
            + PREFIX_PRICE + "5.10";

    public static final String MESSAGE_SUCCESS = "New dish added: %1$s";
    public static final String MESSAGE_DUPLICATE_DISH = "This dish already exists in the menu";

    private final Dish toAdd;

    /**
     * Creates an MenuAddCommand to add the specified {@code Dish}
     */
    public MenuAddCommand(Dish dish) {
        requireNonNull(dish);
        toAdd = dish;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasDish(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_DISH);
        }

        model.addDish(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MenuAddCommand // instanceof handles nulls
                && toAdd.equals(((MenuAddCommand) other).toAdd));
    }
}

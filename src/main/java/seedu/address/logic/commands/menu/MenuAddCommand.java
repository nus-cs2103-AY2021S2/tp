package seedu.address.logic.commands.menu;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.List;

import seedu.address.commons.core.Pair;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.dish.Dish;
import seedu.address.model.ingredient.Ingredient;

/**
 * Adds a dish to the menu.
 */
public class MenuAddCommand extends Command {

    public static final String COMPONENT_WORD = "menu";
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMPONENT_WORD + " " + COMMAND_WORD + ": Adds a dish to the menu. \n"
            + "Parameters: "
            + PREFIX_NAME + "[NAME] "
            + PREFIX_PRICE + "[PRICE] "
            + PREFIX_INGREDIENT + "[INGREDIENT_ID]... "
            + PREFIX_QUANTITY + "[INGREDIENT_QUANTITY]...\n"
            + "Example: " + COMPONENT_WORD + " " + COMMAND_WORD + " "
            + PREFIX_NAME + "Chicken Chop "
            + PREFIX_PRICE + "5.10 "
            + PREFIX_INGREDIENT + "2 "
            + PREFIX_QUANTITY + "1";


    public static final String MESSAGE_SUCCESS = "New dish added: %1$s";

    private final String name;
    private final double price;
    private final List<Pair<Index, Integer>> ingredientIdsQuantityList;

    /**
     * Creates an MenuAddCommand to add the specified {@code Dish}
     */
    public MenuAddCommand(String name, double price,
                          List<Pair<Index, Integer>> ingredientIdsQuantityList) {
        this.name = name;
        this.price = price;
        this.ingredientIdsQuantityList = ingredientIdsQuantityList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Pair<Ingredient, Integer>> ingredientQuantityList =
                MenuCommandUtil.lookupIngredientIds(ingredientIdsQuantityList, model);

        Dish toAdd = new Dish(name, price, ingredientQuantityList);

        if (MenuCommandUtil.isValidDishAddition(toAdd, model)) {
            model.addDish(toAdd);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.getName()), CommandResult.CRtype.DISH);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MenuAddCommand // instanceof handles nulls
                && name.equals(((MenuAddCommand) other).name)
                && price == ((MenuAddCommand) other).price
                && ingredientIdsQuantityList.equals(((MenuAddCommand) other).ingredientIdsQuantityList));
    }
}

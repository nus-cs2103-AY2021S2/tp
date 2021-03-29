package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CARBOS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FATS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROTEINS;

import seedu.address.logic.commands.AddFoodItemCommand;
import seedu.address.logic.commands.UpdateFoodItemCommand.EditFoodDescriptor;
import seedu.address.model.food.Food;

/**
 * A utility class for Food.
 */
public class FoodUtil {

    /**
     * Returns an add command string for adding the {@code food}.
     */
    public static String getAddCommand(Food food) {
        return AddFoodItemCommand.COMMAND_WORD + " " + getFoodDetails(food);
    }

    /**
     * Returns the part of command string for the given {@code food}'s details.
     *
     * @param food food object
     * @return string output of food object
     */
    public static String getFoodDetails(Food food) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + food.getName() + " ");
        sb.append(PREFIX_CARBOS + String.format("%.2f", food.getCarbos()) + " ");
        sb.append(PREFIX_FATS + String.format("%.2f", food.getCarbos()) + " ");
        sb.append(PREFIX_PROTEINS + String.format("%.2f", food.getCarbos()) + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditFoodDescriptor}'s details.
     */
    public static String getEditFoodDescriptorDetails(EditFoodDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name).append(" "));
        descriptor.getCarbos().ifPresent(carbos -> sb.append(PREFIX_CARBOS).append(carbos).append(" "));
        descriptor.getFats().ifPresent(fats -> sb.append(PREFIX_FATS).append(fats).append(" "));
        descriptor.getProteins().ifPresent(proteins -> sb.append(PREFIX_PROTEINS).append(proteins).append(" "));
        return sb.toString();
    }

}

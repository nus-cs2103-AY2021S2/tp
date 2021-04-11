package seedu.address.model;

import seedu.address.model.food.FoodIntakeList;
import seedu.address.model.food.UniqueFoodList;
import seedu.address.model.user.User;

/**
 * Unmodifiable view of DietLAH!
 */
public interface ReadOnlyDietLah {

    /**
     * Returns a view of the food list.
     *
     * @return food list
     */
    UniqueFoodList getFoodList();

    /**
     * Returns a view of the user.
     *
     * @return user
     */
    User getUser();

    /**
     * Returns a view of the food intake list.
     *
     * @return food intake list
     */
    FoodIntakeList getFoodIntakeList();

}

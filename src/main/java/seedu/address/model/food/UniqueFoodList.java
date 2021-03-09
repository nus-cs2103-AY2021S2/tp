package seedu.address.model.food;

import java.util.ArrayList;

import seedu.address.model.food.exceptions.FoodItemNotFoundException;

public class UniqueFoodList {
    private ArrayList<Food> foodList;

    public UniqueFoodList() {
        foodList = new ArrayList<>();
    }

    /**
     * Views food list.
     *
     * @return food list
     */
    public ArrayList getFoodList() {
        return this.foodList;
    }

    /**
     * Adds a food item into the food list.
     *
     * @param foodItem food item
     * @return success message
     */
    public String addFoodItem(Food foodItem) {
        this.foodList.add(foodItem);
        String result = "Success adding " + foodItem.getName() + " to food list.";
        return result;
    }

    /**
     * Updates the relevant info of the food into the food list.
     *
     * @param foodItem updated food item
     * @return success message
     */
    public void updateFoodItem(Food foodItem) {
        for (Food food : this.foodList) {
            if (food.getName().equals(foodItem.getName())) {
                food.updateCarbos(foodItem.getCarbos());
                food.updateFats(foodItem.getFats());
                food.updateProteins(foodItem.getProteins());
//                String result = "Success updating " + food.getName() + " to food list.";
//                return result;
            }
        }
        throw new FoodItemNotFoundException();
    }

    /**
     * Checks if the food list contains a particular food item.
     * @param foodItem food item
     * @return true or false on whether a match is found
     */
    public boolean hasFoodItem(Food foodItem) {
        for (Food food : this.foodList) {
            if (food.getName().equals(foodItem.getName())) {
                return true;
            }
        }
        return false;
    }
}

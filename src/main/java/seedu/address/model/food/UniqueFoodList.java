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
     */
    public void updateFoodItem(Food foodItem) {
        for (Food food : this.foodList) {
            if (food.getName().equals(foodItem.getName())) {
                food.updateCarbos(foodItem.getCarbos());
                food.updateFats(foodItem.getFats());
                food.updateProteins(foodItem.getProteins());
                return;
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

    /**
     * Deletes a food item from the food list based on the food name.
     *
     * @param index food item index
     */
    public void deleteFoodItem(int index) {
        this.foodList.remove(index);
    }

    /**
     * Lists all food items in the food list.
     *
     * @return string output of all food items
     */
    public String listAllFoodItem() {
        StringBuilder stringBuilder = new StringBuilder();
        int counter = 1; //Used for for-loop counter indicator.
        for (Food food : this.foodList) {
            stringBuilder.append(counter + ". " + food.toString() + "\n");
            counter++;
        }
        return stringBuilder.toString();
    }
}

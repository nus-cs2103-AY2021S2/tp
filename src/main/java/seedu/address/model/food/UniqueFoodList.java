package seedu.address.model.food;

import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.food.exceptions.FoodItemNotFoundException;

public class UniqueFoodList {

    private ObservableList<Food> foodList;

    public UniqueFoodList() {
        foodList = FXCollections.observableArrayList();
    }

    /**
     * Views food list.
     *
     * @return food list
     */
    public ObservableList<Food> getFoodList() {
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
     * Checks if the food list contains a particular food item.
     *
     * @param foodName food name
     * @return positive index if found, otherwise return -1
     */
    public int getFoodItemIndex(String foodName) {
        for (int i = 0; i < this.foodList.size(); i++) {
            if (this.foodList.get(i).getName().equals(foodName)) {
                return i;
            }
        }
        return -1;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UniqueFoodList that = (UniqueFoodList) o;
        return Objects.equals(foodList, that.foodList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodList);
    }

}

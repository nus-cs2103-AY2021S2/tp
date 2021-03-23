package seedu.address.model.dish;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.Pair;
import seedu.address.model.Item;
import seedu.address.model.ingredient.Ingredient;

public class Dish implements Item {
    private String name;
    private double price;
    private List<Pair<Ingredient, Integer>> ingredientQuantityList;

    /**
     * Dish constructor
     * @param name
     * @param price
     * @param ingredientQuantityList
     */
    @JsonCreator
    public Dish(@JsonProperty("dish") String name, @JsonProperty("price") double price,
                @JsonProperty("ingredientQuantityList:") List<Pair<Ingredient, Integer>> ingredientQuantityList) {
        this.name = name;
        this.price = price;
        this.ingredientQuantityList = ingredientQuantityList;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public List<Pair<Ingredient, Integer>> getIngredientQuantityList() {
        return ingredientQuantityList;
    }

    @Override
    public boolean isSame(Item other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Dish)) {
            return false;
        }

        Dish otherDish = (Dish) other;
        return otherDish != null
                && this.getName().equals(otherDish.getName());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Dish)) {
            return false;
        }

        Dish otherDish = (Dish) other;
        return otherDish.name.equals(name)
                && otherDish.price == price
                && listEquals(otherDish.getIngredientQuantityList());
    }

    private boolean listEquals(List<Pair<Ingredient, Integer>> otherList) {
        if (otherList.size() != ingredientQuantityList.size()) {
            return false;
        }

        boolean result = true;
        for (int i = 0; i < ingredientQuantityList.size(); i++) {
            Pair<Ingredient, Integer> current = ingredientQuantityList.get(i);
            Pair<Ingredient, Integer> other = otherList.get(i);
            result = current.equals(other);
        }

        return result;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ")
                .append(getName())
                .append("; Price: ")
                .append(getPrice());

        if (!ingredientQuantityList.isEmpty()) {
            builder.append("; Ingredients: ");
            for (Pair<Ingredient, Integer> pair : ingredientQuantityList) {
                builder.append(pair.getKey());
                builder.append(" x");
                builder.append(pair.getValue());
            }
        }
        return builder.toString();
    }

}

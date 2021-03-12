package seedu.address.model.dish;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import javafx.util.Pair;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.Item;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

public class Dish extends Item {
    private String name;
    private double price;
    private List<Pair<Ingredient, Integer>> ingredientQuantityList;

    public Dish(String name, double price, List<Pair<Ingredient, Integer>> ingredientQuantityList) {
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

    public boolean isSameDish(Dish otherDish) {
        if (otherDish == this) {
            return true;
        }

        return otherDish != null
                && otherDish.getName().equals(otherDish.getName());
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
        if (otherList.size() != ingredientQuantityList.size()) return false;

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

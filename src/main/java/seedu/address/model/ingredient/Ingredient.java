package seedu.address.model.ingredient;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.Item;

public class Ingredient implements Item {
    private String name;
    private int quantity;

    /**
     * Ingredient constructor
     * @param name
     * @param quantity
     */
    @JsonCreator
    public Ingredient(@JsonProperty("name") String name, @JsonProperty("quantity") int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean isSame(Item other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Ingredient)) {
            return false;
        }

        Ingredient otherIngredient = (Ingredient) other;
        return otherIngredient != null
                && this.getName().equals(otherIngredient.getName());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Ingredient)) {
            return false;
        }

        Ingredient otherIngredient = (Ingredient) other;
        return this.name.equals(name) && otherIngredient.quantity == quantity;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Name: ")
                .append(getName())
                .append("; Quantity: ")
                .append(getQuantity());

        return builder.toString();
    }
}

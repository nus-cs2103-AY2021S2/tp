package seedu.address.testutil;

import seedu.address.model.food.Food;

public class FoodBuilder {
    public static final String DEFAULT_NAME = "chocolate";
    public static final double DEFAULT_CARBOS = 57;
    public static final double DEFAULT_FATS = 30;
    public static final double DEFAULT_PROTEINS = 7.3;
    public static final double DEFAULT_KILOCALORIES = 527.2;

    private String foodName;
    private Double carbos;
    private Double fats;
    private Double proteins;
    private Double kiloCalories;

    /**
     * Creates a  {@code FoodBuilder} with the default details.
     */
    public FoodBuilder() {
        foodName = DEFAULT_NAME;
        carbos = DEFAULT_CARBOS;
        fats = DEFAULT_FATS;
        proteins = DEFAULT_PROTEINS;
        kiloCalories = DEFAULT_KILOCALORIES;
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public FoodBuilder(Food foodToCopy) {
        foodName = foodToCopy.getName();
        carbos = foodToCopy.getCarbos();
        fats = foodToCopy.getFats();
        proteins = foodToCopy.getProteins();
        kiloCalories = foodToCopy.getKiloCalories();
    }

    /**
     * Sets the {@code Name} of the {@code Food} that we are building.
     */
    public FoodBuilder withFoodName(String name) {
        this.foodName = name;
        return this;
    }

    /**
     * Sets the {@code Carbos} of the {@code Food} that we are building.
     */
    public FoodBuilder withCarbos(Double carbos) {
        this.carbos = carbos;
        return this;
    }

    /**
     * Sets the {@code Fats} of the {@code Food} that we are building.
     */
    public FoodBuilder withFats(Double fats) {
        this.fats = fats;
        return this;
    }

    /**
     * Sets the {@code Fats} of the {@code Food} that we are building.
     */
    public FoodBuilder withProteins(Double proteins) {
        this.proteins = proteins;
        return this;
    }

    /**
     * Sets the {@code KiloCalories} of the {@code Food} that we are building.
     */
    public FoodBuilder withKiloCalories(Double kiloCalories) {
        this.kiloCalories = kiloCalories;
        return this;
    }

    public Food build() {
        return new Food(foodName, carbos, fats, proteins);
    }
}

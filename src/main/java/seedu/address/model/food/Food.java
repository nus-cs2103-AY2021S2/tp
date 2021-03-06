package seedu.address.model.food;

public class Food {

    public static final double PROTEIN_AND_CARBOS_MULTIPLIER = 4; //Conversion to KCAL
    public static final double FAT_MULTIPLIER = 9; //Conversion to KCAL

    public final String name;
    private double fats;
    private double carbos;
    private double proteins;
    private double kiloCalories;
    private boolean isUpdated; //Sets to True once any update function is called for this food item.

    /**
     * Initialises the food class.
     *
     * @param name     food name
     * @param fats     amount of fats
     * @param carbos   amount of carbos
     * @param proteins amount of proteins
     */
    public Food(String name, double fats, double carbos, double proteins) {
        this.name = name.toLowerCase();
        this.fats = fats;
        this.carbos = carbos;
        this.proteins = proteins;
        this.kiloCalories = calculateKiloCalories();
        this.isUpdated = false;
    }

    public double getFats() {
        return this.fats;
    }

    public double getCarbos() {
        return this.carbos;
    }

    public double getProteins() {
        return this.proteins;
    }

    public double getKiloCalories() {
        return this.kiloCalories;
    }

    /**
     * Updates the amount of fats for this food.
     *
     * @param fats new amount of fats
     * @return updated food item
     */
    public Food updateFats(double fats) {
        this.fats = fats;
        updateKiloCalories();
        setUpdateTrue();
        return this;
    }

    /**
     * Updates the amount of carbos for this food.
     *
     * @param carbos new carbo value
     * @return updated food item
     */

    public Food updateCarbos(double carbos) {
        this.carbos = carbos;
        updateKiloCalories();
        setUpdateTrue();
        return this;
    }

    /**
     * Updates the amount of proteins for this food.
     *
     * @param proteins new protein value
     * @return updated food item
     */
    public Food updateProteins(double proteins) {
        this.proteins = proteins;
        updateKiloCalories();
        setUpdateTrue();
        return this;
    }

    /**
     * Sets food data as updated if it is updated at least once.
     */
    public void setUpdateTrue() {
        this.isUpdated = true;
    }

    /**
     * Updates kilocalories each time a nutrient value is updated.
     */
    public void updateKiloCalories() {
        this.kiloCalories = calculateKiloCalories();
    }

    /**
     * Calculates total kilocalories based on input fats, carbos and proteins.
     *
     * @return total converted energy in kilocalories
     */
    public double calculateKiloCalories() {
        double convertedFats = this.fats * FAT_MULTIPLIER;
        double convertedCarbos = this.carbos * PROTEIN_AND_CARBOS_MULTIPLIER;
        double convertedProteins = this.proteins * PROTEIN_AND_CARBOS_MULTIPLIER;
        double totalKiloCalories = convertedCarbos + convertedFats + convertedProteins;
        return totalKiloCalories;
    }

    @Override
    public String toString() {
        String statusString = new String();
        if (this.isUpdated) {
            statusString = "updated";
        } else {
            statusString = "saved";
        }
        String result = this.name + " (Protein: " + this.proteins + "g, Carbohydrates: " + this.carbos + "g, Fats: "
                + this.fats + ") has been " + statusString + ".";
        return result;
    }
}

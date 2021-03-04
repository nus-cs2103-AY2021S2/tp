package seedu.address.model.cheese;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Cheese in the Cheese inventory Management System (CHIM)
 * Guarantees: immutable;
 */
public class Cheese {
    private static int currentId = 0;

    // Identity fields
    // Primary key for Cheese
    private final int cheeseId;
    private final CheeseType cheeseType;

    // Data fields
    private final ManufactureDate manufactureDate;
    private final ExpiryDate expiryDate;

    /**
     * Every field must be present and not null.
     */
    public Cheese(ManufactureDate manufactureDate, ExpiryDate expiryDate, CheeseType cheeseType) {
        this(manufactureDate, expiryDate, cheeseType, currentId++);
    }

    public Cheese(ManufactureDate manufactureDate, ExpiryDate expiryDate, CheeseType cheeseType,
                  Cheese previousCheese) {
        this(manufactureDate, expiryDate, cheeseType, previousCheese.cheeseId);
    }

    private Cheese(ManufactureDate manufactureDate, ExpiryDate expiryDate, CheeseType cheeseType, int cheeseId) {
        requireAllNonNull(manufactureDate, expiryDate, cheeseType);
        this.manufactureDate = manufactureDate;
        this.expiryDate = expiryDate;
        this.cheeseType = cheeseType;
        this.cheeseId = cheeseId;
    }

    public ManufactureDate getManufactureDate() {
        return manufactureDate;
    }

    public ExpiryDate getExpiryDate() {
        return expiryDate;
    }

    public CheeseType getCheeseType() {
        return cheeseType;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    /**
     * Returns true if both cheeses have the same name.
     * This defines a weaker notion of equality between two cheeses.
     */
    public boolean isSameCheese(Cheese otherCheese) {
        if (otherCheese == this) {
            return true;
        }

        return otherCheese != null
            && otherCheese.cheeseId == cheeseId;
    }

    /**
     * Returns true if both cheeses have the same identity and data fields.
     * This defines a stronger notion of equality between two cheeses.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Cheese)) {
            return false;
        }

        Cheese otherCheese = (Cheese) other;
        return otherCheese.getCheeseId() == getCheeseId()
            && otherCheese.getCheeseType().equals(getCheeseType())
            && otherCheese.getManufactureDate().equals(getManufactureDate())
            && otherCheese.getExpiryDate().equals(getExpiryDate());
    }

}

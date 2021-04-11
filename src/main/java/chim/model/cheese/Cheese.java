package chim.model.cheese;

import static chim.commons.util.AppUtil.checkArgument;
import static chim.commons.util.CollectionUtil.requireAllNonNull;
import static chim.commons.util.DateUtil.isBeforeToday;

import java.util.Optional;

/**
 * Represents a Cheese in the Cheese Inventory Management System (CHIM)
 * Guarantees: immutable;
 */
public class Cheese {

    // Identity fields
    // Primary key for Cheese
    private final CheeseId cheeseId;
    private final CheeseType cheeseType;

    // Data fields
    private final ManufactureDate manufactureDate;
    private final Optional<ExpiryDate> expiryDate;
    private final boolean isAssigned;

    public Cheese(CheeseType cheeseType, ManufactureDate manufactureDate, ExpiryDate expiryDate) {
        this(cheeseType, manufactureDate, expiryDate, CheeseId.getNextId(), false);
    }

    public Cheese(CheeseType cheeseType, ManufactureDate manufactureDate, ExpiryDate expiryDate, CheeseId cheeseId) {
        this(cheeseType, manufactureDate, expiryDate, cheeseId, true);
    }

    /**
     * Every compulsory field must be present and not null.
     */
    public Cheese(CheeseType cheeseType, ManufactureDate manufactureDate, ExpiryDate expiryDate,
                  CheeseId cheeseId, boolean isAssigned) {
        requireAllNonNull(cheeseType, manufactureDate, cheeseId);
        checkCheeseArguments(cheeseType, manufactureDate, expiryDate, cheeseId, isAssigned);
        this.cheeseType = cheeseType;
        this.manufactureDate = manufactureDate;
        this.expiryDate = Optional.ofNullable(expiryDate);
        this.cheeseId = cheeseId;
        this.isAssigned = isAssigned;
    }

    public CheeseType getCheeseType() {
        return cheeseType;
    }

    public ManufactureDate getManufactureDate() {
        return manufactureDate;
    }

    public Optional<ExpiryDate> getExpiryDate() {
        return expiryDate;
    }

    public CheeseId getCheeseId() {
        return cheeseId;
    }

    public boolean isCheeseAssigned() {
        return isAssigned;
    }

    /**
     * Checks whether the given parameters are valid for an order
     */
    public static void checkCheeseArguments(CheeseType cheeseType, ManufactureDate manufactureDate,
                                            ExpiryDate expiryDate, CheeseId cheeseId,
                                            boolean isAssigned) {
        checkArgument(isBeforeToday(manufactureDate), "The manufacture date cannot be after today.");

        if (expiryDate != null) {
            checkArgument(expiryDate.isAfterOrEquals(manufactureDate),
                "The expiry date of the cheese should be after the manufacture date.");
        }
    }

    public boolean isSameType(CheeseType cheeseType) {
        return this.cheeseType.equals(cheeseType);
    }

    /**
     * Returns a cheese with same fields that is marked assigned.
     */
    public Cheese assignToOrder() {
        return new Cheese(cheeseType, manufactureDate, expiryDate.orElse(null), cheeseId);
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
            && otherCheese.cheeseId.equals(cheeseId);
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
        return otherCheese.getCheeseId().equals(getCheeseId())
            && otherCheese.getCheeseType().equals(getCheeseType())
            && otherCheese.getManufactureDate().equals(getManufactureDate())
            && otherCheese.getExpiryDate().equals(getExpiryDate());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append("Cheese Type: ")
            .append(getCheeseType())
            .append("; Manufacture Date: ")
            .append(getManufactureDate())
            .append("; Expiry Date: ")
            .append(getExpiryDate().map(ExpiryDate::toString).orElse("-"))
            .append("; Status: ")
            .append(isCheeseAssigned() ? "Assigned " : "Not Assigned");

        return builder.toString();
    }

}

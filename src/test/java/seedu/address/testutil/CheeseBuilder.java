package seedu.address.testutil;

import seedu.address.model.cheese.Cheese;
import seedu.address.model.cheese.CheeseId;
import seedu.address.model.cheese.CheeseType;
import seedu.address.model.cheese.ExpiryDate;
import seedu.address.model.cheese.ManufactureDate;
import seedu.address.model.cheese.MaturityDate;

/**
 * A utility class to help with building Cheese objects.
 */
public class CheeseBuilder {

    public static final String DEFAULT_CHEESE_TYPE = "Brie";
    public static final String DEFAULT_MANUFACTURE_DATE = "2020-08-05 08:00";
    public static final String DEFAULT_MATURITY_DATE = "2021-08-05 08:00";
    public static final String DEFAULT_EXPIRY_DATE = "2023-08-05 00:00";

    private CheeseType cheeseType;
    private ManufactureDate manufactureDate;
    private MaturityDate maturityDate;
    private ExpiryDate expiryDate;
    private CheeseId cheeseId;
    private boolean isAssigned;

    /**
     * Creates a {@code CheeseBuilder} with the default details.
     */
    public CheeseBuilder() {
        cheeseType = CheeseType.getCheeseType(DEFAULT_CHEESE_TYPE);
        manufactureDate = new ManufactureDate(DEFAULT_MANUFACTURE_DATE);
        maturityDate = new MaturityDate(DEFAULT_MATURITY_DATE);
        expiryDate = new ExpiryDate(DEFAULT_EXPIRY_DATE);
        cheeseId = null;
        isAssigned = false;
    }

    /**
     * Initializes the CheeseBuilder with the data of {@code cheeseToCopy}.
     * Makes an exact copy (with the same ID) of the {@code cheeseToCopy}.
     */
    public CheeseBuilder(Cheese cheeseToCopy) {
        cheeseType = cheeseToCopy.getCheeseType();
        manufactureDate = cheeseToCopy.getManufactureDate();
        maturityDate = cheeseToCopy.getMaturityDate().orElse(null);
        expiryDate = cheeseToCopy.getExpiryDate().orElse(null);
        cheeseId = cheeseToCopy.getCheeseId();
        isAssigned = cheeseToCopy.getAssignStatus();
    }

    /**
     * Sets the {@code CheeseType} of the {@code Cheese} that we are building.
     */
    public CheeseBuilder withCheeseType(String cheeseType) {
        this.cheeseType = CheeseType.getCheeseType(cheeseType);
        return this;
    }

    /**
     * Sets the {@code ManufactureDate} of the {@code Cheese} that we are building.
     */
    public CheeseBuilder withManufactureDate(String manufactureDate) {
        this.manufactureDate = new ManufactureDate(manufactureDate);
        return this;
    }

    /**
     * Sets the {@code MaturityDate} of the {@code Cheese} that we are building.
     */
    public CheeseBuilder withMaturityDate(String maturityDate) {
        if (maturityDate == null) {
            this.maturityDate = null;
        } else {
            this.maturityDate = new MaturityDate(maturityDate);
        }
        return this;
    }

    /**
     * Sets the {@code ExpiryDate} of the {@code Cheese} that we are building.
     */
    public CheeseBuilder withExpiryDate(String expiryDate) {
        if (expiryDate == null) {
            this.expiryDate = null;
        } else {
            this.expiryDate = new ExpiryDate(expiryDate);
        }
        return this;
    }

    /**
     * Sets the {@code CheeseId} of the {@code Cheese} that we are building.
     */
    public CheeseBuilder withCheeseId(int id) {
        this.cheeseId = CheeseId.getNextId(id);
        return this;
    }

    /**
     * Sets the {@code CheeseId} of the {@code Cheese} explicitly that we are building.
     */
    public CheeseBuilder withId(CheeseId id) {
        this.cheeseId = id;
        return this;
    }

    /**
     * Sets the {@code isAssigned} of the {@code Cheese} that we are building.
     */
    public CheeseBuilder withAssignStatus(boolean isAssigned) {
        this.isAssigned = isAssigned;
        return this;
    }

    /**
     * Returns the immutable {@code Cheese} object representing the data we have.
     * @return an {@code Cheese} representation of data
     */
    public Cheese build() {
        if (cheeseId == null) {
            return new Cheese(cheeseType, manufactureDate, maturityDate, expiryDate);
        } else {
            return new Cheese(cheeseType, manufactureDate, maturityDate, expiryDate, cheeseId, isAssigned);
        }
    }

}

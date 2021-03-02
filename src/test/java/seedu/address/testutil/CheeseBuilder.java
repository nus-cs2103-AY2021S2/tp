package seedu.address.testutil;

import seedu.address.model.cheese.Cheese;
import seedu.address.model.cheese.CheeseType;
import seedu.address.model.cheese.ExpiryDate;
import seedu.address.model.cheese.ManufactureDate;

public class CheeseBuilder {

    public static final String DEFAULT_MANUFACTURE_DATE = "2020-08-05 08:00";
    public static final String DEFAULT_EXPIRY_DATE = "2021-08-05 00:00";
    public static final String DEFAULT_CHEESE_TYPE = "Brie";

    private ManufactureDate manufactureDate;
    private ExpiryDate expiryDate;
    private CheeseType cheeseType;
    private final Cheese cheeseToCopy;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public CheeseBuilder() {
        manufactureDate = new ManufactureDate(DEFAULT_MANUFACTURE_DATE);
        expiryDate = new ExpiryDate(DEFAULT_EXPIRY_DATE);
        cheeseType = CheeseType.getCheeseType(DEFAULT_CHEESE_TYPE);
        cheeseToCopy = null;
    }

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public CheeseBuilder(Cheese cheeseToCopy) {
        manufactureDate = cheeseToCopy.getManufactureDate();
        expiryDate = cheeseToCopy.getExpiryDate();
        cheeseType = cheeseToCopy.getCheeseType();
        this.cheeseToCopy = cheeseToCopy;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public CheeseBuilder withManufactureDate(String manufactureDate) {
        this.manufactureDate = new ManufactureDate(manufactureDate);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public CheeseBuilder withExpiryDate(String expiryDate) {
        this.expiryDate = new ExpiryDate(expiryDate);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public CheeseBuilder withCheeseType(String cheeseType) {
        this.cheeseType = CheeseType.getCheeseType(cheeseType);
        return this;
    }

    /**
     * Build cheese object
     * @return
     */
    public Cheese build() {
        if (cheeseToCopy == null) {
            return new Cheese(manufactureDate, expiryDate, cheeseType);
        } else {
            return new Cheese(manufactureDate, expiryDate, cheeseType, cheeseToCopy);
        }
    }

}

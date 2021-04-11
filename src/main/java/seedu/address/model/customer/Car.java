package seedu.address.model.customer;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents a car in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCar(String, String)}
 */
public class Car {

    public static final String MESSAGE_CONSTRAINTS = "CarBrand+CarType cannot be empty \n"
            + "Format: [c/CAR_BRAND_OWNED+CAR_TYPE_OWNED|COE_EXPIRY_DATE] [cp/CAR_BRAND_PREFERRED+CAR_TYPE_PREFERRED]";

    /**
     * The first character of the preferred car must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final String carBrand;
    private final String carType;

    /**
     * Constructs a {@code Car}.
     *
     * @param carBrand A valid carBrand.
     * @param carType A valid carType.
     */
    public Car(String carBrand, String carType) {
        requireNonNull(carBrand);
        requireNonNull(carType);
        checkArgument(isValidCar(carBrand, carType), MESSAGE_CONSTRAINTS);

        this.carBrand = carBrand;
        this.carType = carType;
    }

    /**
     * Returns true if a given string is a valid car.
     */
    public static boolean isValidCar(String carBrand, String carType) {
        return isValidCarBrand(carBrand) && isValidCarType(carType);
    }

    /**
     * Returns true if a given string is a valid carBrand.
     */
    public static boolean isValidCarBrand(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid carType.
     */
    public static boolean isValidCarType(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    /**
     * Compares carBrand and carType strings.
     * @param otherCar
     * @return Boolean result
     */
    public boolean isSameCarBrandAndCarType(Car otherCar) {
        return isSameCarBrand(otherCar) && isSameCarType(otherCar);
    }

    /**
     * Compares carBrand string.
     * @param otherCar
     * @return Boolean result
     */
    public boolean isSameCarBrand(Car otherCar) {
        return this.carBrand.equals(otherCar.carBrand);
    }

    /**
     * Compares carType string.
     * @param otherCar
     * @return Boolean result
     */
    public boolean isSameCarType(Car otherCar) {
        return this.carType.equals(otherCar.carType);
    }
    @Override
    public String toString() {
        return carBrand + "," + carType;
    }

    @Override
    public boolean equals(Object other) {
        return other == this; // Not checking carBrand or CarType since it shouldn't be unique

    }

    /**
     * Helps with comparing car Objects.
     * carBrand and carType attributes are essential to determine same attributes.
     */
    @Override
    public int hashCode() {
        return Objects.hash(carBrand, carType);
    }

    public String getCarBrand() {
        return carBrand;
    }

    public String getCarType() {
        return carType;
    }
}

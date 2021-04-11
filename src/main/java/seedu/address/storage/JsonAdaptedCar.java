package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.customer.Car;

public class JsonAdaptedCar {
    private final String carBrand;
    private final String carType;

    /**
     * Constructs a {@code JsonAdaptedCar} with the given {@code carId}.
     */
    @JsonCreator
    public JsonAdaptedCar(@JsonProperty("carBrand") String carBrand, @JsonProperty("carType") String carType) {
        this.carBrand = carBrand;
        this.carType = carType;
    }

    /**
     * Converts a given {@code Car} into this class for Jackson use.
     */
    public JsonAdaptedCar(Car source) {
        this.carBrand = source.getCarBrand();
        this.carType = source.getCarType();
    }


    public String getCarBrand() {
        return carBrand;
    }

    public String getCarType() {
        return carType;
    }

    /**
     * Converts this Jackson-friendly adapted car object into the model's {@code Car} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted car.
     */
    public Car toModelType() throws IllegalValueException {
        if (!Car.isValidCar(carBrand, carType)) {
            throw new IllegalValueException(Car.MESSAGE_CONSTRAINTS);
        }
        return new Car(carBrand, carType);
    }


}

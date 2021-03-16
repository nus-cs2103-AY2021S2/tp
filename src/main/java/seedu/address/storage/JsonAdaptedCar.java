package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.customer.Car;

public class JsonAdaptedCar {


    private final String carId;

    /**
     * Constructs a {@code JsonAdaptedCar} with the given {@code carId}.
     */
    @JsonCreator
    public JsonAdaptedCar(String carId) {
        this.carId = carId;
    }

    /**
     * Converts a given {@code Car} into this class for Jackson use.
     */
    public JsonAdaptedCar(Car source) {
        carId = source.carId;
    }

    @JsonValue
    public String getCarId() {
        return carId;
    }

    /**
     * Converts this Jackson-friendly adapted car object into the model's {@code Car} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted car.
     */
    public Car toModelType() throws IllegalValueException {
        if (!Car.isValidCar(carId)) {
            throw new IllegalValueException(Car.MESSAGE_CONSTRAINTS);
        }
        return new Car(carId);
    }


}

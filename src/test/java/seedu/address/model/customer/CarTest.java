package seedu.address.model.customer;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class CarTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Car(null, null));
        assertThrows(NullPointerException.class, () -> new Car(null, "suv"));
        assertThrows(NullPointerException.class, () -> new Car("honda", null));
    }

    @Test
    public void constructor_invalidCar_throwsIllegalArgumentException() {
        String invalidCarBrand = "";
        String invalidCarType = "";
        assertThrows(IllegalArgumentException.class, () -> new Car(invalidCarBrand, invalidCarType));
    }

    @Test
    void isValidCar() {
        // null Car
        assertThrows(NullPointerException.class, () -> Car.isValidCarBrand(null));

        // blank Car
        assertFalse(Car.isValidCar("", "")); // empty string
        assertFalse(Car.isValidCar("  ", "  ")); // spaces only

        // valid Car
        assertTrue(Car.isValidCar("Porsche", "SUV"));
        assertTrue(Car.isValidCar("Honda", "Sedan"));
    }

    @Test
    void isValidCarBrand() {
        // null CarBrand
        assertThrows(NullPointerException.class, () -> Car.isValidCarBrand(null));

        // blank CarBrand
        assertFalse(Car.isValidCarBrand("")); // empty string
        assertFalse(Car.isValidCarBrand(" ")); // spaces only

        // valid CarBrand
        assertTrue(Car.isValidCarBrand("Porsche"));
        assertTrue(Car.isValidCarBrand("Honda"));
    }

    @Test
    void isValidCarType() {
        // null CarType
        assertThrows(NullPointerException.class, () -> Car.isValidCarType(null));

        // blank CarType
        assertFalse(Car.isValidCarType("")); // empty string
        assertFalse(Car.isValidCarType(" ")); // spaces only

        // valid CarType
        assertTrue(Car.isValidCarType("Porsche"));
        assertTrue(Car.isValidCarType("Honda"));
    }
}

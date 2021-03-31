package seedu.address.logic.filters;

import static java.util.Objects.requireNonNull;

import seedu.address.model.customer.Car;

/**
 * Filters Cars based on user specified filters carBrand, carType.
 */
public abstract class CarFilter extends AbstractFilter {
    /**
     * Creates a filter for Cars
     * @param filterString
     */
    public CarFilter(String filterString) {
        super(filterString.trim());
        requireNonNull(filterString);
    }

    /**
     * Parses filter command for c/, cp/ prefixes.
     * @param car
     * @return
     */
    public static Car parseCar(String car) {
        String trimmedCar = car.trim();
        String[] carDetails = trimmedCar.split("\\+");
        if (carDetails.length != 2) {
            //throw new ParseException(Car.MESSAGE_CONSTRAINTS);
        }
        String filterCarBrand = carDetails[0].trim();
        String filterCarType = carDetails[1].trim();
        if (filterCarBrand.isEmpty()) {
            filterCarBrand = null;
        }
        if (filterCarType.isEmpty()) {
            filterCarType = null;
        }
        return new Car(filterCarBrand, filterCarType);
    }

    /**
     * Predicate for abstract test method use.
     *
     * @param car Car member from carsOwned or carsPreferred.
     * @param filterCar Specified Car to filter by.
     * @return
     */
    public boolean carFilterPredicate(Car car, Car filterCar) {
        if (filterCar.carBrand == null) {
            return car.isSameCarType(filterCar);
        } else if (filterCar.carType == null) {
            return car.isSameCarBrand(filterCar);
        } else if ((filterCar.carBrand != null) && (filterCar.carType != null)) {
            return car.isSameCarBrandAndCarType(filterCar);
        } else {
            return false;
        }
    }
}

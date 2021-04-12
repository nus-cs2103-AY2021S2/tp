package seedu.address.logic.filters;

import static java.util.Objects.requireNonNull;

import seedu.address.model.customer.Car;

/**
 * Filters Cars based on user specified filters carBrand, carType.
 */
public abstract class CarFilter extends Filter {
    /**
     * Creates a filter for Cars
     *
     * @param filterString
     */
    public CarFilter(String filterString) {
        super(filterString.trim());
        requireNonNull(filterString);
    }

    /**
     * Parses filter command for c/, cp/ prefixes.
     *
     * @param car
     * @return
     */
    public static Car parseCar(String car) {
        String[] carDetails = car.split("\\+");

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
     * @param car       Car member from carsOwned or carsPreferred.
     * @param filterCar Specified Car to filter by.
     * @return
     */
    public boolean carFilterPredicate(Car car, String filterCar) {
        filterCar = filterCar.trim();
        String[] carDetails = filterCar.split("\\+", 2);
        if (carDetails.length == 1) {

            return car.getCarBrand().toLowerCase().contains(filterCar.toLowerCase())
                || car.getCarType().toLowerCase().contains(filterCar.toLowerCase());
        } else {
            return car.getCarBrand().toLowerCase().contains(carDetails[0].toLowerCase())
                || car.getCarType().toLowerCase().contains(carDetails[1].toLowerCase());
        }
    }
}

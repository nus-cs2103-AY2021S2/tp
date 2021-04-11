package seedu.address.model.customer;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a customer in the address book. Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Customer {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Address address;
    private final DateOfBirth dateOfBirth;
    private final Set<Tag> tags = new HashSet<>();
    private final Map<Car, CoeExpiry> carsOwned;
    private final Set<Car> carsPreferred = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Customer(Name name, Phone phone, Email email, Address address, DateOfBirth dateOfBirth,
                    Set<Tag> tags, Map<Car, CoeExpiry> carsOwned, Set<Car> carsPreferred) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.tags.addAll(tags);
        if (carsOwned == null) {
            carsOwned = new HashMap<>();
        }
        this.carsOwned = carsOwned;
        if (carsPreferred != null) {
            this.carsPreferred.addAll(carsPreferred);
        }
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException} if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable Map of {car : corresponding CoeExpiry} key-value pairs
     *
     * @return - cars owned and their CoeExpiry
     */
    public Map<Car, CoeExpiry> getCarsOwned() {
        return Collections.unmodifiableMap(carsOwned);
    }

    /**
     * Returns an mutable car set.
     */
    public Set<Car> getCarsPreferred() {
        return carsPreferred;
    }

    /**
     * Returns true if both customer have the same name. This defines a weaker notion of equality between two
     * customers.
     */
    public boolean isSameCustomer(Customer otherCustomer) {
        if (otherCustomer == this) {
            return true;
        }

        return otherCustomer != null
            && otherCustomer.getName().equals(getName());
    }

    /**
     * Returns true if both customers have the same identity and data fields. This defines a stronger notion of equality
     * between two customers.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Customer)) {
            return false;
        }

        Customer otherCustomer = (Customer) other;
        return otherCustomer.getName().equals(getName())
            && otherCustomer.getPhone().equals(getPhone())
            && otherCustomer.getEmail().equals(getEmail())
            && otherCustomer.getAddress().equals(getAddress())
            && otherCustomer.getDateOfBirth().equals(getDateOfBirth())
            && otherCustomer.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
            .append("; Phone: ")
            .append(getPhone())
            .append("; Email: ")
            .append(getEmail())
            .append("; Address: ")
            .append(getAddress())
            .append("; Date Of Birth: ")
            .append(getDateOfBirth());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        if (!carsOwned.isEmpty()) {
            builder.append("; CarsOwned: ");
            carsOwned.forEach((a, b) -> builder.append(a + " "));
        }

        Set<Car> cars = getCarsPreferred();
        if (!cars.isEmpty()) {
            builder.append("; CarsPreferred:");
            cars.forEach(builder::append);
        }

        return builder.toString();
    }

}

package seedu.address.model.property;

import java.util.Objects;

import seedu.address.model.name.Name;
import seedu.address.model.property.client.AskingPrice;
import seedu.address.model.property.client.Client;
import seedu.address.model.remark.Remark;

/**
 * Represents a Property in the property book.
 * Guarantees: field values are validated, immutable.
 */
public class Property {
    // Mandatory fields
    private final Name name;
    private final Type propertyType;
    private final Address address;
    private final PostalCode postalCode;
    private final Deadline deadline;

    // Optional fields
    private final Remark remarks;
    private final Client client;

    /**
     * Constructs a {@code Property} without any optional fields.
     */
    public Property(Name name, Type propertyType, Address address, PostalCode postalCode, Deadline deadline) {
        this.name = name;
        this.propertyType = propertyType;
        this.address = address;
        this.postalCode = postalCode;
        this.deadline = deadline;
        this.remarks = null;
        this.client = null;
    }

    /**
     * Constructs a {@code Property} with remarks but without client's information.
     */
    public Property(Name name, Type propertyType, Address address, PostalCode postalCode, Deadline deadline,
                    Remark remarks) {
        this.name = name;
        this.propertyType = propertyType;
        this.address = address;
        this.postalCode = postalCode;
        this.deadline = deadline;
        this.remarks = remarks;
        this.client = null;
    }

    /**
     * Constructs a {@code Property} with client's information but without remarks.
     */
    public Property(Name name, Type propertyType, Address address, PostalCode postalCode, Deadline deadline,
                    Client client) {
        this.name = name;
        this.propertyType = propertyType;
        this.address = address;
        this.postalCode = postalCode;
        this.deadline = deadline;
        this.remarks = null;
        this.client = client;
    }

    /**
     * Constructs a {@code Property} with all information.
     * Every field must be present and not null.
     */
    public Property(Name name, Type propertyType, Address address, PostalCode postalCode, Deadline deadline,
                    Remark remarks, Client client) {
        this.name = name;
        this.propertyType = propertyType;
        this.address = address;
        this.postalCode = postalCode;
        this.deadline = deadline;
        this.remarks = remarks;
        this.client = client;
    }

    public Name getName() {
        return name;
    }

    public Type getPropertyType() {
        return propertyType;
    }

    public Address getAddress() {
        return address;
    }

    public PostalCode getPostalCode() {
        return postalCode;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public Remark getRemarks() {
        return remarks;
    }

    public Client getClient() {
        return client;
    }

    public AskingPrice getAskingPrice() {
        return client == null ? null : client.getClientAskingPrice();
    }

    /**
     * Returns true if both properties have the same postal code and same address.
     * This defines a weaker notion of equality between two properties.
     */
    public boolean isSameProperty(Property otherProperty) {
        if (otherProperty == this) {
            return true;
        }

        return otherProperty != null
                && otherProperty.getPostalCode().equals(getPostalCode())
                && otherProperty.getAddress().equals(getAddress());
    }

    /**
     * Returns true if both properties have the same identity and data fields.
     * This defines a stronger notion of equality between two properties.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Property)) {
            return false;
        }

        Property otherProperty = (Property) other;
        return otherProperty.getName().equals(getName())
                && otherProperty.getPropertyType().equals(getPropertyType())
                && otherProperty.getAddress().equals(getAddress())
                && otherProperty.getPostalCode().equals(getPostalCode())
                && otherProperty.getDeadline().equals(getDeadline());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, propertyType, address, postalCode, deadline);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(name.toString())
                .append("; Type: ")
                .append(propertyType.toString())
                .append("; Address: ")
                .append(address.toString())
                .append("; Postal Code: ")
                .append(postalCode.toString())
                .append("; Deadline: ")
                .append(deadline.toString());

        if (remarks != null) {
            builder.append("; Remarks: ").append(remarks.toString());
        }
        if (client != null) {
            builder.append("; ").append(client.toString());
        }
        return builder.toString();
    }
}

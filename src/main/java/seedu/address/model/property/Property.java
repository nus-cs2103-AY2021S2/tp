package seedu.address.model.property;

import java.util.Objects;

import seedu.address.model.name.Name;
import seedu.address.model.remark.Remark;

public class Property {
    // Mandatory fields
    private final Name name;
    private final Type propertyType;
    private final Address address;
    private final PostalCode postalCode;
    private final Deadline deadline;

    // Optional fields
    private final Remark remarks;
    private final String clientName;
    private final String clientContact;
    private final String clientEmail;
    private final Integer clientAskingPrice;

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
        this.clientName = null;
        this.clientContact = null;
        this.clientEmail = null;
        this.clientAskingPrice = null;
    }

    /**
     * Constructs a {@code Property} without client's information.
     */
    public Property(Name name, Type propertyType, Address address, PostalCode postalCode, Deadline deadline,
                    Remark remarks) {
        this.name = name;
        this.propertyType = propertyType;
        this.address = address;
        this.postalCode = postalCode;
        this.deadline = deadline;
        this.remarks = remarks;
        this.clientName = null;
        this.clientContact = null;
        this.clientEmail = null;
        this.clientAskingPrice = null;
    }

    /**
     * Constructs a {@code Property} with all information.
     * Every field must be present and not null.
     */
    public Property(Name name, Type propertyType, Address address, PostalCode postalCode, Deadline deadline,
                    Remark remarks, String clientName, String clientContact, String clientEmail,
                    Integer clientAskingPrice) {
        this.name = name;
        this.propertyType = propertyType;
        this.address = address;
        this.postalCode = postalCode;
        this.deadline = deadline;
        this.remarks = remarks;
        this.clientName = clientName;
        this.clientContact = clientContact;
        this.clientEmail = clientEmail;
        this.clientAskingPrice = clientAskingPrice;
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

    public String getClientName() {
        return clientName;
    }

    public String getClientContact() {
        return clientContact;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public Integer getClientAskingPrice() {
        return clientAskingPrice;
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
        builder.append(getName())
                .append("; Type: ")
                .append(getPropertyType())
                .append("; Address: ")
                .append(getAddress())
                .append("; Postal Code: ")
                .append(getPostalCode())
                .append("; Deadline: ")
                .append(getDeadline());

        if (remarks != null) {
            builder.append("; Remarks: ").append(getRemarks());
        }
        if (clientName != null) {
            builder.append("; Client Name: ").append(getClientName());
        }
        if (clientContact != null) {
            builder.append("; Client Contact: ").append(getClientContact());
        }
        if (clientEmail != null) {
            builder.append("; Client Email: ").append(getClientEmail());
        }
        if (clientAskingPrice != null) {
            builder.append("; Client Asking Price: ").append(getClientAskingPrice());
        }
        return builder.toString();
    }
}

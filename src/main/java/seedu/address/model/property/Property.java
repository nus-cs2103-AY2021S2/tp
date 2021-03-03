package seedu.address.model.property;

import java.time.LocalDate;

public class Property {
    private final Name name;
    private final Type propertyType;
    private final Address address;
    private final PostalCode postalCode;
    private final LocalDate deadline;
    private final String remarks;
    private final String clientName;
    private final String clientContact;
    private final String clientEmail;
    private final int clientAskingPrice;

    public Property(Name name, Type propertyType, Address address, PostalCode postalCode, LocalDate deadline,
                    String remarks, String clientName, String clientContact, String clientEmail,
                    int clientAskingPrice) {
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

    public LocalDate getDeadline() {
        return deadline;
    }

    public String getRemarks() {
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

    public int getClientAskingPrice() {
        return clientAskingPrice;
    }
}

package seedu.address.model.property;

import java.time.LocalDate;

public class Property {
    private final String name;
    private final String propertyType;
    private final String address;
    private final String postalCode;
    private final LocalDate deadline;
    private final String remarks;
    private final String clientName;
    private final String clientContact;
    private final String clientEmail;
    private final int clientAskingPrice;

    public Property(String name, String propertyType, String address, String postalCode, LocalDate deadline,
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

    public String getName() {
        return name;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
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

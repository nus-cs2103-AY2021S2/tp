package seedu.address.model.property;

public class Property {
    private final Name name;
    private final Type propertyType;
    private final Address address;
    private final PostalCode postalCode;
    private final Deadline deadline;
    private final Remark remarks;
    private final String clientName;
    private final String clientContact;
    private final String clientEmail;
    private final Integer clientAskingPrice;

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
}

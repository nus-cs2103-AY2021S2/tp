package seedu.address.testutil;

import seedu.address.model.name.Name;
import seedu.address.model.property.client.AskingPrice;
import seedu.address.model.property.client.Client;
import seedu.address.model.property.client.Contact;
import seedu.address.model.property.client.Email;

/**
 * A utility class to help with building Client objects.
 */
public class ClientBuilder {

    public static final String DEFAULT_NAME = "Alice";
    public static final String DEFAULT_CONTACT = "91234567";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ASKING_PRICE = "$800,000";

    private Name clientName;
    private Contact clientContact;
    private Email clientEmail;
    private AskingPrice clientAskingPrice;

    /**
     * Creates a {@code ClientBuilder} with the default details.
     */
    public ClientBuilder() {
        clientName = new Name(DEFAULT_NAME);
        clientContact = new Contact(DEFAULT_CONTACT);
        clientEmail = new Email(DEFAULT_EMAIL);
        clientAskingPrice = new AskingPrice(DEFAULT_ASKING_PRICE);
    }

    /**
     * Initializes the ClientBuilder with the data of {@code clientToCopy}.
     */
    public ClientBuilder(Client clientToCopy) {
        clientName = clientToCopy.getClientName();
        clientContact = clientToCopy.getClientContact();
        clientEmail = clientToCopy.getClientEmail();
        clientAskingPrice = clientToCopy.getClientAskingPrice();
    }

    /**
     * Sets the {@code Name} of the {@code Client} that we are building.
     */
    public ClientBuilder withClientName(String clientName) {
        this.clientName = new Name(clientName);
        return this;
    }

    /**
     * Sets the {@code Contact} of the {@code Client} that we are building.
     */
    public ClientBuilder withClientContact(String clientContact) {
        this.clientContact = new Contact(clientContact);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Client} that we are building.
     */
    public ClientBuilder withClientEmail(String clientEmail) {
        this.clientEmail = new Email(clientEmail);
        return this;
    }

    /**
     * Sets the {@code AskingPrice} of the {@code Client} that we are building.
     */
    public ClientBuilder withClientAskingPrice(String clientAskingPrice) {
        this.clientAskingPrice = new AskingPrice(clientAskingPrice);
        return this;
    }

    public Client build() {
        return new Client(clientName, clientContact, clientEmail, clientAskingPrice);
    }
}

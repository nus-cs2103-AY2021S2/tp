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

    public static final String DEFAULT_NAME = "Mallory";
    public static final String DEFAULT_CONTACT = "84237541";
    public static final String DEFAULT_EMAIL = "mallory_7541@gmail.com";
    public static final Long DEFAULT_ASKING_PRICE = Long.parseLong("1000000");

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
    public ClientBuilder withClientAskingPrice(Long clientAskingPrice) {
        this.clientAskingPrice = new AskingPrice(clientAskingPrice);
        return this;
    }

    /**
     * Sets the {@code Client} that we are building to have no name.
     */
    public ClientBuilder withNoClientName() {
        clientName = null;
        return this;
    }

    /**
     * Sets the {@code Client} that we are building to have no contact.
     */
    public ClientBuilder withNoClientContact() {
        clientContact = null;
        return this;
    }

    /**
     * Sets the {@code Client} that we are building to have no email.
     */
    public ClientBuilder withNoClientEmail() {
        clientEmail = null;
        return this;
    }

    /**
     * Sets the {@code Client} that we are building to have no asking price.
     */
    public ClientBuilder withNoClientAskingPrice() {
        clientAskingPrice = null;
        return this;
    }

    public Client build() {
        return new Client(clientName, clientContact, clientEmail, clientAskingPrice);
    }
}

package seedu.address.model.property.client;

import java.util.Objects;

import seedu.address.model.name.Name;

/**
 * Represents the seller of a Property.
 * Guarantees: field values are validated, immutable.
 */
public class Client {
    private final Name clientName;
    private final Contact clientContact;
    private final Email clientEmail;
    private final AskingPrice clientAskingPrice;

    /**
     * Constructs a {@code Client} with all information.
     */
    public Client(Name clientName, Contact clientContact, Email clientEmail, AskingPrice clientAskingPrice) {
        this.clientName = clientName;
        this.clientContact = clientContact;
        this.clientEmail = clientEmail;
        this.clientAskingPrice = clientAskingPrice;
    }

    public Name getClientName() {
        return clientName;
    }

    public Contact getClientContact() {
        return clientContact;
    }

    public Email getClientEmail() {
        return clientEmail;
    }

    public AskingPrice getClientAskingPrice() {
        return clientAskingPrice;
    }

    /**
     * Returns true if both client have the same name.
     * This defines a weaker notion of equality between two clients.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient != null
                && otherClient.getClientName().equals(getClientName());
    }

    /**
     * Returns true if both clients have the same identity and data fields.
     * This defines a stronger notion of equality between two clients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Client)) {
            return false;
        }

        Client otherClient = (Client) other;
        return otherClient.getClientName().equals(getClientName())
                && otherClient.getClientContact().equals(getClientContact())
                && otherClient.getClientEmail().equals(getClientContact())
                && otherClient.getClientAskingPrice().equals(getClientAskingPrice());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(clientName, clientContact, clientEmail, clientAskingPrice);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

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

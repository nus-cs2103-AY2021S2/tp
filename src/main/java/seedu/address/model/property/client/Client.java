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
     * Constructs a {@code Client}.
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
     * Converts toString of client back to Client object.
     */
    public static Client fromStringToClient(String toString) {
        Name name = null;
        Contact contact = null;
        Email email = null;
        AskingPrice price = null;

        if (toString.contains("Client Name: ")) {
            int start = toString.indexOf("Client Name: ") + 13;
            int end = toString.indexOf(';', start);
            name = new Name(toString.substring(start, end));
        }

        if (toString.contains("Client Contact: ")) {
            int start = toString.indexOf("Client Contact: ") + 16;
            int end = toString.indexOf(';', start);
            contact = new Contact(toString.substring(start, end));
        }

        if (toString.contains("Client Email: ")) {
            int start = toString.indexOf("Client Email: ") + 14;
            int end = toString.indexOf(';', start);
            email = new Email(toString.substring(start, end));
        }

        if (toString.contains("Client Asking Price: ")) {
            int start = toString.indexOf("Client Asking Price: ") + 21;
            int end = toString.indexOf(';', start);
            price = new AskingPrice(toString.substring(start, end));
        }

        return new Client(name, contact, email, price);
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
                && otherClient.getClientEmail().equals(getClientEmail())
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
            builder.append("Client Name: ").append(getClientName());
        }
        if (clientContact != null) {
            if (builder.length() != 0) {
                builder.append("; ");
            }
            builder.append("Client Contact: ").append(getClientContact());
        }
        if (clientEmail != null) {
            if (builder.length() != 0) {
                builder.append("; ");
            }
            builder.append("Client Email: ").append(getClientEmail());
        }
        if (clientAskingPrice != null) {
            if (builder.length() != 0) {
                builder.append("; ");
            }
            builder.append("Client Asking Price: ").append(getClientAskingPrice());
        }

        if (builder.length() != 0) {
            builder.append(";");
        }

        return builder.toString();
    }
}

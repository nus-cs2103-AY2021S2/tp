package seedu.address.model.property.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.name.Name;

public class ClientTest {
    private static final String DEFAULT_CLIENT_NAME_STRING = "Alice";
    private static final String DEFAULT_CLIENT_CONTACT_STRING = "91234567";
    private static final String DEFAULT_CLIENT_EMAIL_STRING = "alice@gmail.com";
    private static final String DEFAULT_CLIENT_ASKING_PRICE_STRING = "$800,000";

    private static final Name DEFAULT_CLIENT_NAME = new Name(DEFAULT_CLIENT_NAME_STRING);
    private static final Contact DEFAULT_CLIENT_CONTACT = new Contact(DEFAULT_CLIENT_CONTACT_STRING);
    private static final Email DEFAULT_CLIENT_EMAIL = new Email(DEFAULT_CLIENT_EMAIL_STRING);
    private static final AskingPrice DEFAULT_CLIENT_ASKING_PRICE = new AskingPrice(DEFAULT_CLIENT_ASKING_PRICE_STRING);

    @Test
    public void testStringConversion() {
        // Missing all fields
        assertEquals("",
                new Client(null, null, null, null).toString());

        // All fields present
        assertEquals("Client Name: Alice; Client Contact: 91234567; Client Email: alice@gmail.com;"
                + " Client Asking Price: $800,000;",
                new Client(DEFAULT_CLIENT_NAME, DEFAULT_CLIENT_CONTACT, DEFAULT_CLIENT_EMAIL,
                        DEFAULT_CLIENT_ASKING_PRICE).toString());

        // Missing name
        assertEquals("Client Contact: 91234567; Client Email: alice@gmail.com; Client Asking Price: $800,000;",
                new Client(null, DEFAULT_CLIENT_CONTACT, DEFAULT_CLIENT_EMAIL, DEFAULT_CLIENT_ASKING_PRICE)
                        .toString());

        // Missing contact
        assertEquals("Client Name: Alice; Client Email: alice@gmail.com; Client Asking Price: $800,000;",
                new Client(DEFAULT_CLIENT_NAME, null, DEFAULT_CLIENT_EMAIL, DEFAULT_CLIENT_ASKING_PRICE)
                        .toString());

        // Missing email
        assertEquals("Client Name: Alice; Client Contact: 91234567; Client Asking Price: $800,000;",
                new Client(DEFAULT_CLIENT_NAME, DEFAULT_CLIENT_CONTACT, null, DEFAULT_CLIENT_ASKING_PRICE)
                        .toString());

        // Missing asking price
        assertEquals("Client Name: Alice; Client Contact: 91234567; Client Email: alice@gmail.com;",
                new Client(DEFAULT_CLIENT_NAME, DEFAULT_CLIENT_CONTACT, DEFAULT_CLIENT_EMAIL, null)
                        .toString());

        // Missing two fields
        assertEquals("Client Name: Alice; Client Email: alice@gmail.com;",
                new Client(DEFAULT_CLIENT_NAME, null, DEFAULT_CLIENT_EMAIL, null).toString());
        assertEquals("Client Contact: 91234567; Client Asking Price: $800,000;",
                new Client(null, DEFAULT_CLIENT_CONTACT, null, DEFAULT_CLIENT_ASKING_PRICE).toString());

        // Missing three fields
        assertEquals("Client Name: Alice;",
                new Client(DEFAULT_CLIENT_NAME, null, null, null).toString());
        assertEquals("Client Asking Price: $800,000;",
                new Client(null, null, null, DEFAULT_CLIENT_ASKING_PRICE).toString());
    }
}

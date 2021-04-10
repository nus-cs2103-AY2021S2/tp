package seedu.address.model.property.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalClients.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ClientBuilder;

public class ClientTest {

    @Test
    public void testStringConversion() {
        // All fields present
        assertEquals("Client Name: Alice; Client Contact: 91234567; Client Email: alice@gmail.com; "
                        + "Client Asking Price: $1,000,000", new ClientBuilder(ALICE).build().toString());

        // Missing all fields
        assertEquals("", new ClientBuilder(ALICE).withNoClientName().withNoClientContact().withNoClientEmail()
                .withNoClientAskingPrice().build().toString());

        // Missing name
        assertEquals("Client Contact: 91234567; Client Email: alice@gmail.com; Client Asking Price: $1,000,000",
                new ClientBuilder(ALICE).withNoClientName().build().toString());

        // Missing contact
        assertEquals("Client Name: Alice; Client Email: alice@gmail.com; Client Asking Price: $1,000,000",
                new ClientBuilder(ALICE).withNoClientContact().build().toString());

        // Missing email
        assertEquals("Client Name: Alice; Client Contact: 91234567; Client Asking Price: $1,000,000",
                new ClientBuilder(ALICE).withNoClientEmail().build().toString());

        // Missing asking price
        assertEquals("Client Name: Alice; Client Contact: 91234567; Client Email: alice@gmail.com",
                new ClientBuilder(ALICE).withNoClientAskingPrice().build().toString());

        // Missing two fields
        assertEquals("Client Name: Alice; Client Email: alice@gmail.com",
                new ClientBuilder(ALICE).withNoClientContact().withNoClientAskingPrice().build().toString());
        assertEquals("Client Contact: 91234567; Client Asking Price: $1,000,000",
                new ClientBuilder(ALICE).withNoClientName().withNoClientEmail().build().toString());

        // Missing three fields
        assertEquals("Client Name: Alice",
                new ClientBuilder(ALICE).withNoClientContact().withNoClientEmail().withNoClientAskingPrice()
                        .build().toString());
        assertEquals("Client Asking Price: $1,000,000",
                new ClientBuilder(ALICE).withNoClientName().withNoClientContact().withNoClientEmail()
                        .build().toString());
    }
}

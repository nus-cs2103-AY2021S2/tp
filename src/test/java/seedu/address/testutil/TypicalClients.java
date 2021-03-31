package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.property.client.Client;

/**
 * A utility class containing a list of {@code Client} objects to be used in tests.
 */
public class TypicalClients {
    public static final Client ALICE = new ClientBuilder().withClientName("Alice")
            .withClientContact("91234567").withClientEmail("alice@gmail.com")
            .withClientAskingPrice("$1,000,000").build();
    public static final Client BOB = new ClientBuilder().withClientName("Bob")
            .withClientContact("98664535").withClientEmail("bob@gmail.com")
            .withClientAskingPrice("$800,000").build();
    public static final Client CALEB = new ClientBuilder().withClientName("Caleb")
            .withClientContact("84459627").withClientEmail("caleb_goh@gmail.com")
            .withClientAskingPrice("$350,000").build();
    public static final Client DARREN = new ClientBuilder().withClientName("Darren")
            .withClientContact("81347564").withClientEmail("darren_likes_swe@hotmail.com.sg")
            .withClientAskingPrice("$2,000,000").build();
    public static final Client FIN = new ClientBuilder().withClientName("Fin")
            .withClientContact("83864380").withClientEmail("fin@gmail.com")
            .withClientAskingPrice("$8,000,000").build();
    public static final Client JOEL = new ClientBuilder().withClientName("Joel")
            .withClientContact("87262389").withClientEmail("joel@gmail.com")
            .withClientAskingPrice("$5,000,000").build();

    private TypicalClients() {} // prevents instantiation

    public static List<Client> getTypicalClients() {
        return new ArrayList<>(Arrays.asList(ALICE, BOB, CALEB, DARREN));
    }
}

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
            .withClientAskingPrice(Long.parseLong("1000000")).build();
    public static final Client BOB = new ClientBuilder().withClientName("Bob")
            .withClientContact("98664535").withClientEmail("bob@gmail.com")
            .withClientAskingPrice(Long.parseLong("800000")).build();
    public static final Client CALEB = new ClientBuilder().withClientName("Caleb")
            .withClientContact("84459627").withClientEmail("caleb_goh@gmail.com")
            .withClientAskingPrice(Long.parseLong("350000")).build();
    public static final Client DARREN = new ClientBuilder().withClientName("Darren")
            .withClientContact("81347564").withClientEmail("darren_likes_swe@hotmail.com.sg")
            .withClientAskingPrice(Long.parseLong("2000000")).build();
    public static final Client FIN = new ClientBuilder().withClientName("Fin")
            .withClientContact("83864380").withClientEmail("fin@gmail.com")
            .withClientAskingPrice(Long.parseLong("8000000")).build();
    public static final Client JOEL = new ClientBuilder().withClientName("Joel")
            .withClientContact("87262389").withClientEmail("joel@gmail.com")
            .withClientAskingPrice(Long.parseLong("5000000")).build();

    private TypicalClients() {} // prevents instantiation

    public static List<Client> getTypicalClients() {
        return new ArrayList<>(Arrays.asList(ALICE, BOB, CALEB, DARREN));
    }
}

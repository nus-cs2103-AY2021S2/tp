package seedu.iscam.testutil;

import static seedu.iscam.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_LOCATION_AMY;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_PLAN_AMY;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_PLAN_BOB;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.iscam.model.client.Client;
import seedu.iscam.model.util.clientbook.ClientBook;

/**
 * A utility class containing a list of {@code Client} objects to be used in tests.
 */
public class TypicalClients {

    public static final Client ALICE = new ClientBuilder().withName("Alice Pauline")
            .withLocation("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withPlan("Plan A")
            .withTags("friends").withImage("default.png").build();
    public static final Client BENSON = new ClientBuilder().withName("Benson Meier")
            .withLocation("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432").withPlan("Plan B")
            .withTags("owesMoney", "friends").withImage("default.png").build();
    public static final Client CARL = new ClientBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withLocation("wall street").withPlan("Life").build();
    public static final Client DANIEL = new ClientBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withLocation("10th street")
            .withPlan("Plan A").withTags("friends").withImage("default.png").build();
    public static final Client ELLE = new ClientBuilder().withName("Elle Meyer").withPhone("89482224")
            .withEmail("werner@example.com").withLocation("michegan ave").withPlan("Protect").withImage("default.png")
            .build();
    public static final Client FIONA = new ClientBuilder().withName("Fiona Kunz").withPhone("64824273")
            .withEmail("lydia@example.com").withLocation("little tokyo").withPlan("MediShield").withImage("default.png")
            .build();
    public static final Client GEORGE = new ClientBuilder().withName("George Best").withPhone("94824412")
            .withEmail("anna@example.com").withLocation("4th street").withPlan("Plan A").withImage("default.png")
            .build();

    // Manually added
    public static final Client HOON = new ClientBuilder().withName("Hoon Meier").withPhone("88482424")
            .withEmail("stefan@example.com").withLocation("little india").withPlan("Plan B").withImage("default.png")
            .build();
    public static final Client IDA = new ClientBuilder().withName("Ida Mueller").withPhone("84821310")
            .withEmail("hans@example.com").withLocation("chicago ave").withPlan("Plan C").withImage("default.png")
            .build();

    // Manually added - Client's details found in {@code CommandTestUtil}
    public static final Client AMY = new ClientBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withLocation(VALID_LOCATION_AMY)
            .withPlan(VALID_PLAN_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Client BOB = new ClientBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withLocation(VALID_LOCATION_BOB).withPlan(VALID_PLAN_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalClients() {} // prevents instantiation

    /**
     * Returns an {@code ClientBook} with all the typical clients.
     */
    public static ClientBook getTypicalClientBook() {
        ClientBook cb = new ClientBook();
        for (Client client : getTypicalClients()) {
            cb.addClient(client);
        }
        return cb;
    }

    public static List<Client> getTypicalClients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

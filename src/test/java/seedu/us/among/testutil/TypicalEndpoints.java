package seedu.us.among.testutil;

import static seedu.us.among.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.us.among.model.EndpointList;
import seedu.us.among.model.endpoint.Endpoint;

/**
 * A utility class containing a list of {@code Endpoint} objects to be used in tests.
 */
public class TypicalEndpoints {

    public static final Endpoint ALICE = new EndpointBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Endpoint BENSON = new EndpointBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Endpoint CARL = new EndpointBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Endpoint DANIEL = new EndpointBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").build();
    public static final Endpoint ELLE = new EndpointBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Endpoint FIONA = new EndpointBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Endpoint GEORGE = new EndpointBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Endpoint HOON = new EndpointBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Endpoint IDA = new EndpointBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Endpoint's details found in {@code CommandTestUtil}
    public static final Endpoint AMY = new EndpointBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Endpoint BOB = new EndpointBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalEndpoints() {} // prevents instantiation

    /**
     * Returns an {@code EndpointList} with all the typical endpoints.
     */
    public static EndpointList getTypicalEndpointList() {
        EndpointList ab = new EndpointList();
        for (Endpoint endpoint : getTypicalEndpoints()) {
            ab.addEndpoint(endpoint);
        }
        return ab;
    }

    public static List<Endpoint> getTypicalEndpoints() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

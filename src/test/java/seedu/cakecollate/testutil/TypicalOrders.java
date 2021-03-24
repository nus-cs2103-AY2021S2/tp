package seedu.cakecollate.testutil;

import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_BERRY_ORDER;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_CHOCOLATE_ORDER;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_DELIVERY_DATE_AMY;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_DELIVERY_DATE_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.cakecollate.model.CakeCollate;
import seedu.cakecollate.model.order.Order;


/**
 * A utility class containing a list of {@code Order} objects to be used in tests.
 */
public class TypicalOrders {

    // needs to correspond to typicalOrdersCakeCollate
    public static final Order ALICE = new OrderBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withOrderDescriptions("2 x Strawberry Cakes").withTags("friends")
            .withDeliveryDate("01/01/2022").withDeliveryStatus()
            .withRequest("").build();
    public static final Order BENSON = new OrderBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withOrderDescriptions("2 x Chocolate Cakes")
            .withTags("owesMoney", "friends")
            .withDeliveryDate("01-01-2022").withDeliveryStatus()
            .withRequest("More pineapples").build();
    public static final Order CARL = new OrderBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withOrderDescriptions("1 Vanilla Cake")
            .withDeliveryDate("01.01.2022").withDeliveryStatus()
            .withRequest("Custom cake decoration, diamond hands.").build();
    public static final Order DANIEL = new OrderBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withDeliveryStatus().withAddress("10th street")
            .withOrderDescriptions("1 Chocolate Chip Muffin").withTags("friends")
            .withDeliveryDate("01 Jan 2022").withDeliveryStatus()
            .withRequest("").build();
    public static final Order ELLE = new OrderBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withOrderDescriptions("1 Raisins Cake Thing", "1 x Blackforest cake", "1 x Vanilla Cake")
            .withDeliveryDate("31/12/2022").withDeliveryStatus()
            .withRequest("").build();
    public static final Order FIONA = new OrderBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withOrderDescriptions("2 x Vanilla cakes").withDeliveryDate("28-02-2022").withDeliveryStatus()
            .withRequest("Replace vanilla essence with banana essence.").build();
    public static final Order GEORGE = new OrderBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withOrderDescriptions("2 lava cakes").withDeliveryDate("06.03.2022").withDeliveryStatus()
            .withRequest("").build();

    // Manually added
    public static final Order HOON = new OrderBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withOrderDescriptions("2 jelly hearts")
            .withOrderDescriptions("1 mango cake").withDeliveryDate("01/01/2022").withDeliveryStatus()
            .withRequest("More sugar, he wants diabetus.").build();
    public static final Order IDA = new OrderBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withOrderDescriptions("a mango cake")
            .withOrderDescriptions("vanilla with lemon zest").withDeliveryDate("01/01/2022")
            .withDeliveryStatus()
            .withRequest("Mangoes to be diced.").build();

    // Manually added - Order's details found in {@code CommandTestUtil}
    public static final Order AMY = new OrderBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withOrderDescriptions(VALID_CHOCOLATE_ORDER)
            .withTags(VALID_TAG_FRIEND).withDeliveryDate(VALID_DELIVERY_DATE_AMY).withDeliveryStatus().build();
    public static final Order BOB = new OrderBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withOrderDescriptions(VALID_BERRY_ORDER)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withDeliveryDate(VALID_DELIVERY_DATE_BOB)
            .withDeliveryStatus().build();


    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalOrders() {} // prevents instantiation

    /**
     * Returns an {@code CakeCollate} with all the typical orders.
     */
    public static CakeCollate getTypicalCakeCollate() {
        CakeCollate ab = new CakeCollate();
        for (Order order : getTypicalOrders()) {
            ab.addOrder(order);
        }
        return ab;
    }

    public static List<Order> getTypicalOrders() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

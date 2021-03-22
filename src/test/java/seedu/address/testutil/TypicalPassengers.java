package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.passenger.Passenger;

/**
 * A utility class containing a list of {@code Passenger} objects to be used in tests.
 */
public class TypicalPassengers {

    //todo edit withPrice() entries if needed

    public static final Passenger ALICE = new PassengerBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withPhone("94351253").withTripDay("FRIDAY").withTripTime("1800").withPrice("1.69")
            .withTags("friends").build();
    public static final Passenger BENSON = new PassengerBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withPhone("98765432").withTripDay("FRIDAY").withTripTime("1800").withPrice("1.69")
            .withTags("owesMoney", "friends").build();
    public static final Passenger CARL = new PassengerBuilder().withName("Carl Kurz").withPhone("95352563")
            .withTripDay("FRIDAY").withTripTime("1800").withPrice("1.69")
            .withAddress("wall street").build();
    public static final Passenger DANIEL = new PassengerBuilder().withName("Daniel Meier").withPhone("87652533")
            .withTripDay("FRIDAY").withTripTime("1800").withPrice("1.69")
            .withAddress("10th street").withTags("friends").build();
    public static final Passenger ELLE = new PassengerBuilder().withName("Elle Meyer").withPhone("9482224")
            .withTripDay("FRIDAY").withTripTime("2100").withPrice("1.69")
            .withAddress("michegan ave").build();
    public static final Passenger FIONA = new PassengerBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withTripDay("MONDAY").withTripTime("0830")
            .withAddress("little tokyo").build();
    public static final Passenger GEORGE = new PassengerBuilder().withName("George Best").withPhone("9482442")
            .withTripDay("THURSDAY").withTripTime("0715").withPrice("1.69")
            .withAddress("4th street").build();

    // Passengers with drivers already assigned
    public static final Passenger HILARY = new PassengerBuilder().withName("Hilary Clinton").withPhone("9486666")
            .withTripDay("MONDAY").withTripTime("1930").withPrice("1.69")
            .withAddress("6th ave").withDriver().buildWithDriver();
    public static final Passenger IRENE = new PassengerBuilder().withName("Irene Newton").withPhone("91238888")
            .withTripDay("MONDAY").withTripTime("1930").withPrice("1.80")
            .withAddress("5th ave").withDriver().buildWithDriver();
    public static final Passenger JACKSON = new PassengerBuilder().withName("Jackson Mehoff").withPhone("91236969")
            .withTripDay("MONDAY").withTripTime("1930").withPrice("1.30")
            .withAddress("4th ave").withDriver().buildWithDriver();
    public static final Passenger KINGSLEY = new PassengerBuilder().withName("Kingsley Kuan").withPhone("91867510")
            .withTripDay("MONDAY").withTripTime("1930").withPrice("1.90")
            .withAddress("3rd ave").withDriver().buildWithDriver();

    // Passengers with drivers removed
    public static final Passenger HILARY_NO_DRIVER = new PassengerBuilder().withName("Hilary Clinton")
            .withPhone("9486666").withTripDay("MONDAY").withTripTime("1930").withPrice("1.69")
            .withAddress("6th ave").build();
    public static final Passenger IRENE_NO_DRIVER = new PassengerBuilder().withName("Irene Newton")
            .withPhone("91238888").withTripDay("MONDAY").withTripTime("1930").withPrice("1.80")
            .withAddress("5th ave").build();
    public static final Passenger JACKSON_NO_DRIVER = new PassengerBuilder().withName("Jackson Mehoff")
            .withPhone("91236969").withTripDay("MONDAY").withTripTime("1930").withPrice("1.30")
            .withAddress("4th ave").build();
    public static final Passenger KINGSLEY_NO_DRIVER = new PassengerBuilder().withName("Kingsley Kuan")
            .withPhone("91867510").withTripDay("MONDAY").withTripTime("1930").withPrice("1.90")
            .withAddress("3rd ave").build();

    // Manually added
    public static final Passenger HOON = new PassengerBuilder().withName("Hoon Meier").withPhone("8482424")
            .withTripDay("FRIDAY").withTripTime("1800").withPrice("1.69")
            .withAddress("little india").build();
    public static final Passenger IDA = new PassengerBuilder().withName("Ida Mueller").withPhone("8482131")
            .withTripDay("FRIDAY").withTripTime("1800").withPrice("1.69")
            .withAddress("chicago ave").build();

    // Manually added - Passenger's details found in {@code CommandTestUtil}
    public static final Passenger AMY = new PassengerBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withTripDay("FRIDAY").withTripTime("1800").withPrice("1.69")
            .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Passenger BOB = new PassengerBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withTripDay("FRIDAY").withTripTime("1800").withPrice("1.69")
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPassengers() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical passengers.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Passenger passenger : getTypicalPassengers()) {
            ab.addPassenger(passenger);
        }
        return ab;
    }

    public static List<Passenger> getTypicalPassengers() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

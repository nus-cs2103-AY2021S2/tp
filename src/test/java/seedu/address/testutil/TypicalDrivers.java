package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import seedu.address.model.person.driver.Driver;

/**
 * A utility class containing a list of {@code Passenger} objects to be used in tests.
 */
public class TypicalDrivers {

    public static final Driver DRIVER_ALICE = new DriverBuilder().withName("Alice Pauline").withPhone("94351253")
            .build();
    public static final Driver DRIVER_BENSON = new DriverBuilder().withName("Benson Meier").withPhone("98765432")
            .build();
    public static final Driver DRIVER_CARL = new DriverBuilder().withName("Carl Kurz").withPhone("95352563")
            .build();
    public static final Driver DRIVER_DANIEL = new DriverBuilder().withName("Daniel Meier").withPhone("87652533")
            .build();
    public static final Driver DRIVER_ELLE = new DriverBuilder().withName("Elle Meyer").withPhone("9482224")
            .build();
    public static final Driver DRIVER_FIONA = new DriverBuilder().withName("Fiona Kunz").withPhone("9482427")
            .build();
    public static final Driver DRIVER_GEORGE = new DriverBuilder().withName("George Best").withPhone("9482442")
            .build();

    // Manually added
    public static final Driver DRIVER_HOON = new DriverBuilder().withName("Hoon Meier").withPhone("8482424")
            .build();
    public static final Driver DRIVER_IDA = new DriverBuilder().withName("Ida Mueller").withPhone("8482131")
            .build();

    // Manually added - Passenger's details found in {@code CommandTestUtil}
    public static final Driver DRIVER_AMY = new DriverBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .build();
    public static final Driver DRIVER_BOB = new DriverBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .build();

    private TypicalDrivers() {} // prevents instantiation
}

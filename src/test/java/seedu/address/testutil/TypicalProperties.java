package seedu.address.testutil;

import static seedu.address.testutil.TypicalClients.BOB;
import static seedu.address.testutil.TypicalClients.CALEB;
import static seedu.address.testutil.TypicalClients.DARREN;
import static seedu.address.testutil.TypicalClients.FIN;
import static seedu.address.testutil.TypicalClients.JOEL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.PropertyBook;
import seedu.address.model.property.Property;
import seedu.address.model.util.DateTimeFormat;

/**
 * A utility class containing a list of {@code Property} objects to be used in tests.
 */
public class TypicalProperties {

    public static final Property MAYFAIR = new PropertyBuilder().withName("Mayfair")
            .withType("Condo").withAddress("1 Jurong East Street 32, #08-111")
            .withPostal("609477").withDeadline(LocalDate.parse("31-12-2021", DateTimeFormat.INPUT_DATE_FORMAT))
            .withTags("4 bedrooms", "No need for renovation")
            .build();
    public static final Property BURGHLEY_DRIVE = new PropertyBuilder().withName("Burghley Drive")
            .withType("Landed").withAddress("12 Burghley Drive")
            .withPostal("558977").withDeadline(LocalDate.parse("31-07-2021", DateTimeFormat.INPUT_DATE_FORMAT))
            .withRemark("Lowest selling price is $5,040,0000")
            .withTags("99 year leasehold", "Balcony").withClient(BOB)
            .build();
    public static final Property WOODLANDS_CRESCENT = new PropertyBuilder().withName("Woodlands Crescent")
            .withType("Hdb").withAddress("Blk 784 Woodlands Crescent #01-01")
            .withPostal("731784").withDeadline(LocalDate.parse("01-08-2021", DateTimeFormat.INPUT_DATE_FORMAT))
            .withTags("2 bedrooms", "65 square metres").withClient(CALEB)
            .build();
    public static final Property JURONG = new PropertyBuilder().withName("Jurong")
            .withType("Hdb").withAddress("Jurong Ave 1, #01-01")
            .withPostal("640111").withDeadline(LocalDate.parse("01-04-2021", DateTimeFormat.INPUT_DATE_FORMAT))
            .withTags("3 bedrooms", "100 square metres").withClient(DARREN)
            .build();

    // Manually added
    public static final Property AMPANG_KITCHEN = new PropertyBuilder().withName("Ampang Kitchen")
            .withType("Landed").withAddress("39 Jln Ampang")
            .withPostal("268625").withDeadline(LocalDate.parse("11-09-2021", DateTimeFormat.INPUT_DATE_FORMAT))
            .withTags("Freehold").withClient(FIN)
            .build();
    public static final Property KAP_RESIDENCES = new PropertyBuilder().withName("Kap Residences")
            .withType("Condo").withAddress("11 King Albert Park, #03-04")
            .withPostal("598292").withDeadline(LocalDate.parse("04-07-2021", DateTimeFormat.INPUT_DATE_FORMAT))
            .withTags("5 bedrooms", "Move in ready").withClient(JOEL)
            .build();

    private TypicalProperties() {} // prevents instantiation

    /**
     * Returns a {@code PropertyBook} with all the typical properties.
     */
    public static PropertyBook getTypicalPropertyBook() {
        PropertyBook propertyBook = new PropertyBook();
        for (Property property : getTypicalProperties()) {
            propertyBook.addProperty(property);
        }
        return propertyBook;
    }

    public static List<Property> getTypicalProperties() {
        return new ArrayList<>(Arrays.asList(MAYFAIR, BURGHLEY_DRIVE, WOODLANDS_CRESCENT, JURONG));
    }

}

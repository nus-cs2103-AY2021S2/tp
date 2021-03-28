package seedu.address.testutil;

import static seedu.address.testutil.TypicalClients.BOB;
import static seedu.address.testutil.TypicalClients.CALEB;
import static seedu.address.testutil.TypicalClients.DAVE;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.PropertyBook;
import seedu.address.model.property.Property;

/**
 * A utility class containing a list of {@code Property} objects to be used in tests.
 */
public class TypicalProperties {

    public static final Property WOODLANDS_CRESCENT = new PropertyBuilder().withName("Woodlands Crescent")
            .withType("Hdb").withAddress("Blk 784 Woodlands Crescent #01-01")
            .withPostal("731784").withDeadline(LocalDate.parse("2021-08-31"))
            .withTags("2 bedrooms", "65 square metres").withClient(CALEB)
            .build();
    public static final Property MAYFAIR = new PropertyBuilder().withName("Mayfair")
            .withType("Condo").withAddress("1 Jurong East Street 32, #08-111")
            .withPostal("609477").withDeadline(LocalDate.parse("2021-12-31"))
            .withTags("4 bedrooms", "No need for renovation")
            .build();
    public static final Property BURGHLEY_DRIVE = new PropertyBuilder().withName("Burghley Drive")
            .withType("Landed").withAddress("12 Burghley Drive")
            .withPostal("123456").withDeadline(LocalDate.parse("2021-07-31"))
            .withTags("99 year leasehold").withClient(BOB)
            .build();
    public static final Property JURONG = new PropertyBuilder().withName("Jurong")
            .withType("Hdb").withAddress("Jurong Ave 1, #01-01")
            .withPostal("640111").withDeadline(LocalDate.parse("2021-08-31"))
            .withTags("3 bedrooms", "100 square metres").withClient(DAVE)
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

    /**
     * Returns a {@code PropertyBook} with all the typical properties and client.
     */
    public static PropertyBook getTypicalPropertyBookWithClient() {
        PropertyBook propertyBook = new PropertyBook();
        for (Property property : getTypicalProperties()) {
            propertyBook.addProperty(property);
        }
        propertyBook.addProperty(JURONG);
        return propertyBook;
    }
    

    public static List<Property> getTypicalProperties() {
        return new ArrayList<>(Arrays.asList(WOODLANDS_CRESCENT, MAYFAIR, BURGHLEY_DRIVE));
    }
}

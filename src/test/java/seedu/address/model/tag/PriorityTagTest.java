package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class PriorityTagTest {
    public static final String low = "LOW";
    public static final String medium = "MEDIUM";
    public static final String high = "HIGH";



    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new PriorityTag(invalidName));
    }

    @Test
    public void isValidPriorityTag() {
        // valid test
        assertTrue(PriorityTag.validateTag("LOW"));
        assertTrue(PriorityTag.validateTag("MEDIUM"));
        assertTrue(PriorityTag.validateTag("HIGH"));
        assertTrue(PriorityTag.validateTag(high));
        assertTrue(PriorityTag.validateTag(medium));
        assertTrue(PriorityTag.validateTag(high));

        // valid tags after constructing
        PriorityTag pt1 = new PriorityTag(low);
        PriorityTag pt2 = new PriorityTag(medium);
        PriorityTag pt3 = new PriorityTag(high);

        assertTrue(PriorityTag.validateTag(pt1.getTagName()));
        assertTrue(PriorityTag.validateTag(pt2.getTagName()));
        assertTrue(PriorityTag.validateTag(pt3.getTagName()));

        //invalid test
        assertFalse(PriorityTag.validateTag("Hello")); //alphabets
        assertFalse(PriorityTag.validateTag("")); // empty string
        assertFalse(PriorityTag.validateTag("***")); //special characters
        assertFalse(PriorityTag.validateTag("???hello")); //special characters and alphabets
        assertFalse(PriorityTag.validateTag("123")); //numerics
        assertFalse(PriorityTag.validateTag("123test")); //numerics and alphabets

        assertFalse(PriorityTag.validateTag("MED")); // similar cases
        assertFalse(PriorityTag.validateTag("HiGH")); // similar cases
        assertFalse(PriorityTag.validateTag("LoW")); // similar cases

    }

    @Test
    public void isEqualPriorityTag() {
        // valid tags after constructing
        PriorityTag pt1 = new PriorityTag(low);
        PriorityTag pt2 = new PriorityTag(medium);
        PriorityTag pt3 = new PriorityTag(high);

        PriorityTag pt1Copy = new PriorityTag(low);
        PriorityTag pt2Copy = new PriorityTag(medium);
        PriorityTag pt3Copy = new PriorityTag(high);

        assertTrue(pt1.equals(pt1Copy));
        assertTrue(pt2.equals(pt2Copy));
        assertTrue(pt3.equals(pt3Copy));


    }

    @Test
    public void isNotEqualPriorityTag() {
        // valid tags after constructing
        PriorityTag pt1 = new PriorityTag(low);
        PriorityTag pt2 = new PriorityTag(medium);
        PriorityTag pt3 = new PriorityTag(high);

        PriorityTag pt1Copy = new PriorityTag(low);
        PriorityTag pt2Copy = new PriorityTag(medium);
        PriorityTag pt3Copy = new PriorityTag(high);

        // not equal tags
        assertFalse(pt1.equals(pt2Copy));
        assertFalse(pt2.equals(pt3Copy));
        assertFalse(pt3.equals(pt1Copy));

    }




}

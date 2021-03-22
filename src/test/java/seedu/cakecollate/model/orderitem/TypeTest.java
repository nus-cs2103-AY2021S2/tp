package seedu.cakecollate.model.orderitem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cakecollate.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TypeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Type(null));
    }

    @Test
    public void constructor_invalidType_throwsIllegalArgumentException() {
        String invalidType = "";
        assertThrows(IllegalArgumentException.class, () -> new Type(invalidType));
    }

    @Test
    public void isValid() {
        // null type
        assertThrows(NullPointerException.class, () -> Type.isValidType(null));

        // invalid types
        assertFalse(Type.isValidType("")); // empty string
        assertFalse(Type.isValidType(" ")); // spaces only
        assertFalse(Type.isValidType("123")); // numbers only
        assertFalse(Type.isValidType("#abc$")); // containing some special characters
        assertFalse(Type.isValidType(" strawberry cake")); //starting with a space
        assertFalse(Type.isValidType(" strawberry cake ")); //starting and ending with a space

        // valid types
        assertTrue(Type.isValidType("Strawberry cake")); //typical order type
        assertTrue(Type.isValidType("s")); // one character
        assertTrue(Type
                .isValidType("Large black forest cake with "
                        + "buttercream frosting and chocolate drizzle topping")); // long order type
    }

    @Test
    public void equal() {
        Type typeOne = new Type("strawberry cake");
        Type typeTwo = new Type("Strawberry Cake");
        Type typeThree = new Type("strawberry cake");

        assertEquals(typeOne, typeThree); //same value for type
        assertNotEquals(typeOne, typeTwo); //type is case sensitive
    }
}

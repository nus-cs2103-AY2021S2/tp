package seedu.cakecollate.model.orderitem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

        // valid types
        assertTrue(Type.isValidType("Strawberry cake")); //typical order type
        assertTrue(Type.isValidType("s")); // one character
        assertTrue(Type.isValidType("really really really long type with a really long cake description"
                + " such as seven layered colourful rainbow cake with violet indigo blue green"
                + " yellow orange and red")); //really long type

        // invalid types
        assertFalse(Type.isValidType("???")); //special characters
        assertFalse(Type.isValidType("({")); //special characters
        assertFalse(Type.isValidType("+++")); //special characters
        assertFalse(Type.isValidType("--")); //special characters
        assertFalse(Type.isValidType("*")); //special characters
        assertFalse(Type.isValidType("###")); //special characters
        assertFalse(Type.isValidType("@@@")); //special characters
        assertFalse(Type.isValidType("contains, commas")); //special characters
        assertFalse(Type.isValidType("--))((==**&&^^!!@@")); //mixed special characters
        assertFalse(Type.isValidType("///strawberry cake ???")); //special characters
        assertFalse(Type.isValidType("123456")); //contains numbers
        assertFalse(Type.isValidType("123abc")); //contains numbers
        assertFalse(Type
                .isValidType("8 \" large black forest cake with "
                        + "buttercream frosting and chocolate drizzle topping")); //contains special characters
        assertFalse(Type.isValidType("")); // empty string
        assertFalse(Type.isValidType(" strawberry cake")); //starting with a space

    }

    @Test
    public void equals() {
        Type typeOne = new Type("strawberry cake");
        Type typeTwo = new Type("Strawberry Cake");
        Type typeThree = new Type("strawberry cake");

        assertEquals(typeOne, typeThree); //same value for type
        assertEquals(typeOne, typeTwo); //type is case insensitive
    }

    @Test
    public void toStringMethod() {
        String expectedString = "Strawberry Cake";
        Type strawberryCake = new Type("Strawberry Cake");
        assertEquals(expectedString, strawberryCake.toString());
    }

    @Test
    public void toHashcodeMethod() {
        //same type -> same hashcode
        Type chocolateOne = new Type("Chocolate Cake");
        Type chocolateTwo = new Type("Chocolate Cake");
        assertEquals(chocolateOne.hashCode(), chocolateTwo.hashCode());
    }

    @Test
    public void getValue() {
        Type chocolate = new Type("Chocolate Cake");
        assertEquals("Chocolate Cake", chocolate.getValue());
    }
}

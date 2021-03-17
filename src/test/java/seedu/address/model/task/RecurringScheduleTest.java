package seedu.address.model.task;

//import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RecurringScheduleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RecurringSchedule(null));
    }

    /*
    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new RecurringSchedule(invalidEmail));
    }
     */

    @Test
    public void isValidRecurringSchedule() {
        // null email
        assertThrows(NullPointerException.class, () -> RecurringSchedule.isValidRecurringSchedule(null));

        /*
        // blank email
        assertFalse(RecurringSchedule.isValidRecurringSchedule("")); // empty string
        assertFalse(RecurringSchedule.isValidRecurringSchedule(" ")); // spaces only

        // missing parts
        assertFalse(RecurringSchedule.isValidRecurringSchedule("@example.com")); // missing local part
        assertFalse(RecurringSchedule.isValidRecurringSchedule("peterjackexample.com")); // missing '@' symbol
        assertFalse(RecurringSchedule.isValidRecurringSchedule("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(RecurringSchedule.isValidRecurringSchedule("peterjack@-")); // invalid domain name
        assertFalse(RecurringSchedule.isValidRecurringSchedule("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(RecurringSchedule.isValidRecurringSchedule("peter jack@example.com")); // spaces in local part
        assertFalse(RecurringSchedule.isValidRecurringSchedule("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(RecurringSchedule.isValidRecurringSchedule(" peterjack@example.com")); // leading space
        assertFalse(RecurringSchedule.isValidRecurringSchedule("peterjack@example.com ")); // trailing space
        assertFalse(RecurringSchedule.isValidRecurringSchedule("peterjack@@example.com")); // double '@' symbol
        assertFalse(RecurringSchedule.isValidRecurringSchedule("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(RecurringSchedule.isValidRecurringSchedule("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(RecurringSchedule.isValidRecurringSchedule("peterjack@.example.com"));
        // domain name starts with a period
        assertFalse(RecurringSchedule.isValidRecurringSchedule("peterjack@example.com."));
        // domain name ends with a period
        assertFalse(RecurringSchedule.isValidRecurringSchedule("peterjack@-example.com"));
        // domain name starts with a hyphen
        assertFalse(RecurringSchedule.isValidRecurringSchedule("peterjack@example.com-"));
        // domain name ends with a hyphen
         */

//        // valid email
//        assertTrue(RecurringSchedule.isValidRecurringSchedule("PeterJack_1190@example.com"));
//        assertTrue(RecurringSchedule.isValidRecurringSchedule("a@bc")); // minimal
//        assertTrue(RecurringSchedule.isValidRecurringSchedule("test@localhost")); // alphabets only
//        assertTrue(RecurringSchedule.isValidRecurringSchedule("!#$%&'*+/=?`{|}~^.-@example.org"));
//        // special characters local part
//        assertTrue(RecurringSchedule.isValidRecurringSchedule("123@145")); // numeric local part and domain name
//        assertTrue(RecurringSchedule.isValidRecurringSchedule("a1+be!@example1.com"));
//        // mixture of alphanumeric and special characters
//        assertTrue(RecurringSchedule.isValidRecurringSchedule("peter_jack@very-very-very-long-example.com"));
//        // long domain name
//        assertTrue(RecurringSchedule.isValidRecurringSchedule("if.you.dream.it_you.can.do.it@example.com"));
//        // long local part
    }
}

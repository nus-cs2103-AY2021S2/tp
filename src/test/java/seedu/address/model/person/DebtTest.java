package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DebtTest {
    private Debt test1 = new Debt("5");
    private Debt test2 = new Debt("4");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Debt((String) null));
    }

    @Test
    public void constructor_invalidDebt_throwsIllegalArgumentException() {
        String invalidDebtString = "1.001";
        assertThrows(IllegalArgumentException.class, () -> new Debt(invalidDebtString));
    }

    @Test
    public void isValidDebt() {
        assertThrows(NullPointerException.class, () -> Debt.isValidDebt(null));

        assertFalse(Debt.isValidDebt(""));
        assertFalse(Debt.isValidDebt(" "));
        assertFalse(Debt.isValidDebt("abc"));

        assertTrue(Debt.isValidDebt("-2"));
        assertTrue(Debt.isValidDebt("0"));
        assertTrue(Debt.isValidDebt("100.1"));
        assertTrue(Debt.isValidDebt("0.01"));
    }

    @Test
    public void add() {
        Debt result = new Debt("9");
        assertTrue(result.equals(Debt.add(test1, test2)));
    }

    @Test
    public void subtract() {
        Debt result = new Debt("1");
        Debt result2 = new Debt("-1");

        assertTrue(result.equals(Debt.subtract(test1, test2)));
        assertTrue(result2.equals(Debt.subtract(test2, test1)));
    }
}

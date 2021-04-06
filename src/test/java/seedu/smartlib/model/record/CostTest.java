package seedu.smartlib.model.record;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smartlib.model.record.Cost.RATES_PER_HOUR;

import org.junit.jupiter.api.Test;


public class CostTest {

    @Test
    public void getCost_positiveIntegerParameter_success() {
        assertEquals(0, new Cost(0).getCost());
        assertEquals(1 * RATES_PER_HOUR, new Cost(1).getCost());
        assertEquals(100 * RATES_PER_HOUR, new Cost(100).getCost());
    }
}

package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DateConversionException;

public class DateUtilTest {

    @Test
    public void encodeDate_invalidDate_throwsDateConversionException() {
        String invalidDate = "209-01-01";
        String invalidDate1 = "20090101";
        String invalidDate2 = "2001-01-01";
        String invalidDate3 = "a";
        String invalidDate4 = "";
        String invalidDate5 = " ";

        assertThrows(DateConversionException.class, () -> DateUtil.encodeDate(invalidDate));
        assertThrows(DateConversionException.class, () -> DateUtil.encodeDate(invalidDate1));
        assertThrows(DateConversionException.class, () -> DateUtil.encodeDate(invalidDate2));
        assertThrows(DateConversionException.class, () -> DateUtil.encodeDate(invalidDate3));
        assertThrows(DateConversionException.class, () -> DateUtil.encodeDate(invalidDate4));
        assertThrows(DateConversionException.class, () -> DateUtil.encodeDate(invalidDate5));
    }

    @Test
    public void decodeDate_validDate_success() {
        LocalDate validDate = LocalDate.of(2020, 1, 1);
        assertEquals(DateUtil.decodeDate(validDate), "01 Jan 2020");
    }

    @Test
    public void decodeDate_invalidDate_throwsDateConversionException() {
        LocalDate invalidDate = null;
        assertThrows(NullPointerException.class, () -> DateUtil.decodeDate(invalidDate));
    }

    @Test
    public void decodeDateForStorage_invalidDate_throwsDateConversionException() {
        LocalDate invalidDate = null;
        assertThrows(NullPointerException.class, () -> DateUtil.decodeDateForStorage(invalidDate));
    }
}

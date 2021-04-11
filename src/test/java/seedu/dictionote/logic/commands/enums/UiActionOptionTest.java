package seedu.dictionote.logic.commands.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.testutil.TypicalUiActions.EXPECTED_UI_OPTION;
import static seedu.dictionote.testutil.TypicalUiActions.VALID_UI_OPTIONS;

import org.junit.jupiter.api.Test;

class UiActionOptionTest {

    @Test
    void getUiActionOption_equal() {
        for (int i = 0; i < VALID_UI_OPTIONS.length; i++) {
            assertEquals(UiActionOption.getUiActionOption(VALID_UI_OPTIONS[i]), EXPECTED_UI_OPTION[i]);
            assertEquals(UiActionOption.getUiActionOption(VALID_UI_OPTIONS[i] + " " ), UiActionOption.ALL.NONE);
        }
    }

    @Test
    void isValidOption_success() {
        for (int i = 0; i < VALID_UI_OPTIONS.length; i++) {
            assertTrue(UiActionOption.isValidOption(VALID_UI_OPTIONS[i]));
        }


        assertFalse(UiActionOption.isValidOption(""));
    }
}

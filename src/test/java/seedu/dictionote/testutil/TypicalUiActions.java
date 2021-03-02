package seedu.dictionote.testutil;

import static seedu.dictionote.logic.parser.CliSyntax.OPTION_ALL_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_CONTACT_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_DICTIONARY_CONTENT_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_DICTIONARY_LIST_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_DICTIONARY_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_LIST_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_NOTE_CONTENT_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_NOTE_LIST_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_NOTE_PANEL;

import seedu.dictionote.logic.commands.enums.UiActionOption;

/**
 * A utility class containing a arrau of {@code UiActionOption} objects to be used in tests.
 */
public class TypicalUiActions {

    private static final String VALID_OPTION_1 = OPTION_ALL_PANEL;
    private static final UiActionOption EXCEPTION_OPTION_1 = UiActionOption.ALL;

    private static final String VALID_OPTION_2 = OPTION_CONTACT_PANEL;
    private static final UiActionOption EXCEPTION_OPTION_2 = UiActionOption.CONTACT;

    private static final String VALID_OPTION_3 = OPTION_DICTIONARY_CONTENT_PANEL;
    private static final UiActionOption EXCEPTION_OPTION_3 = UiActionOption.DICTIONARY_CONTENT;

    private static final String VALID_OPTION_4 = OPTION_DICTIONARY_LIST_PANEL;
    private static final UiActionOption EXCEPTION_OPTION_4 = UiActionOption.DICTIONARY_LIST;

    private static final String VALID_OPTION_5 = OPTION_DICTIONARY_PANEL;
    private static final UiActionOption EXCEPTION_OPTION_5 = UiActionOption.DICTIONARY;

    private static final String VALID_OPTION_6 = OPTION_LIST_PANEL;
    private static final UiActionOption EXCEPTION_OPTION_6 = UiActionOption.LIST;

    private static final String VALID_OPTION_7 = OPTION_NOTE_CONTENT_PANEL;
    private static final UiActionOption EXCEPTION_OPTION_7 = UiActionOption.NOTE_CONTENT;

    private static final String VALID_OPTION_8 = OPTION_NOTE_LIST_PANEL;
    private static final UiActionOption EXCEPTION_OPTION_8 = UiActionOption.NOTE_LIST;

    private static final String VALID_OPTION_9 = OPTION_NOTE_PANEL;
    private static final UiActionOption EXCEPTION_OPTION_9 = UiActionOption.NOTE;


    public static final String[] VALID_UI_OPTIONS = {
        VALID_OPTION_1, VALID_OPTION_2, VALID_OPTION_3,
        VALID_OPTION_4, VALID_OPTION_5, VALID_OPTION_6,
        VALID_OPTION_7, VALID_OPTION_8, VALID_OPTION_9
    };

    public static final UiActionOption[] EXPECTED_UI_OPTION = {
        EXCEPTION_OPTION_1, EXCEPTION_OPTION_2, EXCEPTION_OPTION_3,
        EXCEPTION_OPTION_4, EXCEPTION_OPTION_5, EXCEPTION_OPTION_6,
        EXCEPTION_OPTION_7, EXCEPTION_OPTION_8, EXCEPTION_OPTION_9
    };
}

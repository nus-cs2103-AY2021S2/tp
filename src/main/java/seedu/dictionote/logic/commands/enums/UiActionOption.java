package seedu.dictionote.logic.commands.enums;

import static seedu.dictionote.logic.parser.CliSyntax.OPTION_ALL_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_CONTACT_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_DICTIONARY_CONTENT_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_DICTIONARY_LIST_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_DICTIONARY_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_LIST_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_NOTE_CONTENT_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_NOTE_LIST_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_NOTE_PANEL;

/**
 * UiFeedback enums is used in CommandResult to indicate the desire UI actions from the UI.
 */
public enum UiActionOption {
    DICTIONARY, DICTIONARY_LIST, DICTIONARY_CONTENT, DICTIONARY_DEFINITIONS,
    NOTE, NOTE_LIST, NOTE_CONTENT,
    LIST, CONTACT, ALL, NONE;


    public static final String MESSAGE_CONSTRAINTS = "Options have to be one of the following: "
        + OPTION_ALL_PANEL + ", "
        + OPTION_CONTACT_PANEL + ", "
        + OPTION_DICTIONARY_CONTENT_PANEL + ", "
        + OPTION_DICTIONARY_LIST_PANEL + ", "
        + OPTION_DICTIONARY_PANEL + ", "
        + OPTION_LIST_PANEL + ", "
        + OPTION_NOTE_CONTENT_PANEL + ", "
        + OPTION_NOTE_LIST_PANEL + ", "
        + OPTION_NOTE_PANEL + ".";

    /**
     * Return the corresponding UiActionOption, return NONE if the option is invalid
     * @param option a string
     * @return
     */
    public static UiActionOption getUiActionOption(String option) {
        switch (option) {
        case OPTION_ALL_PANEL:
            return UiActionOption.ALL;
        case OPTION_CONTACT_PANEL:
            return UiActionOption.CONTACT;
        case OPTION_DICTIONARY_CONTENT_PANEL:
            return UiActionOption.DICTIONARY_CONTENT;
        case OPTION_DICTIONARY_LIST_PANEL:
            return UiActionOption.DICTIONARY_LIST;
        case OPTION_DICTIONARY_PANEL:
            return UiActionOption.DICTIONARY;
        case OPTION_LIST_PANEL:
            return UiActionOption.LIST;
        case OPTION_NOTE_CONTENT_PANEL:
            return UiActionOption.NOTE_CONTENT;
        case OPTION_NOTE_LIST_PANEL:
            return UiActionOption.NOTE_LIST;
        case OPTION_NOTE_PANEL:
            return UiActionOption.NOTE;
        default:
            return UiActionOption.NONE;
        }
    }

    /**
     * Returns true if a given string is a valid option.
     */
    public static boolean isValidOption(String option) {
        switch (option) {
        case OPTION_ALL_PANEL:
        case OPTION_CONTACT_PANEL:
        case OPTION_DICTIONARY_CONTENT_PANEL:
        case OPTION_DICTIONARY_LIST_PANEL:
        case OPTION_DICTIONARY_PANEL:
        case OPTION_LIST_PANEL:
        case OPTION_NOTE_CONTENT_PANEL:
        case OPTION_NOTE_LIST_PANEL:
        case OPTION_NOTE_PANEL:
            return true;
        default:
            return false;
        }
    }
}

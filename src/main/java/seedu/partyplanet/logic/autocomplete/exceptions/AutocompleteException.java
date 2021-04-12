package seedu.partyplanet.logic.autocomplete.exceptions;

import seedu.partyplanet.commons.exceptions.IllegalValueException;

/**
 * Represents a parse error encountered by a AutocompleteParser.
 */
public class AutocompleteException extends IllegalValueException {

    public AutocompleteException(String message) {
        super(message);
    }

    public AutocompleteException(String message, Throwable cause) {
        super(message, cause);
    }

}


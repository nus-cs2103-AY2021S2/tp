package seedu.partyplanet.logic.autocomplete;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AutocompleteParserTest {

    private final AutocompleteParser autocompleteParser = new AutocompleteParser();

    @Test
    public void parse_edit() throws Exception {
        String input = "edit 1";
        AutocompleteUtil autocompleteUtil = autocompleteParser.parseCommand(input);

        String arguments = " 1";
        AutocompleteUtil editAutocompleteUtil = new EditAutocompleteUtil(arguments);

        assertEquals(autocompleteUtil, editAutocompleteUtil);
    }

    @Test
    public void parse_eEdit() throws Exception {
        String input = "eedit 1";
        AutocompleteUtil autocompleteUtil = autocompleteParser.parseCommand(input);

        String arguments = " 1";
        AutocompleteUtil eEditAutocompleteUtil = new EEditAutocompleteUtil(arguments);

        assertEquals(autocompleteUtil, eEditAutocompleteUtil);
    }

}

package seedu.partyplanet.logic.autocomplete;

import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.logic.parser.exceptions.ParseException;
import seedu.partyplanet.model.Model;

public interface AutocompleteUtil {

    /**
     * Parses an edit command to autocomplete all specified fields.
     * @param model Model instance.
     * @return String of new autocompleted command.
     * @throws ParseException If the input command does not follow requirements.
     * @throws CommandException If the input command is out of bounds.
     */
    public String parse(Model model) throws ParseException, CommandException;

}

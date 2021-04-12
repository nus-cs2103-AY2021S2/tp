package seedu.partyplanet.logic.autocomplete;

import static java.util.Objects.requireNonNull;
import static seedu.partyplanet.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import seedu.partyplanet.commons.core.index.Index;
import seedu.partyplanet.logic.commands.EEditCommand;
import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.logic.parser.ArgumentMultimap;
import seedu.partyplanet.logic.parser.ArgumentTokenizer;
import seedu.partyplanet.logic.parser.ParserUtil;
import seedu.partyplanet.logic.parser.Prefix;
import seedu.partyplanet.logic.parser.exceptions.ParseException;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.event.Event;

public class EEditAutocompleteUtil implements AutocompleteUtil {

    private static final String INDEX_OUT_OF_BOUNDS_ERROR = "Index provided does not match any event!";

    private final String input;

    /**
     * EEditAutocompleteUtil Constructor.
     */
    public EEditAutocompleteUtil(String input) {
        requireNonNull(input);
        this.input = input;
    }

    /**
     * Parses an eedit command to autocomplete all specified fields.
     * @param model Model instance containing event book.
     * @return String of new autocompleted command.
     * @throws ParseException If the input command does not follow requirements.
     * @throws CommandException If the input command is out of bounds.
     */
    public String parse(Model model) throws ParseException, CommandException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(input, PREFIX_NAME, PREFIX_DATE, PREFIX_REMARK);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble().split(" ")[0]);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EEditCommand.MESSAGE_USAGE), pe);
        }

        ObservableList<Event> filteredEventsList = model.getFilteredEventList();
        if (index.getZeroBased() >= filteredEventsList.size()) {
            throw new CommandException(INDEX_OUT_OF_BOUNDS_ERROR);
        }

        Event event = filteredEventsList.get(index.getZeroBased());

        // Create a Map of Prefix to the relevant getter method
        Map<Prefix, String> prefixMethodMap = Map.of(
                PREFIX_DATE, event.getEventDate().value,
                PREFIX_NAME, event.getName().fullName,
                PREFIX_REMARK, event.getRemark().value
        );

        String output = "eedit " + argMultimap.getPreamble();

        // Here we can assume Prefixes are sorted in the order they are entered.
        for (Prefix prefix: argMultimap.getPrefixPositionOrders()) {
            List<String> values = argMultimap.getAllValues(prefix);

            // Remove Preamble
            if (prefix.getPrefix().equals("")) {
                continue;
            }

            // If Prefix is not a relevant/correct Prefix, ignore.
            if (!prefixMethodMap.keySet().contains(prefix)) {
                output += " " + prefix;
                continue;
            }

            boolean hasOutput = false;
            if (values.size() > 0) {
                for (String value: values) {
                    if (value.length() > 0) {
                        output += " " + prefix + " " + value;
                        hasOutput = true;
                    }
                }
            }

            if (!hasOutput) {
                output += " " + prefix + " " + prefixMethodMap.get(prefix);
            }

        }

        return output;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof EEditAutocompleteUtil)) {
            return false;
        }

        return this.input.equals(((EEditAutocompleteUtil) obj).input);
    }

}

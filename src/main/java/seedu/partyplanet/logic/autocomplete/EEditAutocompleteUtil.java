package seedu.partyplanet.logic.autocomplete;

import static java.util.Objects.requireNonNull;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import seedu.partyplanet.commons.core.Messages;
import seedu.partyplanet.commons.core.index.Index;
import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.logic.parser.ArgumentMultimap;
import seedu.partyplanet.logic.parser.ArgumentTokenizer;
import seedu.partyplanet.logic.parser.ParserUtil;
import seedu.partyplanet.logic.parser.Prefix;
import seedu.partyplanet.logic.parser.exceptions.ParseException;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.event.Event;

public class EEditAutocompleteUtil {

    private static final String INDEX_NOT_SPECIFIED_OR_INVALID_MESSAGE = "Index not specified or Invalid!";

    /**
     * Parses an edit command to autocomplete remark.
     * @param arguments User's input command.
     * @param model Model instance containing address book.
     * @return String of new autocompleted command.
     * @throws ParseException If the input command does not follow requirements.
     * @throws CommandException If the input command is out of bounds.
     */
    public String parseCommand(String arguments, Model model) throws ParseException, CommandException {
        requireNonNull(arguments);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(arguments, PREFIX_NAME, PREFIX_DATE, PREFIX_REMARK);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble().split(" ")[0]);
        } catch (ParseException pe) {
            throw new ParseException(INDEX_NOT_SPECIFIED_OR_INVALID_MESSAGE);
        }

        ObservableList<Event> filteredEventsList = model.getFilteredEventList();
        if (index.getZeroBased() >= filteredEventsList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event event = filteredEventsList.get(index.getZeroBased());

        // Create a Map of Prefix to the relevant getter method
        Map<Prefix, String> prefixMethodMap = Map.of(
            PREFIX_DATE, event.getEventDate().value,
            PREFIX_NAME, event.getName().fullName,
            PREFIX_REMARK, event.getDetails().value
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

}

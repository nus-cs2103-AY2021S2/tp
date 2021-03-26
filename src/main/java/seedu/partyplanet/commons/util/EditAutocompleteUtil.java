package seedu.partyplanet.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.partyplanet.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.ObservableList;
import seedu.partyplanet.commons.core.Messages;
import seedu.partyplanet.commons.core.index.Index;
import seedu.partyplanet.logic.commands.EditCommand;
import seedu.partyplanet.logic.commands.HelpCommand;
import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.logic.parser.ArgumentMultimap;
import seedu.partyplanet.logic.parser.ArgumentTokenizer;
import seedu.partyplanet.logic.parser.ParserUtil;
import seedu.partyplanet.logic.parser.Prefix;
import seedu.partyplanet.logic.parser.exceptions.ParseException;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.person.Person;
import seedu.partyplanet.model.tag.Tag;

public class EditAutocompleteUtil {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Used to convert Set of {@code Tag}s into a String with Tag Prefixes.
     */
    private static String getTagsAsAutocompletedString(Set<Tag> tags) {
        return tags
            .stream()
            .map(t -> "-t " + t.tagName)
            .reduce((a, b) -> a + " " + b)
            .orElse("-t");
    }

    /**
     * Parses an edit command to autocomplete remark.
     * @param userInput User's input command.
     * @param model Model instance containing address book.
     * @return String of new autocompleted command.
     * @throws ParseException If the input command does not follow requirements.
     * @throws CommandException If the input command is out of bounds.
     */
    public String parseEditCommand(String userInput, Model model) throws ParseException, CommandException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        if (!commandWord.equals("edit")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        requireNonNull(arguments);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(arguments, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_BIRTHDAY, PREFIX_ADDRESS, PREFIX_REMARK, PREFIX_TAG);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        ObservableList<Person> filteredPersonList = model.getFilteredPersonList();
        if (index.getZeroBased() >= filteredPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person person = filteredPersonList.get(index.getZeroBased());

        // Create a Map of Prefix to the relevant getter method
        Map<Prefix, Supplier<String>> prefixMethodMap = Map.of(
            PREFIX_ADDRESS, () -> person.getAddress().value,
            PREFIX_BIRTHDAY, () -> person.getBirthday().value,
            PREFIX_EMAIL, () -> person.getEmail().value,
            PREFIX_NAME, () -> person.getName().fullName,
            PREFIX_REMARK, () -> person.getRemark().value,
            PREFIX_TAG, () -> getTagsAsAutocompletedString(person.getTags())
        );

        String output = "edit " + index.getOneBased();
        
        // Here we can assume Prefixes are sorted in the order they are entered.
        for (Prefix prefix: argMultimap.getPrefixPositionOrders()) {
            List<String> values = argMultimap.getAllValues(prefix);
            String lastValue = values.get(values.size() - 1);

            // Remove Preamble
            if (prefix.getPrefix().equals("")) {
                continue;
            }

            // If Prefix is not a relevant/correct Prefix, ignore.
            if (!prefixMethodMap.keySet().contains(prefix)) {
                output += " " + prefix;
                continue;
            }

            // If Prefix value already provided
            if (lastValue.length() > 0) {
                output += " " + prefix + " " + lastValue;
                continue;
            }

            // Special Consideration for Tag Sets
            if (prefix.equals(PREFIX_TAG)) {
                output += " " + prefixMethodMap.get(prefix).get();
                continue;
            }

            output += " " + prefix + " " + prefixMethodMap.get(prefix).get();
        }

        return output;
    }

}

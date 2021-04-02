package seedu.booking.logic.parser.promptparsers;

import java.util.Set;
import java.util.stream.Stream;

import seedu.booking.logic.commands.PromptAddPersonCommand;
import seedu.booking.logic.commands.PromptPersonEmailCommand;
import seedu.booking.logic.commands.PromptPersonNameCommand;
import seedu.booking.logic.commands.PromptPersonPhoneCommand;
import seedu.booking.logic.commands.PromptPersonTagsCommand;
import seedu.booking.logic.parser.ArgumentMultimap;
import seedu.booking.logic.parser.ParserUtil;
import seedu.booking.logic.parser.Prefix;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.Tag;
import seedu.booking.model.person.Email;
import seedu.booking.model.person.Name;
import seedu.booking.model.person.Phone;

public class PromptAddPersonCommandParser {
    /**
     * Parses the given {@code String} of arguments for venue in the context of adding a venue
     * and returns an PromptAddVenue object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PromptAddPersonCommand parse(String args) throws ParseException {
        return new PromptAddPersonCommand();
    }

    /**
     * Parses the given {@code String} of arguments for name in the context of adding a person.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PromptPersonNameCommand parseName(String args) throws ParseException {
        Name name;
        name = ParserUtil.parseName(args);

        return new PromptPersonNameCommand(name);
    }

    /**
     * Parses the given {@code String} of arguments for phone in the context of adding a person.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PromptPersonPhoneCommand parsePhone(String args) throws ParseException {
        Phone phone;
        phone = ParserUtil.parsePhone(args);

        return new PromptPersonPhoneCommand(phone);
    }

    /**
     * Parses the given {@code String} of arguments for email in the context of adding a person.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PromptPersonEmailCommand parseEmail(String args) throws ParseException {
        Email email;
        email = ParserUtil.parseEmail(args);

        return new PromptPersonEmailCommand(email);
    }

    /**
     * Parses the given {@code String} of arguments for tags in the context of adding a person.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PromptPersonTagsCommand parseTags(String args) throws ParseException {
        Set<Tag> tags = ParserUtil.parseTagsForPromptCommands(args);

        return new PromptPersonTagsCommand(tags);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

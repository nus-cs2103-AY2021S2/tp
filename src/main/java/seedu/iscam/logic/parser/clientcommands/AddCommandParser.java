package seedu.iscam.logic.parser.clientcommands;

import static seedu.iscam.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_IMAGE;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_PLAN;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.iscam.logic.commands.AddCommand;
import seedu.iscam.logic.parser.ArgumentMultimap;
import seedu.iscam.logic.parser.ArgumentTokenizer;
import seedu.iscam.logic.parser.Parser;
import seedu.iscam.logic.parser.ParserUtil;
import seedu.iscam.logic.parser.Prefix;
import seedu.iscam.logic.parser.exceptions.ParseException;
import seedu.iscam.logic.parser.exceptions.ParseFormatException;
import seedu.iscam.model.client.Client;
import seedu.iscam.model.client.Email;
import seedu.iscam.model.client.Image;
import seedu.iscam.model.client.InsurancePlan;
import seedu.iscam.model.client.Phone;
import seedu.iscam.model.commons.Location;
import seedu.iscam.model.commons.Name;
import seedu.iscam.model.commons.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_LOCATION, PREFIX_PLAN,
                        PREFIX_TAG, PREFIX_IMAGE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_LOCATION, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseFormatException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        InsurancePlan plan = ParserUtil.parsePlan(argMultimap.getValue(PREFIX_PLAN).orElse("No plans yet"));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Location location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get());
        // TODO: Set a better place to refer default.png to
        Image imageRes = ParserUtil.parseImageRes(argMultimap.getValue(PREFIX_IMAGE).orElse("default.png"));

        Client client = new Client(name, phone, email, location, plan, tagList, imageRes);

        return new AddCommand(client);
    }

}

package seedu.address.logic.parser.addcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.addcommand.AddPersonCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddPersonCommand object
 */
public class AddPersonCommandParser extends AddCommandParser implements Parser<AddPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPersonCommand
     * and returns an AddPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPersonCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_BIRTHDAY, PREFIX_TAG);

        boolean isPreambleEmpty = argMultimap.getPreamble().isEmpty();
        boolean isNamePrefixPresent = arePrefixesPresent(argMultimap, PREFIX_NAME);
        boolean isBirthdayPrefixPresent = arePrefixesPresent(argMultimap, PREFIX_BIRTHDAY);

        if (!isNamePrefixPresent || !isPreambleEmpty || !isBirthdayPrefixPresent) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddPersonCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME)
            .filter(Name::isValidName)
            .orElseThrow(() -> new ParseException(Name.MESSAGE_CONSTRAINTS)));

        Birthday birthday = ParserUtil.parseBirthday(argMultimap.getValue(PREFIX_BIRTHDAY)
                        .filter(Birthday::isValidBirthday)
                        .orElseThrow(() -> new ParseException(Birthday.MESSAGE_CONSTRAINTS)),
                name.fullName);

        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Person person = new Person(name, birthday, tagList);

        return new AddPersonCommand(person);
    }

}

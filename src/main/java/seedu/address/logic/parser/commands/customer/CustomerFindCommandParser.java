package seedu.address.logic.parser.commands.customer;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.logic.commands.customer.CustomerFindCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.commands.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonNameContainsWordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class CustomerFindCommandParser implements Parser<CustomerFindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CustomerFindCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CustomerFindCommand.MESSAGE_USAGE));
        }

        Optional<String> nameArgs = argMultimap.getValue(PREFIX_NAME);
        List<String> keywords = new ArrayList<>();
        if (nameArgs.isPresent()) {
            try {
                keywords = ParserUtil.parseKeywords(nameArgs.get());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        CustomerFindCommand.MESSAGE_USAGE), pe);
            }
        }
        return new CustomerFindCommand(new PersonNameContainsWordsPredicate(keywords));
    }

}

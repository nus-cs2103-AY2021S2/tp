package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attribute.Attribute;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        List<String> splitArgs = Arrays.asList(trimmedArgs.split("\\s+"));

        if (trimmedArgs.isEmpty()) {
            return new ListCommand();
        } else {
            Set<Attribute> attributes = ParserUtil.parseAttributes(splitArgs);
            return new ListCommand(attributes);
        }
    }

}

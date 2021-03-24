package dog.pawbook.logic.parser;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_NAME;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_SESSION;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import dog.pawbook.logic.commands.AddProgramCommand;
import dog.pawbook.logic.parser.exceptions.ParseException;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.program.Program;
import dog.pawbook.model.managedentity.program.Session;
import dog.pawbook.model.managedentity.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddProgramCommandParser extends CommandWithPrefixParser<AddProgramCommand> {
    private static final Prefix[] PROGRAM_COMPULSORY_PREFIXES = {PREFIX_NAME, PREFIX_SESSION};
    private static final Prefix[] PROGRAM_OPTIONAL_PREFIXES = {PREFIX_TAG};
    private static final Prefix[] PROGRAM_ALL_PREFIXES =
        Stream.of(PROGRAM_COMPULSORY_PREFIXES, PROGRAM_OPTIONAL_PREFIXES).flatMap(Stream::of).toArray(Prefix[]::new);

    @Override
    protected Prefix[] getCompulsoryPrefixes() {
        return PROGRAM_COMPULSORY_PREFIXES;
    }

    @Override
    protected Prefix[] getAllPrefixes() {
        return PROGRAM_ALL_PREFIXES;
    }

    @Override
    protected String getUsageText() {
        return AddProgramCommand.MESSAGE_USAGE;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddProgramCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = extractArguments(args);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Set<Session> sessionList = ParserUtil.parseSessions(argMultimap.getAllValues(PREFIX_SESSION));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Program program = new Program(name, sessionList, tagList);

        return new AddProgramCommand(program);
    }
}

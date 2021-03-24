package dog.pawbook.logic.parser;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_NAME;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_PHONE;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import dog.pawbook.logic.commands.AddOwnerCommand;
import dog.pawbook.logic.parser.exceptions.ParseException;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.owner.Address;
import dog.pawbook.model.managedentity.owner.Email;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.owner.Phone;
import dog.pawbook.model.managedentity.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddOwnerCommandParser extends CommandWithPrefixParser<AddOwnerCommand> {
    private static final Prefix[] OWNER_COMPULSORY_PREFIXES = {PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL};
    private static final Prefix[] OWNER_OPTIONAL_PREFIXES = {PREFIX_TAG};
    private static final Prefix[] OWNER_ALL_PREFIXES =
            Stream.of(OWNER_COMPULSORY_PREFIXES, OWNER_OPTIONAL_PREFIXES).flatMap(Stream::of).toArray(Prefix[]::new);

    @Override
    protected Prefix[] getCompulsoryPrefixes() {
        return OWNER_COMPULSORY_PREFIXES;
    }

    @Override
    protected Prefix[] getAllPrefixes() {
        return OWNER_ALL_PREFIXES;
    }

    @Override
    protected String getUsageText() {
        return AddOwnerCommand.MESSAGE_USAGE;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddOwnerCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = extractArguments(args);

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Owner owner = new Owner(name, phone, email, address, tagList);

        return new AddOwnerCommand(owner);
    }
}

package dog.pawbook.logic.parser;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_BREED;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_DATEOFBIRTH;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_NAME;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_OWNERID;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_SEX;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import dog.pawbook.logic.commands.AddDogCommand;
import dog.pawbook.logic.parser.exceptions.ParseException;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.dog.Breed;
import dog.pawbook.model.managedentity.dog.DateOfBirth;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.dog.Sex;
import dog.pawbook.model.managedentity.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddDogCommandParser extends CommandWithCompulsoryPrefixNoPreambleParser<AddDogCommand> {
    private static final Prefix[] DOG_COMPULSORY_PREFIXES = {
        PREFIX_NAME, PREFIX_BREED, PREFIX_DATEOFBIRTH, PREFIX_OWNERID, PREFIX_SEX
    };
    private static final Prefix[] DOG_OPTIONAL_PREFIXES = {PREFIX_TAG};
    private static final Prefix[] DOG_ALL_PREFIXES =
            Stream.of(DOG_COMPULSORY_PREFIXES, DOG_OPTIONAL_PREFIXES).flatMap(Stream::of).toArray(Prefix[]::new);

    @Override
    protected Prefix[] getCompulsoryPrefixes() {
        return DOG_COMPULSORY_PREFIXES;
    }

    @Override
    protected Prefix[] getAllPrefixes() {
        return DOG_ALL_PREFIXES;
    }

    @Override
    protected String getUsageText() {
        return AddDogCommand.MESSAGE_USAGE;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddDogCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = extractArguments(args);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Breed breed = ParserUtil.parseBreed(argMultimap.getValue(PREFIX_BREED).get());
        DateOfBirth dob = ParserUtil.parseDob(argMultimap.getValue(PREFIX_DATEOFBIRTH).get());
        int ownerID = ParserUtil.parseId(argMultimap.getValue(PREFIX_OWNERID).get());
        Sex sex = ParserUtil.parseSex(argMultimap.getValue(PREFIX_SEX).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Dog dog = new Dog(name, breed, dob, sex, ownerID, tagList);

        return new AddDogCommand(dog);
    }
}

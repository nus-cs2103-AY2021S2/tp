package dog.pawbook.logic.parser;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_BREED;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_DOB;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_OWNERID;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_SEX;

import java.util.stream.Stream;

import dog.pawbook.logic.commands.EditDogCommand;
import dog.pawbook.logic.parser.exceptions.ParseException;

public class EditDogCommandParser extends EditCommandParser<EditDogCommand> {
    private static final Prefix[] EDIT_DOG_SPECIFIC_PREFIXES = {PREFIX_BREED, PREFIX_DOB, PREFIX_OWNERID, PREFIX_SEX};
    private static final Prefix[] EDIT_DOG_ALL_PREFIXES =
            Stream.of(EDIT_COMMON_PREFIXES, EDIT_DOG_SPECIFIC_PREFIXES).flatMap(Stream::of).toArray(Prefix[]::new);

    @Override
    protected Prefix[] getAllPrefixes() {
        return EDIT_DOG_ALL_PREFIXES;
    }

    @Override
    protected String getUsageText() {
        return EditDogCommand.MESSAGE_USAGE;
    }

    @Override
    protected EditDogCommand genEditCommand(int id, ArgumentMultimap argMultimap) throws ParseException {
        EditDogCommand.EditDogDescriptor editDogDescriptor = new EditDogCommand.EditDogDescriptor();

        fillCommonAttributes(editDogDescriptor, argMultimap);

        if (argMultimap.getValue(PREFIX_BREED).isPresent()) {
            editDogDescriptor.setBreed(ParserUtil.parseBreed(argMultimap.getValue(PREFIX_BREED).get()));
        }
        if (argMultimap.getValue(PREFIX_DOB).isPresent()) {
            editDogDescriptor.setDob(ParserUtil.parseDob(argMultimap.getValue(PREFIX_DOB).get()));
        }
        if (argMultimap.getValue(PREFIX_OWNERID).isPresent()) {
            editDogDescriptor.setOwnerId(ParserUtil.parseId(argMultimap.getValue(PREFIX_OWNERID).get()));
        }
        if (argMultimap.getValue(PREFIX_SEX).isPresent()) {
            editDogDescriptor.setSex(ParserUtil.parseSex(argMultimap.getValue(PREFIX_SEX).get()));
        }

        if (editDogDescriptor.isNoFieldEdited()) {
            throw new ParseException(EditDogCommand.MESSAGE_NOT_EDITED);
        }

        return new EditDogCommand(id, editDogDescriptor);
    }
}

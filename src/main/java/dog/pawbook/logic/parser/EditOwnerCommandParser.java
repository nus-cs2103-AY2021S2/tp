package dog.pawbook.logic.parser;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.stream.Stream;

import dog.pawbook.logic.commands.EditOwnerCommand;
import dog.pawbook.logic.parser.exceptions.ParseException;

public class EditOwnerCommandParser extends EditCommandParser<EditOwnerCommand> {
    private static final Prefix[] EDIT_OWNER_SPECIFIC_PREFIXES = {PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL};
    private static final Prefix[] EDIT_OWNER_ALL_PREFIXES =
            Stream.of(EDIT_COMMON_PREFIXES, EDIT_OWNER_SPECIFIC_PREFIXES).flatMap(Stream::of).toArray(Prefix[]::new);

    @Override
    protected Prefix[] getAllPrefixes() {
        return EDIT_OWNER_ALL_PREFIXES;
    }

    @Override
    protected String getUsageText() {
        return EditOwnerCommand.MESSAGE_USAGE;
    }

    @Override
    protected EditOwnerCommand genEditCommand(int id, ArgumentMultimap argMultimap) throws ParseException {
        EditOwnerCommand.EditOwnerDescriptor editOwnerDescriptor = new EditOwnerCommand.EditOwnerDescriptor();

        fillCommonAttributes(editOwnerDescriptor, argMultimap);

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editOwnerDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editOwnerDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editOwnerDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }

        if (editOwnerDescriptor.isNoFieldEdited()) {
            throw new ParseException(EditOwnerCommand.MESSAGE_NOT_EDITED);
        }

        return new EditOwnerCommand(id, editOwnerDescriptor);
    }
}

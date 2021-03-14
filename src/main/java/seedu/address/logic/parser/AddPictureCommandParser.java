package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.nio.file.Path;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddPictureCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddPictureCommandParser implements Parser<AddPictureCommand> {

    private void throwParseException() throws ParseException {
        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPictureCommand.MESSAGE_USAGE)
        );
    }

    @Override
    public AddPictureCommand parse(String args) throws ParseException {
        String[] argArr = args.trim().split("\\s+");

        if (argArr.length < 2) {
            throwParseException();
        }

        Index index = null;

        try {
            index = ParserUtil.parseIndex(argArr[0]);
        } catch (ParseException pe) {
            throwParseException();
        }

        Path path = ParserUtil.parsePictureFilePath(argArr[1]);
        return new AddPictureCommand(index, path);
    }
}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.FileUtil;
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

        if (!FileUtil.isValidPath(argArr[1])) {
            throw new ParseException("Invalid file path supplied");
        }
        Path path = Paths.get(argArr[1]);

        if (!FileUtil.isFileExists(path)) {
            throw new ParseException("Cannot find file at specified path");
        }

        // Check extension
        String fileName = path.toString();
        int lastIndexOf = fileName.lastIndexOf('.');
        String ext = fileName.substring(lastIndexOf);

        String[] allowedExtensions = { ".png", ".jpeg", ".jpg" };
        boolean hasCorrectExt = Arrays.stream(allowedExtensions)
                .map(ext::equals)
                .reduce(false, (x, y) -> x || y);
        if (!hasCorrectExt) {
            throw new ParseException("Given file is not an image. Accepted extensions: "
                    + String.join(", ", allowedExtensions) );
        }

        return new AddPictureCommand(index, path);
    }
}

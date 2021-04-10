package seedu.taskify.logic.parser;

import static seedu.taskify.logic.commands.util.DeleteUtil.isDeletingTasksByStatus;
import static seedu.taskify.logic.parser.ParserUtil.parseInputToStatus;

import seedu.taskify.logic.commands.DeleteByStatusCommand;
import seedu.taskify.logic.parser.exceptions.ParseException;
import seedu.taskify.model.task.Status;

public class DeleteByStatusCommandParser implements Parser<DeleteByStatusCommand> {

    @Override
    public DeleteByStatusCommand parse(String args) throws ParseException {
        assert isDeletingTasksByStatus(args);
        Status status = parseInputToStatus(args);
        return new DeleteByStatusCommand(status);
    }
}

package seedu.address.logic.commands;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.List;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

public class BatchCommand<T extends Command> extends Command {

    private final Logger logger = LogsCenter.getLogger(getClass());

    public static final String COMMAND_WORD = "batch";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Batch operation for all the listed clients."
            + "Example: " + COMMAND_WORD + " -edit -1, 2 -t/colleagues";

    private final String command;
    private final Parser<T> commandParser;
    private final List<Index> targetIndices;
    private final String inputCommandArgs;

    public BatchCommand(String command, Parser<T> commandParser, List<Index> targetIndices, String inputCommandArgs) {
        this.command = command;
        this.commandParser = commandParser;
        this.targetIndices = targetIndices;
        this.inputCommandArgs = inputCommandArgs;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        for (Index i : targetIndices) {
            String newCommandInput = i.getOneBased() + " " + inputCommandArgs;
            try {
                commandParser.parse(newCommandInput).execute(model);
            } catch (ParseException e) {
                logger.info("ERROR " + newCommandInput);
                return new CommandResult("Batch operation halted.", false, false, false);
            }
        }

        return new CommandResult("Batch operation successful!", false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BatchCommand // instanceof handles nulls
                && targetIndices.equals(((BatchCommand) other).targetIndices)); // state check
    }

}

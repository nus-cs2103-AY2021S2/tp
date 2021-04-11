package seedu.module.logic.parser;

import static seedu.module.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.module.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.module.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.module.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.module.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.module.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.module.logic.parser.CliSyntax.PREFIX_WORKLOAD;
import static seedu.module.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.module.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.module.logic.commands.SortCommand;
import seedu.module.model.task.Task;

public class SortCommandParserTest {

    private static final String VALID_DEADLINE_INPUT = " " + PREFIX_DEADLINE.getPrefix();
    private static final String VALID_DESCRIPTION_INPUT = " " + PREFIX_DESCRIPTION.getPrefix();
    private static final String VALID_MODULE_INPUT = " " + PREFIX_MODULE.getPrefix();
    private static final String VALID_WORKLOAD_INPUT = " " + PREFIX_WORKLOAD.getPrefix();
    private static final String VALID_TAG_INPUT = " " + PREFIX_TAG.getPrefix();
    private static final String VALID_TASK_NAME_INPUT = " " + PREFIX_TASK_NAME.getPrefix();
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);


    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_wrongParameter_failure() {
        // no slash
        assertParseFailure(parser, "w", MESSAGE_INVALID_FORMAT);

        // no slash
        assertParseFailure(parser, "b", MESSAGE_INVALID_FORMAT);

        // no slash
        assertParseFailure(parser, "s", MESSAGE_INVALID_FORMAT);

        // no slash
        assertParseFailure(parser, "t", MESSAGE_INVALID_FORMAT);

        // use full name
        assertParseFailure(parser, "deadline/", MESSAGE_INVALID_FORMAT);



    }

    @Test
    public void parse_correctParameter_success() {
        // empty arguments
        assertParseSuccess(parser, "", new SortCommand(new Task.DeadlineComparator()));

        // module prefix
        assertParseSuccess(parser, VALID_MODULE_INPUT, new SortCommand(new Task.ModuleComparator()));

        // deadline prefix
        assertParseSuccess(parser, VALID_DEADLINE_INPUT, new SortCommand(new Task.DeadlineComparator()));

        // description prefix
        assertParseSuccess(parser, VALID_DESCRIPTION_INPUT, new SortCommand(new Task.DescriptionComparator()));

        // workload prefix
        assertParseSuccess(parser, VALID_WORKLOAD_INPUT, new SortCommand(new Task.WorkloadComparator()));

        // tag prefix
        assertParseSuccess(parser, VALID_TAG_INPUT, new SortCommand(new Task.TagComparator()));

        // task name prefix
        assertParseSuccess(parser, VALID_TASK_NAME_INPUT, new SortCommand(new Task.NameComparator()));
    }


}

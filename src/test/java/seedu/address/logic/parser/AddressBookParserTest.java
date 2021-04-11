package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.MASSBLACKLIST_BLACKLIST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MASSBLACKLIST_KEYWORD_BLACKLIST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_JANE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_DIRECTION_ASCENDING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_DIRECTION_DESCENDING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLACKLIST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.BlacklistCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CollectCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MassBlacklistCommand;
import seedu.address.logic.commands.MassDeleteCommand;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.AddressContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PersonTagContainsKeywordsPredicate;
import seedu.address.model.person.predicates.ReturnTruePredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();
    private ReturnTruePredicate returnTruePredicate = new ReturnTruePredicate();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_blacklist() throws Exception {
        BlacklistCommand command = (BlacklistCommand) parser.parseCommand(
                BlacklistCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new BlacklistCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_massBlacklist() throws Exception {
        String input = MassBlacklistCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + "-"
                + INDEX_SECOND_PERSON.getOneBased() + " " + PREFIX_BLACKLIST + " "
                + VALID_MASSBLACKLIST_KEYWORD_BLACKLIST;

        MassBlacklistCommand command = (MassBlacklistCommand) parser.parseCommand(input);
        assertEquals(new MassBlacklistCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON,
                MASSBLACKLIST_BLACKLIST), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_collect() throws Exception {
        CollectCommand command = (CollectCommand) parser.parseCommand(
                CollectCommand.COMMAND_WORD + " " + PREFIX_NAME);
        assertEquals(new CollectCommand(PREFIX_NAME, CollectCommand.DEFAULT_SEPARATOR), command);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_massDelete() throws Exception {
        String input = MassDeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased()
                + "-" + INDEX_SECOND_PERSON.getOneBased();
        MassDeleteCommand command = (MassDeleteCommand) parser.parseCommand(input);
        assertEquals(new MassDeleteCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        // The remark field in the EditPersonDescriptor of the EditCommand should be null.
        descriptor.setRemark(null);
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> nameKeywords = Arrays.asList("foo", "bar", "baz");
        List<String> tagKeywords = Arrays.asList("tagOne", "tagTwo");
        List<String> addressKeywords = Arrays.asList("address1", "address2");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " "
                        + PREFIX_NAME + nameKeywords.stream().collect(Collectors.joining(" ")) + " "
                        + PREFIX_TAG + tagKeywords.stream().collect(Collectors.joining(" ")) + " "
                        + PREFIX_ADDRESS + addressKeywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(nameKeywords),
                        new PersonTagContainsKeywordsPredicate(tagKeywords),
                        new AddressContainsKeywordsPredicate(addressKeywords),
                        returnTruePredicate,
                        returnTruePredicate, returnTruePredicate, returnTruePredicate),
                command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_remark() throws Exception {
        assertTrue(parser.parseCommand(RemarkCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased()
                + " " + PREFIX_REMARK + VALID_REMARK_BOB) instanceof RemarkCommand);
        assertTrue(parser.parseCommand(RemarkCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased()
                + " " + PREFIX_REMARK + VALID_REMARK_JANE) instanceof RemarkCommand);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " "
                + VALID_SORT_DIRECTION_ASCENDING) instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " "
                + VALID_SORT_DIRECTION_DESCENDING) instanceof SortCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}

package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCommandAliases.getTypicalAlias;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.VALID_INDEXES;
import static seedu.address.testutil.TypicalIndexes.VALID_INDEXES_STRING;
import static seedu.address.testutil.TypicalIndexes.VALID_INDEX_STRING;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAliasCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddTagCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteAliasCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EmailCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListAliasCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SelectClearCommand;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.commands.SelectIndexCommand;
import seedu.address.logic.commands.SelectShowCommand;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.DisplayFilterPredicate;
import seedu.address.model.UniqueAliasMap;
import seedu.address.model.alias.CommandAlias;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.FieldsContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.RemarkContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;
import seedu.address.testutil.CommandAliasBuilder;
import seedu.address.testutil.CommandAliasUtil;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();
    private final UniqueAliasMap emptyAliases = new UniqueAliasMap();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person), emptyAliases);
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD, emptyAliases) instanceof ClearCommand);
        assertTrue(parser.parseCommand(
                ClearCommand.COMMAND_WORD + " 3", emptyAliases) instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased(), emptyAliases);
        assertEquals(DeleteCommand
                .buildDeleteIndexCommand(Collections.singletonList(INDEX_FIRST_PERSON)), command);

        DeleteCommand commandMultipleIndexes = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + VALID_INDEXES_STRING, emptyAliases);
        assertEquals(DeleteCommand.buildDeleteIndexCommand(VALID_INDEXES), commandMultipleIndexes);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " "
                + PersonUtil.getEditPersonDescriptorDetails(descriptor), emptyAliases);
        assertEquals(EditCommand
                        .buildEditIndexCommand(Collections.singletonList(INDEX_FIRST_PERSON), descriptor),
                command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, emptyAliases) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3", emptyAliases) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                MessageFormat.format("{0} {1}",
                        FindCommand.COMMAND_WORD,
                        keywords.stream().collect(Collectors.joining(" "))), emptyAliases);
        String s = MessageFormat.format("{0} {1}",
                FindCommand.COMMAND_WORD,
                keywords.stream().collect(Collectors.joining(" ")));
        FieldsContainsKeywordsPredicate predicate = new FieldsContainsKeywordsPredicate(keywords);
        assertEquals(new FindCommand(predicate), command);
    }

    @Test
    public void parseCommand_findName() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                MessageFormat.format("{0} {1} {2}",
                        FindCommand.COMMAND_WORD,
                        PREFIX_NAME,
                        keywords.stream().collect(Collectors.joining(" "))), emptyAliases);
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(keywords);
        assertEquals(new FindCommand(predicate), command);
    }

    @Test
    public void parseCommand_findTag() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                MessageFormat.format("{0} {1} {2}",
                        FindCommand.COMMAND_WORD,
                        PREFIX_TAG,
                        keywords.stream().collect(Collectors.joining(" "))), emptyAliases);
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(keywords);
        assertEquals(new FindCommand(predicate), command);
    }

    @Test
    public void parseCommand_findRemark() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                MessageFormat.format("{0} {1} {2}",
                        FindCommand.COMMAND_WORD,
                        PREFIX_REMARK,
                        keywords.stream().collect(Collectors.joining(" "))), emptyAliases);
        RemarkContainsKeywordsPredicate predicate = new RemarkContainsKeywordsPredicate(keywords);
        assertEquals(new FindCommand(predicate), command);
    }

    @Test
    public void parseCommand_findEmail() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                MessageFormat.format("{0} {1} {2}",
                        FindCommand.COMMAND_WORD,
                        PREFIX_EMAIL,
                        keywords.stream().collect(Collectors.joining(" "))), emptyAliases);
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate(keywords);
        assertEquals(new FindCommand(predicate), command);
    }

    @Test
    public void parseCommand_filter() throws Exception {
        final List<String> keywords = Arrays.asList(PREFIX_ADDRESS.toString(), PREFIX_PHONE.toString(),
                PREFIX_EMAIL.toString());
        ArgumentMultimap argumentMultimap = new ArgumentMultimap();
        argumentMultimap.put(PREFIX_ADDRESS, "");
        argumentMultimap.put(PREFIX_PHONE, "");
        argumentMultimap.put(PREFIX_EMAIL, "");
        final List<String> prefixes = Arrays
                .asList(PREFIX_ADDRESS.getPrefix(), PREFIX_PHONE.getPrefix(),
                        PREFIX_EMAIL.getPrefix());
        DisplayFilterPredicate displayFilterPredicate = new DisplayFilterPredicate(argumentMultimap);
        FilterCommand command = (FilterCommand) parser.parseCommand(FilterCommand.COMMAND_WORD + " "
                + keywords.stream().collect(Collectors.joining(" ")), emptyAliases);
        assertEquals(new FilterCommand(displayFilterPredicate, prefixes), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, emptyAliases) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3", emptyAliases) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD, emptyAliases) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3", emptyAliases) instanceof ListCommand);
    }

    @Test
    public void parseCommand_email() throws Exception {
        assertTrue(parser.parseCommand(
                EmailCommand.COMMAND_WORD + " " + EmailCommandParser.SPECIAL_INDEX,
                emptyAliases) instanceof EmailCommand);
        assertTrue(parser.parseCommand(
                EmailCommand.COMMAND_WORD + " " + EmailCommandParser.SELECTED,
                emptyAliases) instanceof EmailCommand);
        assertTrue(parser.parseCommand(EmailCommand.COMMAND_WORD + " " + VALID_INDEXES_STRING,
                emptyAliases) instanceof EmailCommand);
        assertTrue(parser.parseCommand(EmailCommand.COMMAND_WORD + " " + VALID_INDEX_STRING,
                emptyAliases) instanceof EmailCommand);
    }

    @Test
    public void parseCommand_aliasAdd() throws Exception {
        CommandAlias commandAlias = new CommandAliasBuilder().build();
        AddAliasCommand command = (AddAliasCommand) parser.parseCommand(
                CommandAliasUtil.getAddAliasCommand(commandAlias), emptyAliases);
        assertEquals(new AddAliasCommand(commandAlias), command);
    }

    @Test
    public void parseCommand_aliasDelete() throws Exception {
        DeleteAliasCommand command = (DeleteAliasCommand) parser.parseCommand(DeleteAliasCommand.COMMAND_WORD
                + " " + DeleteAliasCommand.DELETE_SUB_COMMAND_WORD + " " + getTypicalAlias(), emptyAliases);
        assertEquals(new DeleteAliasCommand(getTypicalAlias()), command);
    }

    @Test
    public void parseCommand_aliasList() throws Exception {
        assertTrue(parser.parseCommand(ListAliasCommand.COMMAND_WORD + " "
                + ListAliasCommand.LIST_SUB_COMMAND_WORD, emptyAliases) instanceof ListAliasCommand);
        assertTrue(parser.parseCommand(ListAliasCommand.COMMAND_WORD + " "
                + ListAliasCommand.LIST_SUB_COMMAND_WORD + " 3", emptyAliases) instanceof ListAliasCommand);
    }

    @Test
    public void parseCommand_select() throws Exception {
        assertTrue(parser.parseCommand(
                SelectCommand.COMMAND_WORD + " " + SelectIndexCommandParser.SPECIAL_INDEX,
                emptyAliases) instanceof SelectIndexCommand);
        assertTrue(parser.parseCommand(
                SelectCommand.COMMAND_WORD + " " + VALID_INDEXES_STRING,
                emptyAliases) instanceof SelectIndexCommand);
        assertTrue(parser.parseCommand(
                SelectCommand.COMMAND_WORD + " " + SelectCommand.CLEAR_SUB_COMMAND_WORD,
                emptyAliases) instanceof SelectClearCommand);
        assertTrue(parser.parseCommand(
                SelectCommand.COMMAND_WORD + " " + SelectCommand.SHOW_SUB_COMMAND_WORD,
                emptyAliases) instanceof SelectShowCommand);
    }

    @Test
    public void parseCommand_tag() throws Exception {
        assertTrue(parser.parseCommand(TagCommand.COMMAND_WORD + " " + TagCommand.ADD_SUB_COMMAND_WORD + " "
                + VALID_INDEX_STRING + " " + TAG_DESC_FRIEND, emptyAliases) instanceof AddTagCommand);
        assertTrue(parser.parseCommand(TagCommand.COMMAND_WORD + " " + TagCommand.DELETE_SUB_COMMAND_WORD + " "
                + VALID_INDEXES_STRING + " " + TAG_DESC_HUSBAND, emptyAliases) instanceof DeleteTagCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                HelpCommand.MESSAGE_USAGE), () -> parser.parseCommand("", emptyAliases));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand(
                "unknownCommand", emptyAliases));
    }

}

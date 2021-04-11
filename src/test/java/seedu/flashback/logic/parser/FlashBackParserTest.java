package seedu.flashback.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashback.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashback.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.flashback.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.flashback.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.flashback.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.flashback.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.flashback.testutil.Assert.assertThrows;
import static seedu.flashback.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.flashback.logic.commands.AddCommand;
import seedu.flashback.logic.commands.ClearCommand;
import seedu.flashback.logic.commands.DeleteCommand;
import seedu.flashback.logic.commands.EditCommand;
import seedu.flashback.logic.commands.ExitCommand;
import seedu.flashback.logic.commands.FilterCommand;
import seedu.flashback.logic.commands.FindCommand;
import seedu.flashback.logic.commands.HelpCommand;
import seedu.flashback.logic.commands.ListCommand;
import seedu.flashback.logic.parser.exceptions.ParseException;
import seedu.flashback.model.flashcard.Flashcard;
import seedu.flashback.model.flashcard.FlashcardContainsKeywordsPredicate;
import seedu.flashback.model.flashcard.FlashcardFilterPredicate;
import seedu.flashback.testutil.EditCardDescriptorBuilder;
import seedu.flashback.testutil.FlashcardBuilder;
import seedu.flashback.testutil.FlashcardUtil;

public class FlashBackParserTest {

    private final FlashBackParser parser = new FlashBackParser();

    @Test
    public void parseCommand_add() throws Exception {
        Flashcard flashcard = new FlashcardBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(FlashcardUtil.getAddCommand(flashcard));
        assertEquals(new AddCommand(flashcard), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_FLASHCARD.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_FLASHCARD), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Flashcard flashcard = new FlashcardBuilder().build();
        EditCommand.EditCardDescriptor descriptor = new EditCardDescriptorBuilder(flashcard).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_FLASHCARD.getOneBased() + " "
                + FlashcardUtil.getEditFlashcardDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_FLASHCARD, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new FlashcardContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_filter() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");

        // Filter question
        FilterCommand command = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD + " " + PREFIX_QUESTION
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FilterCommand(new FlashcardFilterPredicate(keywords, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>())), command);

        // Filter category
        command = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD + " " + PREFIX_CATEGORY
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FilterCommand(new FlashcardFilterPredicate(new ArrayList<>(), keywords,
                new ArrayList<>(), new ArrayList<>())), command);

        // Filter priority
        command = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD + " " + PREFIX_PRIORITY
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FilterCommand(new FlashcardFilterPredicate(new ArrayList<>(), new ArrayList<>(),
                keywords, new ArrayList<>())), command);

        // Filter tag
        command = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD + " " + PREFIX_TAG
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FilterCommand(new FlashcardFilterPredicate(new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), keywords)), command);

        // Filter multiple
        command = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD + " " + PREFIX_PRIORITY
                        + keywords.stream().collect(Collectors.joining(" "))
                        + " " + PREFIX_QUESTION + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FilterCommand(new FlashcardFilterPredicate(keywords, new ArrayList<>(),
                keywords, new ArrayList<>())), command);

        // Filter all
        command = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD + " " + PREFIX_QUESTION
                        + keywords.stream().collect(Collectors.joining(" "))
                        + " " + PREFIX_TAG + keywords.stream().collect(Collectors.joining(" "))
                        + " " + PREFIX_CATEGORY + keywords.stream().collect(Collectors.joining(" "))
                        + " " + PREFIX_PRIORITY + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FilterCommand(new FlashcardFilterPredicate(keywords, keywords,
                keywords, keywords)), command);

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
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}

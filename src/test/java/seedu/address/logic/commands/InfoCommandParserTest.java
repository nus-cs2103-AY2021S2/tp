package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.InfoCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

public class InfoCommandParserTest {
    @Test
    public void arePrefixesPresentTest() {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(" m/", PREFIX_MODULE_CODE);
        assertFalse(InfoCommandParser.arePrefixesPresent(argMultimap, PREFIX_MODULE_CODE));

        argMultimap = ArgumentTokenizer.tokenize("        m/", PREFIX_MODULE_CODE);
        assertFalse(InfoCommandParser.arePrefixesPresent(argMultimap, PREFIX_MODULE_CODE));

        argMultimap = ArgumentTokenizer.tokenize(" m/;askldghl;kdshgkl;dsj;flksd;ghk;sdf", PREFIX_MODULE_CODE);
        assertTrue(InfoCommandParser.arePrefixesPresent(argMultimap, PREFIX_MODULE_CODE));

        argMultimap = ArgumentTokenizer.tokenize(" m/cs2103", PREFIX_MODULE_CODE);
        assertTrue(InfoCommandParser.arePrefixesPresent(argMultimap, PREFIX_MODULE_CODE));

        argMultimap = ArgumentTokenizer.tokenize(" m/;askldghl;kdshgkl;dsj;flksd;ghk;sdf asdfsadfsda",
                PREFIX_MODULE_CODE);
        assertTrue(InfoCommandParser.arePrefixesPresent(argMultimap, PREFIX_MODULE_CODE));

        argMultimap = ArgumentTokenizer.tokenize(" m/       ;askldghl;kdshgkl;dsj;flksd;ghk;sdf asdfsadfsda",
                PREFIX_MODULE_CODE);
        assertTrue(InfoCommandParser.arePrefixesPresent(argMultimap, PREFIX_MODULE_CODE));
    }

    @Test
    public void parseTest() {
        InfoCommandParser parser = new InfoCommandParser();
        try {
            assertEquals(parser.parse("").getModuleCode(), new InfoCommand().getModuleCode());
            assertEquals(parser.parse(" m/cs2103").getModuleCode(),
                    new InfoCommand("cs2103").getModuleCode());
            assertEquals(parser.parse(" m/       ;askldghl;kdshgkl;dsj;flksd;ghk;sdf asdfsadfsda").getModuleCode(),
                    new InfoCommand(";askldghl;kdshgkl;dsj;flksd;ghk;sdf asdfsadfsda").getModuleCode());
        } catch (ParseException p) {
            
        }
    }
}

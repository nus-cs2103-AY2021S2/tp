package seedu.partyplanet.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.partyplanet.logic.parser.exceptions.ParseException;


public class ListCommandUtilTest {

    private static final ArgumentMultimap argMap = new ArgumentMultimap();

    private static final List<String> NAMES = List.of("John Doe", "Abraham", "abraham");
    private static final List<String> TAGS = List.of("tag1", "tag2", "TAG1", "TAG2");
    private static final List<String> REMARKS = List.of("remark1", "remark2");

    @BeforeAll
    public static void setUp() {
        for (String name: NAMES) {
            argMap.put(PREFIX_NAME, name);
        }
        for (String remark: REMARKS) {
            argMap.put(PREFIX_REMARK, remark);
        }
        for (String tag: TAGS) {
            argMap.put(PREFIX_TAG, tag);
        }
    }

    @Test
    public void getParsedNames() throws Exception {
        List<String> names;
        try {
            names = ListCommandUtil.getParsedNames(argMap);
        } catch (ParseException e) {
            throw new Exception(e);
        }
        names.removeAll(NAMES);
        assertEquals(0, names.size());
    }

    @Test
    public void getParsedTags() throws Exception {
        List<String> tags;
        try {
            tags = ListCommandUtil.getParsedTags(argMap);
        } catch (ParseException e) {
            throw new Exception(e);
        }
        tags.removeAll(TAGS);
        assertEquals(0, tags.size());
    }

    @Test
    public void getParsedRemarks() throws Exception {
        List<String> remarks;
        try {
            remarks = ListCommandUtil.getParsedRemarks(argMap);
        } catch (ParseException e) {
            throw new Exception(e);
        }
        remarks.removeAll(REMARKS);
        assertEquals(0, remarks.size());
    }
}

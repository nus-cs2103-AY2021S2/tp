package seedu.partyplanet.logic.parser;

import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.stream.Collectors;

import seedu.partyplanet.logic.parser.exceptions.ParseException;

/**
 * A utility class for common methods and formats used in ListCommandParser and EListCommandParser.
 */
public class ListCommandUtil {

    /**
     * Returns a list of parsed name strings from the argument map.
     */
    public static List<String> getParsedNames(ArgumentMultimap argMap) throws ParseException {
        List<String> names = argMap.getAllValues(PREFIX_NAME);
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            names.set(i, ParserUtil.parseName(name).fullName);
        }
        return names;
    }

    /**
     * Returns a list of parsed tag strings from the argument map.
     */
    public static List<String> getParsedTags(ArgumentMultimap argMap) throws ParseException {
        List<String> tags = argMap.getAllValues(PREFIX_TAG);
        return ParserUtil.parseTags(tags)
                .stream()
                .map(x -> x.tagName)
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of parsed remarks from the argument map.
     */
    public static List<String> getParsedRemarks(ArgumentMultimap argMap) throws ParseException {
        List<String> remarks = argMap.getAllValues(PREFIX_REMARK);
        for (int i = 0; i < remarks.size(); i++) {
            String remark = remarks.get(i);
            remarks.set(i, ParserUtil.parseRemark(remark).value);
        }
        return remarks;
    }

    /**
     * Generates output string for each list searching criteria.
     * e.g. {@code critera} = 'exact name', {@code criteriaValues} = ['Alice', 'Bob', ...]
     */
    public static String getCriteriaString(String criteria, List<String> criteriaValues) {
        return "\n\u2022 Requires " + criteria + ": " + String.join(", ", criteriaValues);
    }
}

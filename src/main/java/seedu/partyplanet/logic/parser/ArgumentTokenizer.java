package seedu.partyplanet.logic.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Tokenizes arguments string of the form: {@code preamble <prefix> value <prefix> value ...}<br>
 *     e.g. {@code some preamble text -t 11.00 -t 12.00 -k -m July}  where prefixes are {@code -t -k -m}.<br>
 * 1. An argument's value can be an empty string e.g. the value of {@code -k} in the above example.<br>
 * 2. Leading and trailing whitespaces of an argument value will be discarded.<br>
 * 3. An argument may be repeated and all its values will be accumulated e.g. the value of {@code -t}
 *    in the above example.<br>
 */
public class ArgumentTokenizer {

    /**
     * Tokenizes an arguments string and returns an {@code ArgumentMultimap} object that maps prefixes to their
     * respective argument values. Only the given prefixes will be recognized in the arguments string.
     *
     * @param argsString Arguments string of the form: {@code preamble <prefix> value <prefix> value ...}
     * @param prefixes   Prefixes to tokenize the arguments string with
     * @return           ArgumentMultimap object that maps prefixes to their arguments
     */
    public static ArgumentMultimap tokenize(String argsString, Prefix... prefixes) {
        List<PrefixPosition> positions = findAllPrefixPositions(argsString, prefixes);
        return extractArguments(argsString, positions);
    }

    /**
     * Finds all zero-based prefix positions in the given arguments string.
     *
     * @param argsString Arguments string of the form: {@code preamble <prefix> value <prefix> value ...}
     * @param prefixes   Prefixes to find in the arguments string
     * @return           List of zero-based prefix positions in the given arguments string
     */
    private static List<PrefixPosition> findAllPrefixPositions(String argsString, Prefix... prefixes) {
        return Arrays.stream(prefixes)
                .flatMap(prefix -> findPrefixPositions(argsString, prefix).stream())
                .collect(Collectors.toList());
    }

    /**
     * {@see findAllPrefixPositions}
     */
    private static List<PrefixPosition> findPrefixPositions(String argsString, Prefix prefix) {
        List<PrefixPosition> positions = new ArrayList<>();

        PrefixPosition extendedPrefix = findPrefixPosition(argsString, prefix, 0);
        while (extendedPrefix != null) {
            positions.add(extendedPrefix);
            extendedPrefix = findPrefixPosition(argsString, prefix, extendedPrefix.getStartPosition());
        }

        return positions;
    }

    /**
     * Returns the index of the first occurrence of any prefix string from {@code prefix} in
     * {@code argsString} starting from index {@code fromIndex}, including the
     * preceding whitespace. An occurrence
     * is valid if there is both a whitespace preceding and following {@code prefix},
     * or if the following whitespace is replaced by the end of string.
     * Returns null if no such occurrence can be found.
     *
     * e.g Assume {@code fromIndex} = 0, {@code prefix} = "-p, --prefix":
     *
     * - {@code argsString} = "-e abc-d" returns -1
     * - {@code argsString} = "-p abc-d" returns -1
     * - {@code argsString} = " -p abc-d" returns 1
     * - {@code argsString} = " -pp abc-d" returns -1
     * - {@code argsString} = "-e abc-d -p -d" returns 9
     * - {@code argsString} = "-e abc-d -p" returns 9
     * - {@code argsString} = "-e abc-d --prefix" returns 9
     */
    private static PrefixPosition findPrefixPosition(String argsString, Prefix prefix, int fromIndex) {
        int minPrefixIndex = argsString.length();
        String minPrefixString = null;

        // Inevitable loop over possible prefixes due to recursive nature of search in `findPrefixPositions`.
        // Since spaces between prefixes are required, to change to split -> iterative find method.
        for (String prefixString: prefix.getPrefixes()) {
            int prefixIndex = argsString.indexOf(" " + prefixString, fromIndex);
            if (prefixIndex != -1 && prefixIndex < minPrefixIndex) {
                minPrefixIndex = prefixIndex;
                minPrefixString = prefixString;
            }
        }
        if (minPrefixString == null) {
            return null; // invalid index
        }

        int trailingIndex = minPrefixIndex + 1 + minPrefixString.length(); // index of character after prefix
        if (trailingIndex == argsString.length() || argsString.charAt(trailingIndex) == ' ') {
            // valid trailing character, +1 for leading whitespace
            return new PrefixPosition(minPrefixString, prefix, minPrefixIndex + 1);
        }
        return null;
    }

    /**
     * Extracts prefixes and their argument values, and returns an {@code ArgumentMultimap} object that maps the
     * extracted prefixes to their respective arguments. Prefixes are extracted based on their zero-based positions in
     * {@code argsString}.
     *
     * @param argsString      Arguments string of the form: {@code preamble <prefix> value <prefix> value ...}
     * @param prefixPositions Zero-based positions of all prefixes in {@code argsString}
     * @return                ArgumentMultimap object that maps prefixes to their arguments
     */
    private static ArgumentMultimap extractArguments(String argsString, List<PrefixPosition> prefixPositions) {

        // Sort prefixes by position to allow calculation of prefix length
        prefixPositions.sort((prefix1, prefix2) -> prefix1.getStartPosition() - prefix2.getStartPosition());

        // Add PrefixPositions at start and end to represent preamble and end of string
        PrefixPosition preambleMarker = new PrefixPosition("", new Prefix(), 0);
        prefixPositions.add(0, preambleMarker);
        PrefixPosition endPositionMarker = new PrefixPosition("", new Prefix(), argsString.length());
        prefixPositions.add(endPositionMarker);

        // Create a map of prefixes to argument (if any)
        ArgumentMultimap argMultimap = new ArgumentMultimap();
        for (int i = 0; i < prefixPositions.size() - 1; i++) {
            Prefix argPrefix = prefixPositions.get(i).getPrefix();
            String argValue = extractArgumentValue(argsString, prefixPositions.get(i), prefixPositions.get(i + 1));
            argMultimap.put(argPrefix, argValue);
        }

        return argMultimap;
    }

    /**
     * Returns the trimmed value of the argument in the arguments string specified by {@code currentPrefixPosition}.
     * The end position of the value is determined by {@code nextPrefixPosition}.
     */
    private static String extractArgumentValue(String argsString,
                                        PrefixPosition currentPrefixPosition,
                                        PrefixPosition nextPrefixPosition) {
        String prefixString = currentPrefixPosition.getPrefixString();

        int valueStartPos = currentPrefixPosition.getStartPosition() + prefixString.length();
        String value = argsString.substring(valueStartPos, nextPrefixPosition.getStartPosition());

        return value.trim();
    }

    /**
     * Represents a prefix's position in an arguments string.
     */
    private static class PrefixPosition {
        private final int startPosition;
        private final String prefixString;
        private final Prefix prefix;

        PrefixPosition(String prefixString, Prefix prefix, int startPosition) {
            this.prefixString = prefixString;
            this.prefix = prefix;
            this.startPosition = startPosition;
        }

        int getStartPosition() {
            return startPosition;
        }

        String getPrefixString() {
            return prefixString;
        }

        Prefix getPrefix() {
            return prefix;
        }
    }

}

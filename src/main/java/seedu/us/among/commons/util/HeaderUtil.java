package seedu.us.among.commons.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import seedu.us.among.logic.request.exceptions.RequestException;
import seedu.us.among.model.header.Header;

/**
 * Helper function for retrieving key-value from header strings.
 */
public class HeaderUtil {
    public static final String MESSAGE_INVALID_HEADER_FORMAT = "There was an error parsing the header."
            + " Please check that your header specified is in the correct format.";

    /**
     * Converts hashset of headers to a hashmap of header key-value pair.
     *
     * @param headerSet set of headers from endpoint
     * @return hashmap of header key-value pair
     */
    public static Map<String, String> parseHeaders(Set<Header> headerSet) throws RequestException {
        Map<String, String> headerMap = new HashMap<>();
        for (Header header : headerSet) {
            String headerString = header.toString();
            String[] headerPair = headerString.split(":", 2);
            if (headerPair.length != 2) {
                throw new RequestException(MESSAGE_INVALID_HEADER_FORMAT);
            }
            headerMap.put(headerPair[0].trim(), headerPair[1].trim());
        }
        return headerMap;
    }
}

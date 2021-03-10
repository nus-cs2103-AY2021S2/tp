package seedu.us.among.commons.util;

import java.util.HashMap;
import java.util.Set;

import seedu.us.among.model.endpoint.header.Header;

/**
 * Helper function for retrieving key-value from header strings.
 */
public class HeaderUtil {

    /**
     * Converts hashset of headers to a hashmap of header key-value pair.
     *
     * @param headerSet set of headers from endpoint
     * @return hashmap of header key-value pair
     */
    public static HashMap<String, String> parseHeaders(Set<Header> headerSet) {
        HashMap<String, String> headerMap = new HashMap<>();
        for (Header header : headerSet) {
            String headerString = header.toString();
            String[] headerPair = headerString.split(":", 2);
            assert headerPair.length == 2;
            headerMap.put(headerPair[0].trim(), headerPair[1].trim());
        }
        return headerMap;
    }
}

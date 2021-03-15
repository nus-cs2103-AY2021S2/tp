package seedu.us.among.model.endpoint;

import java.util.HashMap;
import java.util.Map;

//Solution below adapted from
//https://www.stubbornjava.com/posts/java-enum-lookup-by-name-or-field-without-throwing-exceptions
public enum MethodType {
    GET, POST, PUT, DELETE, HEAD, OPTIONS, PATCH;

    private static final Map<String, MethodType> methodIndex = new HashMap<>();

    static {
        for (MethodType method : MethodType.values()) {
            methodIndex.put(method.name(), method);
        }
    }

    public static boolean getIfPresent(String method) {
        return getMethod(method) != null;
    }

    public static MethodType getMethod(String method) {
        return methodIndex.get(method.toUpperCase());
    }
}

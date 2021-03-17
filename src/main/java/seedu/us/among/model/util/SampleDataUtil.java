package seedu.us.among.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.us.among.model.EndpointList;
import seedu.us.among.model.ReadOnlyEndpointList;
import seedu.us.among.model.endpoint.Address;
import seedu.us.among.model.endpoint.Data;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Method;
import seedu.us.among.model.endpoint.Response;
import seedu.us.among.model.endpoint.header.Header;
import seedu.us.among.model.tag.Tag;

/**
 * Contains utility methods for populating {@code EndpointList} with sample
 * data.
 */
public class SampleDataUtil {
    public static Endpoint[] getSampleEndpoint() {
        return new Endpoint[] {
            new Endpoint(new Method("GET"),
                    new Address("https://api.github.com/repos/AY2021S2-CS2103T-T12-4/tp/readme"),
                    getHeaderSet("\"Accept: application/vnd.github.v3+json\""),
                    getTagSet("github", "interesting"), new Response()),
            new Endpoint(new Method("GET"),
                    new Address("https://api.github.com/users/AY2021S2-CS2103T-T12-4/repos"),
                    getHeaderSet("\"Accept: application/vnd.github.v3+json\""),
                    getTagSet("github", "interesting"), new Response()),
            new Endpoint(new Method("GET"),
                    new Address("https://api.github.com/repos/AY2021S2-CS2103T-T12-4/tp/stats/contributors"),
                    getHeaderSet("\"Accept: application/vnd.github.v3+json\""),
                    getTagSet("github", "interesting"), new Response()),
            new Endpoint(new Method("GET"),
                    new Address("https://api.data.gov.sg/v1/environment/2-hour-weather-forecast"),
                    getHeaderSet("\"Accept: application/json\""), getTagSet("singapore"), new Response()),
            new Endpoint(new Method("GET"), new Address("https://api.data.gov.sg/v1/transport/taxi-availability"),
                    getHeaderSet("\"Accept: application/vnd.geo+json\""),
                    getTagSet("singapore", "transport"), new Response()),
            new Endpoint(new Method("GET"),
                    new Address("https://cat-fact.herokuapp.com/facts/random?animal_type=cat&amount=2"),
                    getHeaderSet("\"Content-Type: application/json\""),
                    getTagSet("cats"), new Response()),
            new Endpoint(new Method("GET"),
                    new Address("https://reqres.in/api/users?delay=3"),
                    getHeaderSet("\"Content-Type: application/json\""),
                    getTagSet("delay"), new Response()),
            new Endpoint(new Method("GET"),
                    new Address("https://the-one-api.dev/v2/book"), getHeaderSet("\"Content-Type: application/json\""),
                    getTagSet("book", "story"), new Response()),
            new Endpoint(new Method("POST"),
                    new Address("https://reqres.in/api/users"),
                    new Data("{\"name\": \"tarzan\", \"job\": \"the jungle man\"}"),
                    getHeaderSet("\"Content-Type: application/json\""),
                    getTagSet("nature"), new Response()),
            new Endpoint(new Method("POST"),
                    new Address("https://reqres.in/api/register"),
                    new Data("{\"email\": \"eve.holt@reqres.in\"}"), getHeaderSet("\"Content-Type: application/json\""),
                    getTagSet("failed", "registration"), new Response()),
            new Endpoint(new Method("POST"),
                    new Address("https://reqres.in/api/register"),
                    new Data("{\"email\": \"eve.holt@reqres.in\", \"password\": \"not-secure\"}"),
                    getHeaderSet("\"Content-Type: application/json\""),
                    getTagSet("successful", "registration"), new Response())
        };
    }

    public static ReadOnlyEndpointList getSampleEndpointList() {
        EndpointList sampleAb = new EndpointList();
        for (Endpoint sampleEndpoint : getSampleEndpoint()) {
            sampleAb.addEndpoint(sampleEndpoint);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings).map(Tag::new).collect(Collectors.toSet());
    }

    /**
     * Returns a header set containing the list of strings given.
     */
    public static Set<Header> getHeaderSet(String... strings) {
        return Arrays.stream(strings).map(Header::new).collect(Collectors.toSet());
    }

    /**
     * Returns a valid endpoint containing response result.
     */
    public static Endpoint getSampleValidEndpoint() {
        String responseEntity = "{\"data\":{\"id\":2,\"email\":\"janet.weaver@reqres.in\",\"first_name\":"
                + "\"Janet\","
                + "\"last_name\":\"Weaver\",\"avatar\":\"https://reqres.in/img/faces/2-image.jpg\"},"
                + "\"support\":{\"url\":\"https://reqres.in/#support-heading\",\"text\":\"To keep ReqRes"
                + " free, contributions towards server costs are appreciated!\"}}";
        return new Endpoint(new Method("GET"), new Address("https://reqres.in/api/users/2"),
                new Data(),
                getHeaderSet("\"Content-Type: application/json\""),
                getTagSet(),
                new Response("", "200", "OK",
                        "", responseEntity, ""));
    }

    /**
     * Returns sample response from a valid endpoint.
     */
    public static String getSampleValidResponse() {
        return "{\"data\":{\"id\":2,\"email\":\"janet.weaver@reqres.in\",\"first_name\":"
                + "\"Janet\","
                + "\"last_name\":\"Weaver\",\"avatar\":\"https://reqres.in/img/faces/2-image.jpg\"},"
                + "\"support\":{\"url\":\"https://reqres.in/#support-heading\",\"text\":\"To keep ReqRes"
                + " free, contributions towards server costs are appreciated!\"}}";
    }

    /**
     * Returns sample invalid url endpoint.
     */
    public static Endpoint getSampleInvalidUrlEndpoint(String method) {
        return new Endpoint(new Method(method), new Address("https://thisisatotallyinvalidendpointtotest"),
                new Data(),
                getHeaderSet("\"Content-Type: application/json\""),
                getTagSet(),
                new Response("", "", "",
                        "", "", ""));
    }

    /**
     * Returns sample valid url endpoint but with html response when executed.
     */
    public static Endpoint getSampleHtmlResponseEndpoint(String method) {
        return new Endpoint(new Method(method), new Address("https://google.com"),
                new Data(),
                getHeaderSet("\"Content-Type: application/json\""),
                getTagSet(),
                new Response("", "", "",
                        "", "", ""));
    }

}

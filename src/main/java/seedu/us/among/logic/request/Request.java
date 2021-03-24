package seedu.us.among.logic.request;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import seedu.us.among.commons.util.HeaderUtil;
import seedu.us.among.commons.util.JsonUtil;
import seedu.us.among.commons.util.StringUtil;
import seedu.us.among.logic.request.exceptions.RequestException;
import seedu.us.among.model.endpoint.Data;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.MethodType;
import seedu.us.among.model.endpoint.Response;
import seedu.us.among.model.header.Header;

/**
 * Parent class of request sending classes. Contains the two compulsory fields method and address.
 */
public abstract class Request {
    public static final int CONVERT_NANO_SECONDS = 1_000_000_000;
    private static CloseableHttpClient httpclient = createHttpClient();
    private static final int TIMEOUT = 60 * 1000;

    private final MethodType method;
    private final String address;
    private final Set<Header> headers;
    private final Data data;

    /**
     * Constructor for Request.
     *
     * @param endpoint endpoint to make API call on
     */
    public Request(Endpoint endpoint) {
        this.method = endpoint.getMethodType();
        this.address = endpoint.getAddress().value;
        this.headers = endpoint.getHeaders();
        this.data = endpoint.getData();
    }

    public String getAddress() {
        return this.address;
    }

    public Set<Header> getHeaders() {
        return this.headers;
    }

    public Data getData() {
        return this.data;
    }

    public MethodType getMethod() {
        return this.method;
    }

    public static CloseableHttpClient getHttpclient() {
        return httpclient;
    }

    /**
     * Executes the API call by sending the appropriate request.
     *
     * @return returns the response from the API call
     */
    public abstract Response send() throws IOException, RequestException;

    /**
     * Creates a http client with timeout set to 60 seconds.
     *
     * @return http client to execute request
     */
    private static CloseableHttpClient createHttpClient() {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(TIMEOUT)
                .setConnectionRequestTimeout(TIMEOUT)
                .setSocketTimeout(TIMEOUT).build();
        return HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }

    /**
     * Executes API call.
     *
     * @param request request to execute
     * @return response from api call
     */
    public Response execute(HttpUriRequest request) throws IOException {
        return executeTimed(request);
    }

    private String formatEntity(HttpEntity entity) throws IOException {
        String responseEntity = "";
        if (entity != null) {
            responseEntity = EntityUtils.toString(entity);
            if (entity.getContentType().getValue().toLowerCase().contains("application/json")) {
                responseEntity = JsonUtil.toPrettyPrintJsonString(responseEntity);
            }
        }
        return responseEntity;
    }

    /**
     * Executes API call and records the time taken to execute the call.
     *
     * @param request request to execute
     * @return response from api call
     */
    private Response executeTimed(HttpUriRequest request) throws IOException {
        // double try is needed because we want to time the execution as accurately as possible
        // also that the following will auto close httpClient and response
        try (CloseableHttpClient httpClient = createHttpClient()) {
            Request.httpclient = httpClient;
            long start = System.nanoTime();
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                long end = System.nanoTime();
                double responseTimeInSecond = (double) (end - start) / CONVERT_NANO_SECONDS;

                HttpEntity entity = response.getEntity();
                String responseEntity = formatEntity(entity);

                return new Response(response.getProtocolVersion().toString(),
                        String.valueOf(response.getStatusLine().getStatusCode()),
                        response.getStatusLine().getReasonPhrase(),
                        response.getStatusLine().toString(),
                        responseEntity,
                        StringUtil.getResponseTimeInString(responseTimeInSecond));
            }
        }
    }

    /**
     * Sets header for given request.
     *
     * @param request request to set header for
     * @param headerSet set of headers from endpoint
     * @return request with headers set
     */
    public HttpUriRequest setHeaders(HttpUriRequest request, Set<Header> headerSet) throws RequestException {
        Map<String, String> headerMap = HeaderUtil.parseHeaders(headerSet);
        for (Map.Entry<String, String> headerPair : headerMap.entrySet()) {
            request.addHeader(headerPair.getKey(), headerPair.getValue());
        }
        return request;
    }

    /**
     * Sets data for given request.
     *
     * @param request request to set data for
     * @param data data from endpoint
     * @return request with data set
     */
    public HttpUriRequest setData(HttpUriRequest request, Data data) throws IOException {
        StringEntity entity = new StringEntity(data.value);
        entity.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        if (request instanceof HttpPost) {
            HttpPost postRequest = (HttpPost) request;
            postRequest.setEntity(entity);
        } else if (request instanceof HttpPut) {
            HttpPut putRequest = (HttpPut) request;
            putRequest.setEntity(entity);
        } else {
            HttpPatch patchRequest = (HttpPatch) request;
            patchRequest.setEntity(entity);
        }
        return request;
    }
}

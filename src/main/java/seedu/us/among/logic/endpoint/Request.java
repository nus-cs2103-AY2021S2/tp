package seedu.us.among.logic.endpoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import seedu.us.among.commons.util.HeaderUtil;
import seedu.us.among.commons.util.JsonUtil;
import seedu.us.among.commons.util.StringUtil;
import seedu.us.among.logic.endpoint.exceptions.RequestException;
import seedu.us.among.model.endpoint.Data;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.MethodType;
import seedu.us.among.model.endpoint.Response;
import seedu.us.among.model.endpoint.header.Header;

/**
 * Parent class of request sending classes. Contains the two compulsory fields method and address.
 */
public abstract class Request {
    private static CloseableHttpClient httpclient = HttpClients.createDefault();
    private static final int timeout = 60;

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
        this.method = endpoint.getMethod().getMethodType();
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
    private CloseableHttpClient createHttpClient() {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000).build();
        return HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }

    /**
     * Executes API call.
     *
     * @param request request to execute
     * @return response from api call
     */
    public Response execute(HttpUriRequest request) throws IOException {
        //solution adapted from https://mkyong.com/java/apache-httpclient-examples/
        CloseableHttpClient httpClient = createHttpClient();
        Request.httpclient = httpClient;
        CloseableHttpResponse response;
        String responseEntity = "";

        double responseTimeInSecond;
        long start = System.nanoTime();
        response = httpClient.execute(request);
        long end = System.nanoTime();
        long duration = end - start;
        responseTimeInSecond = (double) duration / 1_000_000_000;

        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //return data as string
            responseEntity = EntityUtils.toString(entity);
            if (entity.getContentType().getValue().toLowerCase().contains("application/json")) {
                responseEntity = JsonUtil.toPrettyPrintJsonString(responseEntity);
            }
        }

        response.close();
        httpClient.close();

        return new Response(response.getProtocolVersion().toString(),
                String.valueOf(response.getStatusLine().getStatusCode()),
                response.getStatusLine().getReasonPhrase(),
                response.getStatusLine().toString(),
                responseEntity,
                StringUtil.getResponseTimeInString(responseTimeInSecond));
    }

    /**
     * Sets header for given request.
     *
     * @param request request to set header for
     * @param headerSet set of headers from endpoint
     * @return request with headers set
     */
    public HttpUriRequest setHeaders(HttpUriRequest request, Set<Header> headerSet) throws RequestException {
        HashMap<String, String> headerMap = HeaderUtil.parseHeaders(headerSet);
        for (HashMap.Entry<String, String> headerPair : headerMap.entrySet()) {
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
        //strange checkstyle requirement for separator wrap due to casting, using print statement to bypass???
        if (request instanceof HttpPost) {
            System.out.println("Set Data"); ((HttpPost) request)
                    .setEntity(entity);
        } else {
            System.out.println("Set Data"); ((HttpPut) request)
                    .setEntity(entity);
        }
        return request;
    }
}

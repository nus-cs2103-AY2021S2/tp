package seedu.us.among.logic.endpoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import seedu.us.among.commons.util.HeaderUtil;
import seedu.us.among.commons.util.JsonUtil;
import seedu.us.among.commons.util.StringUtil;
import seedu.us.among.model.endpoint.Data;
import seedu.us.among.model.endpoint.MethodType;
import seedu.us.among.model.endpoint.Response;
import seedu.us.among.model.endpoint.header.Header;

/**
 * Parent class of request sending classes. Contains the two compulsory fields method and address.
 */
public abstract class Request {
    private static final int timeout = 60;

    private final MethodType method;
    private final String address;

    /**
     * Constructor for Request.
     *
     * @param method request method
     * @param address request address
     */
    public Request(MethodType method, String address) {
        this.method = method;
        this.address = address;
    }

    public MethodType getMethodType() {
        return this.method;
    }

    public String getAddress() {
        return this.address;
    }

    /**
     * Executes the API call by sending the appropriate request.
     *
     * @return returns the response from the API call
     */
    public abstract Response send() throws IOException;

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
        CloseableHttpResponse response;
        String responseEntity = "";
        double responseTimeInSecond;
        try {
            long start = System.nanoTime();
            response = httpClient.execute(request);
            long end = System.nanoTime();
            long duration = end - start;
            responseTimeInSecond = (double) duration / 1_000_000_000;

            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    //return data as string
                    responseEntity = EntityUtils.toString(entity);
                    responseEntity = JsonUtil.toPrettyPrintJsonString(responseEntity);
                }

            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }

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
    public HttpUriRequest setHeaders(HttpUriRequest request, Set<Header> headerSet) {
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
        //strange checkstyle requirement for separator wrap due to casting
        entity.setContentType(ContentType.APPLICATION_JSON.getMimeType()); ((HttpPost) request)
                .setEntity(entity);
        return request;
    }
}

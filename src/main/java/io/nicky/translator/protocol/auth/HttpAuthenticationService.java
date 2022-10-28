package io.nicky.auth;

import com.google.common.base.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.Map;

/**
 * The type Http authentication service.
 */
public abstract class HttpAuthenticationService extends BaseAuthenticationService {
    private final Proxy proxy;

    /**
     * Instantiates a new Http authentication service.
     *
     * @param proxy the proxy
     */
    protected HttpAuthenticationService(final Proxy proxy) {
        super();
        Validate.notNull((Object) proxy);
        this.proxy = proxy;
    }

    /**
     * Gets proxy.
     *
     * @return the proxy
     */
    public Proxy getProxy() {
        return this.proxy;
    }

    /**
     * Create url connection http url connection.
     *
     * @param url the url
     * @return the http url connection
     * @throws IOException the io exception
     */
    protected HttpURLConnection createUrlConnection(final URL url) throws IOException {
        Validate.notNull((Object) url);
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection(this.proxy);
        connection.setConnectTimeout(15000);
        connection.setReadTimeout(15000);
        connection.setUseCaches(false);
        return connection;
    }

    /**
     * Perform post request string.
     *
     * @param url         the url
     * @param post        the post
     * @param contentType the content type
     * @return the string
     * @throws IOException the io exception
     */
    public String performPostRequest(final URL url, final String post, final String contentType) throws IOException {
        Validate.notNull((Object) url);
        Validate.notNull((Object) post);
        Validate.notNull((Object) contentType);
        final HttpURLConnection connection = this.createUrlConnection(url);
        final byte[] postAsBytes = post.getBytes(Charsets.UTF_8);
        connection.setRequestProperty("Content-Type", contentType + "; charset=utf-8");
        connection.setRequestProperty("Content-Length", "" + postAsBytes.length);
        connection.setDoOutput(true);
        OutputStream outputStream = null;
        try {
            outputStream = connection.getOutputStream();
            IOUtils.write(postAsBytes, outputStream);
        } finally {
            IOUtils.closeQuietly(outputStream);
        }
        InputStream inputStream = null;
        try {
            inputStream = connection.getInputStream();
            return IOUtils.toString(inputStream, Charsets.UTF_8);
        } catch (IOException e) {
            IOUtils.closeQuietly(inputStream);
            inputStream = connection.getErrorStream();
            if (inputStream != null) {
                return IOUtils.toString(inputStream, Charsets.UTF_8);
            }
            throw e;
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * Perform get request string.
     *
     * @param url the url
     * @return the string
     * @throws IOException the io exception
     */
    public String performGetRequest(final URL url) throws IOException {
        Validate.notNull((Object) url);
        final HttpURLConnection connection = this.createUrlConnection(url);
        InputStream inputStream = null;
        try {
            inputStream = connection.getInputStream();
            return IOUtils.toString(inputStream, Charsets.UTF_8);
        } catch (IOException e) {
            IOUtils.closeQuietly(inputStream);
            inputStream = connection.getErrorStream();
            if (inputStream != null) {
                return IOUtils.toString(inputStream, Charsets.UTF_8);
            }
            throw e;
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * Constant url url.
     *
     * @param url the url
     * @return the url
     */
    public static URL constantURL(final String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException ex) {
            throw new Error("Couldn't create constant for " + url, ex);
        }
    }

    /**
     * Build query string.
     *
     * @param query the query
     * @return the string
     */
    public static String buildQuery(final Map<String, Object> query) {
        if (query == null) {
            return "";
        }
        final StringBuilder builder = new StringBuilder();
        for (final Map.Entry<String, Object> entry : query.entrySet()) {
            if (builder.length() > 0) {
                builder.append('&');
            }
            try {
                builder.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            } catch (UnsupportedEncodingException ignored) {
            }
            if (entry.getValue() != null) {
                builder.append('=');
                try {
                    builder.append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
                } catch (UnsupportedEncodingException ignored) {
                }
            }
        }
        return builder.toString();
    }

    /**
     * Concatenate url url.
     *
     * @param url   the url
     * @param query the query
     * @return the url
     */
    public static URL concatenateURL(final URL url, final String query) {
        try {
            if (url.getQuery() != null && url.getQuery().length() > 0) {
                return new URL(url.getProtocol(), url.getHost(), url.getPort(), url.getFile() + "&" + query);
            }
            return new URL(url.getProtocol(), url.getHost(), url.getPort(), url.getFile() + "?" + query);
        } catch (MalformedURLException ex) {
            throw new IllegalArgumentException("Could not concatenate given URL with GET arguments!", ex);
        }
    }

}

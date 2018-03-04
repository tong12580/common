package com.jokers.common.http;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * https客户端
 *
 * @author yuton
 * @version 1.0
 * @since 2017/5/30 14:24
 */
@Slf4j
class HttpsClientPoolThread {
    private static final String HTTP_CONNECTION = "http";
    private static final String HTTPS_CONNECTION = "https";
    private static final int MAX_TOTAL = 200;
    private static final int MAX_CON_PER_ROUTE = 20;
    private static final int TIME_OUT = 10 * 1000;
    private static final String USER_AGENT_MSG = "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2)";
    private static final String ACCEPT_LANGUAGE_MSG = "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3";
    private static final String ACCEPT_CHARSET_MSG = "GB2312,utf-8;q=0.7,*;q=0.7";
    private static final String ACCEPT_CHARSET = "Accept-Charset";
    private static final String CONTENT_TYPE_JSON = "application/json;charset=utf-8";
    private static final String ACCEPT_LANGUAGE = "Accept-Language";
    private static final String ACCEPT_ENCODING = "Accept-Encoding";
    private static final String ACCEPT_ENCODING_MSG = "gzip,deflate";
    private static volatile HttpsClientPoolThread httpsClientPoolThread;

    private HttpsClientPoolThread() {
    }

    static HttpsClientPoolThread builder() {
        if (null == httpsClientPoolThread) {
            synchronized (HttpsClientPoolThread.class) {
                if (null == httpsClientPoolThread) {
                    httpsClientPoolThread = new HttpsClientPoolThread();
                }
            }
        }
        return httpsClientPoolThread;
    }


    /**
     * 使用连接池创建Client实体
     * 支持SSL
     * 添加一个默认的Header
     *
     * @return CloseableHttpClient
     */
    CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder()
                    .loadTrustMaterial(null, (chain, authType) -> true)
                    .build();
            SSLConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(sslContext);
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                    .<ConnectionSocketFactory>create()
                    .register(HTTP_CONNECTION, PlainConnectionSocketFactory.getSocketFactory())
                    .register(HTTPS_CONNECTION, sslSF)
                    .build();
            SocketConfig socketConfig = SocketConfig
                    .custom()
                    .setSoTimeout(TIME_OUT)
                    .build();
            PoolingHttpClientConnectionManager poolConnManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            poolConnManager.setMaxTotal(MAX_TOTAL);
            poolConnManager.setDefaultMaxPerRoute(MAX_CON_PER_ROUTE);
            poolConnManager.setDefaultSocketConfig(socketConfig);
            HttpRequestRetryHandler httpRequestRetryHandler = (e, i, httpContext) -> {
                if (i >= 5) {
                    return false;
                }
                if (e instanceof NoHttpResponseException) {
                    return false;
                }
                if (e instanceof SSLHandshakeException) {
                    return false;
                }
                if (e instanceof InterruptedIOException) {
                    return false;
                }
                if (e instanceof UnknownHostException) {
                    return false;
                }
                if (e instanceof SSLException) {
                    return false;
                }
                HttpClientContext clientContext = HttpClientContext.adapt(httpContext);
                HttpRequest request = clientContext.getRequest();
                return !(request instanceof HttpEntityEnclosingRequest);
            };
            RequestConfig requestConfig = RequestConfig
                    .custom()
                    .setConnectionRequestTimeout(TIME_OUT)
                    .setConnectTimeout(TIME_OUT)
                    .setSocketTimeout(TIME_OUT)
                    .build();
            List<Header> headers = Lists.newArrayList();
            headers.add(new BasicHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE_JSON));
            headers.add(new BasicHeader(HTTP.USER_AGENT, USER_AGENT_MSG));
            headers.add(new BasicHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE));
            headers.add(new BasicHeader(ACCEPT_LANGUAGE, ACCEPT_LANGUAGE_MSG));
            headers.add(new BasicHeader(ACCEPT_CHARSET, ACCEPT_CHARSET_MSG));
            headers.add(new BasicHeader(ACCEPT_ENCODING, ACCEPT_ENCODING_MSG));
            return HttpClients
                    .custom()
                    .setConnectionManager(poolConnManager)
                    .setDefaultRequestConfig(requestConfig)
                    .setRetryHandler(httpRequestRetryHandler)
                    .setDefaultHeaders(headers)
                    .build();
        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
            log.error(e.getMessage());
        }
        return HttpClients.createDefault();
    }
}

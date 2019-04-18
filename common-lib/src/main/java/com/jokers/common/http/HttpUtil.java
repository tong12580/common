package com.jokers.common.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * HTTP请求公共类
 *
 * @author yuTong
 * @version 1.0
 * @since 2016/05/19 01:04:35
 */
@Slf4j
public class HttpUtil {
    /**
     * Constant <code>SELECT_PUBLIC_IP_ADDRESS="http://www.ip138.com/ip2city.asp"</code>
     */
    public static final String SELECT_PUBLIC_IP_ADDRESS = "http://www.ip138.com/ip2city.asp";
    /**
     * Constant <code>CONTENT_TYPE_APPLICATION_OCTET_STREAM="application/octet-stream"</code>
     */
    public static final String CONTENT_TYPE_APPLICATION_OCTET_STREAM = "application/octet-stream";
    /**
     * Constant <code>LOCATION="Location"</code>
     */
    public static final String LOCATION = "Location";
    /**
     * Constant <code>CONTENT_DISPOSITION="Content-Disposition"</code>
     */
    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String APPLICATION_JSON = "application/json";
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";
    private static final String CONTENT_TYPE_APPLICATION = "application/x-www-form-urlencoded;charset=utf-8";
    private static final String CONTENT_TYPE_JSON = "application/json;charset=utf-8";
    private static final String ACCEPT = "Accept";
    private static final String AUTHORIZATION = "Authorization";

    /**
     * JSON参数的Post请求
     * 支持SSL
     *
     * @param url  String
     * @param json String
     * @return String
     * @throws java.io.IOException java.io.IOException
     * @author yuTong
     */
    public static String postJSON(String url, String json) throws IOException {

//        String encoderJson;
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient;
        String result = null;
        try {
            httpClient = HttpsClientPoolThread.builder().createSSLClientDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
            httpPost.addHeader(ACCEPT, APPLICATION_JSON);
            StringEntity se = new StringEntity(json, Consts.UTF_8);
            se.setContentType(CONTENT_TYPE_TEXT_JSON);
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
            httpPost.setEntity(se);

            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            if (null != entity) {
                result = EntityUtils.toString(entity, "UTF-8");
            }

        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        } finally {
            try {
                assert response != null;
                response.close();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return result;
    }

    /**
     * 云之讯短信请求
     *
     * @param authorization authorization
     * @param url           String
     * @param json          String
     * @return String
     * @author yuTong
     */
    public static String httpPostWithJSONToSMS(String url, String json, String authorization) {

        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient;
        String result = null;
        try {
            httpClient = HttpsClientPoolThread.builder().createSSLClientDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader(ACCEPT, APPLICATION_JSON);
            httpPost.addHeader(AUTHORIZATION, authorization);
            if (StringUtils.isNotBlank(json)) {
                StringEntity se = new StringEntity(json, StandardCharsets.UTF_8);
                se.setContentType(CONTENT_TYPE_JSON);
                se.setContentEncoding(StandardCharsets.UTF_8.name());
                httpPost.setEntity(se);
            }
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                result = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            }

        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            try {
                assert null != response;
                response.close();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return result;
    }

    /**
     * <p>httpJSON.</p>
     *
     * @param url     String
     * @param json    String json字符串
     * @param headers String 请求头
     * @return String
     */
    public static String httpJSON(String url, String json, List<Header> headers) {
        CloseableHttpClient httpClient;
        CloseableHttpResponse response = null;
        String result = null;
        httpClient = HttpsClientPoolThread.builder().createSSLClientDefault();
        HttpPost httpPost = new HttpPost(url);
        headers.forEach(httpPost::addHeader);
        StringEntity se = new StringEntity(json, Consts.UTF_8);
        httpPost.setEntity(se);
        try {
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                result = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            try {
                assert response != null;
                response.close();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }

        return result;

    }

    /**
     * Map参数的Post请求
     *
     * @param url    String
     * @param params 参数对
     * @return String
     */
    public static String httpRequestJSON(String url, Map<String, Object> params) {

        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient;
        String result = null;
        httpClient = HttpsClientPoolThread.builder().createSSLClientDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE_APPLICATION);

        List<BasicNameValuePair> pairs = new ArrayList<>();

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            BasicNameValuePair valuePair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
            pairs.add(valuePair);
        }

        try {

            httpPost.setEntity(new UrlEncodedFormEntity(pairs, StandardCharsets.UTF_8));
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            if (null != entity) {
                result = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            }

        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            try {
                assert response != null;
                response.close();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }

        return result;
    }

    /**
     * HTTP的GET调用
     *
     * @param url String
     * @return String
     * @author yuTong
     */
    public static String httpGet(String url) {
        CloseableHttpClient httpClient = HttpsClientPoolThread.builder().createSSLClientDefault();
        HttpGet httpGet = new HttpGet(url);
        String result = null;
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                result = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    /**
     * httpGet
     *
     * @param url    String
     * @param params Map
     * @return String
     */
    public static String httpGet(String url, Map<String, Object> params) {
        if (!CollectionUtils.isEmpty(params)) {
            StringBuilder urlMap = new StringBuilder(url);
            urlMap.append("?");
            Object keyValue;
            for (String key : params.keySet()) {
                keyValue = params.get(key);
                if (null != keyValue && !Objects.equals(keyValue, "")) {
                    urlMap.append(key).append("=").append(keyValue).append("&");
                }
            }
            return httpGet(urlMap.toString());
        } else {
            return httpGet(url);
        }
    }
}

package com.stc.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientUtil {

    private static RequestConfig requestConfig = null;

    static{
        requestConfig = RequestConfig.custom().setSocketTimeout(5000).
                setConnectTimeout(5000).setConnectionRequestTimeout(5000).build();
    }

    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void main(String[] args) throws IOException {
        System.out.println(get("http://127.0.0.1:8801"));
    }

    private static String get(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity,"utf-8");
    }
}

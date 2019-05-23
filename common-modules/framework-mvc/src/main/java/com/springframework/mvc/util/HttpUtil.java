package com.springframework.mvc.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.charset.Charset;

/**
 * @author summer
 * 2019/5/23
 */
@Slf4j
@Component
public class HttpUtil {

    private PoolingHttpClientConnectionManager cm = null;
    @Value("${http.client.connectionRequestTimeout:60000}")
    private int connectionRequestTimeout = 60000;
    @Value("${http.client.socketTimeout:60000}")
    private int socketTimeout = 60000;
    @Value("${http.client.connectTimeout:60000}")
    private int connectTimeout = 60000;
    @Value("${http.client.maxTotalPool:200}")
    private int maxTotalPool = 200;
    @Value("${http.client.maxConPerRoute:20}")
    private int maxConPerRoute = 20;

    @Value("${http.client.notifierEnable:false}")
    private boolean notifierEnable = false;
    @Autowired
    private ApplicationEventPublisher publisher;

    @PostConstruct
    public void init() {
        final Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
        cm = new PoolingHttpClientConnectionManager(registry);
        cm.setMaxTotal(maxTotalPool);
        cm.setDefaultMaxPerRoute(maxConPerRoute);
    }

    @PreDestroy
    public void destroy() {
        if (cm != null) {
            try {
                cm.close();
            } catch (Exception e) {
                //ignore
                log.error("PoolingHttpClientConnectionManager 连接池释放失败 ", e);
            }
        }
    }


    public CloseableHttpClient getHttpClient() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setConnectTimeout(connectTimeout)
                .setSocketTimeout(socketTimeout)
                .build();
        final CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(requestConfig).build();
        if (cm != null && cm.getTotalStats() != null) {
            final int available = cm.getTotalStats().getAvailable();
            final int pending = cm.getTotalStats().getPending();
            if (available <= 0 && pending > 0) {
                log.warn(String.format("now client pool " + cm.getTotalStats().toString() + " 当前 available : %s 不足", available));
            }
            log.info("now client pool " + cm.getTotalStats().toString());
        }
        return httpClient;
    }

    public String doPostJson(String url, String json) {
        CloseableHttpClient client = getHttpClient();
        HttpPost httpPost = new HttpPost(url);
        if (StringUtils.hasText(json)) {
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
        }
        try (CloseableHttpResponse response = client.execute(httpPost)) {
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK && response.getStatusLine().getStatusCode() != HttpStatus.SC_CREATED) {
                return null;
            }
            return EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
        } catch (Exception ex) {
            log.error("Http post execute error url:{}  params:{}", url, json, ex);
        }
        return null;
    }

}

package webmagic.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientUtil {
    private static PoolingHttpClientConnectionManager poolManager;
    private static RequestConfig config;

    public static CloseableHttpClient getHttpClient(){
        if(poolManager == null){
            poolManager = new PoolingHttpClientConnectionManager();
            poolManager.setMaxTotal(100);//最大连接数
            poolManager.setDefaultMaxPerRoute(20);//路由最大连接数
        }
        if(config == null){
            //配置连接池中连接参数
            config = RequestConfig.custom()
                    .setConnectTimeout(5000)//发送请求的超时时间
                    .setSocketTimeout(2000)//相应超时时间
                    .setConnectionRequestTimeout(500)//从连接池中获取的超时时间
                    .build();
        }
        //拿到httpClient
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(poolManager)
                .setDefaultRequestConfig(config)
                .build();
        return httpClient;
    }

    public static String doGet(String url){
        String result = "";
        CloseableHttpClient httpClient = getHttpClient();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == 200){
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}

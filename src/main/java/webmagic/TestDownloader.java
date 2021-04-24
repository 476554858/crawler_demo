package webmagic;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import java.util.ArrayList;

public class TestDownloader {
    public static void main(String[] args) {
        HttpClientDownloader  httpClientDownloader = new HttpClientDownloader();

        ArrayList<Proxy> proxies = new ArrayList<Proxy>();
        proxies.add(new Proxy("27.38.99.186", 9797));
        proxies.add(new Proxy("14.115.105.247", 9797));
        httpClientDownloader.setProxyProvider(new SimpleProxyProvider(proxies));

        Spider.create(new TestPageProcessor2())
                .addUrl("https://www.baidu.com/s?wd=webmagic")
                .setDownloader(httpClientDownloader)
                .run();
    }
}

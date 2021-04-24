package webmagic.jd;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

public class JDProcessor implements PageProcessor{
    @Override
    public void process(Page page) {
        //每次爬取的时间间隔是1-3秒
        this.site.setSleepTime((int)Math.random()*2000 + 1000);

        if(page.getHtml().$("#J_goodsList") != null){
            //列表页
            List<String> detailLinks = page.getHtml().$("#J_goodsList .gl-item").$(".p-img a").links().all();
            System.out.println(detailLinks.size());
        }else {
           //详情页
        }

    }

    private Site site = Site.me()
            .setTimeOut(5000)
            .setCharset("UTF-8")
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        String keyWord = "小米手机";
        String minPrice = "2000";
        String maxPrice = "5000";

        List<String> urls = new ArrayList<String>();
        for (int i = 1; i <= 20; i++) {
            String url = "https://search.jd.com/search?keyword=" + keyWord + "&ev=exprice_" +  minPrice + "-" + maxPrice + "&page=" + i;
            urls.add(url);
        }

        Spider.create(new JDProcessor())
                .addUrl(urls.toArray(new String[20]))
                .start();
    }
}

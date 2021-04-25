package webmagic.jd;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.nodes.Document;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import webmagic.util.HttpClientUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JDProcessor implements PageProcessor{
    @Override
    public void process(Page page) {
        //每次爬取的时间间隔是1-3秒
        this.site.setSleepTime((int)Math.random()*2000 + 1000);

        if(page.getHtml().$("#J_goodsList").get() != null){
            //列表页
            List<String> detailLinks = page.getHtml().$("#J_goodsList .gl-item").$(".p-img a").links().all();
           page.addTargetRequests(detailLinks);
        }else {
           //详情页
            Document dom = page.getHtml().getDocument();
            //获取商品标题
            String title = dom.select(".sku-name").get(0).text();
            //获取商品的图片地址
            String imgUrl = dom.select("#spec-img").get(0).attr("data-origin");
            //获取商品得地址
            String url = page.getUrl().get();
            String skuId = url.substring(url.lastIndexOf("/") + 1, url.indexOf(".html"));
            String priceJson = HttpClientUtil.doGet("https://p.3.cn/prices/mgets?skuIds=J_" + skuId);
            List<Map<String, String>> priceList = (List<Map<String, String>>) JSONObject.parse(priceJson);
            Map<String, String> priceMap = priceList.get(0);
            String p = priceMap.get("p");
            double price = Double.parseDouble(p);
            //获取商品的好评度
            String commentJson = HttpClientUtil.doGet("https://club.jd.com/comment/productPageComments.action?productId=" + skuId + "&score=0&sortType=5&page=0&pageSize=10");
            Map<String, Object> commentMap = (Map<String, Object>) JSONObject.parse(commentJson);
            Map<String, Object> summaryMap = (Map<String, Object>) commentMap.get("productCommentSummary");
            Integer commentCount = (Integer) summaryMap.get("commentCount");
            Integer goodRateShow = (Integer) summaryMap.get("goodRateShow");


            Product product = new Product();
            product.setTitle(title);
            product.setImgUrl(imgUrl);
            product.setUrl(url);
            product.setPrice(price);
            product.setSource("京东");
            product.setCommentAmount(commentCount);
            product.setGoodRateShow(goodRateShow);
            System.out.println(product);
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
        for (int i = 1; i <= 1; i++) {
            String url = "https://search.jd.com/search?keyword=" + keyWord + "&ev=exprice_" +  minPrice + "-" + maxPrice + "&page=" + i;
            urls.add(url);
        }

        Spider.create(new JDProcessor())
                .addUrl(urls.toArray(new String[urls.size()]))
                .start();
    }
}

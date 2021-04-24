package webmagic.demo;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GZPageProcessor implements PageProcessor{

    @Override
    public void process(Page page) {
        Document document = page.getHtml().getDocument();
        Elements cars = document.select(".carlist li");
        List<Car> carList = new ArrayList<Car>();
        for(Element car : cars){
            String imgUrl = car.select(">a>img").get(0).attr("src");
            String title = car.select(">a>h2").get(0).text();
            String price = car.select(".t-price>p").get(0).text().replaceAll("万", "").trim();
            BigDecimal priceB = new BigDecimal(price);
            String strings = car.select(".t-i").get(0).text();
            String[] split = strings.split("\\|");
            String buyDate = split[0].trim();
            String km = split[1].trim();

            Car car1 = new Car();
            car1.setBuyDate(buyDate);
            car1.setImgUrl(imgUrl);
            car1.setKm(km);
            car1.setPrice(priceB);
            car1.setTitle(title);
            car1.setSource("瓜子");

            carList.add(car1);
        }

        page.putField("carList", carList);
    }

    private Site site = Site.me()
            .setTimeOut(5000)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        List<String> urlList = new ArrayList<String>();
        for (int i = 1; i <= 20; i++) {
            String url = "https://www.guazi.com/bj/buy/o" + i + "p10-16/";
            urlList.add(url);
        }
        Spider.create(new GZPageProcessor())
                .addUrl(urlList.toArray(new String[20]))
                .addPipeline(new CarPipeline())
                .thread(5)
                .start();
    }
}

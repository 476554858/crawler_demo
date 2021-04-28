package webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.processor.PageProcessor;

public class TestSelenium implements PageProcessor{
    public void process(Page page) {
        System.out.println(page.getHtml());
    }

    public Site getSite() {
        return Site.me();
    }

    public static void main(String[] args) {
        System.setProperty("selenuim_config", "D:\\chrome\\chromedriver_win32\\config.ini");
        Spider.create(new TestSelenium())
//                .addUrl("https://item.jd.com/100011199522.html")
                .addUrl("https://www.jd.com")
//                .thread(2)
                .setDownloader(new SeleniumDownloader("D:\\chrome\\chromedriver_win32\\chromedriver.exe"))
                .thread(2)
                .start();
    }
}

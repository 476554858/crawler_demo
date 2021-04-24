package webmagic;

import us.codecraft.webmagic.Spider;

public class TestSpider {

    public static void main(String[] args) {
        Spider spider = Spider.create(new TestPageProcessor2())
                .addUrl("https://hao.360.com/?a1004")
                .addUrl("https://www.bilibili.com")
                .thread(5);
        spider.start();
        System.err.println("hello");
    }
}

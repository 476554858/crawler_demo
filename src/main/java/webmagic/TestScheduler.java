package webmagic;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

public class TestScheduler {
    public static void main(String[] args) {
        Spider.create(new TestPageProcessor2())
                .addUrl("https://www.so.com/s?ie=utf-8&src=hao_isearch2_cube&q=哔哩哔哩&eci=13186680&nlpv=lab463zc")
                .addUrl("https://www.bilibili.com")
                .setScheduler(new FileCacheQueueScheduler("D:/crawler"))
                .run();
    }
}

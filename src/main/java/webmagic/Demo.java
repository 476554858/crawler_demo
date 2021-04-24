package webmagic;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import us.codecraft.webmagic.*;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class Demo {
    public static void main(String[] args) {
        HttpClientDownloader downloader = new HttpClientDownloader();
        Spider.create(new MyPageProcessor())
                .addPipeline(new MyPipeline())
                .setDownloader(downloader)
                .addUrl("http://www.sikiedu.com")
                .run();
    }
}

class MyPageProcessor implements PageProcessor{

    @Override
    public void process(Page page) {
        Document document = page.getHtml().getDocument();
        Element span = document.select(".subtitle").get(0).previousElementSibling().select("span").get(0);
        String text = span.text();
        page.putField("title", text);
    }

    @Override
    public Site getSite() {
        return new Site();
    }
}

class MyPipeline implements Pipeline{

    @Override
    public void process(ResultItems resultItems, Task task) {
        String title = resultItems.get("title").toString();
        System.out.println("拿到标题：" + title);
    }
}
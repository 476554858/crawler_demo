package webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

public class TestPageProcessor implements PageProcessor{
    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        //用xpath获取ip
//        List<String> ipList = html.xpath("//table[@id='ip_list']//tbody//tr//td[2]//text()").all();
        List<String> ipList = html.xpath("//body//div[@class='body']//div[@id='content']//div[@class='con-body']//div//tbody//tr//td[1]//text()").all();
        page.putField("ipList", ipList);
        //用css选择器获取端口号
//        List<String> portList = html.css("#ip_list tbody tr td:nth-child(3)", "text").all();
//        System.out.println(portList);
//
//        List<String> links = html.css("#ip_list tbody tr td:nth-child(4)").links().regex(".*/2018.*").all();
//        System.out.println(links);
//        page.addTargetRequests(links);
    }

    @Override
    public Site getSite() {
        return new Site();
    }

    public static void main(String[] args) {
        Spider.create(new TestPageProcessor())
//                .addUrl("https://www.xicidaili.com/nn/")
                .addUrl("https://www.kuaidaili.com/free/inha/")
                .addPipeline(new FilePipeline("D:/crawler"))
                .addPipeline(new JsonFilePipeline("D:/crawler"))
                .run();
    }
}

package webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

public class TestPageProcessor2 implements PageProcessor{
    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        System.out.println(html);
        //用xpath获取ip
        List<String> ipList = html.xpath("//body//div[@class='body']//div[@id='content']//div[@class='con-body']//div//tbody//tr//td[1]//text()").all();
        System.out.println(ipList);
    }

    private Site site = Site.me()
            .setCharset("UTF-8")
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36")
            .setTimeOut(5000)
            .setRetryTimes(3)
            .setCycleRetryTimes(3)
            .setSleepTime(5000)
            .addHeader("cookie",  "BDUSS=RpOHlVcXBUQUcycVQyN2VnRHZ4dGV1OG9IUnJ3QzROdlZ0aUFNfkRWZjNpS1ZlSVFBQUFBJCQAAAAAAAAAAAEAAAAc7p-0adTZvPo51NnSsrK7vPsAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAPf7fV73-31eVk; BIDUPSID=50971F2B7E273258448CF71DE1F93984; PSTM=1586058206; BDUSS_BFESS=RpOHlVcXBUQUcycVQyN2VnRHZ4dGV1OG9IUnJ3QzROdlZ0aUFNfkRWZjNpS1ZlSVFBQUFBJCQAAAAAAAAAAAEAAAAc7p-0adTZvPo51NnSsrK7vPsAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAPf7fV73-31eVk; BAIDUID=ACD3B7F7F65E8428C96FC3AF44545C11:FG=1; BAIDUID_BFESS=ACD3B7F7F65E8428C96FC3AF44545C11:FG=1; __yjs_duid=1_f78407e22d0282ff3f71a04ddbf68ce71617802726937; BD_UPN=12314353; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; Hm_lvt_aec699bb6442ba076c8981c6dc490771=1618930490; COOKIE_SESSION=2_6_8_2_13_4_0_0_7_2_254_0_86550_15709222_293_256_1618930565_1618930528_1618930272%7C9%2315709143_5_1618930272%7C2; delPer=0; BD_CK_SAM=1; PSINO=5; H_PS_PSSID=33816_31253_33691_33848_33758_33676_26350_22159_33810; H_PS_645EC=6091%2Fq54Z3qbaeDWvUG%2BuxjM%2BNSC0VW%2FiIjYsQA%2B2qh89OQBtai9ED1mWRw; BA_HECTOR=0l818gal248h248kkk1g809330q");

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new TestPageProcessor2())
                .addUrl("https://www.baidu.com/s?wd=webmagic")
                .run();
    }
}

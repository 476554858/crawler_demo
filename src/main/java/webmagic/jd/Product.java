package webmagic.jd;

public class Product {

    private String title;

    private String imgUrl;

    private String url;

    private Double price;

    private String source;

    private Integer commentAmount;

    private Integer goodRateShow;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCommentAmount() {
        return commentAmount;
    }

    public void setCommentAmount(Integer commentAmount) {
        this.commentAmount = commentAmount;
    }

    public Integer getGoodRateShow() {
        return goodRateShow;
    }

    public void setGoodRateShow(Integer goodRateShow) {
        this.goodRateShow = goodRateShow;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", url='" + url + '\'' +
                ", price=" + price +
                ", source='" + source + '\'' +
                '}';
    }
}

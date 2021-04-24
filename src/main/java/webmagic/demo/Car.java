package webmagic.demo;

import java.math.BigDecimal;

public class Car {

    private int id;

    private String title;

    private BigDecimal price;

    private String buyDate;

    private String km;

    private String imgUrl;

    private String source;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", buyDate='" + buyDate + '\'' +
                ", km='" + km + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}

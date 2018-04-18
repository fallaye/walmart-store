package cvs.com.cvs;

import java.io.Serializable;

/**
 * Created by fallaye on 4/16/18.
 */

public class Items implements Serializable{

    String itemId, name, shortDescription, thumbnailImage, salePrice, categoryPath;

    public Items(String itemId, String name, String shortDescription, String thumbnailImage, String salePrice, String categoryPath) {
        this.itemId = itemId;
        this.name = name;
        this.shortDescription = shortDescription;
        this.thumbnailImage = thumbnailImage;
        this.salePrice = salePrice;
        this.categoryPath = categoryPath;
    }

    public Items() {

    }

    public String getItemId() {

        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return shortDescription;
    }

    public void setDesc(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getThumgnailImage() {
        return thumbnailImage;
    }

    public void setThumgnailImage(String thumgnailImage) {
        this.thumbnailImage = thumgnailImage;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
    }

    @Override
    public String toString() {
        return "Items{" +
                "itemId='" + itemId + '\'' +
                ", name='" + name + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", thumgnailImage='" + thumbnailImage + '\'' +
                ", salePrice='" + salePrice + '\'' +
                ", categoryPath='" + categoryPath + '\'' +
                '}';
    }
}

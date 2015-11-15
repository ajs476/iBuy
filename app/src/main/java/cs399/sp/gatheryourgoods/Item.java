package cs399.sp.gatheryourgoods;

/**
 * Created by Alex on 11/14/2015.
 */
public class Item {
    private String itemName;
    private String itemCategory;
    private String itemAmount;

    public void setItemName(String itemName){
        this.itemName = itemName;
    }
    public String getItemName(){
        return itemName;
    }
    public String getItemCategory(){
        return itemCategory;
    }
    public void setItemCategory(String itemCategory){
        this.itemCategory = itemCategory;
    }
    public String getItemAmount(){
        return itemAmount;
    }
    public void setItemAmount(String itemAmount){
        this.itemAmount = itemAmount;
    }


}

package cvs.com.cvs;

import java.util.ArrayList;

/**
 * Created by fallaye on 4/17/18.
 */

public class ShoppingCartItems {

    private static ShoppingCartItems mInstance;
    private ArrayList<Items> shoppingCartList = null;

    public static ShoppingCartItems getInstance(){
        if(mInstance == null)
            mInstance = new ShoppingCartItems();
        return mInstance;
    }

    private ShoppingCartItems(){
        shoppingCartList = new ArrayList<>();
    }

    public ArrayList<Items> getShoppingCartList(){
        return this.shoppingCartList;
    }

    public void addItemsToShoppingCart(Items items){
        shoppingCartList.add(items);
    }


}

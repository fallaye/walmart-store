package cvs.com.cvs;

import java.util.ArrayList;

/**
 * Created by fallaye on 4/17/18.
 */

public class Data {

    private ArrayList<Items> items;

    @Override
    public String toString() {
        return "Data{" +
                "items=" + items +
                '}';
    }

    public ArrayList<Items> getItems() {
        return items;
    }

    public void setItems(ArrayList<Items> items) {
        this.items = items;
    }
}

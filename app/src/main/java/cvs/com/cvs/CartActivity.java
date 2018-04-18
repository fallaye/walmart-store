package cvs.com.cvs;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    ArrayList<Items> shoppingCartItems;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        shoppingCartItems = ShoppingCartItems.getInstance().getShoppingCartList();

        listView = findViewById(R.id.listView);
        ShoppingListAdapter adapter = new ShoppingListAdapter(this, R.layout.shopping_cart_items, shoppingCartItems);
        listView.setAdapter(adapter);



    }
}

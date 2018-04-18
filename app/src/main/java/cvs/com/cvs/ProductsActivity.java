package cvs.com.cvs;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity: ";
    List<Items> productsList;
    Context context;
    ProductsAdapter productsAdapter;
    RecyclerView recyclerView;
    Button showCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        showCart = findViewById(R.id.btnShowCart);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        loadDataToView();

    }

    private void loadDataToView() {

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<Data> call = api.getData();
        call.enqueue(new Callback<Data>() {

            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {


                productsList = response.body().getItems();

                //Log.d(TAG, "In loadDataToView. Num Products:" + response.body().getItems().size());

                //for (int i = 0; i < productsList.size(); i++) {
                    //Log.d(TAG, productsList.get(i).getName());
                //}
                productsAdapter = new ProductsAdapter(getApplicationContext(), productsList);
                recyclerView.setAdapter(productsAdapter);

                recyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                            @Override public void onItemClick(View view, int position) {

                                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                                Bundle bundle = new Bundle();
                                //bundle.putString("itemId", productsList.get(position).getItemId());
                                bundle.putSerializable("item", productsList.get(position));

                                intent.putExtras(bundle);
                                startActivity(intent);
                            }

                            @Override public void onLongItemClick(View view, int position) {

                            }
                        })
                );
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, t.getMessage());
            }
        });

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(Constants.PRODUCT_ID, Constants.PRODUCT_NAME, importance);
            mChannel.setDescription(Constants.PRODUCT_CATEGORY);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mNotificationManager.createNotificationChannel(mChannel);
        }

        MyNotificationManager.getInstance(this).displayNotification(Constants.PRODUCT_NAME, Constants.PRODUCT_CATEGORY);

    }

    public void showCart(View view) {
        if(ShoppingCartItems.getInstance().getShoppingCartList().size() == 0){
            Toast.makeText(this, "Your Cart Is Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }
}

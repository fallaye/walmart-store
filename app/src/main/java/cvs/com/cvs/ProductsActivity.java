package cvs.com.cvs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

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

    }
}

package cvs.com.cvs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    String id;
    Bundle bundle;
    Items items;

    TextView tvId, tvName, tvDesc, tvPrice, tvCat;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvId = findViewById(R.id.tvId);
        tvName = findViewById(R.id.tvName);
        tvDesc = findViewById(R.id.tvDesc);
        tvCat = findViewById(R.id.tvCat);
        tvPrice = findViewById(R.id.tvPrice);
        imageView = findViewById(R.id.imageView);

        bundle = getIntent().getExtras();
        items = (Items) bundle.getSerializable("item");

        tvId.setText("Product ID: " + items.getItemId());
        tvName.setText(items.getName());
        tvDesc.setText(items.getDes());
        tvCat.setText(items.getCategoryPath());
        tvPrice.setText("Price   $" + items.getSalePrice());
        Picasso.with(getApplicationContext()).load(items.getThumgnailImage()).into(imageView);



    }
}

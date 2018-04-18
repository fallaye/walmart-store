package cvs.com.cvs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by fallaye on 4/16/18.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {

    Context context;
    List<Items> productsList;

    public ProductsAdapter(Context context, List<Items> list){
        this.context = context;
        this.productsList = list;
    }


    @Override
    public ProductsAdapter.ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_container, null);

        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductsAdapter.ProductsViewHolder holder, int position) {
        Items items = productsList.get(position);
        holder.tvId.setText(items.getItemId());
        holder.tvName.setText(items.getName());
        //holder.tvDesc.setText(items.getDesc());
        holder.tvPrice.setText("$" + items.getSalePrice());
        holder.tvCat.setText(items.getCategoryPath());
        Picasso.with(context).load(productsList.get(position)
                .getThumgnailImage())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder{
        TextView tvId, tvName, tvDesc, tvPrice, tvCat;
        ImageView imageView;

        public ProductsViewHolder(View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tvId);
            tvName = itemView.findViewById(R.id.tvName);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvCat = itemView.findViewById(R.id.tvCat);
            imageView = itemView.findViewById(R.id.imageView);

        }



    }
}

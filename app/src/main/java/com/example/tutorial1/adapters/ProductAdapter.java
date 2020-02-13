package com.example.tutorial1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorial1.R;
import com.example.tutorial1.models.ProductModel;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.PViewHolder>{

    List<ProductModel> products;
    Context context;

    @NonNull
    @Override
    public PViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.product_item,parent,false);
        return new ProductAdapter.PViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PViewHolder holder, int position) {

        ProductModel singleproduct=products.get(position);

        holder.prodcutName.setText(singleproduct.getpName());


    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public class PViewHolder extends RecyclerView.ViewHolder{

        public TextView prodcutName,productCount;
        public ImageView prodcuImage;

        public PViewHolder(@NonNull View itemView) {
            super(itemView);

            prodcutName=itemView.findViewById(R.id.product_image_name);
            productCount=itemView.findViewById(R.id.products_counter);
            prodcuImage=itemView.findViewById(R.id.product_image);
        }
    }
}

package com.example.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gharakaama.AddressDetails;
import com.example.gharakaama.R;
import com.example.modelclass.Home_ModelClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SubcategoryAdapter extends RecyclerView.Adapter<SubcategoryAdapter.ViewHolder> {

    Context context;
    ArrayList<Home_ModelClass> subcategory;
    String categoryId;

    public SubcategoryAdapter(ArrayList<Home_ModelClass> subcategory, FragmentActivity activity, String categoryId) {

        this.context = activity;
        this.subcategory = subcategory;
        this.categoryId = categoryId;
    }

    @NonNull
    @Override
    public SubcategoryAdapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categoryproduct,parent,false);
        return new SubcategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  SubcategoryAdapter.ViewHolder holder, int position) {

        Home_ModelClass hom_page = subcategory.get(position);

        holder.text_CategoryName.setText(hom_page.getCate_name());

        Picasso.with(context).load(hom_page.getCate_image()).into(holder.category_Image);

         holder.btn_subCategoryName.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Intent intent = new Intent(context, AddressDetails.class);
                 intent.putExtra("categoryId",categoryId);
                 intent.putExtra("subCategoryId",hom_page.getCate_id());
                 context.startActivity(intent);
             }
         });


    }

    @Override
    public int getItemCount() {
        return subcategory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView category_Image;
        TextView text_CategoryName;
        CardView cardView;
        Button btn_subCategoryName;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            text_CategoryName = itemView.findViewById(R.id.text_CategoryName);
            category_Image = itemView.findViewById(R.id.category_Image);
            cardView = itemView.findViewById(R.id.cardView);
            btn_subCategoryName = itemView.findViewById(R.id.btn_subCategoryName);
        }
    }
}

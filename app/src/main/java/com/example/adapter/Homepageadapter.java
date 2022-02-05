package com.example.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragment.Subcategory;
import com.example.gharakaama.R;
import com.example.modelclass.Home_ModelClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Homepageadapter extends RecyclerView.Adapter<Homepageadapter.ViewHolder> {

    Context context;
    ArrayList<Home_ModelClass> home;

    public Homepageadapter(ArrayList<Home_ModelClass> homepage, FragmentActivity activity) {

        this.context = activity;
        this.home = homepage;

    }

    @NonNull
    @Override
    public Homepageadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categoryproduct,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  Homepageadapter.ViewHolder holder, int position) {

        Home_ModelClass hom_page = home.get(position);

        holder.text_CategoryName.setText(hom_page.getCate_name());

       // Picasso.with(context).load(hom_page.getCate_image()).into(holder.category_Image);

        holder.btn_subCategoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Subcategory subcategory = new Subcategory();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.framLayout,subcategory,hom_page.getCate_id()).addToBackStack(null).commit();
             }
        });

    }

    @Override
    public int getItemCount() {
        return home.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView category_Image;
        TextView text_CategoryName;
        CardView cardView;
        LinearLayout lin_layout;
        Button btn_subCategoryName;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            text_CategoryName = itemView.findViewById(R.id.text_CategoryName);
            category_Image = itemView.findViewById(R.id.category_Image);
            cardView = itemView.findViewById(R.id.cardView);
            lin_layout = itemView.findViewById(R.id.lin_layout);
            btn_subCategoryName = itemView.findViewById(R.id.btn_subCategoryName);
        }
    }
}

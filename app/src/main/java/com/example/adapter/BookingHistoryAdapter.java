package com.example.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragment.BookingDetails;
import com.example.fragment.Subcategory;
import com.example.gharakaama.R;
import com.example.modelclass.BookingDetails_ModelClass;

import java.util.ArrayList;

public class BookingHistoryAdapter extends RecyclerView.Adapter<BookingHistoryAdapter.ViewHolder> {

    Context context;
    ArrayList<BookingDetails_ModelClass> bookingdetails;

    public BookingHistoryAdapter(ArrayList<BookingDetails_ModelClass> booking, FragmentActivity activity) {

        this.context = activity;
        this.bookingdetails = booking;
    }

    @NonNull
    @Override
    public BookingHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_fragment,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  BookingHistoryAdapter.ViewHolder holder, int position) {

        BookingDetails_ModelClass booking_details = bookingdetails.get(position);

        holder.bookingId.setText(booking_details.getBook_id());
        holder.OrderStatus.setText(booking_details.getOrder_status());
        holder.catagory.setText(booking_details.getService_catagory_1());

        holder.nav_ViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                SharedPreferences oredrhistory_SharedPreferences = context.getSharedPreferences("details",context.MODE_PRIVATE);
                SharedPreferences.Editor editor = oredrhistory_SharedPreferences.edit();
                editor.putString("Book_id",booking_details.getBook_id());
                editor.putString("Service_catagory_1",booking_details.getService_catagory_1());
                editor.putString("Service_catagory_2",booking_details.getService_catagory_2());
                editor.putString("ServiceTypeDesc",booking_details.getServiceTypeDesc());
                editor.putString("DateTime",booking_details.getDate()+",  "+booking_details.getTime());
                editor.putString("Address1",booking_details.getAddress1());
                editor.putString("Contact_no",booking_details.getContact_no());
                editor.apply();
                editor.commit();

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                BookingDetails bookingDetails = new BookingDetails();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.framLayout, bookingDetails).addToBackStack(null).commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return bookingdetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {



        TextView bookingId,OrderStatus,catagory,ViewAll;
        Button nav_ViewAll;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            bookingId = itemView.findViewById(R.id.bookingId);
            OrderStatus = itemView.findViewById(R.id.OrderStatus);
            catagory = itemView.findViewById(R.id.catagory);
            nav_ViewAll = itemView.findViewById(R.id.nav_ViewAll);
            ViewAll = itemView.findViewById(R.id.ViewAll);
        }
    }
}

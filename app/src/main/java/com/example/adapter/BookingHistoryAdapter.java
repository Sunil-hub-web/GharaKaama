package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookinghistory,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  BookingHistoryAdapter.ViewHolder holder, int position) {

        BookingDetails_ModelClass booking_details = bookingdetails.get(position);

        holder.book_id.setText(booking_details.getBook_id());
        holder.service_catagory.setText(booking_details.getService_catagory_1());
        holder.service_subcatagory.setText(booking_details.getService_catagory_2());
        holder.ServiceTypeDesc.setText(booking_details.getServiceTypeDesc());
        holder.datetime.setText(booking_details.getDate()+",  "+booking_details.getTime());
        holder.addressdetails.setText(booking_details.getAddress1()+"\n"+booking_details.getLocality()+","+
                booking_details.getLandmark()+","+booking_details.getState()+"\n"+booking_details.getContact_no());

    }

    @Override
    public int getItemCount() {
        return bookingdetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView book_id,service_catagory,service_subcatagory,ServiceTypeDesc,datetime,addressdetails;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            book_id = itemView.findViewById(R.id.book_id);
            service_catagory = itemView.findViewById(R.id.service_catagory);
            service_subcatagory = itemView.findViewById(R.id.service_subcatagory);
            ServiceTypeDesc = itemView.findViewById(R.id.ServiceTypeDesc);
            datetime = itemView.findViewById(R.id.datetime);
            addressdetails = itemView.findViewById(R.id.addressdetails);
        }
    }
}

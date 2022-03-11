package com.example.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gharakaama.MainActivity;
import com.example.gharakaama.R;

public class BookingDetails extends Fragment {

    TextView book_id, service_catagory, service_subcatagory, ServiceTypeDesc, datetime, addressdetails,welcome,text_details;
    String Book_id,Service_catagory_1,Service_catagory_2,Service_TypeDesc,DateTime,Address1,Locality,
            Contact_no,Landmark,State;

    ImageView image_Logo,image_back;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View itemView = inflater.inflate(R.layout.bookinghistory, container, false);

        book_id = itemView.findViewById(R.id.book_id);
        service_catagory = itemView.findViewById(R.id.service_catagory);
        service_subcatagory = itemView.findViewById(R.id.service_subcatagory);
        ServiceTypeDesc = itemView.findViewById(R.id.ServiceTypeDesc);
        datetime = itemView.findViewById(R.id.datetime);
        addressdetails = itemView.findViewById(R.id.addressdetails);

        image_Logo = itemView.findViewById(R.id.image_Logo);
        image_back = itemView.findViewById(R.id.image_back);
        welcome = itemView.findViewById(R.id.welcome);
        text_details = itemView.findViewById(R.id.text_details);

        image_Logo.setVisibility(View.GONE);
        welcome.setVisibility(View.VISIBLE);
        image_back.setVisibility(View.VISIBLE);
        welcome.setText("Booking Details");

        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                welcome.setVisibility(View.INVISIBLE);
                image_back.setVisibility(View.INVISIBLE);
                image_Logo.setVisibility(View.VISIBLE);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                BookingHistory bookingHistory = new BookingHistory();
                ft.replace(R.id.framLayout, bookingHistory);
                ft.commit();

            }
        });



        SharedPreferences oredrhistory_SharedPreferences = getContext().getSharedPreferences("details",getContext().MODE_PRIVATE);
        Book_id = oredrhistory_SharedPreferences.getString("Book_id",null);
        Service_catagory_1 = oredrhistory_SharedPreferences.getString("Service_catagory_1",null);
        Service_catagory_2 = oredrhistory_SharedPreferences.getString("Service_catagory_2",null);
        Service_TypeDesc = oredrhistory_SharedPreferences.getString("ServiceTypeDesc",null);
        DateTime = oredrhistory_SharedPreferences.getString("DateTime",null);
        Address1 = oredrhistory_SharedPreferences.getString("Address1",null);
        Contact_no = oredrhistory_SharedPreferences.getString("Contact_no",null);


        book_id.setText(Book_id);
        service_catagory.setText(Service_catagory_1);
        service_subcatagory.setText(Service_catagory_2);
        ServiceTypeDesc.setText(Service_TypeDesc);
        datetime.setText(DateTime);
        addressdetails.setText(Address1 + "\n" + Contact_no);

        return itemView;
    }
}

package com.example.fragment;

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

public class AboutUs extends Fragment {

    ImageView image_Logo,image_back;
    TextView welcome,text_details;

    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater,
                             @Nullable  ViewGroup container,
                             @Nullable  Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.aboutus_fragment,container,false);

        image_Logo = view.findViewById(R.id.image_Logo);
        image_back = view.findViewById(R.id.image_back);
        welcome = view.findViewById(R.id.welcome);
        text_details = view.findViewById(R.id.text_details);

        image_Logo.setVisibility(View.GONE);
        welcome.setVisibility(View.VISIBLE);
        image_back.setVisibility(View.VISIBLE);
        welcome.setText("About Us");

        text_details.setText(" GharaKaama.com is an one step application for " +
                "services related to Engineering plan and structural design of Building, " +
                "Space optimization solution for real estate developers, Minor and Major " +
                "maintenance work related to your house or Premises, Plumbing, Electrical, " +
                "Home Security, Gardening, Cleaning, Pest Control, Fabrication, Lift maintenance " +
                "services, Arrangements of essentials for celebrations, Home appliance repair, " +
                "Carpeting Work, Priest/Brahmin arrangements and Medicine supply for Senior " +
                "citizens. We believe in providing best solution to our customers. Which is " +
                "based on three motto: The great value for money, within time and with best quality. .");
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                welcome.setVisibility(View.INVISIBLE);
                image_back.setVisibility(View.INVISIBLE);
                image_Logo.setVisibility(View.VISIBLE);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Homepage homepage = new Homepage();
                ft.replace(R.id.framLayout, homepage);
                ft.commit();

                MainActivity.bottomNavigation.setSelectedItemId(R.id.home);
            }
        });

        return view;
    }
}

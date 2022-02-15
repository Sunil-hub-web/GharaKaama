package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.example.gharakaama.R;

public class AddressDetails extends Fragment {

    Spinner field,sub_field;
    EditText edit_fullname,edit_MobileNumber,edit_EmailId,edit_Address,edit_locality,edit_landmark,edit_State;
    String str_fullname,str_MobileNumber,str_EmailId,str_Address,str_locality,str_landmark,str_State,userId,
            str_categoryId,str_SubCategoryId,fieldselected,str_Selected1,str_Selected2,ServiceTypeDesc,date,
            time,str_address;
    Button btn_Submit;
    String[] fieldname = {"--Select--", "Major", "Minor", "Full"};
    String[] fieldnamehour = {"--Select hour--", "1 hour", "2 hour", "3 hour","4 hour","5 hour","6 hour",
            "7 hour","8 hour","9 hour", "10 hour","11 hour","12 hour","13 hour","14 hour",
            "15 hour","16 hour","17 hour","18 hour","19 hour","20 hour","21 hour","22 hour",
            "23 hour","24 hour"};

    String[] fieldnamedays = {"--Select Days--", "1 Days", "2 Days","3 Days","4 Days","5 Days","6 Days",
            "7 Days","8 Days","9 Days", "10 Days","11 Days","12 Days","13 Days","14 Days",
            "15 Days","16 Days","17 Days","18 Days","19 Days","20 Days","21 Days","22 Days",
            "23 Days","24 Days","25 Days","26 Days","27 Days","28 Days","29 Days","30 Days"};
    TextView ShowDayhour;
    private AwesomeValidation awesomeValidation;

    ImageView image_Logo,image_back;
    TextView welcome;

    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater,
                             @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_address_details,container,false);

        return view;
    }
}

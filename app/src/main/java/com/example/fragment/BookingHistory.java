package com.example.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adapter.BookingHistoryAdapter;
import com.example.extra.AppUrl;
import com.example.extra.SharedPrefManager;
import com.example.gharakaama.LoginPage;
import com.example.gharakaama.R;
import com.example.modelclass.BookingDetails_ModelClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookingHistory extends Fragment {

    RecyclerView recyclerBookingHistory;
    BookingHistoryAdapter bookingHistoryAdapter;
    LinearLayoutManager linearLayoutManager;

    ArrayList<BookingDetails_ModelClass> booking = new ArrayList<>(); ;

    String userId;

    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater,
                             @Nullable  ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bookinghistory_fragment,container,false);

        recyclerBookingHistory = view.findViewById(R.id.recyclerBookingHistory);

        userId = SharedPrefManager.getInstance(getActivity()).getUser().getUserid();

        getOrderHistory(userId);

        return view;
    }

    public void getOrderHistory(String userId){

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Get Order Details Please wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.ordersHistory, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String success = jsonObject.getString("success");

                    if(success.equals("true")){

                        JSONArray jsonArray_All_Bookings = jsonObject.getJSONArray("All_Bookings");

                        for(int i=0;i<jsonArray_All_Bookings.length();i++){

                            JSONObject jsonObject_All_Bookings = jsonArray_All_Bookings.getJSONObject(i);

                            String order_id = jsonObject_All_Bookings.getString("order_id");
                            String order_status = jsonObject_All_Bookings.getString("order_status");
                            String book_id = jsonObject_All_Bookings.getString("book_id");
                            String service_catagory_1 = jsonObject_All_Bookings.getString("service_catagory_1");
                            String service_catagory_2 = jsonObject_All_Bookings.getString("service_catagory_2");
                            String ServiceTypeDesc = jsonObject_All_Bookings.getString("ServiceTypeDesc");
                            String description = jsonObject_All_Bookings.getString("description");
                            String date = jsonObject_All_Bookings.getString("date");
                            String time = jsonObject_All_Bookings.getString("time");

                            JSONObject jsonObject_Address = jsonObject_All_Bookings.getJSONObject("Address");

                            String address1 = jsonObject_Address.getString("address1");
                            String locality = jsonObject_Address.getString("locality");
                            String landmark = jsonObject_Address.getString("landmark");
                            String state = jsonObject_Address.getString("state");
                            String contact_no = jsonObject_Address.getString("contact_no");

                            BookingDetails_ModelClass bookingDetails_modelClass = new BookingDetails_ModelClass(

                                    order_id,order_status,book_id,service_catagory_1,service_catagory_2,
                                    ServiceTypeDesc,description,date,time,address1,locality,landmark,state,
                                    contact_no

                            );

                            booking.add(bookingDetails_modelClass);

                        }

                        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                        bookingHistoryAdapter = new BookingHistoryAdapter(booking,getActivity());
                        recyclerBookingHistory.setLayoutManager(linearLayoutManager);
                        recyclerBookingHistory.setHasFixedSize(true);
                        recyclerBookingHistory.setAdapter(bookingHistoryAdapter);

                    }else if(success.equals("false")){

                        String msg = jsonObject.getString("msg");
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                error.printStackTrace();
                Toast.makeText(getActivity(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("user_id",userId);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000,1,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }
}

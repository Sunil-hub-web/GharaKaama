package com.example.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adapter.Homepageadapter;
import com.example.extra.AppUrl;
import com.example.gharakaama.LoginPage;
import com.example.gharakaama.MainActivity;
import com.example.gharakaama.R;
import com.example.modelclass.Home_ModelClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Homepage extends Fragment {

    RecyclerView recyclerCategory;
    GridLayoutManager gridLayoutManager;
    Homepageadapter homepageadapter;
    ArrayList<Home_ModelClass> homepage = new ArrayList<>();

    ImageView image_Logo,image_back;
    TextView welcome;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable  ViewGroup container,
                             @Nullable  Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment,container,false);

        recyclerCategory = view.findViewById(R.id.recyclerCategory);

        image_Logo = view.findViewById(R.id.image_Logo);
        image_back = view.findViewById(R.id.image_back);
        welcome = view.findViewById(R.id.welcome);

        welcome.setVisibility(View.GONE);
        image_back.setVisibility(View.GONE);
        image_Logo.setVisibility(View.VISIBLE);

        //MainActivity.bottomNavigation.setSelectedItemId(R.id.home);

        getCategoryDetails();

        return view;
    }

    public void getCategoryDetails(){

        homepage.clear();

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Get CategoryDetails Please wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppUrl.getAllCategory, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String success = jsonObject.getString("success");


                    if(success.equals("true")){

                        //String All_Category = jsonObject.getString("All_Category");



                        JSONArray jsonArray_All_Category = jsonObject.getJSONArray("All_Category");

                        for (int i=0;i<jsonArray_All_Category.length();i++){

                            JSONObject jsonObject_All_Category = jsonArray_All_Category.getJSONObject(i);

                            String category_id = jsonObject_All_Category.getString("category_id");
                            String cate_name = jsonObject_All_Category.getString("cate_name");
                            String cate_img = jsonObject_All_Category.getString("cate_img");

                            Home_ModelClass home_modelClass = new Home_ModelClass(
                                    category_id,cate_name,cate_img
                            );

                            homepage.add(home_modelClass);
                        }

                        gridLayoutManager = new GridLayoutManager(getActivity(),3,GridLayoutManager.VERTICAL,false);
                        homepageadapter = new Homepageadapter(homepage,getActivity());
                        recyclerCategory.setLayoutManager(gridLayoutManager);
                        recyclerCategory.setHasFixedSize(true);
                        recyclerCategory.setAdapter(homepageadapter);

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
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000,1,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}

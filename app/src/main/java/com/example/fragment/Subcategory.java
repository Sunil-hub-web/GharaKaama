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

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adapter.Homepageadapter;
import com.example.adapter.SubcategoryAdapter;
import com.example.extra.AppUrl;
import com.example.gharakaama.MainActivity;
import com.example.gharakaama.R;
import com.example.modelclass.Home_ModelClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Subcategory extends Fragment {

    RecyclerView recyclerSubCategory;
    String categoryId;

    GridLayoutManager gridLayoutManager;
    SubcategoryAdapter subcategoryAdapter;
    ArrayList<Home_ModelClass> subcategory = new ArrayList<>();

    ImageView image_Logo,image_back;
    TextView welcome;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.subcategory_fragment,container,false);

        recyclerSubCategory = view.findViewById(R.id.recyclerSubCategory);
        image_Logo = view.findViewById(R.id.image_Logo);
        image_back = view.findViewById(R.id.image_back);
        welcome = view.findViewById(R.id.welcome);

        image_Logo.setVisibility(View.GONE);
        image_back.setVisibility(View.VISIBLE);
        welcome.setVisibility(View.VISIBLE);
        welcome.setText("Sub Category");

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

        //categoryId = getArguments().getString("categoryId");

        categoryId = getTag();

        getSubCategoryDetails(categoryId);

        return view;
    }

    public void getSubCategoryDetails(String categoryId){

        subcategory.clear();

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Get SubCategoryDetails Please wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.getAllSubCategory, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String success = jsonObject.getString("success");


                    if(success.equals("true")){

                        //String All_Category = jsonObject.getString("All_Category");

                        JSONArray jsonArray_All_SubCategory = jsonObject.getJSONArray("All_SubCategory");

                        for (int i=0;i<jsonArray_All_SubCategory.length();i++){

                            JSONObject jsonObject_All_SubCategory = jsonArray_All_SubCategory.getJSONObject(i);

                            String category_id = jsonObject_All_SubCategory.getString("subcate_id");
                            String cate_name = jsonObject_All_SubCategory.getString("subcate_name");
                            String cate_img = jsonObject_All_SubCategory.getString("SubCat_img");

                            Home_ModelClass home_modelClass = new Home_ModelClass(
                                    category_id,cate_name,cate_img
                            );

                            subcategory.add(home_modelClass);

                        }

                        gridLayoutManager = new GridLayoutManager(getActivity(),3,GridLayoutManager.VERTICAL,false);
                        subcategoryAdapter = new SubcategoryAdapter(subcategory,getActivity(),categoryId);
                        recyclerSubCategory.setLayoutManager(gridLayoutManager);
                        recyclerSubCategory.setHasFixedSize(true);
                        recyclerSubCategory.setAdapter(subcategoryAdapter);

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
                params.put("ParentCatId",categoryId);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000,1,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}

package com.example.gharakaama;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.extra.AppUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForgetPassword extends AppCompatActivity {

    Button btn_SendOtp;
    EditText edit_EmailId;
    String str_EmailId;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        /*btn_SendOtp = findViewById(R.id.btn_SendOtp);
        edit_EmailId = findViewById(R.id.edit_EmailId);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation (ForgetPassword.this,R.id.edit_EmailId, Patterns.EMAIL_ADDRESS,R.string.enteremail);

        btn_SendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 if(awesomeValidation.validate()){

                    str_EmailId = edit_EmailId.getText().toString().trim();
                    forgetPassword(str_EmailId);

                }else{

                    Toast.makeText(ForgetPassword.this, "Enter Valide EmailId", Toast.LENGTH_SHORT).show();

                }
            }
        });*/
    }

    public void forgetPassword(String email){

        ProgressDialog progressDialog = new ProgressDialog(ForgetPassword.this);
        progressDialog.setMessage("Reset password Please wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.forgetPassword, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    if(success.equals("true")){

                        String msg = jsonObject.getString("msg");
                        Toast.makeText(ForgetPassword.this, msg, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(ForgetPassword.this,LoginPage.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    Toast.makeText(ForgetPassword.this, "Please check Internet Connection", Toast.LENGTH_SHORT).show();

                } else {

                    Log.d("successresponceVolley", "" + error.networkResponse);
                    NetworkResponse networkResponse = error.networkResponse;
                    if (networkResponse != null && networkResponse.data != null) {
                        try {
                            String jError = new String(networkResponse.data);
                            JSONObject jsonError = new JSONObject(jError);
//                            if (error.networkResponse.statusCode == 400) {
                            String data = jsonError.getString("msg");
                            Toast.makeText(ForgetPassword.this, data, Toast.LENGTH_SHORT).show();

//                            } else if (error.networkResponse.statusCode == 404) {
//                                JSONArray data = jsonError.getJSONArray("msg");
//                                JSONObject jsonitemChild = data.getJSONObject(0);
//                                String ms = jsonitemChild.toString();
//                                Toast.makeText(RegisterActivity.this, ms, Toast.LENGTH_SHORT).show();
//
//                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("successresponceVolley", "" + e);
                        }
                    }
                }
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("emails",email);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000,1,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(ForgetPassword.this);
        requestQueue.add(stringRequest);
    }
}
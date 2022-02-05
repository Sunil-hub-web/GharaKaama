package com.example.gharakaama;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.example.extra.AppUrl;
import com.example.extra.SharedPrefManager;
import com.example.modelclass.Login_ModelClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginPage extends AppCompatActivity {

    Button btn_signin;
    TextView text_RegisterNow,text_ForgotPassword;
    EditText edit_MobileNumber,edit_Password;
    String str_MobileNumber,str_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        text_ForgotPassword = findViewById(R.id.text_ForgotPassword);
        btn_signin = findViewById(R.id.btn_signin);
        text_RegisterNow = findViewById(R.id.text_RegisterNow);
        edit_MobileNumber = findViewById(R.id.edit_MobileNumber);
        edit_Password = findViewById(R.id.edit_Password);

        text_ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginPage.this,ForgetPassword.class);
                startActivity(intent);
            }
        });

        text_RegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginPage.this,UserRegister.class);
                startActivity(intent);
            }
        });

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edit_MobileNumber.getText().toString().trim().equals("")){

                    edit_MobileNumber.setError("Fill The Field");

                }else if(edit_Password.getText().toString().trim().equals("")){

                    edit_Password.setError("Fill The Field");

                }else{

                    str_MobileNumber = edit_MobileNumber.getText().toString().trim();
                    str_Password = edit_Password.getText().toString().trim();

                    userLogin(str_MobileNumber,str_Password);
                }


            }
        });
    }

    public void userLogin(String mobileNo,String password){

        ProgressDialog progressDialog = new ProgressDialog(LoginPage.this);
        progressDialog.setMessage("User Login Please wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String success = jsonObject.getString("success");
                    if(success.equals("true")){

                        String msg = jsonObject.getString("msg");
                        Toast.makeText(LoginPage.this, msg, Toast.LENGTH_SHORT).show();

                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String email = jsonObject.getString("email");
                        String contact_no = jsonObject.getString("contact_no");
                        String user_type = jsonObject.getString("contact_no");
                        String password = edit_Password.getText().toString().trim();

                        Login_ModelClass login_modelClass = new Login_ModelClass(
                                id,contact_no,email,name,password
                        );

                        SharedPrefManager.getInstance(LoginPage.this).userLogin(login_modelClass);

                        Intent intent = new Intent(LoginPage.this,MainActivity.class);
                        startActivity(intent);

                    }else if(success.equals("false")){

                        String msg = jsonObject.getString("msg");
                        Toast.makeText(LoginPage.this, msg, Toast.LENGTH_LONG).show();
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

                    Toast.makeText(LoginPage.this, "Please check Internet Connection", Toast.LENGTH_SHORT).show();

                } else {

                    Log.d("successresponceVolley", "" + error.networkResponse);
                    NetworkResponse networkResponse = error.networkResponse;
                    if (networkResponse != null && networkResponse.data != null) {
                        try {
                            String jError = new String(networkResponse.data);
                            JSONObject jsonError = new JSONObject(jError);
//                            if (error.networkResponse.statusCode == 400) {
                            String data = jsonError.getString("msg");
                            Toast.makeText(LoginPage.this, data, Toast.LENGTH_SHORT).show();

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
                params.put("contactNo",mobileNo);
                params.put("password",password);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000,1,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(LoginPage.this);
        requestQueue.add(stringRequest);

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(SharedPrefManager.getInstance(LoginPage.this).isLoggedIn()){

            Intent intent = new Intent(LoginPage.this,MainActivity.class);
            startActivity(intent);
        }
    }
}
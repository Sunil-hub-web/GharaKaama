package com.example.gharakaama;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
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
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.extra.AppUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserRegister extends AppCompatActivity {

    Button btn_signup;
    TextView text_signin;
    EditText edit_fullname,edit_MobileNumber,edit_EmailId,edit_Password,edit_ConfirmPassword;
    String str_fullname,str_MobileNumber,str_EmailId,str_Password,str_ConfirmPassword;
    private AwesomeValidation awesomeValidation;
    boolean passwordVisiable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

       /* text_signin = findViewById(R.id.text_signin);
        btn_signup = findViewById(R.id.btn_signup);
        edit_fullname = findViewById(R.id.edit_fullname);
        edit_MobileNumber = findViewById(R.id.edit_MobileNumber);
        edit_EmailId = findViewById(R.id.edit_EmailId);
        edit_Password = findViewById(R.id.edit_Password);
        edit_ConfirmPassword = findViewById(R.id.edit_ConfirmPassword);

        awesomeValidation = new AwesomeValidation (ValidationStyle.BASIC);

        awesomeValidation.addValidation (UserRegister.this,R.id.edit_fullname,"^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$",R.string.entername);
        awesomeValidation.addValidation (UserRegister.this,R.id.edit_MobileNumber,"^[0-9]{10}$",R.string.entercontact);
        awesomeValidation.addValidation (UserRegister.this,R.id.edit_EmailId, Patterns.EMAIL_ADDRESS,R.string.enteremail);
        //awesomeValidation.addValidation (UserRegister.this,R.id.edit_Password,"^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",R.string.enterpassword);
        //awesomeValidation.addValidation (UserRegister.this,R.id.edit_ConfirmPassword,"^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",R.string.enterpassword);


        text_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserRegister.this,LoginPage.class);
                startActivity(intent);
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(awesomeValidation.validate()){

                    if(edit_Password.getText().toString().trim().equals(edit_ConfirmPassword.getText().toString().trim())){

                        str_fullname = edit_fullname.getText().toString().trim();
                        str_EmailId = edit_EmailId.getText().toString().trim();
                        str_Password = edit_Password.getText().toString().trim();
                        str_MobileNumber = edit_MobileNumber.getText().toString().trim();

                        userRegister(str_fullname,str_MobileNumber,str_EmailId,str_Password);

                    }else{

                        edit_ConfirmPassword.setError("Password Not Match");
                    }

                }else{

                    Toast.makeText(UserRegister.this, "Enter a valide data", Toast.LENGTH_SHORT).show();
                }

            }
        });

        edit_Password.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {

                    if (event.getRawX() >= edit_Password.getRight() - edit_Password.getCompoundDrawables()[Right].getBounds().width()) {

                        int selection = edit_Password.getSelectionEnd();
                        if (passwordVisiable) {

                            //set Drawable Image here
                            edit_Password.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.lock, 0, R.drawable.baseline_visibility_off, 0);
                            edit_Password.setCompoundDrawablePadding(25);
                            // for show Password
                            edit_Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisiable = false;

                        } else {

                            //set Drawable Image here
                            edit_Password.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.lock, 0, R.drawable.baseline_visibility, 0);
                            edit_Password.setCompoundDrawablePadding(25);
                            // for show Password
                            edit_Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisiable = true;
                        }

                        edit_Password.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        edit_ConfirmPassword.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {

                    if (event.getRawX() >= edit_ConfirmPassword.getRight() - edit_ConfirmPassword.getCompoundDrawables()[Right].getBounds().width()) {

                        int selection = edit_ConfirmPassword.getSelectionEnd();
                        if (passwordVisiable) {

                            //set Drawable Image here
                            edit_ConfirmPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.lock, 0, R.drawable.baseline_visibility_off, 0);
                            edit_ConfirmPassword.setCompoundDrawablePadding(25);
                            // for show Password
                            edit_ConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisiable = false;

                        } else {

                            //set Drawable Image here
                            edit_ConfirmPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.lock, 0, R.drawable.baseline_visibility, 0);
                            edit_ConfirmPassword.setCompoundDrawablePadding(25);
                            // for show Password
                            edit_ConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisiable = true;
                        }

                        edit_ConfirmPassword.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });*/
    }

   /* public void userRegister(String userName,String mobileNo,String email,String password){


        ProgressDialog progressDialog = new ProgressDialog(UserRegister.this);
        progressDialog.setMessage("User Register Please wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.register, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    String success = jsonObject.getString("success");

                    if(success.equals("true")){

                        String msg = jsonObject.getString("msg");
                        Toast.makeText(UserRegister.this, msg, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(UserRegister.this,LoginPage.class);
                        startActivity(intent);

                    }else if(success.equals("false")){

                        String msg = jsonObject.getString("msg");
                        Toast.makeText(UserRegister.this, msg, Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                *//*error.printStackTrace();
                Toast.makeText(UserRegister.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
*//*
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    Toast.makeText(UserRegister.this, "Please check Internet Connection", Toast.LENGTH_SHORT).show();

                } else {

                    Log.d("successresponceVolley", "" + error.networkResponse);
                    NetworkResponse networkResponse = error.networkResponse;
                    if (networkResponse != null && networkResponse.data != null) {
                        try {
                            String jError = new String(networkResponse.data);
                            JSONObject jsonError = new JSONObject(jError);
//                            if (error.networkResponse.statusCode == 400) {
                            String data = jsonError.getString("msg");
                            Toast.makeText(UserRegister.this, data, Toast.LENGTH_SHORT).show();

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
                params.put("fullname",userName);
                params.put("contact",mobileNo);
                params.put("email",email);
                params.put("password",password);

                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000,1,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(UserRegister.this);
        requestQueue.add(stringRequest);

    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
       /* finish();
        System.exit(0);*/
    }
}
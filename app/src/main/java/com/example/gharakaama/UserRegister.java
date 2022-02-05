package com.example.gharakaama;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        text_signin = findViewById(R.id.text_signin);
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
        awesomeValidation.addValidation (UserRegister.this,R.id.edit_Password,"^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",R.string.enterpassword);
        awesomeValidation.addValidation (UserRegister.this,R.id.edit_ConfirmPassword,"^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",R.string.enterpassword);


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
    }

    public void userRegister(String userName,String mobileNo,String email,String password){


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
                error.printStackTrace();
                Toast.makeText(UserRegister.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
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


    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
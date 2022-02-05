package com.example.gharakaama;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.extra.AppUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForgetPassword extends AppCompatActivity {

    Button btn_SendOtp;
    EditText edit_EmailId;
    String str_EmailId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        btn_SendOtp = findViewById(R.id.btn_SendOtp);
        edit_EmailId = findViewById(R.id.edit_EmailId);

        btn_SendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(edit_EmailId.getText())){

                    edit_EmailId.setError("Fill The Field");
                    edit_EmailId.setFocusable(true);

                }else{

                    str_EmailId = edit_EmailId.getText().toString().trim();
                    forgetPassword(str_EmailId);

                }
            }
        });
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
                error.printStackTrace();
                Toast.makeText(ForgetPassword.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();

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
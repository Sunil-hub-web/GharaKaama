package com.example.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.extra.AppUrl;
import com.example.extra.SharedPrefManager;
import com.example.gharakaama.ForgetPassword;
import com.example.gharakaama.LoginPage;
import com.example.gharakaama.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangePassword_Fragment extends Fragment {

    EditText edit_password,edit_ConfirmPassword;
    String str_password,str_ConfirmPassword,str_UserId,oldPassword;
    Button btn_ChangePassword;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.changepassword_fragment, container, false);

        edit_password = view.findViewById(R.id.edit_password);
        edit_ConfirmPassword = view.findViewById(R.id.edit_ConfirmPassword);
        btn_ChangePassword = view.findViewById(R.id.btn_ChangePassword);

        str_UserId = SharedPrefManager.getInstance(getActivity()).getUser().getUserid();
        oldPassword = SharedPrefManager.getInstance(getActivity()).getUser().getPassword();

        btn_ChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(edit_password.getText())){

                    edit_password.setError("Fill The Details");

                }else if(TextUtils.isEmpty(edit_ConfirmPassword.getText())){

                    edit_ConfirmPassword.setError("Fill The Details");

                }else if(!isValidPassword(edit_password.getText().toString().trim())){

                    edit_password.setError("Enter The Valid Password");

                }else if(edit_password.getText().toString().trim().equals(edit_ConfirmPassword.getText().toString().trim())){

                    str_password = edit_password.getText().toString().trim();
                    changepassword(str_UserId,oldPassword,str_password);

                }else{

                    edit_ConfirmPassword.setError("Password not match");
                }
            }
        });

        return view;
    }

    public void changepassword(String userId, String oldPassword, String newPassword){

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Password Update Please wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.changePassword, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    if(success.equals("true")){

                        String msg = jsonObject.getString("msg");
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();

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
                params.put("old_pass",oldPassword);
                params.put("new_pass",newPassword);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000,1,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";

        pattern =  Pattern.compile (PASSWORD_PATTERN);
        matcher = pattern.matcher (password);

        return matcher.matches ( );

    }
}

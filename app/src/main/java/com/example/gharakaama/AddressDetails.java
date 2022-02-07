package com.example.gharakaama;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.example.extra.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddressDetails extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_details);

        sub_field = findViewById(R.id.sub_field);
        field = findViewById(R.id.field);
        ShowDayhour = findViewById(R.id.ShowDayhour);
        edit_fullname = findViewById(R.id.edit_fullname);
        edit_MobileNumber = findViewById(R.id.edit_MobileNumber);
        edit_EmailId = findViewById(R.id.edit_EmailId);
        edit_Address = findViewById(R.id.edit_Address);
        edit_locality = findViewById(R.id.edit_locality);
        edit_landmark = findViewById(R.id.edit_landmark);
        edit_State = findViewById(R.id.edit_State);
        btn_Submit = findViewById(R.id.btn_Submit);

        ArrayAdapter WorkingField = new ArrayAdapter(this, R.layout.spinneritem, fieldname);
        WorkingField.setDropDownViewResource(R.layout.spinnerdropdownitem);
        field.setAdapter(WorkingField);
        field.setSelection(-1, true);

        Intent intent = getIntent();
        str_categoryId = intent.getStringExtra("categoryId");
        str_SubCategoryId = intent.getStringExtra("subCategoryId");

        Date todaysdate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        date = format.format(todaysdate);

        Date today = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("hh:mm a");
        time = format1.format(today);

        Log.d("details",date + time + str_categoryId + str_SubCategoryId);

        userId = SharedPrefManager.getInstance(AddressDetails.this).getUser().getUserid();

        awesomeValidation = new AwesomeValidation (ValidationStyle.BASIC);

        awesomeValidation.addValidation (AddressDetails.this,R.id.edit_fullname,"^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$",R.string.entername);
       // awesomeValidation.addValidation (AddressDetails.this,R.id.edit_Address,"^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$",R.string.entername);
        //awesomeValidation.addValidation (AddressDetails.this,R.id.edit_locality,"^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$",R.string.entername);
        //awesomeValidation.addValidation (AddressDetails.this,R.id.edit_landmark,"^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$",R.string.entername);
        //awesomeValidation.addValidation (AddressDetails.this,R.id.edit_State,"^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$",R.string.entername);
        awesomeValidation.addValidation (AddressDetails.this,R.id.edit_MobileNumber,"^[0-9]{10}$",R.string.entercontact);
        awesomeValidation.addValidation (AddressDetails.this,R.id.edit_EmailId, Patterns.EMAIL_ADDRESS,R.string.enteremail);


        field.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                fieldselected = field.getSelectedItem().toString();



                if(fieldselected.equals("Minor")){

                    ArrayAdapter WorkingFieldhour = new ArrayAdapter(AddressDetails.this, R.layout.spinneritem, fieldnamehour);
                    WorkingFieldhour.setDropDownViewResource(R.layout.spinnerdropdownitem);
                    sub_field.setAdapter(WorkingFieldhour);
                    sub_field.setSelection(-1, true);

                    ShowDayhour.setText("Hours");

                    ShowDayhour.setVisibility(View.VISIBLE);
                    sub_field.setVisibility(View.VISIBLE);

                    str_Selected2 = sub_field.getSelectedItem().toString();

                }else if(fieldselected.equals("Major")){

                    ArrayAdapter WorkingFielddays = new ArrayAdapter(AddressDetails.this, R.layout.spinneritem, fieldnamedays);
                    WorkingFielddays.setDropDownViewResource(R.layout.spinnerdropdownitem);
                    sub_field.setAdapter(WorkingFielddays);
                    sub_field.setSelection(-1, true);

                    ShowDayhour.setText("Days");

                    ShowDayhour.setVisibility(View.VISIBLE);
                    sub_field.setVisibility(View.VISIBLE);

                    str_Selected2 = sub_field.getSelectedItem().toString();

                }else if(fieldselected.equals("Full")){

                    ShowDayhour.setVisibility(View.GONE);
                    sub_field.setVisibility(View.GONE);

                    str_Selected2 = "";

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        Log.d("details",date +","+time+","+str_categoryId+","+str_SubCategoryId+","+ServiceTypeDesc);

        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(awesomeValidation.validate()){

                    str_Selected1 = field.getSelectedItem().toString();

                    if(str_Selected1.equals("Full")){

                        str_Selected2 = "";

                        str_fullname = edit_fullname.getText().toString().trim();
                        str_MobileNumber = edit_MobileNumber.getText().toString().trim();
                        str_EmailId = edit_EmailId.getText().toString().trim();
                        str_Address = edit_Address.getText().toString().trim();
                        str_locality = edit_locality.getText().toString().trim();
                        str_landmark = edit_landmark.getText().toString().trim();
                        str_State = edit_State.getText().toString().trim();

                        str_Selected1 = field.getSelectedItem().toString();
                        ServiceTypeDesc = str_Selected1;

                        str_address = str_fullname+","+str_EmailId+","+str_Address;

                        placeorder(userId,str_categoryId,str_SubCategoryId,ServiceTypeDesc,"description",
                                date,time,str_address,str_locality,str_landmark,str_State,str_MobileNumber);

                    }else{

                        str_fullname = edit_fullname.getText().toString().trim();
                        str_MobileNumber = edit_MobileNumber.getText().toString().trim();
                        str_EmailId = edit_EmailId.getText().toString().trim();
                        str_Address = edit_Address.getText().toString().trim();
                        str_locality = edit_locality.getText().toString().trim();
                        str_landmark = edit_landmark.getText().toString().trim();
                        str_State = edit_State.getText().toString().trim();

                        str_Selected2 = sub_field.getSelectedItem().toString();
                        str_Selected1 = field.getSelectedItem().toString();
                        ServiceTypeDesc = str_Selected1+"/"+str_Selected2;

                        str_address = str_fullname+","+str_EmailId+","+str_Address;

                        placeorder(userId,str_categoryId,str_SubCategoryId,ServiceTypeDesc,"description",
                                date,time,str_address,str_locality,str_landmark,str_State,str_MobileNumber);
                    }



                }else{

                    Toast.makeText(AddressDetails.this, "Enter validate data", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    public void placeorder(String userId,String categoryId,String subCategoryId,String ServiceType,String description,
                           String date,String time,String address,String locality,String landmark,String state,
                           String contact_no){

        ProgressDialog progressDialog = new ProgressDialog(AddressDetails.this);
        progressDialog.setMessage("Order Placed Please wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.placeOrder, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String success = jsonObject.getString("success");

                    if(success.equals("true")){

                        String message = jsonObject.getString("message");
                        String Booking_id = jsonObject.getString("Booking_id");
                        String book_date = jsonObject.getString("book_date");

                        Toast.makeText(AddressDetails.this, message, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(AddressDetails.this,ThanksPage.class);
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
                Toast.makeText(AddressDetails.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();

                params.put("user_id",userId);
                params.put("category1_id",categoryId);
                params.put("category2_id",subCategoryId);
                params.put("ServiceTypeDesc",ServiceType);
                params.put("description",description);
                params.put("date",date);
                params.put("time",time);
                params.put("address1",address);
                params.put("locality",locality);
                params.put("landmark",landmark);
                params.put("state",state);
                params.put("contact_no",contact_no);

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000,1,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(AddressDetails.this);
        requestQueue.add(stringRequest);
    }
}
package com.example.gharakaama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OTPVerification extends AppCompatActivity {

    Button btn_VerifyOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);

        btn_VerifyOtp = findViewById(R.id.btn_VerifyOtp);

        btn_VerifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(OTPVerification.this,ChangePassword.class);
                startActivity(intent);
            }
        });
    }
}
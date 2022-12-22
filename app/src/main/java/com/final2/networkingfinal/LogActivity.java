package com.final2.networkingfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.final2.networkingfinal.databinding.ActivityLogBinding;

public class LogActivity extends AppCompatActivity {
ActivityLogBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvSignUpPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogActivity.this , RegActivity.class));
            }
        });
    }
}
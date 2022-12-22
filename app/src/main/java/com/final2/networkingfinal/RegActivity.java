package com.final2.networkingfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.final2.networkingfinal.databinding.ActivityRegBinding;

public class RegActivity extends AppCompatActivity {
    ActivityRegBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
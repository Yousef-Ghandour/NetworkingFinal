package com.final2.networkingfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.final2.networkingfinal.databinding.ActivityFirstTimeBinding;

import java.util.ArrayList;

public class FirstTimeActivity extends AppCompatActivity {
    ActivityFirstTimeBinding binding;
    int currentImage = 0;
    ArrayList<Integer> WelcomeImages = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstTimeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        WelcomeImages.add(R.drawable.welcome_1);
        WelcomeImages.add(R.drawable.welcome_3);
        WelcomeImages.add(R.drawable.welcome_2);
        binding.ivWelcoming.setImageResource(WelcomeImages.get(currentImage));

        binding.btNextWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentImage == (WelcomeImages.size()-1)){
                    startActivity(new Intent(FirstTimeActivity.this , LogActivity.class));
                    finish();
                }else {
                    currentImage = currentImage + 1;
                    binding.ivWelcoming.setImageResource(WelcomeImages.get(currentImage));
                    if (currentImage == (WelcomeImages.size()-1)){
                        binding.btNextWelcome.setText("Finish");

                    }
                }
            }
        });

    }


}
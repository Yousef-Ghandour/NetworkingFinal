package com.final2.networkingfinal;

import static com.final2.networkingfinal.Commons.Common.FirstOpen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import com.final2.networkingfinal.Commons.CommonFuncs;

public class SplashActivity extends AppCompatActivity {
    CommonFuncs commonFuncs = new CommonFuncs();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (commonFuncs.GetFromSP(this,FirstOpen).equals("Yes")){
            new CountDownTimer(2000, 1000) {
                public void onTick(long millisUntilFinished) {
                }
                public void onFinish() {
                    Intent intent = new Intent(SplashActivity.this, LogActivity.class);
                    startActivity(intent);
                    finish();

                }
            }.start();
        }else {
            commonFuncs.WriteSP(this,FirstOpen,"Yes");
            new CountDownTimer(2000, 1000) {
                public void onTick(long millisUntilFinished) {
                }
                public void onFinish() {
                    Intent intent = new Intent(SplashActivity.this, FirstTimeActivity.class);
                    startActivity(intent);
                    finish();

                }
            }.start();
        }

    }
}
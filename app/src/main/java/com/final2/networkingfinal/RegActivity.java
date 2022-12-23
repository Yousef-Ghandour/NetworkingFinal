package com.final2.networkingfinal;

import static com.android.volley.Request.Method.POST;
import static com.final2.networkingfinal.Commons.Common.APIProviderLogin;
import static com.final2.networkingfinal.Commons.Common.APIProviderReg;
import static com.final2.networkingfinal.Commons.Common.APIUserReg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.final2.networkingfinal.databinding.ActivityRegBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegActivity extends AppCompatActivity {
    ActivityRegBinding binding;
    RequestQueue queue;
    JsonObjectRequest request;
    int jobId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        queue = Volley.newRequestQueue(RegActivity.this);
        if(binding.rbServiceProvider.isChecked()){
            binding.rgJob.setVisibility(View.VISIBLE);
        } else if (binding.rbCustomer.isChecked()){
            binding.rgJob.setVisibility(View.GONE);
        }
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegActivity.this , LogActivity.class));
                finish();
            }
        });
        binding.btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.rbCustomer.isChecked()){
                    registerUser();

                } else if (binding.rbServiceProvider.isChecked()){
                    registerProvider();
                }
            }
        });
    }
    void registerProvider(){

        JSONObject object = new JSONObject();

        try {
            object.put("name" , binding.etNameReg.getText().toString());
            object.put("email" , binding.etEmailReg.getText().toString());
            object.put("password" , binding.etPassReg.getText().toString());
            object.put("phone" , binding.etPhone.getText().toString());
            if(binding.rbCarpenter.isChecked()){
                jobId = 1;
                object.put("work_id" , jobId);
            } else if (binding.rbSmith.isChecked()){
                jobId = 2;
                object.put("work_id" , jobId);

            }
            request = new JsonObjectRequest(POST, APIProviderReg, object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if(response.getBoolean("success") == true){
                            Toast.makeText(RegActivity.this, "" + response.getString("message"), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegActivity.this , LogActivity.class);
                            startActivity(intent);
                        } else{
                            Toast.makeText(RegActivity.this, "" + response.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("thiseeee", "onResponse: " + e.getMessage() );
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        queue.add(request);
    }
    void registerUser(){

        JSONObject object = new JSONObject();

        try {
            object.put("name" , binding.etNameReg.getText().toString());
            object.put("email" , binding.etEmailReg.getText().toString());
            object.put("password" , binding.etPassReg.getText().toString());
            object.put("phone" , binding.etPhone.getText().toString());

            request = new JsonObjectRequest(POST, APIUserReg, object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if(response.getBoolean("success") == true){
                            Toast.makeText(RegActivity.this, "" + response.getString("message"), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegActivity.this , LogActivity.class);
                            startActivity(intent);
                        } else{
                            Toast.makeText(RegActivity.this, "" + response.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("thiseeee", "onResponse: " + e.getMessage() );
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        queue.add(request);
    }

}
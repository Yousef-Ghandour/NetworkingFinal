package com.final2.networkingfinal;

import static com.android.volley.Request.Method.POST;
import static com.final2.networkingfinal.Commons.Common.APIProviderLogin;
import static com.final2.networkingfinal.Commons.Common.APIUserLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.final2.networkingfinal.Commons.CommonFuncs;
import com.final2.networkingfinal.databinding.ActivityLogBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class LogActivity extends AppCompatActivity {
    CommonFuncs commonFuncs = new CommonFuncs();
    ActivityLogBinding binding;
    RequestQueue queue;
    JsonObjectRequest request;
    public static String TOKEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        queue = Volley.newRequestQueue(LogActivity.this);
        binding.tvSignUpPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogActivity.this , RegActivity.class));
            }
        });
        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.rbCustomer.isChecked()){
                    loginUser();

                } else if (binding.rbServiceProvider.isChecked()){
                    loginProvider();
                }

            }
        });
    }
    void loginUser(){

        JSONObject object = new JSONObject();

        try {
            object.put("email" , binding.etEmailLogin.getText().toString());
            object.put("password" , binding.etPassLogin.getText().toString());
            request = new JsonObjectRequest(POST, APIUserLogin, object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        if(response.getBoolean("success")){
                            JSONObject data = response.getJSONObject("data");
                            Toast.makeText(LogActivity.this, "" + response.getString("message"), Toast.LENGTH_SHORT).show();
                            TOKEN = "Bearer" + " " +  data.getString("token");

                            Intent intent = new Intent(LogActivity.this , CustomerMainActivity.class);
                            intent.putExtra("token" , TOKEN);
                            Log.e("token", TOKEN + "");
                            startActivity(intent);
                        } else{
                            Toast.makeText(LogActivity.this, "" + response.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("thiseeee", "onResponse: " + e.getMessage() );
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LogActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        queue.add(request);
    }
    void loginProvider(){

        JSONObject object = new JSONObject();

        try {
            object.put("email" , binding.etEmailLogin.getText().toString());
            object.put("password" , binding.etPassLogin.getText().toString());
            request = new JsonObjectRequest(POST, APIProviderLogin, object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if(response.getBoolean("success") == true){
                            Toast.makeText(LogActivity.this, "" + response.getString("message"), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LogActivity.this , CustomerMainActivity.class);
                            startActivity(intent);
                        } else{
                            Toast.makeText(LogActivity.this, "" + response.getString("message"), Toast.LENGTH_SHORT).show();
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
package com.final2.networkingfinal;

import static com.android.volley.Request.Method.POST;
import static com.final2.networkingfinal.Commons.Common.APIUserLogin;
import static com.final2.networkingfinal.LogActivity.TOKEN;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.final2.networkingfinal.databinding.ActivityLocationOrderBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;

public class LocationOrderActivity extends AppCompatActivity {
    ActivityLocationOrderBinding binding;
    LocationManager mLocManager;
    Intent firstOrder;
    RequestQueue queue;
    JsonObjectRequest request;
    int id;
    String filename;
    String token;
    String problemDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLocationOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        queue = Volley.newRequestQueue(LocationOrderActivity.this);
        firstOrder = getIntent();
        Bitmap bmp = null;
        filename = firstOrder.getStringExtra("image");
        id = Integer.parseInt(firstOrder.getStringExtra("selectedJobId"));
        token = firstOrder.getStringExtra("token");
        try {
            FileInputStream is = this.openFileInput(filename);
            bmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        problemDetails = firstOrder.getStringExtra("problemDetails");
        Log.e("intent1",  problemDetails);
        Log.e("intent2",  filename);
        Log.e("intent3",  id + "");
        Log.e("intent4",  token + "");
        mLocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener mLocListener = new MyLocationListener();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocListener);
        binding.btGetLatLong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(LocationOrderActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(LocationOrderActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                Location lastLocation = mLocManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                String lat = lastLocation.getLatitude() + "";
                String longi = lastLocation.getLongitude() + "";
                binding.tvLat.setText(lat);
                binding.tvLong.setText(longi);
            }
        });
        binding.btAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOrder();
            }
        });
    }
    void addOrder(){

        JSONObject object = new JSONObject();

        try {
            object.put("Authorization" , TOKEN);
            object.put("work_id" , id);
            object.put("details" , problemDetails);
            object.put("details_address" , binding.etLocationDetails.getText().toString());
            object.put("photos[]" , filename);
            object.put("phone" , binding.etOrderPhoneNumber.getText().toString());
            object.put("lat" , binding.tvLat.getText().toString());
            object.put("long" , binding.tvLong.getText().toString());
            request = new JsonObjectRequest(POST, APIUserLogin, object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if(!response.getBoolean("success")){
                            Toast.makeText(LocationOrderActivity.this, "" + response.getString("message"), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LocationOrderActivity.this , CustomerMainActivity.class);
                            startActivity(intent);
                        } else{
                            Toast.makeText(LocationOrderActivity.this, "" + response.getString("message"), Toast.LENGTH_SHORT).show();
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



class MyLocationListener implements LocationListener {
    @Override

    public void onLocationChanged(Location loc){

        String lat = loc.getLatitude() + "";
        String longi = loc.getLatitude() + "";


    }
    public void onProviderDisabled(String provider){

    }


    public void onProviderEnabled(String provider){

    }

    public void onStatusChanged(String provider, int status, Bundle extras){
    }

}


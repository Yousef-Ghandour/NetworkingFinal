package com.final2.networkingfinal;

import static com.final2.networkingfinal.Commons.Common.APIAllWorks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.final2.networkingfinal.Adapters.MainCustomerRVAdapter;
import com.final2.networkingfinal.Models.AllJobs;
import com.final2.networkingfinal.databinding.ActivityMainCustomerBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomerMainActivity extends AppCompatActivity {
    ActivityMainCustomerBinding binding;
    ArrayList<AllJobs> allJobs = new ArrayList<>();
    MainCustomerRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainCustomerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adapter = new MainCustomerRVAdapter(this , allJobs);
        binding.rvCustomerRecycler.setLayoutManager(new GridLayoutManager(this , 2));
        getAllWorks();
        binding.rvCustomerRecycler.setAdapter(adapter);
    }
    void getAllWorks(){
        StringRequest request = new StringRequest(Request.Method.GET, APIAllWorks, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("data");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object1 = array.getJSONObject(i);
                        allJobs.add(new AllJobs(
                                object1.getInt("id"),
                                object1.getString("name") ,
                                object1.getString("icon") ,
                                object1.getString("description"),
                                object1.getInt("active")));
                    }
                    adapter.notifyDataSetChanged();
                    Log.e("TAG", "re1: " + response);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("TAG", "reCatch: " + e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "reError: " + error);


            }
        });
        Volley.newRequestQueue(this).add(request);

    }
}
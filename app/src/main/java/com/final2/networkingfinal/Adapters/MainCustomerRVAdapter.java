package com.final2.networkingfinal.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.final2.networkingfinal.Models.AllJobs;
import com.final2.networkingfinal.R;

import java.util.ArrayList;

public class MainCustomerRVAdapter extends RecyclerView.Adapter<MainCustomerRVAdapter.MyViewHolder> {
    Context context;
    ArrayList<AllJobs> jobs;
    AllJobs allJobs;

    public MainCustomerRVAdapter(Context context, ArrayList<AllJobs> jobs) {
        this.context = context;
        this.jobs = jobs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_customer_adapter , null));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        allJobs = jobs.get(position);
        holder.tvJobName.setText(allJobs.getName());
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivJobImage;
        TextView tvJobName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivJobImage = itemView.findViewById(R.id.ivJobImage);
            tvJobName = itemView.findViewById(R.id.tvJobName);
        }
    }
}

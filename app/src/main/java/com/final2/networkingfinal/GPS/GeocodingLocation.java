package com.final2.networkingfinal.GPS;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GeocodingLocation {

    private static final String TAG = "GeocodingLocation";

    public static void getAddressFromLocation(final String locationAddress,
                                              final Context context, final Handler handler) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                Message message = Message.obtain();
                message.setTarget(handler);

                try {
                    List addressList = geocoder.getFromLocationName(locationAddress, 1);
                    if (addressList != null && addressList.size() > 0) {
                        Address address = (Address) addressList.get(0);

                        message.what = 1;
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("address", address);
                        message.setData(bundle);
                    }
                } catch (IOException e) {
                    Log.e(TAG, "IOException", e);
                    message.what = 2;
                }
                message.sendToTarget();
            }
        };
        thread.start();
    }

}
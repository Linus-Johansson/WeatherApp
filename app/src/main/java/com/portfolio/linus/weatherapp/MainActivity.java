package com.portfolio.linus.weatherapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
     static final String TAG = MainActivity.class.getSimpleName();
     String API_key ="3a1c430f10c723ec544b244b0a51dc3a";
     Double Lat = 37.8267;
     Double Long =-122.4233;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String foreCastURL ="https://api.darksky.net/forecast/"+API_key+"/"+Lat+","+Long;
        // when checked and true I.E, network and internet is Availble.. the http request will run..
        if(isNetworkAvailble()) {


            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url(foreCastURL).build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    try {

                        Log.v(TAG, response.body().string());
                        if (response.isSuccessful()) {

                        } else {
                            alertUserAboutError();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught:", e);
                    }

                }
            });
        }
        Log.e(TAG,"Main UI code is running, hooray!");

    }

    private boolean isNetworkAvailble() {
        ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailble = false;

        if (networkInfo != null && networkInfo.isAvailable()) {
            isAvailble = true;
        }else{
            //Toast.makeText(this, R.string.network_unAvailble_msg,Toast.LENGTH_LONG).show();
            alertAboutNetworkDown();
        }
        return isAvailble;

    }

    private void alertAboutNetworkDown() {
        AlertDialogFragment alertNetworkDown = new AlertDialogFragment();
        alertNetworkDown.show(getFragmentManager(),"Network_error:");
    }

    private void alertUserAboutError() {
        AlertDialogFragment alert = new AlertDialogFragment();
        alert.show(getFragmentManager(),"error_Dialog:");
    }

}

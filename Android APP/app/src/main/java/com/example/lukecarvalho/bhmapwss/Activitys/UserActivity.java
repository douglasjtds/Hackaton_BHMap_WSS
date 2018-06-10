package com.example.lukecarvalho.bhmapwss.Activitys;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.lukecarvalho.bhmapwss.Classes.FireDB;
import com.example.lukecarvalho.bhmapwss.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.HashMap;

public class UserActivity extends AppCompatActivity {

    private String userId;
    private HashMap<String,String> fdbAgentCall;

    private Double lat, lon;
    private LocationManager localManager;
    private LocationListener localListner;
    private FireDB fdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent i = getIntent();
        userId = i.getStringExtra("USER_ID");
        setTitle(userId);

        fdb = new FireDB();

        localManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        localListner = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lat = location.getLatitude();
                lon = location.getLongitude();
                getGPS();

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET
                }, 10);
                return;
            } else {
                getGPS();
            }
        }

        FireDB.Chamado chamado = getNewChamado();

        i = new Intent(getApplicationContext(), RequestAllert.class);
        i.putExtra("CHAMADO", (Serializable) chamado);
        i.putExtra("USER_ID", userId);
        startActivity(i);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    getGPS();
        }
    }

    @SuppressLint("MissingPermission")
    private void getGPS() {
        int update_time = 5000;
        int distance_to_update = 0;
        localManager.requestLocationUpdates("gps", update_time, distance_to_update, localListner);
        TextView tv = findViewById(R.id.cordenate);
        String s = lat + " " + lon + "";
        tv.setText("Localizção do Agente: " + s);

        fdb.updateAgentLocation(userId, lat, lon);
    }

    private FireDB.Chamado getNewChamado() {
        FireDB.Chamado call;

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                FireDB.Chamado c = dataSnapshot.getValue(FireDB.Chamado.class);

                if (dataSnapshot.getValue() != null) {
                    fdbAgentCall = (HashMap<String, String>) dataSnapshot.getValue();
                    String uID, cID;
                    uID = (String) fdbAgentCall.get("AGENTID");
                    cID = String("CHAMADO");

                    System.out.println("SERVICE FIREBASE : " + uID);
                    if (uID == userId) {

                        call = fdb.getCall(uID);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mPostReference.addValueEventListener(postListener);


        return call;
    }


}

package com.example.lukecarvalho.bhmapwss.Activitys;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lukecarvalho.bhmapwss.Classes.FireDB;
import com.example.lukecarvalho.bhmapwss.R;
import com.google.firebase.database.FirebaseDatabase;

public class RequestAllert extends AppCompatActivity {

    private Button btn;
    private String agentID;
    private FireDB.Chamado call;
    private FireDB fdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_allert);

        fdb = new FireDB();

        Intent i = getIntent();
        call = (FireDB.Chamado) i.getExtras().getSerializable("CHAMADO");


        TextView desk = findViewById(R.id.TVAlertDesc);
        desk.setText(call.getDescription());

        TextView local = findViewById(R.id.TVAlertLocal);
        local.setText(call.getEndereco());

        btn = findViewById(R.id.Close);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newAgente_Chamado();
                finish();
            }
        });

        FireDB fdb = new FireDB();
        fdb.updateAgentLocation(agentID, (Double) i.getExtras().getSerializable("LAT"), (Double) i.getExtras().getSerializable("LON"));

    }

    public void newAgente_Chamado () {
        String callID = "0011";
        fdb.writeNew_agentId_chamado_Id(agentID,callID);
    }


}

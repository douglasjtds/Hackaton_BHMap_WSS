package com.example.lukecarvalho.bhmapwss.Classes;

import android.location.Location;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

/**
 * Created by ankursrivastava on 4/2/16.
 */


public class FireDB {
    private static FirebaseDatabase fdb;

    public FireDB() {
        fdb = FirebaseDatabase.getInstance();
    }

    public void updateAgentLocation(String agentId, Double lat, Double lon) {
        String path = "/COP/AGENTES/" + agentId + "/LOCALIZACAO";

        fdb.getReference(path).child("LON").setValue(lon);
        fdb.getReference(path).child("LAT").setValue(lat);
    }

    public void writeNewAgentr(String agentId, String name, Location location) {
        fdb.getReference().child("AGENTES").child(agentId).setValue(new Agent(name, location));
    }

    public void writeNew_agentId_chamado_Id(String agentId, String chamadoId) {
        fdb.getReference().child("AGENTE_CHAMADO").child(agentId).setValue(chamadoId);

//        fdb.getReference().child("chamdos").
    }

    public Chamado getChamado(String chamadoID) {

/*
        fdb.getReference("/COP/CHAMADO/"+chamadoID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value  = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        return new Chamado("Carnaval", "Um monte de gente na rua!", "Praça Sete");
    }

    public class Agent {

        public String NOME;
        public Location LOCALIZACAO;


        public Agent() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public Agent(String name, Location location) {
            this.NOME = name;
            this.LOCALIZACAO = location;
        }
    }

    public class Chamado {
        public String title;
        public String descricao;
        public String ENDERECO;

        public Chamado(String title, String description, String endereco) {
            this.title = title;
            this.descricao = description;
            this.ENDERECO = endereco;
        }

        public Chamado(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return descricao;
        }

        public void setDescription(String description) {
            this.descricao = description;
        }

        public String getEndereco() {
            return ENDERECO;
        }

        public void setEndereco(String endereco) {
            this.ENDERECO = endereco;
        }

    }
}


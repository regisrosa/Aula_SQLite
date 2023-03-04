package com.example.aulasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DisplayMessageActivity extends AppCompatActivity {

    Database db;
    TextView textView;
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    Integer idSelecionado;
    List<String> profiles;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        db = new Database(this);

        Intent intent = getIntent();

        String firstName = intent.getStringExtra("EXTRA_FIRSTNAME");
        String lastName = intent.getStringExtra("EXTRA_LASTNAME");
        String email = intent.getStringExtra("EXTRA_EMAIL");
        String phone = intent.getStringExtra("EXTRA_PHONE");
        db.addProfile(firstName, lastName, email, phone);


        String value = db.getLastProfile();

        textView = findViewById(R.id.textView);
        textView.setText(value);

        listarDados();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                idSelecionado = db.arrayIds.get(position);

                telaUpdateDelete();

                return true;
            }
        });

    }

    //-----Este método renova a Activity depois de uma deleção ou atualização-----
    @Override
    protected void onResume(){
        super.onResume();
        listarDados();
    }

    public void listarDados(){
        listView = findViewById(R.id.list_item);

        profiles = db.getAllProfiles();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, profiles);

        listView.setAdapter(adapter);

    }

    public void telaUpdateDelete() {

        Intent intent = new Intent(this, UpdateDeleteActivity.class);
        intent.putExtra("id", idSelecionado);
        startActivity(intent);

    }

}
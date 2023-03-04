package com.example.aulasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UpdateDeleteActivity extends AppCompatActivity {

    public Integer idSelecionado;
    public SQLiteDatabase sqLiteDatabase;

    public EditText et_firstname, et_lastname, et_email, et_phone;

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        // a classe que controla o banco de dados deve ser instanciada dentro do onCreate senão os comandos sql não funcionam

        et_firstname = findViewById(R.id.editText1);
        et_lastname = findViewById(R.id.editText2);
        et_email = findViewById(R.id.editText3);
        et_phone = findViewById(R.id.editText4);

        Intent intent = getIntent();
        idSelecionado = intent.getIntExtra("id", 0);

        db = new Database(this);
        sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT first_name, last_name, email, phone FROM contacts WHERE contact_id = "
                + idSelecionado.toString(), null);

        cursor.moveToFirst();

        et_firstname.setText(cursor.getString(0));
        et_lastname.setText(cursor.getString(1));
        et_email.setText(cursor.getString(2));
        et_phone.setText(cursor.getString(3));


    }

    public void atualizar(View view){
        String id = idSelecionado.toString();
        try {
            db = new Database(this);
            sqLiteDatabase = db.getWritableDatabase();

            String nome = et_firstname.getText().toString();
            String sobrenome = et_lastname.getText().toString();
            String email = et_email.getText().toString();
            String phone = et_phone.getText().toString();

            ContentValues values = new ContentValues();
            values.put("first_name", nome);
            values.put("last_name", sobrenome);
            values.put("email", email);
            values.put("phone", phone);

            sqLiteDatabase.update("contacts", values, "contact_id = ?", new String[]{String.valueOf(id)} );
            db.close();

        }catch (Exception e){
            e.printStackTrace();

        }

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        startActivity(intent);

    }

    public void excluir(View view){
        String id = idSelecionado.toString();
        db = new Database(this);
        sqLiteDatabase = db.getWritableDatabase();
        sqLiteDatabase.delete("contacts", "contact_id = ? ", new String[]{String.valueOf(id)} );
        db.close();

        finish();
    }

}
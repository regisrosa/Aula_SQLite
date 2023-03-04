package com.example.aulasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String EXTRA_FIRSTNAME;
    public static String EXTRA_LASTNAME;
    public static String EXTRA_EMAIL;
    public static String EXTRA_PHONE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void sendMessage(View view){

        Intent intent = new Intent(this, DisplayMessageActivity.class);

        EditText editText1 = findViewById(R.id.editText1);
        String firstName = editText1.getText().toString();
        intent.putExtra("EXTRA_FIRSTNAME", firstName);

        EditText editText2 = findViewById(R.id.editText2);
        String lastName = editText2.getText().toString();
        intent.putExtra("EXTRA_LASTNAME", lastName);

        EditText editText3 = findViewById(R.id.editText3);
        String email = editText3.getText().toString();
        intent.putExtra("EXTRA_EMAIL", email);

        EditText editText4 = findViewById(R.id.editText4);
        String phone = editText4.getText().toString();
        intent.putExtra("EXTRA_PHONE", phone);

        if(firstName.isEmpty() || lastName.isEmpty()){
            Toast.makeText(this, "Os campos Nome e Sobrenome são de preenchimento obrigatório!", Toast.LENGTH_SHORT).show();
        }else {
            startActivity(intent);
        }

    }
}
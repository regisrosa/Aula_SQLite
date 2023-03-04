package com.example.aulasqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    public static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "databasesqlite";
    public static String TABLE_CONTACTS = "contacts";
    public static String KEY_ID = "contact_id";
    public static String FIRST_KEY_NAME = "first_name";
    public static String LAST_KEY_NAME = "last_name";
    public static String EMAIL = "email";
    public static String PHONE = "phone";

    public ArrayList<Integer> arrayIds;

    public Database(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FIRST_KEY_NAME + " VARCHAR, "
                + LAST_KEY_NAME + " VARCHAR, " + EMAIL + " VARCHAR, " + PHONE + " VARCHAR);";

        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //nao precisa implementar nada aqui
    }

    public void addProfile(String firstName, String lastName, String email, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIRST_KEY_NAME, firstName);
        values.put(LAST_KEY_NAME, lastName);
        values.put(EMAIL, email);
        values.put(PHONE, phone);

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    public String getLastProfile(){

        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS + " ORDER BY " + KEY_ID + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        return (String) cursor.getString(1);//aqui quer dizer que o cursor vai retornar a coluna 1 da tabela que é o firstName
    }

    public List<String> getAllProfiles(){
        arrayIds = new ArrayList<>();
        List<String> profilesList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                //este comando adiciona à lista desde a coluna 1 (firstName) até a coluna 4 (phone)
                profilesList.add(cursor.getString(1) + " | " + cursor.getString(2)
                        + " | " + cursor.getString(3) + " | " + cursor.getString(4));

                arrayIds.add(cursor.getInt(0));

            }while (cursor.moveToNext());
        }

        return profilesList;
    }

}

package com.example.besindegerleri;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.besindegerleri.Besin;
import java.util.ArrayList;
import java.util.List;
public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "besinData";

    // Country table name
    private static final String TABLE_BESIN = "Besin";
    // Country Table Columns names
    private static final String KEY_ID = "id";
    private static final String BESIN = "Besin";
    private static final String PROTEIN = "Protein";
    private static final String KALORI = "Kalori";
    private static final String YAG = "Yag";
    private static final String KARBON = "Karbon";

    public SQLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COUNTRY_TABLE = "CREATE TABLE " + TABLE_BESIN + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + BESIN + " TEXT," +PROTEIN + " DOUBLE,"
                + KALORI + " DOUBLE," + YAG + " DOUBLE," + KARBON + " DOUBLE" +")";



        db.execSQL(CREATE_COUNTRY_TABLE);



    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BESIN);


        // Create tables again
        onCreate(db);
    }
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new country
    void addBesin(Besin besin) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BESIN, besin.getBesin());
        values.put(PROTEIN, besin.getProtein());
        values.put(KALORI, besin.getKalori());
        values.put(YAG, besin.getYag());
        values.put(KARBON, besin.getKarbon());

        // Inserting Row
        db.insert(TABLE_BESIN, null, values);
        db.close(); // Closing database connection
    }



    // Getting single country
    Besin getBesin(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_BESIN, new String[] { KEY_ID,
                        BESIN,KALORI, PROTEIN,YAG,KARBON }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Besin besin = new Besin(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getDouble(2),cursor.getDouble(3),cursor.getDouble(4),cursor.getDouble(5));
        // return country
        return besin;
    }

    // Getting All Countries
    public List getAllBesin() {
        List besinList = new ArrayList();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BESIN;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Besin besin = new Besin();
                besin.setId(Integer.parseInt(cursor.getString(0)));
                besin.setBesin(cursor.getString(1));
                besin.setProtein(cursor.getDouble(2));
                besin.setKalori(cursor.getDouble(3));
                besin.setYag(cursor.getDouble(4));
                besin.setKarbon(cursor.getDouble(5));
                // Adding country to list
                besinList.add(besin);
            } while (cursor.moveToNext());
        }

        // return country list
        return besinList;
    }


    // Updating single country
    public int updateBesin(Besin besin) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BESIN, besin.getBesin());
        values.put(PROTEIN, besin.getProtein());
        values.put(KALORI, besin.getKalori());
        values.put(YAG, besin.getYag());
        values.put(KARBON, besin.getKarbon());

        // updating row
        return db.update(TABLE_BESIN, values, KEY_ID + " = ?",
                new String[] { String.valueOf(besin.getId()) });
    }




    // Deleting single country
    public void deleteBesin(Besin besin) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BESIN, KEY_ID + " = ?",
                new String[] { String.valueOf(besin.getId()) });
        db.close();
    }

    // Deleting all countries
    public void deleteAllBesin() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BESIN,null,null);
        db.close();
    }

    // Getting countries Count
    public int getCountriesBesin() {
        String countQuery = "SELECT  * FROM " + TABLE_BESIN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }




}
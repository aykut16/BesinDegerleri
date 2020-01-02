package com.example.besindegerleri;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "besinData2";

    // Country table name
    private static final String TABLE_TOPLAM = "Toplam";
    private static final String TABLE_BESIN = "Besin";
    // Country Table Columns names
    private static final String KEY_ID = "id";
    private static final String BESIN = "Besin";
    private static final String PROTEIN = "Protein";
    private static final String KALORI = "Kalori";
    private static final String YAG = "Yag";
    private static final String KARBON = "Karbon";


    private static final String KEY_ID1 = "id1";
    private static final String BESIN1 = "Besin1";
    private static final String PROTEIN1 = "Protein1";
    private static final String KALORI1 = "Kalori1";
    private static final String YAG1 = "Yag1";
    private static final String KARBON1 = "Karbon1";





    public SQLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    private static final String CREATE_COUNTRY_TABLE = "CREATE TABLE " + TABLE_BESIN + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + BESIN + " TEXT," + PROTEIN + " DOUBLE,"
            + KALORI + " DOUBLE," + YAG + " DOUBLE," + KARBON + " DOUBLE" + ')';
    private static final     String CREATE_TOPLAM_TABLE = "CREATE TABLE " + TABLE_TOPLAM + "("
            + KEY_ID1 + " INTEGER PRIMARY KEY," + BESIN1 + " TEXT," + PROTEIN1 + " DOUBLE,"
            + KALORI1 + " DOUBLE," + YAG1 + " DOUBLE," + KARBON1 + " DOUBLE" + ')';

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_COUNTRY_TABLE);
        db.execSQL(CREATE_TOPLAM_TABLE);


    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOPLAM);
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

    //Table Toplam


    // Getting single country
    void addToplam(Toplam toplam) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(BESIN1, toplam.getBesin1());
        values.put(PROTEIN1, toplam.getProtein1());
        values.put(KALORI1, toplam.getKalori1());
        values.put(YAG1, toplam.getYag1());
        values.put(KARBON1, toplam.getKarbon1());

        // Inserting Row
        db.insert(TABLE_TOPLAM, null, values);
        db.close(); // Closing database connection
    }
    Toplam getToplam(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TOPLAM, new String[] { KEY_ID1,
                        BESIN1,KALORI1, PROTEIN1,YAG1,KARBON1 }, KEY_ID1 + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Toplam toplam = new Toplam(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getDouble(2),cursor.getDouble(3),cursor.getDouble(4),cursor.getDouble(5));
        // return country
        return toplam;
    }

    // Getting All Countries
    public List getAllToplam() {
        List toplamList = new ArrayList();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TOPLAM;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Toplam toplam = new Toplam();
                toplam.setId1(Integer.parseInt(cursor.getString(0)));
                toplam.setBesin1(cursor.getString(1));
                toplam.setProtein1(cursor.getDouble(2));
                toplam.setKalori1(cursor.getDouble(3));
                toplam.setYag1(cursor.getDouble(4));
                toplam.setKarbon1(cursor.getDouble(5));
                // Adding country to list
                toplamList.add(toplam);
            } while (cursor.moveToNext());
        }

        // return country list
        return toplamList;
    }


    // Updating single country
    public int updateToplam(Toplam toplam) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BESIN1, toplam.getBesin1());
        values.put(PROTEIN1, toplam.getProtein1());
        values.put(KALORI1, toplam.getKalori1());
        values.put(YAG1, toplam.getYag1());
        values.put(KARBON1, toplam.getKarbon1());

        // updating row
        return db.update(TABLE_TOPLAM, values, KEY_ID1 + " = ?",
                new String[] { String.valueOf(toplam.getId1()) });
    }




    // Deleting single country
    public void deleteToplam(Toplam toplam) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TOPLAM, KEY_ID1 + " = ?",
                new String[] { String.valueOf(toplam.getId1()) });
        db.close();
    }

    // Deleting all countries
    public void deleteAllToplam() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TOPLAM,null,null);
        db.close();
    }

    // Getting countries Count
    public int getCountriesToplam() {
        String countQuery = "SELECT  * FROM " + TABLE_TOPLAM;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


public double toplamProtein() {

    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = db.rawQuery(
            "SELECT SUM(Protein1) FROM Toplam", null);
    if(cursor.moveToFirst()) {
        return cursor.getInt(0);
    }
    return 0;
}

    public double toplamKalori() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT SUM(Kalori1) FROM Toplam", null);
        if(cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        return 0;
    }
    public double toplamYag() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT SUM(Yag1) FROM Toplam", null);
        if(cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        return 0;
    }


    public double toplamKarbon() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT SUM(Karbon1) FROM Toplam", null);
        if(cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        return 0;
    }


}
package com.ingeniandolo.monitoreointegral.databaseHelpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String COLUMN_ID = "_id";
    public static final String TABLE_FINCAS = "fincas";
    public static final String COLUMN_FINCA_ID = "id";
    public static final String COLUMN_FINCA_NOMBRE = "nombre";
    public static final String TABLE_BLOQUES = "bloques";
    public static final String COLUMN_BLOQUE_ID = "id";
    public static final String COLUMN_BLOQUE_NOMBRE = "nombre";

    private static final String DATABASE_NAME = "monitoreo_integral.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE_FINCAS = "create table "
            + TABLE_FINCAS + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_FINCA_ID
            + " text not null, " + COLUMN_FINCA_NOMBRE
            + " text not null); ";
    private static final String DATABASE_CREATE_BLOQUES =  "create table "
            + TABLE_BLOQUES + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_BLOQUE_ID
            + " text not null, " + COLUMN_BLOQUE_NOMBRE
            + " text not null); ";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_FINCAS);
        database.execSQL(DATABASE_CREATE_BLOQUES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FINCAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BLOQUES);
        onCreate(db);
    }

}
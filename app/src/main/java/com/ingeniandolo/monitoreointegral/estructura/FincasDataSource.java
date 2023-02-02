package com.ingeniandolo.monitoreointegral.estructura;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.ingeniandolo.monitoreointegral.databaseHelpers.MySQLiteHelper;

public class FincasDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_FINCA_ID,
            MySQLiteHelper.COLUMN_FINCA_NOMBRE };

    public FincasDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Finca createFinca(String id, String nombre) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_FINCA_ID, id);
        values.put(MySQLiteHelper.COLUMN_FINCA_NOMBRE, nombre);
        long insertId = database.insert(MySQLiteHelper.TABLE_FINCAS, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_FINCAS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Finca newFinca = cursorToFinca(cursor);
        cursor.close();
        return newFinca;
    }

    public void deleteFinca(Finca finca) {
        long id = finca.get_Id();
        System.out.println("Finca deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_FINCAS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Finca> getAllFincas() {
        List<Finca> fincas = new ArrayList<Finca>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_FINCAS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Finca finca = cursorToFinca(cursor);
            fincas.add(finca);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return fincas;
    }

    private Finca cursorToFinca(Cursor cursor) {
        Finca finca = new Finca();
        finca.set_Id(cursor.getLong(0));
        finca.setId(cursor.getString(1));
        finca.setNombre(cursor.getString(2));
        return finca;
    }
}
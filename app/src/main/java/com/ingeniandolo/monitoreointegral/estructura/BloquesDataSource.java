package com.ingeniandolo.monitoreointegral.estructura;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.ingeniandolo.monitoreointegral.databaseHelpers.MySQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class BloquesDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_BLOQUE_ID,
            MySQLiteHelper.COLUMN_BLOQUE_NOMBRE };

    public BloquesDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Bloque createBloque(String id, String nombre) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_BLOQUE_ID, id);
        values.put(MySQLiteHelper.COLUMN_BLOQUE_NOMBRE, nombre);
        long insertId = database.insert(MySQLiteHelper.TABLE_BLOQUES, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_BLOQUES,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Bloque newBloque = cursorToBloque(cursor);
        cursor.close();
        return newBloque;
    }

    public void deleteBloque(Bloque bloque) {
        long id = bloque.get_Id();
        System.out.println("Bloque deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_BLOQUES, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Bloque> getAllBloques() {
        List<Bloque> bloques = new ArrayList<Bloque>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_BLOQUES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Bloque bloque = cursorToBloque(cursor);
            bloques.add(bloque);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return bloques;
    }

    private Bloque cursorToBloque(Cursor cursor) {
        Bloque bloque = new Bloque();
        bloque.set_Id(cursor.getLong(0));
        bloque.setId(cursor.getString(1));
        bloque.setNombre(cursor.getString(2));
        return bloque;
    }
}
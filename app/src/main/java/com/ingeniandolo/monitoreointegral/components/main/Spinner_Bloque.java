package com.ingeniandolo.monitoreointegral.components.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ingeniandolo.monitoreointegral.estructura.Bloque;
import com.ingeniandolo.monitoreointegral.estructura.BloquesDataSource;

import java.util.List;

public class Spinner_Bloque extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Contexto
    private Context context_aux;
    //Widgets
    private Spinner spinner_aux;

    public Spinner_Bloque(Spinner view, Context context) {

        spinner_aux = view;
        context_aux = context;

        //Asociamos el listener del evento
        spinner_aux.setOnItemSelectedListener(this);

    }

    public void onClick_button_add_bloque(View view) {

        //Configuramos el dialogo de ingreso de datos
        AlertDialog.Builder builder_bloque = new AlertDialog.Builder(this);
        builder_bloque.setTitle("Nuevo Bloque");
        // Set up the input
        final EditText input = new EditText(getApplicationContext());
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder_bloque.setView(input);
        // Set up the buttons
        builder_bloque.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Ocultamos el dialogo
                dialog.dismiss();
                //Capturamos las entradas
                String text = input.getText().toString();
                //Insertamos el registro en la base de datos
                if (text.trim().length() > 0) {
                    BloquesDataSource db = new BloquesDataSource(getApplicationContext());
                    db.open();
                    db.createBloque("Nuevo", text);
                    //Cargamos el spinner con los datos
                    //loadSpinnerBloques(context_aux);
                } else {
                    Toast.makeText(getApplicationContext(), "Nombre de bloque incorrecto",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder_bloque.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Cancelamos el dialogo
                dialog.cancel();
            }
        });
        //Mostramos el diálogo
        builder_bloque.show();

    }

    //region "Recuperar los datos de la base de datos SQLite local"
    public void loadSpinner() {

        //Definimos el datasource
        BloquesDataSource db = new BloquesDataSource(context_aux);
        //Abrimos el datasource
        db.open();
        //Recuperamos los datos
        List<Bloque> bloques = db.getAllBloques();
        //Creamos el adapter para el spinner
        ArrayAdapter<Bloque> dataAdapter = new ArrayAdapter<Bloque>(context_aux,android.R.layout.simple_spinner_item, bloques);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Enlazamos el data adapter al spinner
        spinner_aux.setAdapter(dataAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Bloque seleccionado: " + label,
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


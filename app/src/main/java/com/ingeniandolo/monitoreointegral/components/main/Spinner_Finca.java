package com.ingeniandolo.monitoreointegral.components.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ingeniandolo.monitoreointegral.R;
import com.ingeniandolo.monitoreointegral.estructura.Finca;
import com.ingeniandolo.monitoreointegral.estructura.FincasDataSource;

import java.util.List;

public class Spinner_Finca extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Contexto
    private Context context_aux;
    //Widgets
    private Spinner spinner_aux;

    public Spinner_Finca(Spinner view, Context context) {

        spinner_aux = view;
        context_aux = context;

        //Asociamos el listener del evento
        spinner_aux.setOnItemSelectedListener(this);

    }

    //region "Recuperar los datos de la base de datos SQLite local"
    public void loadSpinner() {

        //Definimos el datasource
        FincasDataSource db = new FincasDataSource(context_aux);
        //Abrimos el datasource
        db.open();
        //Recuperamos los datos
        List<Finca> fincas = db.getAllFincas();
        //Creamos el adapter para el spinner
        ArrayAdapter<Finca> dataAdapter = new ArrayAdapter<Finca>(context_aux,android.R.layout.simple_spinner_item, fincas);
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
        Toast.makeText(parent.getContext(), "Finca seleccionada: " + label,
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


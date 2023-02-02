package com.ingeniandolo.monitoreointegral;

import android.app.AlertDialog;
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

import com.ingeniandolo.monitoreointegral.components.main.Button_Add_Finca;
import com.ingeniandolo.monitoreointegral.components.main.Spinner_Bloque;
import com.ingeniandolo.monitoreointegral.components.main.Spinner_Finca;
import com.ingeniandolo.monitoreointegral.estructura.Bloque;
import com.ingeniandolo.monitoreointegral.estructura.BloquesDataSource;
import com.ingeniandolo.monitoreointegral.estructura.FincasDataSource;

import java.util.List;

//Para el manejo de setLisAdapter la clase debe extenderse de ListActivity
public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instanciamos las clases de los componentes
        Spinner_Finca spinner_finca = new Spinner_Finca(findViewById(R.id.spinner_finca),getApplicationContext());
        spinner_finca.loadSpinner();

        Button_Add_Finca button_add_finca = new Button_Add_Finca();

        Spinner_Bloque spinner_bloque = new Spinner_Bloque(findViewById(R.id.spinner_bloque),getApplicationContext());
        spinner_bloque.loadSpinner();

    }

}
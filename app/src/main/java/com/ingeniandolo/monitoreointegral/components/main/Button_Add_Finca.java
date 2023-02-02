package com.ingeniandolo.monitoreointegral.components.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ingeniandolo.monitoreointegral.R;
import com.ingeniandolo.monitoreointegral.estructura.FincasDataSource;

public class Button_Add_Finca extends AppCompatActivity {

    public Button_Add_Finca() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick_button_add_finca(View view) {

        //Configuramos el dialogo de ingreso de datos
        AlertDialog.Builder builder_finca = new AlertDialog.Builder(this);
        builder_finca.setTitle("Nueva Finca");
        // Set up the input
        final EditText input = new EditText(getApplicationContext());
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder_finca.setView(input);
        // Set up the buttons
        builder_finca.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Ocultamos el dialogo
                dialog.dismiss();
                //Capturamos las entradas
                String text = input.getText().toString();
                //Insertamos el registro en la base de datos
                if (text.trim().length() > 0) {
                    FincasDataSource db = new FincasDataSource(getApplicationContext());
                    db.open();
                    db.createFinca("Nuevo", text);
                    //Cargamos el spinner con los datos
                    //loadSpinnerFincas(context_aux);
                } else {
                    Toast.makeText(getApplicationContext(), "Nombre de finca incorrecto",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder_finca.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Cancelamos el dialogo
                dialog.cancel();
            }
        });
        //Mostramos el diálogo
        builder_finca.show();

    }

}


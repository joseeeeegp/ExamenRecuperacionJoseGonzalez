package com.example.b;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.b.utilidades.Utilidades;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View view) {
        Intent miIntent = null;
        switch (view.getId()) {
            case R.id.idBotonRegistrar:
                miIntent = new Intent(MainActivity.this, RegistrarCosa.class);
                break;
            case R.id.idConsultaindividual:
                miIntent = new Intent(MainActivity.this, ConsultarCosaIndividual.class);
                break;
            case R.id.idBotonRecycler:
               miIntent = new Intent(MainActivity.this, ConsultaCosaRecycler.class);
                break;
            case R.id.idBotonUnoMas:
                registrar();
                break;
        }
        if (miIntent != null) {
            startActivity(miIntent);
        }
    }

    private void registrar() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Añadir una tarea");

        // Set up the input
        final EditText input = new EditText(this);
        final EditText input2=new EditText(this);
        final EditText input3=new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        // Add a TextView here for the "Title" label, as noted in the comments
        input.setHint("Cosa ");
        layout.addView(input); // Notice this is an add method

        // Add a TextView here for the "Title" label, as noted in the comments
        input2.setHint("Id");
        layout.addView(input2); // Notice this is an add method

        // Add a TextView here for the "Title" label, as noted in the comments
        input2.setHint("Cantidad");
        layout.addView(input3); // Notice this is an add method

        builder.setView(layout);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_cosa", null, 1);

                SQLiteDatabase db = conn.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(Utilidades.CAMPO_ID,input2.getText().toString());
                values.put(Utilidades.CAMPO_COSA, input.getText().toString());
                values.put(Utilidades.CAMPO_CANTIDAD,input3.getText().toString());


                Long idResultante = db.insert(Utilidades.TABLA_COSAS, Utilidades.CAMPO_ID, values);

                Toast.makeText(getApplicationContext(), "Id Registro: " + idResultante, Toast.LENGTH_SHORT).show();
                db.close();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }
}

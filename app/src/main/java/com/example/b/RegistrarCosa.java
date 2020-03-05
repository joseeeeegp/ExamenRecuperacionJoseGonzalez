package com.example.b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.b.utilidades.Utilidades;

public class RegistrarCosa extends AppCompatActivity {

    EditText campoId, campoCosa, campoCantidad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cosa);

        campoId = (EditText) findViewById(R.id.idDelid);
        campoCosa=(EditText) findViewById(R.id.idcosa);
        campoCantidad=(EditText) findViewById(R.id.idcantidad);
    }

    public void onClick(View view){
        registrarObjeto();
    }

    private void registrarObjeto() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_cosa", null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_ID, campoId.getText().toString());
        values.put(Utilidades.CAMPO_COSA, campoCosa.getText().toString());
        values.put(Utilidades.CAMPO_CANTIDAD,campoCantidad.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_COSAS, Utilidades.CAMPO_ID, values);

        //Toast.makeText(getApplicationContext(), "Id Registro: " + idResultante, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),"Se ha realizado el registro",Toast.LENGTH_SHORT).show();
        db.close();


    }

}

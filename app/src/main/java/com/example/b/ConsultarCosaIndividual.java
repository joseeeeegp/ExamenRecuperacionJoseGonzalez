package com.example.b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.b.utilidades.Utilidades;

public class ConsultarCosaIndividual extends AppCompatActivity {
EditText campoId, campoCosa, campoCantidad;
    ConexionSQLiteHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_cosa_individual);
        /**Establezco la conexión**/
        conn = new ConexionSQLiteHelper(this, "bd_cosa", null, 1);

        campoId = (EditText) findViewById(R.id.campoid);
        campoCosa=(EditText) findViewById(R.id.campoCosa);
        campoCantidad=(EditText)findViewById(R.id.campoCantidad);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.botonBuscar:
                consultar();
                break;
            case R.id.botonActualizar:
                actualizar();
                break;
            case R.id.botonEliminar:
                eliminar();
                break;
        }
    }

    private void consultar() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {campoId.getText().toString()};
        String[] campos = {Utilidades.CAMPO_COSA, Utilidades.CAMPO_CANTIDAD};

        try {
            /** luego voy metiendo todas las cosas en el curso**/
            Cursor cursor = db.query(Utilidades.TABLA_COSAS, campos, Utilidades.CAMPO_ID + "=?", parametros, null, null, null);
            cursor.moveToFirst();


            /**cada campo coge sus datos de la bd**/
            campoCosa.setText(cursor.getString(0));
            campoCantidad.setText(cursor.getString(1));


            cursor.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "El documento no existe", Toast.LENGTH_LONG).show();
            limpiar();

        }

    }

    private void limpiar() {
        campoCosa.setText("");
        campoCantidad.setText("");
    }


    private void actualizar() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {campoId.getText().toString()};
        ContentValues values = new ContentValues();

        values.put(Utilidades.CAMPO_COSA, campoCosa.getText().toString());
        values.put(Utilidades.CAMPO_CANTIDAD,campoCantidad.getText().toString());


        db.update(Utilidades.TABLA_COSAS, values, Utilidades.CAMPO_ID + "=?", parametros);

        Toast.makeText(getApplicationContext(), "Se ha actualizado "+ values, Toast.LENGTH_LONG).show();
        db.close();
    }

    private void eliminar() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {campoId.getText().toString()};

        db.delete(Utilidades.TABLA_COSAS, Utilidades.CAMPO_ID + "=?", parametros);
        Toast.makeText(getApplicationContext(), "Se ha eliminado", Toast.LENGTH_LONG).show();

        //para borrar el campoId
        campoId.setText("");
        // para borrar los otros campos
        limpiar();
        db.close();
    }


}

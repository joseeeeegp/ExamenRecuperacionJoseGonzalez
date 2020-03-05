package com.example.b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b.entidades.Cosa;
import com.example.b.utilidades.Utilidades;

import javax.xml.transform.Templates;

public class DetalleCosa extends AppCompatActivity {

    TextView campoId,campoCosa,campoCantidad;
    ConexionSQLiteHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cosa);

        /**Establezco la conexión**/
        conn = new ConexionSQLiteHelper(this, "bd_cosa", null, 1);

        campoId=(TextView) findViewById(R.id.campoid3);
        campoCosa=(TextView) findViewById(R.id.campoCosa3);
        campoCantidad=(TextView)findViewById(R.id.campoCantidad3);

        Bundle objetoEnviado = getIntent().getExtras();
        Cosa cosa= null;

        if(objetoEnviado!=null){
            cosa=(Cosa) objetoEnviado.getSerializable("cosa");

            campoId.setText(cosa.getId().toString());
            campoCosa.setText(cosa.getCosa());
            campoCantidad.setText(cosa.getCantidad());
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.idActualizarDetalle:
                actualizarbd();
                break;
            case R.id.idEliminardetalle:
                eliminarbd();
                break;
        }
    }

    private void eliminarbd() {
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

    private void limpiar() {
        campoCosa.setText("");
        campoCantidad.setText("");
    }

    private void actualizarbd() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {campoId.getText().toString()};
        ContentValues values = new ContentValues();

        values.put(Utilidades.CAMPO_COSA, campoCosa.getText().toString());
        values.put(Utilidades.CAMPO_CANTIDAD,campoCantidad.getText().toString());


        db.update(Utilidades.TABLA_COSAS, values, Utilidades.CAMPO_ID + "=?", parametros);

        Toast.makeText(getApplicationContext(), "Se ha actualizado "+ values, Toast.LENGTH_LONG).show();
        db.close();
    }
}

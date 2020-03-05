package com.example.b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.b.adaptadores.ListaCosaAdapter;
import com.example.b.entidades.Cosa;
import com.example.b.utilidades.Utilidades;

import java.util.ArrayList;
import java.util.Collections;

public class ConsultaCosaRecycler extends AppCompatActivity {

    ArrayList<Cosa> listaCosa;
    RecyclerView recyclerViewCosa;

    ConexionSQLiteHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_cosa_recycler);

        conn = new ConexionSQLiteHelper(this, "bd_cosa", null, 1);

        listaCosa = new ArrayList<>();

        recyclerViewCosa = (RecyclerView) findViewById(R.id.recyclerCosa);
        recyclerViewCosa.setLayoutManager(new LinearLayoutManager(this));

        consultarListaCosa();

        final ListaCosaAdapter adapter = new ListaCosaAdapter(listaCosa);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Seleccion: " + listaCosa.get(recyclerViewCosa.getChildAdapterPosition(v)).getId(), Toast.LENGTH_SHORT).show();

                Cosa cosa = listaCosa.get(recyclerViewCosa.getChildAdapterPosition(v));
                Intent intent = new Intent(ConsultaCosaRecycler.this, DetalleCosa.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("cosa", cosa);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });



        recyclerViewCosa.setAdapter(adapter);

        borrarDeslizar(adapter);

    }


    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.butonmodify:
            modificar();
                break;
            case R.id.butondelete:
            eliminar();
                break;

        }
    }

    private void modificar() {
        Toast.makeText(getApplicationContext(), "Clica encima del item", Toast.LENGTH_LONG).show();
    }

    private void eliminar() {
        Toast.makeText(getApplicationContext(), "Deslizas y me elimino o clicas en el item", Toast.LENGTH_LONG).show();
    }


    private void consultarListaCosa() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Cosa cosa = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_COSAS, null);

        while (cursor.moveToNext()) {
            cosa = new Cosa();
            cosa.setId(cursor.getInt(0));
            cosa.setCosa(cursor.getString(1));
            cosa.setCantidad(cursor.getString(2));

            listaCosa.add(cosa);
        }
    }




    private void borrarDeslizar(final ListaCosaAdapter adapter) {
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                ItemTouchHelper.DOWN | ItemTouchHelper.UP, ItemTouchHelper.LEFT |
                ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                int from = viewHolder.getAdapterPosition();
                int to = viewHolder1.getAdapterPosition();
                Collections.swap(listaCosa, from, to);
                adapter.notifyItemMoved(from, to);
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                SQLiteDatabase db = conn.getWritableDatabase();
                Integer id = listaCosa.get(viewHolder.getAdapterPosition()).getId();
                String[] parametros = {String.valueOf(id)};

                db.delete(Utilidades.TABLA_COSAS, Utilidades.CAMPO_ID + "=?", parametros);
                Toast.makeText(getApplicationContext(), "Se ha eliminado", Toast.LENGTH_LONG).show();

                listaCosa.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        helper.attachToRecyclerView(recyclerViewCosa);
    }
}

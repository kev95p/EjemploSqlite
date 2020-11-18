package com.kev95p.refuerzosqlite.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kev95p.refuerzosqlite.R;
import com.kev95p.refuerzosqlite.adapters.PersonaAdapter;
import com.kev95p.refuerzosqlite.db.DatabaseHelper;
import com.kev95p.refuerzosqlite.db.models.Persona;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lstPersonas;
    List<Persona> personas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstPersonas = findViewById(R.id.lstPersonas);
        cargarListView();

        lstPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                abrirFormPersonaModificar(i);

            }
        });

        lstPersonas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Borrar persona");
                builder.setMessage("Desea eliminar a este persona?");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int in) {
                        borrarPersona(i);
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int in) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });
    }

    private void borrarPersona(int i) {
        DatabaseHelper dbh = DatabaseHelper.getInstancia(MainActivity.this);
        Persona p = personas.get(i);
        dbh.deletePersona(p.getId());
        cargarListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarListView();
    }

    void cargarListView(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseHelper dbh = DatabaseHelper.getInstancia(MainActivity.this);
                final List<Persona> personas = dbh.getAllPersonas();
                MainActivity.this.personas = personas;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        PersonaAdapter personaAdapter = new PersonaAdapter(MainActivity.this,personas);
                        lstPersonas.setAdapter(personaAdapter);
                    }
                });

            }
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.abrirFormUsuario:
                abrirFormUsuario();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void abrirFormUsuario(){
        Intent i = new Intent(this,AgregarUsuarioActivity.class);
        startActivity(i);
    }

    void abrirFormPersonaModificar(int pos){
        Intent i = new Intent(this,AgregarUsuarioActivity.class);
        int id  = personas.get(pos).getId();
        i.putExtra("id",id);
        startActivity(i);
    }
}
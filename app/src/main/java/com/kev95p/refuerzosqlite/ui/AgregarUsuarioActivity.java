package com.kev95p.refuerzosqlite.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.kev95p.refuerzosqlite.R;
import com.kev95p.refuerzosqlite.adapters.PersonaAdapter;
import com.kev95p.refuerzosqlite.db.DatabaseHelper;
import com.kev95p.refuerzosqlite.db.models.Persona;

import java.util.List;

public class AgregarUsuarioActivity extends AppCompatActivity {

    TextView txtNombre;
    TextView txtApellido;

    int idPersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);

        setDataUsuario();

        txtNombre = findViewById(R.id.txtFormNombre);
        txtApellido = findViewById(R.id.txtFormApellido);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_agregar_usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.optGuardarUsuario:
                guardarUsuario();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void setDataUsuario(){
        final int id =  getIntent().getIntExtra("id",0);
        this.idPersona = id;
        if(id != 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    DatabaseHelper dbh = DatabaseHelper.getInstancia(AgregarUsuarioActivity.this);
                    final Persona persona = dbh.getPersona(id);
                    if (persona != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txtNombre.setText(persona.getNombre());
                                txtApellido.setText(persona.getApellido());
                            }
                        });
                    }
                }
            }).start();
        }
    }

    void guardarUsuario(){
        DatabaseHelper db = DatabaseHelper.getInstancia(this);
        Persona p = new Persona();
        if(idPersona != 0 ){
            p.setId(idPersona);
        }
        p.setNombre(txtNombre.getText().toString());
        p.setApellido(txtApellido.getText().toString());

        if(idPersona != 0){
            db.updatePersona(p);
        }
        else{
            db.insertPersona(p);
        }

        finish();
    }
}
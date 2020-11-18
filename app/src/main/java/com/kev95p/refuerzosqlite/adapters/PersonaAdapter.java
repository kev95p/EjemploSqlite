package com.kev95p.refuerzosqlite.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kev95p.refuerzosqlite.R;
import com.kev95p.refuerzosqlite.db.models.Persona;

import java.util.List;

public class PersonaAdapter extends ArrayAdapter<Persona> {
    List<Persona> personas;
    Context context;

    public PersonaAdapter(@NonNull Context context, @NonNull List<Persona> objects) {
        super(context, 0, objects);
        this.personas = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.persona_item, parent, false);
        }

        Persona persona = getItem(position);

        TextView nombre = convertView.findViewById(R.id.txtNombre);
        TextView apellido  = convertView.findViewById(R.id.txtApellido);
        nombre.setText(persona.getNombre());
        apellido.setText(persona.getApellido());

        return convertView;

    }
}
